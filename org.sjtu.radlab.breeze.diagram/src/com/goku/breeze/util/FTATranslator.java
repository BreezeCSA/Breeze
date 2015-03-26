package com.goku.breeze.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.goku.breeze.common.SafetyAttribute;

/*
 * transfer fta xml file to file that could be read
 * in Open FTA
 */
public class FTATranslator {
	class BasicEvent {
		public String desc;
		public String id;
		public double level;

		public BasicEvent(String id, String desc, String level) {
			this.id = id;
			this.desc = desc == null ? "" : desc;
			this.level = level != null ? Double.parseDouble(level) : 0.5;
		}
	}

	public static final String ATTR_DESC = "name";
	public static final String ATTR_ID = "id";
	public static final String ATTR_PROBABILITY = "probability";
	public static final String ATTR_TYPE = "OperationType";
	private static final String EVENT_FTA_BASIC = "B";
	private static final String EVENT_FTA_MIDDLE = "M";
	private static Map<String, String> gateMap = new HashMap<String, String>();
	public static final String NODE_ARCH_FAILURE = "ArchFailure";
	public static final String NODE_FAILURE_ANALYSIS = "FailureAnalysis";
	public static final String NODE_NODE_FAILURE_EVENT = "NodeFailureEvent";
	public static final String NODE_NODE_FAILURE_EVENT_LIST = "NodeFailureEventList";
	private static final int truncateLength = 13;
	static {
		gateMap.put(SafetyAttribute.EVENT_GATE_AND, "A");
		gateMap.put(SafetyAttribute.EVENT_GATE_OR, "O");
		gateMap.put(SafetyAttribute.EVENT_GATE_PAND, "P");
		gateMap.put(SafetyAttribute.EVENT_GATE_XOR, "X");
		gateMap.put(SafetyAttribute.EVENT_GATE_INI, "H");
	}

	public static void main(String[] args) throws IOException, DocumentException {
		String path = "tank.xml", out = "tank";
		File file = new File(path);
		FTATranslator gk = new FTATranslator(file, out, true);
		String fta = gk.getFTAContent(), ped = gk.getPEDContent();
		File ftaFile = new File(out + ".fta");
		File pedFile = new File(out + ".ped");
		try {
			FileOutputStream fos0 = new FileOutputStream(ftaFile);
			FileOutputStream fos1 = new FileOutputStream(pedFile);
			fos0.write(fta.getBytes());
			fos1.write(ped.getBytes());
			fos0.close();
			fos1.close();
			System.out.println("end\nwrite to" + ftaFile.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private final LinkedList<String> errorMsg = new LinkedList<String>();
	private Map<String, BasicEvent> eventSet;
	private String ftaContent = null;
	private String pedContent = null;
	private boolean valid = true;

	public FTATranslator(File inputFile, String outputName, boolean truncate) {
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			doc = reader.read(inputFile);
			this.translate(doc, outputName, truncate);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public FTATranslator(String inputContent, String outputName, boolean truncate) {
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			doc = reader.read(inputContent);
			this.translate(doc, outputName, truncate);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param rootElement
	 * @param truncate
	 * @return
	 * 
	 */
	private Map<String, BasicEvent> getBasicEventList(Element rootElement, boolean truncate) {
		Map<String, BasicEvent> lst = new HashMap<String, BasicEvent>();
		Element eventListElement = rootElement.element(FTATranslator.NODE_NODE_FAILURE_EVENT_LIST);
		List<Element> eles = eventListElement.elements(FTATranslator.NODE_NODE_FAILURE_EVENT);
		for (Element ele : eles) {
			String id = ele.attributeValue(FTATranslator.ATTR_ID);
			if (id == null) {
				this.errorMsg.addLast(ele.getUniquePath() + ": need " + ATTR_ID);
			}
			if (truncate && id.length() > FTATranslator.truncateLength) id = id.substring(0, truncateLength);
			lst.put(id,
					new BasicEvent(id, ele.attributeValue(FTATranslator.ATTR_DESC), ele
							.attributeValue(FTATranslator.ATTR_PROBABILITY)));
		}
		return lst;
	}

	public List<String> getErrorMessage() {
		return this.errorMsg;
	}

	public String getFTAContent() {
		return this.ftaContent;
	}

	public String getPEDContent() {
		return this.pedContent;
	}

	public boolean isValid() {
		return this.valid;
	}

	private void parseFT(Element ele, StringBuilder sb, boolean truncate) {
		String eleId = ele.attributeValue(FTATranslator.ATTR_ID);
		String type = ele.attributeValue(ATTR_TYPE);
		if (eleId == null) {
			this.errorMsg.addLast(ele.getUniquePath() + ": need " + ATTR_ID);
		}
		if (truncate && eleId.length() > FTATranslator.truncateLength) eleId = eleId.substring(0, truncateLength);

		if (type == null) {
			if (!this.eventSet.containsKey(eleId)) {
				this.errorMsg.add(ele.getUniquePath() + ": is not a basic event");
			}
			sb.append(FTATranslator.EVENT_FTA_BASIC).append(" ").append(eleId).append(" ").append("0\n");
		} else {
			String gate = gateMap.get(type);
			if (gate == null) {
				this.errorMsg.addLast(ele.getUniquePath() + "(" + type + "): invalid type of gate");
			}
			sb.append(FTATranslator.EVENT_FTA_MIDDLE).append(" ").append(eleId).append(" 1\n");
			String desc = ele.attributeValue(FTATranslator.ATTR_DESC);
			if (desc == null || desc.length() == 0)
				sb.append("0");
			else sb.append(desc.length()).append(" ").append(desc).append("\n");
			List<Element> children = ele.elements(FTATranslator.NODE_ARCH_FAILURE);
			sb.append(gate).append(" NULL ").append(children.size()).append("\n");
			for (Element child : children)
				this.parseFT(child, sb, truncate);
		}
	}

	private void printError(String err) {
		System.err.println(err);
		System.exit(0);
	}

	private void translate(Document doc, String outputName, boolean truncate) {
		Element rootElement = doc.getRootElement();
		if (rootElement == null || !rootElement.getName().equals(NODE_FAILURE_ANALYSIS)) {
			this.valid = false;
			return;
		}
		StringBuilder sb = new StringBuilder();

		sb.append(outputName).append(".ped\nS NULL 0\n0\n");
		this.eventSet = this.getBasicEventList(rootElement, truncate);

		Element top = rootElement.element(FTATranslator.NODE_ARCH_FAILURE);
		if (top == null) return;
		this.parseFT(top, sb, truncate);
		this.ftaContent = sb.toString();

		sb.delete(0, sb.length());
		DecimalFormat df = new DecimalFormat("0.000000E000");
		for (String eventId : this.eventSet.keySet()) {
			BasicEvent event = this.eventSet.get(eventId);
			sb.append(eventId).append(";;").append(FTATranslator.EVENT_FTA_BASIC).append('\0').append(";").append(event.desc)
					.append(";").append(df.format(event.level).replace('E', 'e')).append(";").append('\0').append(";\n");
		}
		this.pedContent = sb.toString();
	}
}
