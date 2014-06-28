package com.goku.breeze.ui.editor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

//import BreezeModel.Attribute;
import com.goku.breeze.compiler.main.BreezeXMLParser;
import com.goku.breeze.compiler.model.BreezeArch;
import com.goku.breeze.compiler.model.BreezeConnection;
import com.goku.breeze.compiler.model.BreezeConnection.Connection;
import com.goku.breeze.compiler.model.BreezeEvent;
import com.goku.breeze.compiler.model.BreezeMode;
import com.goku.breeze.compiler.model.BreezeModeTransition;
import com.goku.breeze.compiler.model.BreezeNode;
import com.goku.breeze.compiler.model.BreezeObject;
import com.goku.breeze.compiler.model.BreezePort;

public class DTMCGraphModelEditor extends ScrolledComposite {
	private static Map<String, BreezeObject> allObjects;
	static int cc = 0;
	static int count = 0;
	//below 	generate MKV model
	private static Map<String, String> idmap = new HashMap<String, String>();
	//锟斤拷锟斤拷锟角讹拷xml锟斤拷锟絜dge锟斤拷id锟斤拷sourceName锟斤拷map锟侥达拷锟斤拷
	private static Map<String, String> idmapc1 = new HashMap<String, String>();
	//锟斤拷锟斤拷锟角讹拷xml锟斤拷锟絜dge锟斤拷id锟斤拷targetName锟斤拷map锟侥达拷锟斤拷
	private static Map<String, String> idmapc2 = new HashMap<String, String>();
	//锟斤拷锟斤拷锟角讹拷xml锟斤拷锟絜dge锟斤拷id锟斤拷probability锟斤拷map锟侥达拷锟斤拷
	private static Map<String, String> idmapc3 = new HashMap<String, String>();
	private static Map<String, String> idmapd = new HashMap<String, String>();
	private static Map<String, String> idmapn = new HashMap<String, String>();
	//锟斤拷锟斤拷锟角讹拷xml锟斤拷锟絥ode锟斤拷id锟斤拷probability锟斤拷map锟侥达拷锟斤拷
	private static Map<String, String> idmapp = new HashMap<String, String>();
	//锟斤拷锟斤拷锟角讹拷xml锟斤拷锟絥ode锟斤拷id锟斤拷isVirtual锟斤拷map锟侥达拷锟斤拷
	private static Map<String, String> idmapv = new HashMap<String, String>();
	static int ProbabiltyCount = 0;
	//	static String[] RBDid = new String[100];
	//	static String[] RBDname1 = new String[100];
	//	static String[] RBDname2 = new String[100];
	//	static String[] RBDop = new String[100];
	static private String saFilePath;

	public static void idGen() {
		int s = 0;
		File file = new File(saFilePath);
		//	System.out.println("11");
		//	System.out.println(saFilePath);
		Map<String, String> mapEleList = new HashMap<String, String>();
		try {
			mapEleList = parseXml(file);
			// 锟斤拷锟斤拷map
			Entry<String, String> entry;
			Iterator<Entry<String, String>> it = mapEleList.entrySet().iterator();
			while (it.hasNext()) {
				entry = it.next();
				s = s + 1;
			}

		} catch (DocumentException e) {
			e.printStackTrace();
			System.out.println("catch");
		}
		//return er;
	}

	public static void idGenc1() {
		int s = 0;
		File file = new File(saFilePath);
		//System.out.println("11");
		//System.out.println(saFilePath);
		Map<String, String> mapEleList = new HashMap<String, String>();
		try {
			mapEleList = parseXmlc1(file);
			// 锟斤拷锟斤拷map
			Entry<String, String> entry;
			Iterator<Entry<String, String>> it = mapEleList.entrySet().iterator();
			while (it.hasNext()) {
				entry = it.next();
				s = s + 1;
			}

		} catch (DocumentException e) {
			e.printStackTrace();
			System.out.println("catch");
		}
		//return er;
	}

	public static void idGenc2() {
		int s = 0;
		File file = new File(saFilePath);
		//System.out.println("11");
		//System.out.println(saFilePath);
		Map<String, String> mapEleList = new HashMap<String, String>();
		try {
			mapEleList = parseXmlc2(file);
			// 锟斤拷锟斤拷map
			Entry<String, String> entry;
			Iterator<Entry<String, String>> it = mapEleList.entrySet().iterator();
			while (it.hasNext()) {
				entry = it.next();
				s = s + 1;
			}

		} catch (DocumentException e) {
			e.printStackTrace();
			System.out.println("catch");
		}
		//return er;
	}

	public static void idGenc3() {
		int s = 0;
		File file = new File(saFilePath);
		//System.out.println("11");
		//System.out.println(saFilePath);
		Map<String, String> mapEleList = new HashMap<String, String>();
		try {
			mapEleList = parseXmlc3(file);
			// 锟斤拷锟斤拷map
			Entry<String, String> entry;
			Iterator<Entry<String, String>> it = mapEleList.entrySet().iterator();
			while (it.hasNext()) {
				entry = it.next();
				s = s + 1;
			}

		} catch (DocumentException e) {
			e.printStackTrace();
			System.out.println("catch");
		}
		//return er;
	}

	public static void idGenp() {
		int s = 0;
		File file = new File(saFilePath);
		//System.out.println("11");
		//System.out.println(saFilePath);
		Map<String, String> mapEleList = new HashMap<String, String>();
		try {
			mapEleList = parseXmlp(file);
			// 锟斤拷锟斤拷map
			Entry<String, String> entry;
			Iterator<Entry<String, String>> it = mapEleList.entrySet().iterator();
			while (it.hasNext()) {
				entry = it.next();
				s = s + 1;
			}

		} catch (DocumentException e) {
			e.printStackTrace();
			System.out.println("catch");
		}

		//return er;
	}

	public static void idGenv() {
		int s = 0;
		File file = new File(saFilePath);
		//System.out.println("11");
		//System.out.println(saFilePath);
		Map<String, String> mapEleList = new HashMap<String, String>();
		try {
			mapEleList = parseXmlv(file);
			// 锟斤拷锟斤拷map
			Entry<String, String> entry;
			Iterator<Entry<String, String>> it = mapEleList.entrySet().iterator();
			while (it.hasNext()) {
				entry = it.next();
				s = s + 1;
			}

		} catch (DocumentException e) {
			e.printStackTrace();
			System.out.println("catch");
		}

		//return er;
	}

	//锟斤拷锟斤拷为锟斤拷锟斤拷锟绞撅拷暮锟斤拷锟�	//锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷Component reliability锟斤拷拇锟斤拷锟�
	public static Map parseXml(File filePath) throws DocumentException {

		Map<String, String> mapEle = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		Document document = reader.read(filePath);// 锟斤拷取xml锟侥碉拷
		Element root = document.getRootElement();// 锟矫碉拷xml锟侥碉拷锟斤拷诘锟皆拷兀锟斤拷锟斤拷锟斤拷喜锟斤拷"<xml>"
		//System.out.println("锟斤拷锟斤拷锟节碉拷元锟截碉拷锟斤拷锟斤拷===" + root.getName());
		Element parentElement = root.element("arch");
		// 循锟斤拷锟斤拷锟斤拷锟斤拷锟节碉拷锟斤拷锟斤拷锟斤拷咏诘锟�		
		List<Element> elementList = parentElement.elements();
		Iterator<Element> it = elementList.iterator();
		while (it.hasNext()) {
			Element element = it.next();
			if (element.getName() == "node")
			// System.out.println(element);
			{
				idmap.put(element.attributeValue("ID"), element.attributeValue("name"));
			}
		}

		return mapEle;
	}

	public static Map parseXmlc1(File filePath) throws DocumentException {

		Map<String, String> mapEle = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		Document document = reader.read(filePath);// 锟斤拷取xml锟侥碉拷
		Element root = document.getRootElement();// 锟矫碉拷xml锟侥碉拷锟斤拷诘锟皆拷兀锟斤拷锟斤拷锟斤拷喜锟斤拷"<xml>"
		//System.out.println("锟斤拷锟斤拷锟节碉拷元锟截碉拷锟斤拷锟斤拷===" + root.getName());
		Element parentElement = root.element("arch");
		// 循锟斤拷锟斤拷锟斤拷锟斤拷锟节碉拷锟斤拷锟斤拷锟斤拷咏诘锟�		
		List<Element> elementList = parentElement.elements();
		Iterator<Element> it = elementList.iterator();
		while (it.hasNext()) {
			Element element = it.next();
			if (element.getName() == "edge")
			// System.out.println(element);
			{
				idmapc1.put(element.attributeValue("ID"), element.attributeValue("sourceName"));
			}
		}

		return mapEle;
	}

	public static Map parseXmlc2(File filePath) throws DocumentException {

		Map<String, String> mapEle = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		Document document = reader.read(filePath);// 锟斤拷取xml锟侥碉拷
		Element root = document.getRootElement();// 锟矫碉拷xml锟侥碉拷锟斤拷诘锟皆拷兀锟斤拷锟斤拷锟斤拷喜锟斤拷"<xml>"
		//System.out.println("锟斤拷锟斤拷锟节碉拷元锟截碉拷锟斤拷锟斤拷===" + root.getName());
		Element parentElement = root.element("arch");
		// 循锟斤拷锟斤拷锟斤拷锟斤拷锟节碉拷锟斤拷锟斤拷锟斤拷咏诘锟�		
		List<Element> elementList = parentElement.elements();
		Iterator<Element> it = elementList.iterator();
		while (it.hasNext()) {
			Element element = it.next();
			if (element.getName() == "edge")
			// System.out.println(element);
			{
				idmapc2.put(element.attributeValue("ID"), element.attributeValue("targetName"));
			}
		}

		return mapEle;
	}

	public static Map parseXmlc3(File filePath) throws DocumentException {

		Map<String, String> mapEle = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		Document document = reader.read(filePath);// 锟斤拷取xml锟侥碉拷
		Element root = document.getRootElement();// 锟矫碉拷xml锟侥碉拷锟斤拷诘锟皆拷兀锟斤拷锟斤拷锟斤拷喜锟斤拷"<xml>"
		//System.out.println("锟斤拷锟斤拷锟节碉拷元锟截碉拷锟斤拷锟斤拷===" + root.getName());
		Element parentElement = root.element("arch");
		// 循锟斤拷锟斤拷锟斤拷锟斤拷锟节碉拷锟斤拷锟斤拷锟斤拷咏诘锟�		
		List<Element> elementList = parentElement.elements();
		Iterator<Element> it = elementList.iterator();
		while (it.hasNext()) {
			Element element = it.next();
			if (element.getName() == "edge")
			// System.out.println(element);
			{
				idmapc3.put(element.attributeValue("ID"), element.attributeValue("probability"));
			}
		}

		return mapEle;
	}

	public static Map parseXmlp(File filePath) throws DocumentException {

		Map<String, String> mapEle = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		Document document = reader.read(filePath);// 锟斤拷取xml锟侥碉拷
		Element root = document.getRootElement();// 锟矫碉拷xml锟侥碉拷锟斤拷诘锟皆拷兀锟斤拷锟斤拷锟斤拷喜锟斤拷"<xml>"
		//System.out.println("锟斤拷锟斤拷锟节碉拷元锟截碉拷锟斤拷锟斤拷===" + root.getName());
		Element parentElement = root.element("arch");
		// 循锟斤拷锟斤拷锟斤拷锟斤拷锟节碉拷锟斤拷锟斤拷锟斤拷咏诘锟�		
		List<Element> elementList = parentElement.elements();
		Iterator<Element> it = elementList.iterator();
		while (it.hasNext()) {
			Element element = it.next();
			if (element.getName() == "node")
			// System.out.println(element);
			{
				idmapp.put(element.attributeValue("ID"), element.attributeValue("probability"));
			}
		}
		return mapEle;
	}

	public static Map parseXmlv(File filePath) throws DocumentException {

		Map<String, String> mapEle = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		Document document = reader.read(filePath);// 锟斤拷取xml锟侥碉拷
		Element root = document.getRootElement();// 锟矫碉拷xml锟侥碉拷锟斤拷诘锟皆拷兀锟斤拷锟斤拷锟斤拷喜锟斤拷"<xml>"
		//System.out.println("锟斤拷锟斤拷锟节碉拷元锟截碉拷锟斤拷锟斤拷===" + root.getName());
		Element parentElement = root.element("arch");
		// 循锟斤拷锟斤拷锟斤拷锟斤拷锟节碉拷锟斤拷锟斤拷锟斤拷咏诘锟�		
		List<Element> elementList = parentElement.elements();
		Iterator<Element> it = elementList.iterator();
		while (it.hasNext()) {
			Element element = it.next();
			if (element.getName() == "node")
			// System.out.println(element);
			{
				idmapv.put(element.attributeValue("ID"), element.attributeValue("isVirtual"));
			}
		}
		return mapEle;
	}

	private BreezeXMLParser bxp;

	private final Color colorBlue = new Color(Display.getCurrent(), 0, 0, 255);

	private final Color colorWhite = new Color(Display.getCurrent(), 255, 255, 255);

	private final Combo componentSelect = null;

	private Composite composite;

	private Table eventTable, modeTable, transitionTable;

	private TableViewer eventTableViewer, modeTableViewer, transitionTableViewer;

	private final String fileNameWithoutExt;
	private Table methodTable;
	private TableViewer methodTableViewer;

	private Composite panel = null;

	private final DTMCEditor parentEditor;
	public Table table;
	public TableViewer tableViewer;

	public DTMCGraphModelEditor(String saFilePath, String fileNameWithoutExt, Composite parent, DTMCEditor dtmcEditor) {
		super(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		// TODO Auto-generated constructor stub
		this.fileNameWithoutExt = fileNameWithoutExt;
		this.parentEditor = dtmcEditor;
		this.saFilePath = saFilePath;
		this.init(saFilePath);
		//		this.init(null);
		//		System.out.println("RBD");
		//		System.out.println(saFilePath);
		//		System.out.println(fileNameWithoutExt);
	}

	public void doSave() {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("breeze");
		BreezeArch arch = this.bxp.getTopArch();
		Element archElement = root.addElement(BreezeObject.TYPE_ARCH);
		archElement.addAttribute(BreezeObject.ATTR_ID, arch.getId());
		archElement.addAttribute(BreezeObject.ATTR_NAME, arch.getName());

		this.writeEvents(archElement, arch.getEventList());

		Map<String, BreezeNode> nodeMap = arch.getNodeList();
		for (String nodeId : nodeMap.keySet()) {
			BreezeNode node = nodeMap.get(nodeId);
			this.writeNode(node, archElement);
		}

		BreezeConnection connection = arch.getConnectionGraph();
		this.writeConnection(connection, archElement);

		try {
			FileWriter writer = new FileWriter(new File(this.saFilePath));
			XMLWriter xmlWriter = new XMLWriter(writer, OutputFormat.createPrettyPrint());
			xmlWriter.write(doc);
			xmlWriter.close();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Button drawButton(String txt) {
		Button btn = new Button(this.panel, SWT.PUSH | SWT.CENTER);
		btn.setText(txt);
		btn.setBackground(this.colorWhite);
		btn.setLayoutData(new GridData(SWT.BEGINNING, SWT.FILL, true, false, 1, 1));
		return btn;
	}

	private void drawDivider() {
		Label lb = new Label(this.panel, SWT.SEPARATOR | SWT.HORIZONTAL);
		lb.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
	}

	private void drawLabel(String txt) {
		Label lb0 = new Label(this.panel, SWT.NONE);
		lb0.setText(txt);
		GridData gd = new GridData(SWT.BEGINNING, SWT.FILL, true, false, 1, 1);
		lb0.setForeground(this.colorBlue);
		gd.verticalIndent = 20;
		lb0.setLayoutData(gd);
	}

	private void drawPanelCommonsAfter(int height) {
		this.panel.redraw();
		this.panel.layout(true);
		this.setMinSize(this.composite.computeSize(SWT.DEFAULT, height));
		this.layout(true);
	}

	private void drawPanelCommonsBefore() {
		Control[] children = this.panel.getChildren();
		for (Control child : children)
			child.dispose();
	}

	private Table drawTable() {
		Table tb = new Table(this.panel, SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.BORDER);
		tb.setLayoutData(new GridData(SWT.BEGINNING, SWT.FILL, true, true, 1, 10));
		tb.setHeaderVisible(true);
		tb.setLinesVisible(true);
		return tb;
	}

	private Object[] drawTableViewer(Table tb, String[] headers, int[] lengths) {
		TableViewer tv = new TableViewer(tb);
		TableViewerColumn[] cols = new TableViewerColumn[headers.length];
		for (int i = 0; i < headers.length; ++i) {
			cols[i] = new TableViewerColumn(tv, SWT.NONE);
			cols[i].getColumn().setWidth(lengths[i]);
			cols[i].getColumn().setText(headers[i]);
		}
		return new Object[] { tv, cols };
	}

	private void extractAllBreezeObject(BreezeArch top, Map<String, BreezeObject> map) {
		Map<String, BreezeNode> nodeMap = top.getNodeList();
		for (String nodeKey : nodeMap.keySet()) {
			BreezeNode bn = nodeMap.get(nodeKey);
			map.put(nodeKey, bn);
			Map<String, BreezePort> portMap = bn.getPortMap();
			for (String portId : portMap.keySet())
				map.put(portId, portMap.get(portId));
		}
	}

	public void fireModelChange() {
		this.parentEditor.modelModified();
	}

	public void init(final String saFilePath) {

		idmap.clear();
		idmapp.clear();
		idmapc1.clear();
		idmapc2.clear();
		idmapc3.clear();
		idmapv.clear();
		idmapn.clear();
		idmapd.clear();
		idGen();
		idGenc1();
		idGenc2();
		idGenc3();
		idGenp();
		idGenv();
		BreezeArch topArch = null;
		//		this.allObjects = new HashMap<String, BreezeObject>();
		//		if (saFilePath != null) {
		//			try {
		//				this.bxp = new BreezeXMLParser(new File(saFilePath));
		//
		//				topArch = this.bxp.getTopArch();
		//				if (topArch != null)
		//					this.extractAllBreezeObject(topArch, this.allObjects);
		//				this.allObjects.put(topArch.getId(), topArch);
		//			} catch (DocumentException e) {
		//				// TODO Auto-generated catch block
		//				e.printStackTrace();
		//			}
		//		}

		this.setExpandHorizontal(true);
		this.setExpandVertical(true);

		this.composite = new Composite(this, SWT.NONE);
		this.setContent(this.composite);
		this.composite.setBackground(this.colorWhite);
		this.composite.setBackgroundMode(SWT.INHERIT_DEFAULT);

		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		this.composite.setLayout(layout);

		Label lb0 = new Label(this.composite, SWT.NONE);
		lb0.setText("DTMC Graph\n");
		lb0.setLayoutData(new GridData(SWT.BEGINNING, SWT.FILL, true, false, 1, 1));

		this.panel = new Composite(this.composite, SWT.NONE);
		this.panel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		GridLayout panelLayout = new GridLayout();
		panelLayout.numColumns = 1;
		this.panel.setLayout(panelLayout);
		//锟斤拷实锟揭诧拷知锟斤拷锟斤拷锟斤拷锟角革拷什么锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷写锟斤拷 锟斤拷貌锟斤拷锟斤拷锟脚比较猴拷

		Button btn = new Button(this.panel, SWT.PUSH);
		btn.setText("Generate");

		final Label lb5 = new Label(this.panel, SWT.NONE);
		lb5.setText("                                                                                \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n ");

		btn.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				idGen();
				idGenc1();
				idGenc2();
				idGenc3();
				idGenp();
				idGenv();
				//				below	int n for Mat
				int vcount = 0;
				int correct = 0;
				int fault = 0;
				Set<Map.Entry<String, String>> setv = idmapv.entrySet();
				Set<Map.Entry<String, String>> set = idmap.entrySet();
				correct = 0;
				fault = 1;
				idmapn.put("correct", "0");
				idmapn.put("fault", "1");
				idmapn.put("start", "2");
				idmapd.put("correct", "0");
				idmapd.put("fault", "1");
				idmapd.put("start", "2");
				vcount = 3;
				for (Iterator<Map.Entry<String, String>> itv = setv.iterator(); itv.hasNext();) {
					Map.Entry<String, String> entryv = itv.next();
					for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
						Map.Entry<String, String> entry = it.next();
						if (entryv.getKey().contentEquals(entry.getKey())) {
							if (entryv.getKey().contentEquals("nodeCorrectID")) {
								vcount = vcount - 1;
							} else if (entryv.getKey().contentEquals("nodeFaultID")) {
								vcount = vcount - 1;
							} else if (entryv.getKey().contentEquals("nodeStartID")) {
								vcount = vcount - 1;
							} else {
								idmapd.put(entry.getValue(), String.valueOf(vcount));
							}
							//							if (entry.getValue().contentEquals("correct")) correct=vcount;
							//							if (entry.getValue().contentEquals("fault")) fault=vcount;
						}
					}
					if (entryv.getKey().contentEquals("nodeCorrectID")) {
					} else if (entryv.getKey().contentEquals("nodeFaultID")) {
					} else if (entryv.getKey().contentEquals("nodeStartID")) {
					} else {
						idmapn.put(entryv.getKey(), String.valueOf(vcount));
					}

					vcount = vcount + 1;
				}
				//				System.out.println(vcount);
				//				above	int n for Mat
				//				below	init Mat
				double[][] mat = new double[vcount][vcount];
				for (int i = 0; i < vcount; ++i) {
					for (int j = 0; j < vcount; ++j) {
						mat[i][j] = 0;
					}
				}
				//				above	init Mat
				//				below	construct mat
				//				below	construct mat right down part
				mat[correct][correct] = 1;
				mat[fault][fault] = 1;
				//				above	construct mat right down part
				//				below	construct mat main part
				Set<Map.Entry<String, String>> setc1 = idmapc1.entrySet();
				Set<Map.Entry<String, String>> setc2 = idmapc2.entrySet();
				Set<Map.Entry<String, String>> setc3 = idmapc3.entrySet();
				Set<Map.Entry<String, String>> setpd = idmapd.entrySet();
				Set<Map.Entry<String, String>> setpdd = idmapd.entrySet();

				Set<Map.Entry<String, String>> setp = idmapp.entrySet();
				Set<Map.Entry<String, String>> setpn = idmapn.entrySet();
				int nsource = 0;
				int ntarget = 0;
				double prop = 0;
				for (Iterator<Map.Entry<String, String>> itc3 = setc3.iterator(); itc3.hasNext();) {
					prop = 0;
					Map.Entry<String, String> entryc3 = itc3.next();
					//					System.out.println(entryc3.getKey()+"\t"+entryc3.getValue());
					for (Iterator<Map.Entry<String, String>> itc1 = setc1.iterator(); itc1.hasNext();) {
						Map.Entry<String, String> entryc1 = itc1.next();
						if (entryc1.getKey().contentEquals(entryc3.getKey())) {
							for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
								Map.Entry<String, String> entry = it.next();
								if (entry.getValue().contentEquals(entryc1.getValue())) {
									for (Iterator<Map.Entry<String, String>> itp = setp.iterator(); itp.hasNext();) {
										Map.Entry<String, String> entryp = itp.next();
										if (entry.getKey().contentEquals(entryp.getKey())) {
											prop = Double.parseDouble(entryp.getValue());
										}
									}

								}
							}
							for (Iterator<Map.Entry<String, String>> itpd = setpd.iterator(); itpd.hasNext();) {
								Map.Entry<String, String> entrypd = itpd.next();
								if (entryc1.getValue().contentEquals(entrypd.getKey())) {
									nsource = Integer.valueOf(entrypd.getValue());
								}
							}
						}
					}
					for (Iterator<Map.Entry<String, String>> itc2 = setc2.iterator(); itc2.hasNext();) {
						Map.Entry<String, String> entryc2 = itc2.next();
						if (entryc2.getKey().contentEquals(entryc3.getKey())) {
							for (Iterator<Map.Entry<String, String>> itpdd = setpdd.iterator(); itpdd.hasNext();) {
								Map.Entry<String, String> entrypdd = itpdd.next();
								if (entryc2.getValue().contentEquals(entrypdd.getKey())) {
									//									System.out.println(entryc2.getKey()+"\t"+entryc2.getValue());
									ntarget = Integer.valueOf(entrypdd.getValue());
								}
							}
						}
					}
					//					System.out.println("aaaa\t"+Integer.valueOf(nsource).intValue()+"\t"+Integer.valueOf(ntarget).intValue()+"\t"+entryc3.getValue());
					mat[nsource][ntarget] = prop * Double.parseDouble(entryc3.getValue());
				}
				//				above	construct mat main part

				//				below	construct mat right part
				for (Iterator<Map.Entry<String, String>> itp = setp.iterator(); itp.hasNext();) {
					Map.Entry<String, String> entryp = itp.next();
					for (Iterator<Map.Entry<String, String>> itpn = setpn.iterator(); itpn.hasNext();) {
						Map.Entry<String, String> entrypn = itpn.next();
						if (entryp.getKey().contentEquals(entrypn.getKey())) {
							if (entryp.getKey().contentEquals("nodeStartID")) {
							} else if (entryp.getKey().contentEquals("nodeCorrectID")) {
							} else if (entryp.getKey().contentEquals("nodeFaultID")) {
							} else {
								//								System.out.println("cc"+entrypn.getValue()+"\t"+entryp.getValue());
								//								System.out.println("cc"+mat[Integer.valueOf(entrypn.getValue()).intValue()][fault]);
								mat[Integer.valueOf(entrypn.getValue()).intValue()][fault] = 1 - Double.parseDouble(entryp
										.getValue());
								//							System.out.println("cc"+mat[Integer.valueOf(entrypn.getValue()).intValue()][fault]);

							}
						}
					}

				}
				//				above	construct mat right part
				//				above	construct mat

				Mat m = new Mat();
				//				below	I-Q

				double[][] inp = new double[vcount - 2][vcount - 2];
				double[][] inpp = new double[vcount - 2][vcount - 2];
				for (int i = 0; i < vcount - 2; ++i) {
					for (int j = 0; j < vcount - 2; ++j) {
						if (i == j) {
							inp[i][j] = 1 - mat[i + 2][j + 2];
						} else {
							inp[i][j] = 0 - mat[i + 2][j + 2];
						}
					}
				}

				for (int i = 0; i < vcount - 2; ++i) {
					for (int j = 0; j < vcount - 2; ++j) {
						if (i == j) {
							inpp[i][j] = 1 - mat[i + 2][j + 2] / (1 - mat[i + 2][1]);
						} else {
							inpp[i][j] = 0 - mat[i + 2][j + 2] / (1 - mat[i + 2][1]);
						}
					}
				}

				//				above	I-Q
				double[][] re = new double[vcount - 2][vcount - 2];
				re = m.revMat(vcount - 2, inp);
				double[][] rep = new double[vcount - 2][vcount - 2];
				rep = m.revMat(vcount - 2, inpp);

				//				below	shows what the matrix is

				for (int i = 0; i < vcount; ++i) {
					Set<Map.Entry<String, String>> setpr = idmapd.entrySet();
					for (Iterator<Map.Entry<String, String>> itp = setpr.iterator(); itp.hasNext();) {
						Map.Entry<String, String> entryp = itp.next();
						if (i == Integer.valueOf(entryp.getValue()).intValue()) {
							//							System.out.print(entryp.getKey()+"\t");
						}
					}
					for (int j = 0; j < vcount; ++j) {
						//						System.out.print(mat[i][j]+"\t");
					}
					//					System.out.print("\n");
				}

				String[] nn = new String[vcount - 2];
				for (int i = 0; i < vcount - 2; ++i) {
					Set<Map.Entry<String, String>> setpr = idmapd.entrySet();
					for (Iterator<Map.Entry<String, String>> itp = setpr.iterator(); itp.hasNext();) {
						Map.Entry<String, String> entryp = itp.next();
						if (i + 2 == Integer.valueOf(entryp.getValue()).intValue()) {
							//							System.out.print(entryp.getKey()+"\t");
							nn[i] = entryp.getKey();
						}
					}
					for (int j = 0; j < vcount - 2; ++j) {
						//						System.out.print(re[i][j]+"\t");
					}
					//					System.out.print("\n");
				}

				//				above	shows what the matrix is
				String ans = "";
				double max = 0;
				double min = 1;
				int imax = 0;
				String[] nn2 = new String[vcount - 2];
				for (int i = 0; i < vcount - 2; ++i) {
					nn2[i] = nn[i];
				}
				double[] an = new double[vcount - 2];
				double[] bn = new double[vcount - 2];
				boolean[] bb = new boolean[vcount - 2];
				double suma = 0;
				double sumb = 0;
				int op = 0;
				for (int i = 0; i < vcount - 2; ++i) {
					an[i] = re[0][i] * mat[i + 2][0];
					suma = suma + an[i];
				}
				for (int i = 0; i < vcount - 2; ++i) {
					bn[i] = re[0][i] * mat[i + 2][1];
					sumb = sumb + bn[i];
				}
				String str1;
				NumberFormat nf1 = NumberFormat.getPercentInstance();
				nf1.setMinimumFractionDigits(2);
				str1 = nf1.format(suma / (suma + sumb));
				ans = "Correct output" + "\t" + str1 + "\n";

				for (int i = 1; i < vcount - 2; ++i) {
					an[i] = an[i] / suma;
				}
				min = 1;
				for (int i = 1; i < vcount - 2; ++i) {
					bb[i] = true;
				}
				for (int i = 1; i < vcount - 2; ++i) {
					max = 0;
					for (int j = 1; j < vcount - 2; ++j) {
						if (an[j] >= max && an[j] <= min && bb[j] == true) {
							imax = j;
							max = an[j];
						}
					}
					min = max;
					bb[imax] = false;
					String str;
					NumberFormat nf = NumberFormat.getPercentInstance();
					nf.setMinimumFractionDigits(2);
					str = nf.format(an[imax]);
					if (an[imax] != 0) {
						ans = ans + "\t" + nn[imax];
						for (int st = 0; st < 20; ++st) {
							if (st > nn[imax].length()) {
								ans = ans + " ";
								op = op + 1;
								if (op == 4) {
									ans = ans + " ";
									op = 0;
								}
							}
						}
						ans = ans + "\t" + str + "\n";
					}
				}

				nf1 = NumberFormat.getPercentInstance();
				nf1.setMinimumFractionDigits(2);
				str1 = nf1.format(sumb / (suma + sumb));
				ans = ans + "\n" + "Wrong output" + "\t" + str1 + "\n";

				//				for (int i=1;i<vcount-2;++i)
				//				{
				//					bn[i]=an[i]/sum;
				//				}
				//				for (int i=1;i<vcount-2;++i)
				//				{
				//					String str;
				//					NumberFormat nf = NumberFormat.getPercentInstance();
				//					nf.setMinimumFractionDigits(2);
				//					str = nf.format(bn[i]);
				//					ans = ans + "\t" + nn[i]+"\t"+str+"\n";					
				//				}
				for (int i = 1; i < vcount - 2; ++i) {
					bn[i] = bn[i] / sumb;
				}
				min = 1;
				for (int i = 1; i < vcount - 2; ++i) {
					bb[i] = true;
				}
				for (int i = 1; i < vcount - 2; ++i) {
					max = 0;
					for (int j = 1; j < vcount - 2; ++j) {
						if (bn[j] >= max && bn[j] <= min && bb[j] == true) {
							imax = j;
							max = bn[j];
						}
					}
					min = max;
					bb[imax] = false;
					String str;
					NumberFormat nf = NumberFormat.getPercentInstance();
					nf.setMinimumFractionDigits(2);
					str = nf.format(bn[imax]);
					if (bn[imax] != 0) {
						ans = ans + "\t" + nn[imax];
						for (int st = 0; st < 20; ++st) {
							if (st > nn[imax].length())
								ans = ans + " ";
							op = op + 1;
							if (op == 4) {
								ans = ans + " ";
								op = 0;
							}
						}

						ans = ans + "\t" + str + "\n";
					}
				}

				ans = ans + "\n" + "Probabilities of Components Participation" + "\n";
				for (int i = 0; i < vcount - 2; ++i) {
					an[i] = rep[0][i];
				}
				Arrays.sort(nn2);
				for (int i = 0; i < vcount - 2; ++i) {
					for (int j = 0; j < vcount - 2; ++j) {
						if (nn2[i].contentEquals(nn[j])) {
							if (nn2[i].contentEquals("start")) {
							} else {
								String str;
								NumberFormat nf = NumberFormat.getPercentInstance();
								nf.setMinimumFractionDigits(2);
								str = nf.format(an[j]);
								ans = ans + "\t" + nn[j];
								for (int st = 0; st < 20; ++st) {
									if (st > nn[j].length())
										ans = ans + " ";
									op = op + 1;
									if (op == 4) {
										ans = ans + " ";
										op = 0;
									}
								}
								ans = ans + "\t" + str + "\n";
							}

						}
					}
				}

				//lb5.setText("\n" + ans);
				lb5.setText("Under Construction.\n");
				
				//		        TableItem item = new TableItem (table, SWT.NONE,table.getItemCount() );
				//		        item.setText(new String [] {"A" , "B"});//注意,这里是用字符串数组
				//		        
				//		        TableItem item = new TableItem(table, SWT.NONE);
				//		        item.setText( new String[] { "newColumnTableColumn_0",   "newColumnTableColumn_1" } );
				//		        item = new TableItem(table, SWT.NONE);
				//		        item.setText( new String[] { "a", "b" } );
				//		        
				//		        table.removeAll();
				//		        table.update();
				//		        table = tableViewer.getTable();
				//				list1.add(p1);  
				//				tableViewer.setContentProvider(new ContentProvider());
				//		        //设置标签器
				//		        tableViewer.setLabelProvider(new TableLabelProvider());
				//		        //把数据集合给tableView
				//		        tableViewer.setInput(list1);
				//		       
				//		        tableViewer.refresh();
			}
		});
		this.setMinSize(this.composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		//		Text txt = new Text(this.panel, SWT.BORDER);
		//		txt.setText("锟斤拷锟斤拷锟斤拷锟斤看锟斤拷位锟矫对诧拷锟斤拷");
	}

	//above 	generate MKV model

	public void updatePanel(String item) {
		if (item == null)
			return;
		BreezeObject obj = this.allObjects.get(item);

	}

	private void writeConnection(BreezeConnection connection, Element archElement) {
		Map<String, List<Connection>> edges = connection.getConnectionMap();
		for (String key : edges.keySet()) {
			List<Connection> l = edges.get(key);
			for (Connection con : l) {
				Element edgeElement = archElement.addElement(BreezeObject.TYPE_PORT_CONNECTION);
				edgeElement.addAttribute(BreezeObject.ATTR_ID, "");
				edgeElement.addAttribute(BreezeObject.ATTR_SOURCE, con.sourceNode.getId());
				edgeElement.addAttribute(BreezeObject.ATTR_SOURCE_PORT, con.sourcePort.getId());
				edgeElement.addAttribute(BreezeObject.ATTR_TARGET, con.targetNode.getId());
				edgeElement.addAttribute(BreezeObject.ATTR_TARGET_PORT, con.targetPort.getId());
			}
		}
	}

	private void writeEvents(Element archElement, List<BreezeEvent> eventList) {
		for (BreezeEvent event : eventList) {
			Element eventElement = archElement.addElement(BreezeObject.TYPE_EVENT);
			eventElement.addAttribute(BreezeObject.ATTR_ID, event.getId());
			eventElement.addAttribute(BreezeObject.ATTR_PROBABILITY, event.getProbability().toString());
		}
	}

	private void writeNode(BreezeNode node, Element archElement) {
		Element nodeElement = archElement.addElement(BreezeObject.TYPE_NODE);
		nodeElement.addAttribute(BreezeObject.ATTR_ID, node.getId());
		nodeElement.addAttribute(BreezeObject.ATTR_NAME, node.getName());

		// write attributes
		for (BreezeMode mode : node.getModeList()) {
			Element modeElement = nodeElement.addElement(BreezeObject.TYPE_ATTRIBUTE);
			modeElement.addAttribute(BreezeObject.ATTR_ID, mode.getId());
			modeElement.addAttribute(BreezeObject.ATTR_KEY, BreezeObject.TYPE_MODE);
			modeElement.addAttribute(BreezeObject.ATTR_VALUE, mode.getType());

			// write safety Attribute
			Map<String, Object> prop = mode.getProperties();
			if (prop != null) {
				for (String safetyAttr : prop.keySet())
					modeElement.addAttribute(safetyAttr, prop.get(safetyAttr).toString());
			}
		}

		List<BreezeModeTransition> list = node.getTransition();
		Map<String, List<BreezeModeTransition>> portTransitionMap = new HashMap<String, List<BreezeModeTransition>>();
		List<BreezeModeTransition> selfTransitionList = new ArrayList<BreezeModeTransition>();
		// classify transition according to port of transition
		for (BreezeModeTransition bmt : list) {
			if (bmt.getPort() != null) {
				List<BreezeModeTransition> l = portTransitionMap.get(bmt.getPort().getId());
				if (l == null) {
					l = new LinkedList<BreezeModeTransition>();
				}
				l.add(bmt);
				portTransitionMap.put(bmt.getPort().getId(), l);
			} else {
				// write safety specification not attach to port
				selfTransitionList.add(bmt);
			}
		}

		if (selfTransitionList.size() > 0)
			this.writeSafetySpecification(selfTransitionList, nodeElement);

		Map<String, BreezePort> portMap = node.getPortMap();
		for (String portId : portMap.keySet()) {
			List<BreezeModeTransition> l = portTransitionMap.get(portId);
			Element portElement = nodeElement.addElement(BreezeObject.TYPE_PORT);
			BreezePort port = node.getPortMap().get(portId);
			if (port != null) {
				portElement.addAttribute(BreezeObject.ATTR_ID, port.getId());
				portElement.addAttribute(BreezeObject.ATTR_TYPE, port.getType());
				portElement.addAttribute(BreezeObject.ATTR_NAME, "" + port.getProperty(BreezeObject.ATTR_NAME).toString());
			}

			// port safety specification
			if (l != null) {
				this.writeSafetySpecification(l, portElement);
			}
		}
	}

	private void writeSafetySpecification(List<BreezeModeTransition> transitionList, Element element) {
		Element speciElement = element.addElement(BreezeObject.TYPE_SAFATY_SPECIFICATION);
		for (BreezeModeTransition bmt : transitionList) {
			Element tranElement = speciElement.addElement(BreezeObject.TYPE_TRANSITION);
			Element src = tranElement.addElement(BreezeObject.TYPE_MODE);
			src.addAttribute(BreezeObject.ATTR_NAME, BreezeObject.ATTR_SOURCE);
			src.addAttribute(BreezeObject.ATTR_REF, bmt.getSourceState().getId());

			Element tgr = tranElement.addElement(BreezeObject.TYPE_TRIGGER);
			tgr.addAttribute(BreezeObject.ATTR_TYPE, bmt.getTriggerType());

			Element evt = tgr.addElement(BreezeObject.TYPE_EVENT);
			evt.addAttribute(BreezeObject.ATTR_REF, bmt.getTrigger().getId());
			evt.addAttribute(BreezeObject.ATTR_NAME, bmt.getTrigger().getId());

			Element tgt = tranElement.addElement(BreezeObject.TYPE_MODE);
			tgt.addAttribute(BreezeObject.ATTR_NAME, BreezeObject.ATTR_TARGET);
			tgt.addAttribute(BreezeObject.ATTR_REF, bmt.getTargetState().getId());
		}
	}

}
