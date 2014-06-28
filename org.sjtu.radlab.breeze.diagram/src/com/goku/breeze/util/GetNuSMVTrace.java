package com.goku.breeze.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.goku.breeze.compiler.model.BreezeArch;
import com.goku.breeze.compiler.model.BreezeEvent;
import com.goku.breeze.compiler.model.BreezeMode;
import com.goku.breeze.compiler.model.BreezeNode;
import com.goku.breeze.compiler.model.BreezeObject;
import com.goku.breeze.compiler.model.BreezePort;
import com.goku.breeze.model.TraceNode;
import com.goku.breeze.ui.console.ConsoleFactory;
import com.goku.breeze.ui.console.ConsoleUtil;

public class GetNuSMVTrace {

	private static void extractTypeInPort(Map<String, List<String>> portIn, Map<String, BreezeNode> compilerNodeMap,
			Map<String, TraceNode> tnMap) {
		for (String itemId : portIn.keySet()) {
			String moduleName = itemId.split(">")[0];
			String portId = itemId.split(">")[1];

			BreezeNode bn = compilerNodeMap.get(moduleName);
			if (bn == null) continue;

			TraceNode tn = tnMap.get(moduleName);
			if (tn == null) {
				tn = new TraceNode(moduleName, "", bn.getName());
				tnMap.put(moduleName, tn);
			}

			List<String> inVal = portIn.get(itemId);
			List<String> realInVal = new ArrayList<String>();
			BreezePort port = bn.getPortMap().get(portId);

			String eventValList = null;

			for (String eventVal : inVal) {
				if (!eventVal.equals("${None}")) {
					realInVal.add(eventVal);
				}
			}

			if (realInVal.size() > 0) {
				eventValList = realInVal.get(0);
				for (int i = 1; i < realInVal.size(); ++i)
					eventValList += "," + realInVal.get(i);
			} else eventValList = "${None}";

			Object name = port.getProperty(BreezeObject.ATTR_NAME);
			TraceNode child = new TraceNode(port.getId() + ":receive", eventValList, name == null ? null : name.toString()
					+ ":receive");
			tn.getChildren().add(child);
		}
	}

	public static List<TraceNode> getTraceNodeList(String traceFilePath, BreezeArch rootArch) {
		List<TraceNode> nodeList = new ArrayList<TraceNode>();
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			doc = reader.read(new File(traceFilePath));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ConsoleUtil.printError(ConsoleFactory.getConsole(), "Trace file is invalid\n");
			return nodeList;
		}
		Element root = doc.getRootElement();
		if (root == null) return nodeList;
		List<Element> list = root.elements("node");
		if (list == null) return nodeList;

		// construct node map, key is name of instance in smv,value is BreezeNode
		Map<String, BreezeNode> compilerNodeMap = rootArch.getNodeList();

		Map<Integer, String> eventIdNumberMap = new HashMap<Integer, String>();
		// map event number to event id
		for (BreezeEvent be : rootArch.getEventList()) {
			eventIdNumberMap.put(be.getNumber(), be.getId());
		}

		int i = 0;
		for (Element nodeElement : list) {
			Element stateElement = nodeElement.element("state");
			String step = stateElement.attributeValue("id", "" + i++);
			TraceNode traceNode = new TraceNode(step, "", null);
			List<Element> valueElements = stateElement.elements("value");
			traceNode.setChildren(GetNuSMVTrace.parseValueElements(valueElements, compilerNodeMap, eventIdNumberMap, traceNode));
			nodeList.add(traceNode);
		}

		return nodeList;
	}

	private static List<TraceNode> parseValueElements(List<Element> valueElements, Map<String, BreezeNode> compilerNodeMap,
			Map<Integer, String> eventIdNumberMap, TraceNode parent) {
		Map<String, TraceNode> tnMap = new HashMap<String, TraceNode>();

		Map<String, List<String>> portIn = new HashMap<String, List<String>>();

		for (Element valueElement : valueElements) {
			String variable = valueElement.attributeValue("variable");
			String value = valueElement.getTextTrim();
			if (variable == null || value == null) continue;
			String[] part = variable.split("\\.");
			if (part == null || part.length != 2 || part[0].length() <= 4) continue;

			// instance name is with form 'ins_*'
			String moduleName = part[0].substring(4);
			BreezeNode bn = compilerNodeMap.get(moduleName);
			if (bn == null) continue;

			TraceNode tn = tnMap.get(moduleName);
			if (tn == null) {
				tn = new TraceNode(moduleName, "", bn.getName());
				tnMap.put(moduleName, tn);
			}

			List<TraceNode> children = tn.getChildren();
			if (children == null) {
				children = new ArrayList<TraceNode>();
				tn.setChildren(children);
			}

			if (part[1].equals("mode")) {
				String modeValue = "";
				if (value.startsWith(BreezeMode.TYPE_INITIAL) && value.length() > 8)
					modeValue = value.substring(8);
				else if (value.startsWith(BreezeMode.TYPE_FAILURE) && value.length() > 8)
					modeValue = value.substring(8);
				else if (value.startsWith(BreezeMode.TYPE_NORMAL) && value.length() > 7)
					modeValue = value.substring(7);
				else if (value.startsWith(BreezeMode.TYPE_STOP) && value.length() > 8)
					modeValue = value.substring(8);
				else continue;
				children.add(new TraceNode("mode", modeValue, null));
			} else {

				String eventId = null;
				try {
					Integer iv = Integer.parseInt(value);
					eventId = iv == 0 ? "${None}" : eventIdNumberMap.get(iv);
				} catch (Exception e) {
				}

				if (eventId == null) continue;

				BreezePort port = bn.getPortMap().get(part[1]);

				if (port == null) {
					for (String portId : bn.getPortMap().keySet()) {
						if (part[1].startsWith(portId)) {
							List<String> receiveEvent = portIn.get(moduleName + ">" + portId);
							if (receiveEvent == null) {
								receiveEvent = new ArrayList<String>();
								portIn.put(moduleName + ">" + portId, receiveEvent);
							}

							receiveEvent.add(eventId);
							break;
						}
					}
				} else {
					Object name = port.getProperty(BreezeObject.ATTR_NAME);
					TraceNode child = new TraceNode(port.getId() + ":send", eventId, name == null ? null : name.toString()
							+ ":send");
					children.add(child);
				}
			}
		}

		GetNuSMVTrace.extractTypeInPort(portIn, compilerNodeMap, tnMap);

		List<TraceNode> result = new ArrayList<TraceNode>();
		for (String mn : tnMap.keySet()) {
			TraceNode tn = tnMap.get(mn);
			tn.setParent(parent);
			result.add(tn);
		}
		return result;
	}
}
