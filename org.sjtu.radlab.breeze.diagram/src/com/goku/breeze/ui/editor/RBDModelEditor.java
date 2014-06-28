package com.goku.breeze.ui.editor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.goku.breeze.common.ListenerExtractPortName;
import com.goku.breeze.common.ListenerExtractSafetyAttr;
import com.goku.breeze.compiler.event.ParseListener;
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
import com.goku.breeze.ui.console.ConsoleFactory;
import com.goku.breeze.ui.console.ConsoleUtil;
import com.goku.breeze.ui.editor.support.ActionAddModeRBD;
import com.goku.breeze.ui.editor.support.ActionNodeAddTransitionRBD;
import com.goku.breeze.ui.editor.support.ActionNodeRmTransitionRBD;
import com.goku.breeze.ui.editor.support.ActionRmModeRBD;
import com.goku.breeze.ui.editor.support.ModeComboSupportRBD;
import com.goku.breeze.ui.editor.support.ModeTableContentProvider;
import com.goku.breeze.ui.editor.support.ModeTableLabelProvider;
import com.goku.breeze.ui.editor.support.ModeTextSupportRBD;
import com.goku.breeze.ui.editor.support.TransitionComboSupportRBD;
import com.goku.breeze.ui.editor.support.TransitionTableContentProvider;
import com.goku.breeze.ui.editor.support.TransitionTableLabelProvider;

public class RBDModelEditor extends ScrolledComposite {
	class ContentProvider implements IStructuredContentProvider {
		@Override
		public void dispose() {
		}

		@Override
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof List) {
				return ((List) inputElement).toArray();
			} else {
				return new Object[0];
			}
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	class ContentProvider2 implements IStructuredContentProvider {
		@Override
		public void dispose() {
		}

		@Override
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof List) {
				return ((List) inputElement).toArray();
			} else {
				return new Object[0];
			}
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	//锟斤拷锟斤拷锟斤拷op锟斤拷锟絤odifier
	//锟斤拷锟斤拷锟斤拷prop锟斤拷锟絤odifier
	public static class MyCellModifier implements ICellModifier {
		public static String[] NAMES = { "11" };
		private final TableViewer tv;

		public MyCellModifier(TableViewer tv) {
			this.tv = tv;
		}

		@Override
		public boolean canModify(Object element, String property) {
			return true;
		}

		private int getNameIndex(String name) {
			for (int i = 0; i < NAMES.length; i++) {
				if (NAMES[i].equals(name)) {
					return i;
				}
			}
			return -1;
		}

		@Override
		public Object getValue(Object element, String property) {
			People p = (People) element;
			if (property.equals("name")) {
				return new Integer(this.getNameIndex(p.getName()));
			} else if (property.equals("prop")) {
				return p.getProp();
			}
			throw new RuntimeException("error column name : " + property);
		}

		@Override
		public void modify(Object element, String property, Object value) {
			TableItem item = (TableItem) element;
			People p = (People) item.getData();
			if (property.equals("name")) {
				Integer comboIndex = (Integer) value;
				if (comboIndex.intValue() == -1) {
					return;
				}
				String newName = NAMES[comboIndex.intValue()];
				p.setName(newName);
			} else if (property.equals("prop")) {
				String newValue = (String) value;
				if (newValue.equals("")) {
					return;
				}
				p.setProp(newValue);
				//锟节此斤拷锟睫改达拷锟斤拷xml锟斤拷锟斤拷
				modifyXMLFile(p.getId(), newValue);

				idGen();
				idGenb1();
				idGenb2();
				idGenb3();
				idGenp();

			} else {
				throw new RuntimeException("锟斤拷锟斤拷锟斤拷锟斤拷:" + property);
			}
			this.tv.update(p, null);
		}

	}

	static class MyCellModifier2 implements ICellModifier {
		public static String[] NAMES2 = { "锟斤拷锟斤拷", "锟斤拷锟斤拷", "小锟斤拷", "锟戒花" };
		public static String[] NAMESLIST = {};
		private final TableViewer tv;

		public MyCellModifier2(TableViewer tv) {
			this.tv = tv;
		}

		@Override
		public boolean canModify(Object element, String property) {
			return true;
		}

		private int getNameIndex(String name) {
			for (int i = 0; i < NAMES2.length; i++) {
				if (NAMES2[i].equals(name)) {
					return i;
				}
			}
			return -1;
		}

		@Override
		public Object getValue(Object element, String property) {
			People2 p = (People2) element;
			if (property.equals("name1")) {
				return new Integer(this.getNameIndex(p.getName1()));
			} else if (property.equals("op")) {
				return new Boolean(p.getOp().equals("and"));
			} else if (property.equals("name2")) {
				return new Integer(this.getNameIndex(p.getName2()));
			}
			throw new RuntimeException("error column name : " + property);
		}

		@Override
		public void modify(Object element, String property, Object value) {
			TableItem item = (TableItem) element;
			People2 p = (People2) item.getData();
			//            System.out.println("aaaa");
			if (property.equals("name1")) {
				Integer comboIndex = (Integer) value;
				if (comboIndex.intValue() == -1) {
					return;
				}
				String newName = NAMES2[comboIndex.intValue()];
				p.setName1(newName);
			} else if (property.equals("op")) {
				Boolean newValue = (Boolean) value;
				//                System.out.println(newValue);
				if (newValue.booleanValue()) {
					p.setOp("and");
				} else {
					p.setOp("or");
				}
			} else if (property.equals("name2")) {
				Integer comboIndex = (Integer) value;
				if (comboIndex.intValue() == -1) {
					return;
				}
				String newName = NAMES2[comboIndex.intValue()];
				p.setName2(newName);
			} else {
				throw new RuntimeException("锟斤拷锟斤拷锟斤拷锟斤拷:" + property);
			}
			this.tv.update(p, null);
			for (int i = 0; i < count; ++i) {
				if (RBDid[i] == p.getBranchId()) {
					RBDname1[i] = p.getName1();
					RBDop[i] = p.getOp();
					RBDname2[i] = p.getName2();
				}
			}
			//            for(int i=0;i<count;++i)
			//            {
			//            	System.out.println(RBDid[i]+"\t"+RBDname1[i] +"\t"+RBDop[i]+"\t"+RBDname2[i]);
			//            }
		}

	}

	static class People {
		public static List getPeople() {
			List list = new ArrayList();
			ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "RBD enddd");
			int n = 30, i = 0;
			Set<Map.Entry<String, String>> set = idmap.entrySet();
			Set<Map.Entry<String, String>> setp = idmapp.entrySet();
			int nodeSum = 0;
			Iterator<Map.Entry<String, String>> itp = setp.iterator();
			for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
				i = i + 1;
				itp.hasNext();
				ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "RBD enddddd11");
				Map.Entry<String, String> entry = it.next();
				ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "RBD enddddd22");
				Map.Entry<String, String> entryp = itp.next();
				ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "RBD enddddd33");

				ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "\n" + entry.getKey() + "  \t" + entry.getValue() + "\n");
				nodeSum = nodeSum + 1;
				ConsoleUtil
						.printMessage(ConsoleFactory.getConsole(), "\np" + entryp.getKey() + "  \t" + entryp.getValue() + "\n");

				ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "RBD end");

				String id = entry.getKey();
				String name = entry.getValue();
				String prop = entryp.getValue();
				People people = new People(id, name, prop);
				list.add(people);
			}
			return list;
		}

		private String id;
		private String name;
		private String prop;

		public People(String id, String name, String prop) {
			this.id = id;
			this.name = name;
			this.prop = prop;
		}

		public String getId() {
			return this.id;
		}

		public String getName() {
			return this.name;
		}

		public String getProp() {
			return this.prop;
		}

		public void setId(String id) {
			this.id = id;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setProp(String prop) {
			this.prop = prop;
		}
	}

	//    static List bb = People2.getPeople();
	static class People2 {
		public static List getPeople() {
			List list = new ArrayList();
			//  ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "RBD enddd");
			int n = 30, i = 0;
			Set<Map.Entry<String, String>> setb1 = idmapb1.entrySet();
			Set<Map.Entry<String, String>> setb2 = idmapb2.entrySet();
			Set<Map.Entry<String, String>> setb3 = idmapb3.entrySet();
			int nodeSum = 0;
			Iterator<Map.Entry<String, String>> itb2 = setb2.iterator();
			Iterator<Map.Entry<String, String>> itb3 = setb3.iterator();
			for (Iterator<Map.Entry<String, String>> itb1 = setb1.iterator(); itb1.hasNext();) {
				i = i + 1;
				itb2.hasNext();
				itb3.hasNext();
				Map.Entry<String, String> entryb1 = itb1.next();
				Map.Entry<String, String> entryb2 = itb2.next();
				Map.Entry<String, String> entryb3 = itb3.next();
				// ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "\n"+entry.getKey() + "  \t" + entry.getValue()+"\n");    
				nodeSum = nodeSum + 1;
				// ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "\np"+entryp.getKey() + "  \t" + entryp.getValue()+"\n");    

				//ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "RBD end");

				String id = entryb1.getKey();
				String name1 = entryb1.getValue();
				String name2 = entryb3.getValue();
				//   String op = "op";
				String op = entryb2.getValue();
				People2 people = new People2(id, name1, op, name2);

				RBDid[count] = id;
				RBDname1[count] = name1;
				RBDop[count] = op;
				RBDname2[count] = name2;
				if (cc == 0) {
					count = count + 1;
				}
				list.add(people);
			}
			cc = 1;
			return list;
		}

		private String branchId;
		private String name1;
		private String name2;
		private String op;

		public People2(String branchId, String name1, String op, String name2) {
			this.branchId = branchId;
			this.name1 = name1;
			this.op = op;
			this.name2 = name2;
		}

		public String getBranchId() {
			return this.branchId;
		}

		public String getName1() {
			return this.name1;
		}

		public String getName2() {
			return this.name2;
		}

		public String getOp() {
			return this.op;
		}

		public void setBranchId(String branchId) {
			this.branchId = branchId;
		}

		public void setName1(String name1) {
			this.name1 = name1;
		}

		public void setName2(String name2) {
			this.name2 = name2;
		}

		public void setOp(String op) {
			this.op = op;
		}
	}

	static class Sorter extends ViewerSorter {
		private static final int ID = 1;
		public static final Sorter ID_ASC = new Sorter(ID);
		public static final Sorter ID_DESC = new Sorter(-ID);

		private static final int NAME = 2;
		public static final Sorter NAME_ASC = new Sorter(NAME);
		public static final Sorter NAME_DESC = new Sorter(-NAME);
		private static final int PROP = 3;
		public static final Sorter PROP_ASC = new Sorter(PROP);
		public static final Sorter PROP_DESC = new Sorter(-PROP);

		private final int sortType;

		private Sorter(int sortType) {
			this.sortType = sortType;
		}

		@Override
		public int compare(Viewer viewer, Object e1, Object e2) {
			People p1 = (People) e1;
			People p2 = (People) e2;
			switch (this.sortType) {
			case ID: {
				String l1 = p1.getId();
				String l2 = p2.getId();
				return l1.compareTo(l2);
			}
			case -ID: {
				String l1 = p1.getId();
				String l2 = p2.getId();
				return l2.compareTo(l1);
			}
			case NAME: {
				String s1 = p1.getName();
				String s2 = p2.getName();
				return s1.compareTo(s2);
			}
			case -NAME: {
				String s1 = p1.getName();
				String s2 = p2.getName();
				return s2.compareTo(s1);
			}
			case PROP: {
				String s1 = p1.getProp();
				String s2 = p2.getProp();
				return s1.compareTo(s2);
			}
			case -PROP: {
				String s1 = p1.getProp();
				String s2 = p2.getProp();
				return s2.compareTo(s1);
			}
			}
			return 0;
		}
	}

	static class Sorter2 extends ViewerSorter {
		private static final int ID = 1;
		public static final Sorter2 ID_ASC = new Sorter2(ID);
		public static final Sorter2 ID_DESC = new Sorter2(-ID);
		private static final int NAME1 = 2;

		public static final Sorter2 NAME1_ASC = new Sorter2(NAME1);
		public static final Sorter2 NAME1_DESC = new Sorter2(-NAME1);
		private static final int NAME2 = 4;
		public static final Sorter2 NAME2_ASC = new Sorter2(NAME2);
		public static final Sorter2 NAME2_DESC = new Sorter2(-NAME2);
		private static final int OP = 3;
		public static final Sorter2 OP_ASC = new Sorter2(OP);
		public static final Sorter2 OP_DESC = new Sorter2(-OP);

		private final int sortType;

		private Sorter2(int sortType) {
			this.sortType = sortType;
		}

		@Override
		public int compare(Viewer viewer, Object e1, Object e2) {
			People2 p1 = (People2) e1;
			People2 p2 = (People2) e2;
			switch (this.sortType) {
			case ID: {
				String l1 = p1.getBranchId();
				String l2 = p2.getBranchId();
				return l1.compareTo(l2);
			}
			case -ID: {
				String l1 = p1.getBranchId();
				String l2 = p2.getBranchId();
				return l2.compareTo(l1);
			}
			case NAME1: {
				String s1 = p1.getName1();
				String s2 = p2.getName1();
				return s1.compareTo(s2);
			}
			case -NAME1: {
				String s1 = p1.getName1();
				String s2 = p2.getName1();
				return s2.compareTo(s1);
			}
			case OP: {
				String s1 = p1.getOp();
				String s2 = p2.getOp();
				return s1.compareTo(s2);
			}
			case -OP: {
				String s1 = p1.getOp();
				String s2 = p2.getOp();
				return s2.compareTo(s1);
			}
			case NAME2: {
				String i1 = p1.getName2();
				String i2 = p2.getName2();
				return i1.compareTo(i2);
			}
			case -NAME2: {
				String i1 = p1.getName2();
				String i2 = p2.getName2();
				return i2.compareTo(i1);
			}
			}
			return 0;
		}
	}

	class TableLabelProvider extends LabelProvider implements ITableLabelProvider {
		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			if (element instanceof People) {
				People p = (People) element;
				if (columnIndex == 0) {
					return p.getId().toString();
				} else if (columnIndex == 1) {
					return p.getName();
				} else if (columnIndex == 2) {
					return p.getProp();
				}
			}
			return null;
		}
	}

	class TableLabelProvider2 extends LabelProvider implements ITableLabelProvider {
		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			if (element instanceof People2) {
				People2 p = (People2) element;
				if (columnIndex == 0) {
					return p.getBranchId().toString();
				} else if (columnIndex == 1) {
					return p.getName1();
				} else if (columnIndex == 2) {
					return p.getOp();
				} else if (columnIndex == 3) {
					return p.getName2();
				}
			}
			return null;
		}
	}

	private static Map<String, BreezeObject> allObjects;
	static int cc = 0;
	static int count = 0;
	//锟斤拷锟斤拷锟角讹拷xml锟斤拷锟絥ode锟斤拷id锟斤拷name锟斤拷map锟侥达拷锟斤拷
	private static Map<String, String> idmap = new HashMap<String, String>();
	private static Map<String, String> idmapb1 = new HashMap<String, String>();
	private static Map<String, String> idmapb2 = new HashMap<String, String>();
	private static Map<String, String> idmapb3 = new HashMap<String, String>();
	//锟斤拷锟斤拷锟角讹拷xml锟斤拷锟絥ode锟斤拷id锟斤拷probability锟斤拷map锟侥达拷锟斤拷
	private static Map<String, String> idmapp = new HashMap<String, String>();
	static String[] RBDid = new String[100];
	static String[] RBDname1 = new String[100];

	static String[] RBDname2 = new String[100];
	static String[] RBDop = new String[100];

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

	public static void idGenb1() {
		int s = 0;
		File file = new File(saFilePath);
		//System.out.println("11");
		//System.out.println(saFilePath);
		Map<String, String> mapEleList = new HashMap<String, String>();
		try {
			mapEleList = parseXmlb1(file);
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

	public static void idGenb2() {
		int s = 0;
		File file = new File(saFilePath);
		//System.out.println(saFilePath);
		Map<String, String> mapEleList = new HashMap<String, String>();
		try {
			mapEleList = parseXmlb2(file);
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

	public static void idGenb3() {
		int s = 0;
		File file = new File(saFilePath);
		//System.out.println("11");
		//System.out.println(saFilePath);
		Map<String, String> mapEleList = new HashMap<String, String>();
		try {
			mapEleList = parseXmlb3(file);
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

	static void iterb1(Element element) {
		int i = 0;
		// System.out.println(element);

		List<Element> elementList = element.elements();
		Iterator<Element> it = elementList.iterator();
		while (it.hasNext()) {
			if (i == 0) {
				Element childElement = it.next();

				if (childElement.getName() == "ArchFailure") {
					idmapb1.put(element.attributeValue("name"), childElement.attributeValue("ArchFailureEventname"));
				}
				if (childElement.getName() == "FailureEventListofArch") {
					idmapb1.put(element.attributeValue("name"), childElement.attributeValue("name"));
					iterb1(childElement);
				}
			} else {
				Element childElement = it.next();
				if (childElement.getName() == "FailureEventListofArch") {
					iterb1(childElement);
				}
			}
			i = i + 1;
		}

	}

	static void iterb2(Element element) {
		// System.out.println(element);

		List<Element> elementList = element.elements();
		Iterator<Element> it = elementList.iterator();
		while (it.hasNext()) {
			Element childElement = it.next();
			iterb2(childElement);
		}
	}

	static void iterb3(Element element) {
		int i = 0;
		// System.out.println(element);
		idmapb2.put(element.attributeValue("name"), element.attributeValue("OperationType"));
		List<Element> elementList = element.elements();
		Iterator<Element> it = elementList.iterator();
		while (it.hasNext()) {
			if (i == 1) {
				Element childElement = it.next();

				if (childElement.getName() == "ArchFailure") {
					idmapb3.put(element.attributeValue("name"), childElement.attributeValue("ArchFailureEventname"));
				}
				if (childElement.getName() == "FailureEventListofArch") {
					idmapb3.put(element.attributeValue("name"), childElement.attributeValue("name"));
					iterb3(childElement);
				}
			} else {
				Element childElement = it.next();
				if (childElement.getName() == "FailureEventListofArch") {
					iterb3(childElement);
				}
			}
			i = i + 1;
		}

	}

	public static void modifyXMLFile(String id, String value) {
		int returnValue = 0;
		try {
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(new File(saFilePath));
			Element rootElm = document.getRootElement();
			List list = rootElm.elements("arch");
			Iterator iter = list.iterator();
			while (iter.hasNext()) {
				Element elmm = (Element) iter.next();
				List listm = elmm.elements("node");
				//				System.out.println("ss" + elmm.getText());
				Iterator iterm = listm.iterator();
				while (iterm.hasNext()) {
					Element elm = (Element) iterm.next();
					//					System.out.println("tt" + elm.getText());
					//  Iterator iterator = elm.elementIterator("ID"); 
					Attribute attribute = elm.attribute("ID");
					// while (iterator.hasNext()) {
					//					System.out.println("ee" + attribute.getValue() + "set :" + id + "\t" + value);
					//	  Element titleElement = (Element) iterator.next();  
					//	  if (titleElement.getText().equals(id)) { 
					if (attribute.getValue().toString().equalsIgnoreCase(id)) {
						Attribute attribute2 = elm.attribute("probability");
						attribute2.setValue(value);
					} else System.out.println("no");
					//	  }
				}

			}
			try {
				XMLWriter writer = new XMLWriter(new FileWriter(new File(saFilePath)));
				writer.write(document);
				writer.close();
				returnValue = 1;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static Map parseXml(File filePath) throws DocumentException {

		Map<String, String> mapEle = new HashMap<String, String>();
		SAXReader reader = new SAXReader();

		Document document = reader.read(filePath);// 锟斤拷取xml锟侥碉拷
		Element root = document.getRootElement();// 锟矫碉拷xml锟侥碉拷锟斤拷诘锟皆拷兀锟斤拷锟斤拷锟斤拷喜锟斤拷"<xml>"
		//System.out.println("锟斤拷锟斤拷锟节碉拷元锟截碉拷锟斤拷锟斤拷===" + root.getName());
		Element parentElement = root.element("arch");

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

	public static Map parseXmlb1(File filePath) throws DocumentException {

		Map<String, String> mapEle = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		Document document = reader.read(filePath);// 锟斤拷取xml锟侥碉拷
		Element root = document.getRootElement();// 锟矫碉拷xml锟侥碉拷锟斤拷诘锟皆拷兀锟斤拷锟斤拷锟斤拷喜锟斤拷"<xml>"
		//System.out.println("锟斤拷锟斤拷锟节碉拷元锟截碉拷锟斤拷锟斤拷===" + root.getName());
		Element parentElement = root.element("RBDFailureAnalysis");
		Element parent2Element = parentElement.element("ArchFailureList");
		// 循锟斤拷锟斤拷锟斤拷锟斤拷锟节碉拷锟斤拷锟斤拷锟斤拷咏诘锟�		
		List<Element> elementList = parent2Element.elements();
		Iterator<Element> it = elementList.iterator();
		while (it.hasNext()) {
			Element element = it.next();
			iterb1(element);
		}

		return mapEle;
	}

	public static Map parseXmlb2(File filePath) throws DocumentException {
		Map<String, String> mapEle = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		Document document = reader.read(filePath);// 锟斤拷取xml锟侥碉拷
		Element root = document.getRootElement();// 锟矫碉拷xml锟侥碉拷锟斤拷诘锟皆拷兀锟斤拷锟斤拷锟斤拷喜锟斤拷"<xml>"
		//System.out.println("锟斤拷锟斤拷锟节碉拷元锟截碉拷锟斤拷锟斤拷===" + root.getName());
		Element parentElement = root.element("RBDFailureAnalysis");
		Element parent2Element = parentElement.element("ArchFailureList");
		iterb2(parent2Element);
		// 循锟斤拷锟斤拷锟斤拷锟斤拷锟节碉拷锟斤拷锟斤拷锟斤拷咏诘锟�		
		List<Element> elementList = parent2Element.elements();
		Iterator<Element> it = elementList.iterator();
		while (it.hasNext()) {
			Element element = it.next();
			iterb2(element);
		}

		return mapEle;
	}

	public static Map parseXmlb3(File filePath) throws DocumentException {

		Map<String, String> mapEle = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		Document document = reader.read(filePath);// 锟斤拷取xml锟侥碉拷
		Element root = document.getRootElement();// 锟矫碉拷xml锟侥碉拷锟斤拷诘锟皆拷兀锟斤拷锟斤拷锟斤拷喜锟斤拷"<xml>"
		//System.out.println("锟斤拷锟斤拷锟节碉拷元锟截碉拷锟斤拷锟斤拷===" + root.getName());
		Element parentElement = root.element("RBDFailureAnalysis");
		Element parent2Element = parentElement.element("ArchFailureList");
		// 循锟斤拷锟斤拷锟斤拷锟斤拷锟节碉拷锟斤拷锟斤拷锟斤拷咏诘锟�		
		List<Element> elementList = parent2Element.elements();
		Iterator<Element> it = elementList.iterator();
		while (it.hasNext()) {
			Element element = it.next();
			iterb3(element);
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

	private BreezeXMLParser bxp;

	private final Color colorBlack = new Color(Display.getCurrent(), 0, 0, 0);

	private final Color colorBlue = new Color(Display.getCurrent(), 0, 0, 255);

	private final Color colorRed = new Color(Display.getCurrent(), 255, 0, 0);

	private final Color colorWhite = new Color(Display.getCurrent(), 255, 255, 255);

	private final Combo componentSelect = null;

	private Composite composite;

	private Table eventTable, modeTable, transitionTable;

	//锟斤拷锟铰和达拷印锟结构锟睫癸拷
	//锟斤拷锟斤拷锟斤拷op锟斤拷锟絤odifier

	private TableViewer eventTableViewer, modeTableViewer, transitionTableViewer;

	private final String fileNameWithoutExt;

	private Table methodTable;

	private TableViewer methodTableViewer;

	private Composite panel = null;

	private final RBDEditor parentEditor;

	public RBDModelEditor(String saFilePath, String fileNameWithoutExt, Composite parent, RBDEditor rbdEditor) {
		super(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		// TODO Auto-generated constructor stub
		this.fileNameWithoutExt = fileNameWithoutExt;
		this.parentEditor = rbdEditor;
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

	//锟斤拷锟斤拷锟角讹拷xml锟斤拷锟絥ode锟斤拷id锟斤拷name锟斤拷map锟侥达拷锟斤拷

	//锟斤拷锟斤拷锟角讹拷xml锟斤拷山峁癸拷锟絙ranch锟斤拷name1锟斤拷map锟侥达拷锟斤拷

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

	//锟斤拷锟斤拷锟角讹拷xml锟斤拷锟絥ode锟斤拷id锟斤拷probability锟斤拷map锟侥达拷锟斤拷
	private void drawPanelForNode(BreezeNode node) {
		this.drawPanelCommonsBefore();

		// section "Mode"
		this.drawLabel("Probablity");
		this.modeTable = this.drawTable();
		Object[] ret = this.drawTableViewer(this.modeTable, new String[] { "ID", "Probablity" }, new int[] { 200, 200 });
		this.modeTableViewer = (TableViewer) ret[0];
		this.modeTableViewer.setContentProvider(new ModeTableContentProvider());

		this.modeTableViewer.setLabelProvider(new ModeTableLabelProvider(null, null));

		this.modeTableViewer.setInput(node);

		TableViewerColumn[] modeCol = (TableViewerColumn[]) ret[1];
		modeCol[1].setEditingSupport(new ModeComboSupportRBD(this, this.modeTableViewer));

		Button btnAddMode = this.drawButton("Add");
		Button btnRmMode = this.drawButton("Delete");

		this.drawDivider();

		// section "transition"
		this.drawLabel("Transition");
		this.transitionTable = this.drawTable();
		ret = this.drawTableViewer(this.transitionTable, new String[] { "Source", "Target", "Trigger" }, new int[] { 180, 180,
				180 });
		this.transitionTableViewer = (TableViewer) ret[0];

		/*
	 * 
	 */

		/*
	 * 
	 */

		this.transitionTableViewer.setContentProvider(new TransitionTableContentProvider(
				TransitionTableContentProvider.TYPE_NODE, null));
		this.transitionTableViewer.setLabelProvider(new TransitionTableLabelProvider());
		this.transitionTableViewer.setInput(node);
		TableViewerColumn[] cols = (TableViewerColumn[]) ret[1];
		TransitionComboSupportRBD[] es = new TransitionComboSupportRBD[] {
				new TransitionComboSupportRBD(this, this.transitionTableViewer, 0, node),
				new TransitionComboSupportRBD(this, this.transitionTableViewer, 1, node),
				new TransitionComboSupportRBD(this, this.transitionTableViewer, 2, node) };
		cols[0].setEditingSupport(es[0]);
		cols[1].setEditingSupport(es[1]);
		cols[2].setEditingSupport(es[2]);

		Button btnAddTran = this.drawButton("Add");
		Button btnRmTran = this.drawButton("Delete");
		btnAddTran.addSelectionListener(new ActionNodeAddTransitionRBD(this, node, this.transitionTableViewer));
		btnRmTran.addSelectionListener(new ActionNodeRmTransitionRBD(this, node, this.transitionTableViewer));
		modeCol[0].setEditingSupport(new ModeTextSupportRBD(this, this.modeTableViewer, 0,
				new ColumnViewer[] { this.transitionTableViewer }, new TransitionComboSupportRBD[] { es[0], es[1] }));
		btnRmMode.addSelectionListener(new ActionRmModeRBD(node, this.modeTableViewer,
				new TableViewer[] { this.transitionTableViewer }));
		btnAddMode.addSelectionListener(new ActionAddModeRBD(this, node, this.modeTableViewer, new EditingSupport[] { es[0],
				es[1] }));

		this.drawPanelCommonsAfter(800);
	}

	private Table drawTable() {
		Table tb = new Table(this.panel, SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.BORDER);
		tb.setLayoutData(new GridData(SWT.BEGINNING, SWT.FILL, true, true, 1, 10));
		tb.setHeaderVisible(true);
		tb.setLinesVisible(true);
		return tb;
	}

	//锟斤拷锟斤拷锟角讹拷xml锟斤拷山峁癸拷锟絙ranch锟斤拷name1锟斤拷map锟侥达拷锟斤拷

	//锟斤拷锟斤拷锟角讹拷xml锟斤拷山峁癸拷锟絙ranch锟斤拷name2锟斤拷map锟侥达拷锟斤拷

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
		idmapb1.clear();
		idmapb2.clear();
		idmapb3.clear();
		idGen();
		idGenb1();
		idGenb2();
		idGenb3();
		idGenp();
		for (int i = 0; i < 100; ++i) {
			RBDid[i] = null;
			RBDname1[i] = null;
			RBDop[i] = null;
			RBDname2[i] = null;
		}
		count = 0;
		cc = 0;

		BreezeArch topArch = null;
		this.allObjects = new HashMap<String, BreezeObject>();

		if (saFilePath != null) {

			this.bxp = new BreezeXMLParser(new File(saFilePath), new ParseListener[] { new ListenerExtractSafetyAttr(),
					new ListenerExtractPortName() }, null);

			topArch = this.bxp.getTopArch();
			if (topArch != null) this.extractAllBreezeObject(topArch, this.allObjects);
			this.allObjects.put(topArch.getId(), topArch);

		}

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
		lb0.setText("RBD model configuration\n\n");
		lb0.setLayoutData(new GridData(SWT.BEGINNING, SWT.FILL, true, false, 1, 1));

		this.panel = new Composite(this.composite, SWT.NONE);
		this.panel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		GridLayout panelLayout = new GridLayout();
		panelLayout.numColumns = 1;
		this.panel.setLayout(panelLayout);
		//锟斤拷实锟揭诧拷知锟斤拷锟斤拷锟斤拷锟角革拷什么锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷写锟斤拷 锟斤拷貌锟斤拷锟斤拷锟脚比较猴拷

		Label lb7 = new Label(this.panel, SWT.None);
		lb7.setText("Component reliability");

		//		Label lb1 = new Label(this.panel, SWT.NONE);
		//		lb1.setText("");
		//		Table t = new Table(this.panel,SWT.BORDER);
		//		TableItem item1 = new TableItem(t, SWT.NONE); 
		//		item1.setText(0, "Hello World"); 

		//		Table table = new Table(this.panel, SWT.FULL_SELECTION | SWT.HIDE_SELECTION);
		//	       table.setHeaderVisible(true);
		//	       table.setLinesVisible(true);
		//	       TableColumn column1 = new TableColumn(table, SWT.NONE);
		//	       TableColumn column2 = new TableColumn(table, SWT.NONE);
		//
		//	       TableItem item = new TableItem(table, SWT.NONE);
		//	       item.setText(new String[] {  "1", ""});
		//	       column1.pack();
		//	       column2.pack();
		//bb
		//	       final TableViewer viewer = new TableViewer(this.panel, SWT.BORDER | SWT.FULL_SELECTION);
		//	       TableLayout layout2 = new TableLayout(); 
		//	       layout2.addColumnData(new ColumnWeightData(80, true)); 
		//	       layout2.addColumnData(new ColumnWeightData(80, true)); 
		//	       layout2.addColumnData(new ColumnWeightData(80, true)); 
		//	       viewer.getTable().setLayout(layout2); 
		//	       viewer.getTable().setLinesVisible(true); 
		//	       viewer.getTable().setHeaderVisible(true);
		//	       
		//	       TableColumn column1 = new TableColumn(viewer.getTable(), SWT.CENTER); 
		//	       column1.setText("Node"); 
		//	       TableColumn column2 = new TableColumn(viewer.getTable(), SWT.CENTER); 
		//	       column2.setText("Probability"); 
		//	       TableColumn column3 = new TableColumn(viewer.getTable(), SWT.CENTER); 
		//	       column3.setText("Data");
		//aa

		//锟斤拷锟斤拷锟角达拷印id锟侥达拷锟斤拷

		ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "RBD start");
		ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "\nnode ID" + " \t\t\t" + "node name\n");
		idGen();
		idGenb1();
		idGenb2();
		idGenb3();
		idGenp();
		Set<Map.Entry<String, String>> set = idmap.entrySet();
		int nodeSum = 0;
		for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
			Map.Entry<String, String> entry = it.next();

			ConsoleUtil.printMessage(ConsoleFactory.getConsole(), entry.getKey() + "  \t" + entry.getValue() + "\n");
			nodeSum = nodeSum + 1;
		}
		ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "RBD end");

		for (Iterator<Map.Entry<String, String>> iter = set.iterator(); iter.hasNext();) {
			int ii = 1;
			if (ii == 1) {
				Map.Entry<String, String> entry1 = iter.next();
				BreezeObject obj = this.allObjects.get(entry1.getKey());
				//if (obj instanceof BreezeNode) this.drawPanelForNode((BreezeNode)obj);
				ii = 0;
			}
		}
		Table table;
		final TableViewer tableViewer = new TableViewer(this.panel, SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);

		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(97, 79, 373, 154);

		final TableColumn newColumnTableColumn = new TableColumn(table, SWT.NONE);
		newColumnTableColumn.setWidth(200);
		newColumnTableColumn.setText("ID");
		//锟斤拷锟斤拷锟铰硷拷锟斤拷锟斤拷锟斤拷
		newColumnTableColumn.addSelectionListener(new SelectionAdapter() {
			boolean asc = true;

			@Override
			public void widgetSelected(SelectionEvent e) {
				tableViewer.setSorter(this.asc ? Sorter.ID_ASC : Sorter.ID_DESC);
				this.asc = !this.asc;
			}
		});

		final TableColumn newColumnTableColumn_1 = new TableColumn(table, SWT.NONE);
		newColumnTableColumn_1.setWidth(100);
		newColumnTableColumn_1.setText("Name");
		//			        锟斤拷锟斤拷锟铰硷拷锟斤拷锟斤拷锟斤拷
		newColumnTableColumn_1.addSelectionListener(new SelectionAdapter() {
			boolean asc = true;

			@Override
			public void widgetSelected(SelectionEvent e) {
				tableViewer.setSorter(this.asc ? Sorter.NAME_ASC : Sorter.NAME_DESC);
				this.asc = !this.asc;
			}
		});

		final TableColumn newColumnTableColumn_2 = new TableColumn(table, SWT.NONE);
		newColumnTableColumn_2.setWidth(100);
		newColumnTableColumn_2.setText("Reliability");
		//			        锟斤拷锟斤拷锟铰硷拷锟斤拷锟斤拷锟斤拷
		newColumnTableColumn_2.addSelectionListener(new SelectionAdapter() {
			boolean asc = true;

			@Override
			public void widgetSelected(SelectionEvent e) {
				tableViewer.setSorter(this.asc ? Sorter.PROP_ASC : Sorter.PROP_DESC);
				this.asc = !this.asc;
			}
		});
		//锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
		tableViewer.setContentProvider(new ContentProvider());
		//锟斤拷锟矫憋拷签锟斤拷
		tableViewer.setLabelProvider(new TableLabelProvider());
		//锟斤拷锟斤拷菁锟斤拷细锟絫ableView
		tableViewer.setInput(People.getPeople());
		//锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟酵憋拷签锟斤拷锟斤拷锟杰达拷setInput锟矫碉拷锟斤拷锟斤拷菁锟斤拷戏纸锟斤拷锟斤拷示锟斤拷锟斤拷锟揭拷锟斤拷锟捷★拷锟斤拷锟斤拷一锟斤拷锟斤拷锟酵碉拷mvc锟斤拷实锟斤拷.

		tableViewer.setColumnProperties(new String[] { "id", "name", "prop" });
		CellEditor[] cellEditor = new CellEditor[3];
		cellEditor[0] = null;
		cellEditor[1] = null;
		//			        cellEditor[1] = new ComboBoxCellEditor(tableViewer.getTable(),MyCellModifier.NAMES,SWT.READ_ONLY);
		cellEditor[2] = new TextCellEditor(tableViewer.getTable());
		//ConsoleUtil.printMessage(ConsoleFactory.getConsole(), cellEditor[2].getValue()+"\n");    

		tableViewer.setCellEditors(cellEditor);
		ICellModifier modifier = new MyCellModifier(tableViewer);
		tableViewer.setCellModifier(modifier);

		//			        shell.open();
		//			        while (!shell.isDisposed()) {
		//			            if (!display.readAndDispatch())
		//			                display.sleep();
		//			        }

		//锟斤拷锟斤拷锟角达拷印id锟侥达拷锟斤拷

		Label lb6 = new Label(this.panel, SWT.None);
		lb6.setText("Component structure");
		//lb6.setLayoutData(new GridData(SWT.BEGINNING, SWT.FILL, true, false, 1, 1));
		//锟斤拷锟斤拷锟角达拷印锟结构锟侥达拷锟斤拷

		Table table2;
		/**
		 * Launch the application
		 * 
		 * @param args
		 */
		final TableViewer tableViewer2 = new TableViewer(this.panel, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL
				| SWT.H_SCROLL);

		table2 = tableViewer2.getTable();
		table2.setLinesVisible(true);
		table2.setHeaderVisible(true);
		table2.setBounds(97, 79, 373, 154);

		final TableColumn rnewColumnTableColumn = new TableColumn(table2, SWT.NONE);
		rnewColumnTableColumn.setWidth(100);
		rnewColumnTableColumn.setText("BranchID");
		//锟斤拷锟斤拷锟铰硷拷锟斤拷锟斤拷锟斤拷
		rnewColumnTableColumn.addSelectionListener(new SelectionAdapter() {
			boolean asc = true;

			@Override
			public void widgetSelected(SelectionEvent e) {
				tableViewer2.setSorter(this.asc ? Sorter2.ID_ASC : Sorter2.ID_DESC);
				this.asc = !this.asc;
			}
		});

		final TableColumn rnewColumnTableColumn_1 = new TableColumn(table2, SWT.NONE);
		rnewColumnTableColumn_1.setWidth(120);
		rnewColumnTableColumn_1.setText("Structure1");
		rnewColumnTableColumn_1.setAlignment(SWT.RIGHT);
		//锟斤拷锟斤拷锟铰硷拷锟斤拷锟斤拷锟斤拷
		rnewColumnTableColumn_1.addSelectionListener(new SelectionAdapter() {
			boolean asc = true;

			@Override
			public void widgetSelected(SelectionEvent e) {
				tableViewer2.setSorter(this.asc ? Sorter2.NAME1_ASC : Sorter2.NAME1_DESC);
				this.asc = !this.asc;
			}
		});

		final TableColumn rnewColumnTableColumn_2 = new TableColumn(table2, SWT.NONE);
		rnewColumnTableColumn_2.setWidth(60);
		rnewColumnTableColumn_2.setText("Type");
		rnewColumnTableColumn_2.setAlignment(SWT.CENTER);
		//			              锟斤拷锟斤拷锟铰硷拷锟斤拷锟斤拷锟斤拷
		rnewColumnTableColumn_2.addSelectionListener(new SelectionAdapter() {
			boolean asc = true;

			@Override
			public void widgetSelected(SelectionEvent e) {
				tableViewer2.setSorter(this.asc ? Sorter2.OP_ASC : Sorter2.OP_DESC);
				this.asc = !this.asc;
			}
		});

		final TableColumn rnewColumnTableColumn_3 = new TableColumn(table2, SWT.NONE);
		rnewColumnTableColumn_3.setWidth(120);
		rnewColumnTableColumn_3.setText("Structure2");
		//			              锟斤拷锟斤拷锟铰硷拷锟斤拷锟斤拷锟斤拷
		rnewColumnTableColumn_3.addSelectionListener(new SelectionAdapter() {
			boolean asc = true;

			@Override
			public void widgetSelected(SelectionEvent e) {
				tableViewer2.setSorter(this.asc ? Sorter2.NAME2_ASC : Sorter2.NAME2_DESC);
				this.asc = !this.asc;
			}
		});
		//			                
		//			                shell.open();
		//锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
		tableViewer2.setContentProvider(new ContentProvider2());
		//锟斤拷锟矫憋拷签锟斤拷
		tableViewer2.setLabelProvider(new TableLabelProvider2());
		//锟斤拷锟斤拷菁锟斤拷细锟絫ableView
		tableViewer2.setInput(People2.getPeople());

		ArrayList<String> al = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			al.add(String.valueOf(i));
		}
		Iterator<String> it = al.iterator();

		//锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟酵憋拷签锟斤拷锟斤拷锟杰达拷setInput锟矫碉拷锟斤拷锟斤拷菁锟斤拷戏纸锟斤拷锟斤拷示锟斤拷锟斤拷锟揭拷锟斤拷锟捷★拷锟斤拷锟斤拷一锟斤拷锟斤拷锟酵碉拷mvc锟斤拷实锟斤拷.
		tableViewer2.setColumnProperties(new String[] { "branchId", "name1", "op", "name2" });

		//锟斤拷锟铰达拷锟斤拷锟斤拷锟节筹拷取锟斤拷锟斤拷锟斤拷锟斤拷锟剿碉拷锟斤拷锟叫憋拷
		String[] NAMES4 = new String[100];
		int i = 0;
		Set<Map.Entry<String, String>> setb1 = idmapb1.entrySet();
		Set<Map.Entry<String, String>> setb3 = idmapb3.entrySet();
		Iterator<Map.Entry<String, String>> itb3 = setb3.iterator();
		for (Iterator<Map.Entry<String, String>> itb1 = setb1.iterator(); itb1.hasNext();) {
			itb3.hasNext();
			Map.Entry<String, String> entryb1 = itb1.next();
			Map.Entry<String, String> entryb3 = itb3.next();

			String name1 = entryb1.getValue();
			String name2 = entryb3.getValue();
			NAMES4[2 * i] = name1;
			NAMES4[2 * i + 1] = name2;
			i = i + 1;
			//   String op = "op";
		}
		NAMES4[2 * i] = "Branch0";
		//锟斤拷锟较达拷锟斤拷锟斤拷锟节筹拷取锟斤拷锟斤拷锟斤拷锟斤拷锟剿碉拷锟斤拷锟叫憋拷
		//锟斤拷锟斤拷锟角革拷锟杰筹拷陋锟斤拷锟街凤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟剿碉拷锟斤拷选锟筋，锟斤拷小锟斤拷锟斤拷锟斤拷锟斤拷
		String[] NAMES5 = new String[i * 2 + 1];
		for (i = 0; i < NAMES5.length; ++i) {
			NAMES5[i] = "";
		}
		String minName4 = "";

		int min4 = 0;
		int j = 0, k = 0;
		boolean flag = true;
		for (i = 0; i < NAMES5.length; ++i) {
			minName4 = "";
			for (j = 0; j < NAMES5.length; ++j) {

				flag = true;
				if (NAMES4[j].compareTo(minName4) > 0) {
					for (k = 0; k < NAMES5.length; ++k) {
						if (j == 2) {
							//			            					  System.out.println("yyyy"+NAMES5[k]+NAMES4[j]);
						}
						if (NAMES5[k].compareTo(NAMES4[j]) == 0) {
							//			            					  System.out.println("xxxx");
							flag = false;
						}
					}
					if (flag == true) {
						minName4 = NAMES4[j];
						min4 = j;
					}
				}
			}
			NAMES5[NAMES5.length - 1 - i] = NAMES4[min4];
		}

		//锟斤拷锟斤拷锟角革拷锟杰筹拷陋锟斤拷锟街凤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟剿碉拷锟斤拷选锟筋，锟斤拷小锟斤拷锟斤拷锟斤拷锟斤拷
		MyCellModifier2.NAMES2 = NAMES5;
		tableViewer2.setColumnProperties(new String[] { "branchId", "name1", "op", "name2" });
		CellEditor[] cellEditor2 = new CellEditor[5];
		cellEditor2[0] = null;
		cellEditor2[1] = new ComboBoxCellEditor(tableViewer2.getTable(), MyCellModifier2.NAMES2, SWT.READ_ONLY);
		cellEditor2[2] = new CheckboxCellEditor(tableViewer2.getTable());
		cellEditor2[3] = new ComboBoxCellEditor(tableViewer2.getTable(), MyCellModifier2.NAMES2, SWT.READ_ONLY);

		tableViewer2.setCellEditors(cellEditor2);
		ICellModifier modifier2 = new MyCellModifier2(tableViewer2);
		tableViewer2.setCellModifier(modifier2);

		//锟斤拷锟斤拷锟角达拷印锟结构锟侥达拷锟斤拷

		Label lb8 = new Label(this.panel, SWT.None);
		lb8.setText(" ");

		final Button button;
		button = new Button(this.panel, SWT.NONE);
		button.setText("Save and Compute RBD reliability");

		final Label lb3 = new Label(this.panel, SWT.NONE);
		PredictReliability rbd = new ReliabilityBlockDiagram();

		double reliability = rbd.getReliability(saFilePath, saFilePath);
		System.out.println("reliability" + reliability);
		//		
		reliability = (double) Math.round(reliability * 1000000) / 1000000;

		String str1;
		NumberFormat nf1 = NumberFormat.getPercentInstance();
		nf1.setMinimumFractionDigits(2);
		str1 = nf1.format(reliability);

		lb3.setText("RBD Analysis for Architecture: System reliability is " + str1
				+ "                                                     ");

		//		lb3.setText("RBD reliability for Arch: N/A"+"                                                ");
		//		lb3.setLayoutData(new GridData(SWT.BEGINNING, SWT.FILL, true, false, 1, 1));
		//		lb3.setLayoutData(new GridData(SWT.BEGINNING,);

		button.addSelectionListener(new SelectionAdapter() {
			//            public void calc()    
			//            {
			//            	Iterator it = bb.listIterator();
			//            	for ( int i=0;i<8;++i)
			//            	{
			//            	System.out.println(it.next());
			//            	}
			//            	modifyXMLFile(p.getId(),newValue);
			//                
			//                System.out.println(p.getId()+"\t"+newValue);
			//      	      	idGen();  
			//      	      	idGenb1();  
			//      	      	idGenb2();  
			//      	      	idGenb3();  
			//      	      	idGenp();

			//            }
			@Override
			public void widgetSelected(SelectionEvent e) {
				//calc();

				idGen();
				idGenb1();
				idGenb2();
				idGenb3();
				idGenp();
				int c = count;

				boolean flag = true;
				int n1 = 0, n2 = 0;
				String str = "";

				for (int i = 0; i < c; ++i) {
					for (int j = 0; j < c; ++j) {
						//            			  System.out.println(RBDname1[i]+RBDname1[j]+flag);
						if (i != j) {
							if ((RBDname1[i].equals(RBDname1[j])) || (RBDname1[i].equals(RBDname2[j]))) {
								n1 = i;
								n2 = j;
								str = RBDname1[i];
								flag = false;
							}
							if ((RBDname2[i].equals(RBDname1[j])) || (RBDname2[i].equals(RBDname2[j]))) {
								n1 = i;
								n2 = j;
								str = RBDname2[i];
								flag = false;
							}
						}
						if (i == j) {
							if ((RBDname1[i].equals(RBDname2[j]))) {
								n1 = i;
								n2 = j;
								str = RBDname2[i];
								flag = false;
							}
						}

					}
				}

				Set<Map.Entry<String, String>> set1 = idmap.entrySet();
				Set<Map.Entry<String, String>> set3 = idmapp.entrySet();

				for (Iterator<Map.Entry<String, String>> itc1 = set1.iterator(); itc1.hasNext();) {
					Map.Entry<String, String> entryc1 = itc1.next();
					//					System.out.println(entryc1.getKey());
					for (Iterator<Map.Entry<String, String>> itc3 = set3.iterator(); itc3.hasNext();) {
						Map.Entry<String, String> entryc3 = itc3.next();
						if (entryc1.getKey().contentEquals(entryc3.getKey())) {
							//						System.out.println(entryc1.getKey()+" "+entryc1.getValue()+" "+entryc3.getKey()+" "+entryc3.getValue());
							if (Double.parseDouble(entryc3.getValue()) > 1 || Double.parseDouble(entryc3.getValue()) < 0) {
								lb3.setText("Error at : " + entryc1.getValue() + ". Reliability value " + entryc3.getValue()
										+ " is not valid.");
								lb3.setForeground(RBDModelEditor.this.colorRed);
								return;
							}
						}
					}
				}

				//            	  System.out.println("yyyy");
				if (flag == false) {
					//					System.out.println("xxxx");
					if (n1 == n2) {
						lb3.setText("Error at : " + RBDid[n1] + ". Sturcture " + str + " is repeated.");
						lb3.setForeground(RBDModelEditor.this.colorRed);
						return;
					}
					if (n1 != n2) {
						lb3.setText("Error at : " + RBDid[n1] + " and " + RBDid[n2] + ". Sturcture " + str + " is repeated.");
						lb3.setForeground(RBDModelEditor.this.colorRed);

						return;
					}
				}

				//            	  SAXReader saxReader = new SAXReader();  
				//  	            Document document;
				//				try {
				//					String str = "abcdefg";
				//					str = saFilePath.substring(0,str.length()-6) + "rbd_model";
				//					document = saxReader.read(new File(str));
				//
				//		            Element root = document.getRootElement(); 
				////	          		Element root2 = root.addElement(BreezeObject.TYPE_RBD);
				//		            Element root2 = root.addElement("RBD");
				//	          		Element root3 = root2.addElement(BreezeObject.TYPE_RBDLIST);
				//	          		
				//	          		FileWriter writer = new FileWriter(new File(str));
				//	        		System.out.println("write to " + saFilePath);
				//	        		doc.write(writer);
				//	        		writer.close();
				//	          		
				//				} catch (DocumentException e1) {
				//					// TODO Auto-generated catch block
				//					e1.printStackTrace();
				//				}  
				//          		
				RBDModelEditor.this.modifyXMLFile5();
				PredictReliability rbd = new ReliabilityBlockDiagram();
				double reliability2 = rbd.getReliability(saFilePath, saFilePath);
				reliability2 = (double) Math.round(reliability2 * 1000000) / 1000000;
				System.out.println("reliability" + reliability2);

				lb3.setForeground(RBDModelEditor.this.colorBlack);

				String str2;
				NumberFormat nf2 = NumberFormat.getPercentInstance();
				nf2.setMinimumFractionDigits(2);
				str2 = nf2.format(reliability2);

				lb3.setText("RBD Analysis for Architecture: System reliability is " + str2

				+ "                                                ");
			}

		});

		//        Button btn=new Button(this.panel,SWT.PUSH);
		//		btn.setText("Test Button");

		//		MouseListener listener = null;
		//		btn.addMouseListener(listener);		
		//		{
		//			System.out.println("rr");
		//		}
		//		Text txt=new Text(this.panel,SWT.BORDER);
		//		txt.setText("锟斤拷锟斤拷锟斤拷锟斤看锟斤拷位锟矫对诧拷锟斤拷");
		this.setMinSize(this.composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

	//锟斤拷锟斤拷锟角讹拷xml锟斤拷山峁癸拷锟絙ranch锟斤拷name2锟斤拷map锟侥达拷锟斤拷

	//锟斤拷锟斤拷锟角讹拷xml锟斤拷山峁癸拷锟絙ranch锟斤拷op锟斤拷map锟侥达拷锟斤拷
	//锟斤拷未锟斤拷锟斤拷锟斤拷锟�
	//idmapb2锟斤拷锟斤拷锟斤拷锟斤拷锟絠dmapb3锟侥达拷锟斤拷顺锟斤拷锟斤拷锟�
	protected void modifyXMLFile5() {
		int returnValue = 0;
		try {
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(new File(saFilePath));
			/** 锟睫革拷锟斤拷锟斤拷之一: 锟斤拷锟絙ook锟节碉拷锟斤拷show锟斤拷锟皆碉拷锟斤拷锟斤拷为yes,锟斤拷锟睫改筹拷no */
			/** 锟斤拷锟斤拷xpath锟斤拷锟揭讹拷锟斤拷 */
			Element rootElm = document.getRootElement();

			List list = rootElm.elements("RBDFailureAnalysis");
			Iterator iter = list.iterator();
			while (iter.hasNext()) {
				Element elmm = (Element) iter.next();
				rootElm.remove(elmm);
			}
			Element parentElm = rootElm.addElement("RBDFailureAnalysis");
			Element childElm = parentElm.addElement("ArchFailureList");
			String[] drawable = new String[100];
			String[] drawunable = new String[100];
			int iable = 0;
			int iunable = 0;
			//   	            System.out.println("pppp");
			for (int i = 0; i < count; ++i) {
				drawunable[i] = RBDid[i];
				iunable = iunable + 1;
			}
			//   	         for (int i=0;i<iable;++i)
			//	            {
			//	            	System.out.println("able\t"+drawable[i]);
			//	            }
			//	            for (int i=0;i<iunable;++i)
			//	            {
			//	            	System.out.println("unable\t"+drawunable[i]);
			//	            }
			boolean flag = true;

			for (int i = 0; i < count; ++i) {
				flag = true;
				for (int j = 0; j < iunable; ++j) {
					if (RBDname1[i].equals(drawunable[j])) flag = false;
				}
				if (flag == true) {
					drawable[iable] = RBDname1[i];
					iable = iable + 1;
				}

				flag = true;
				for (int j = 0; j < iunable; ++j) {
					if (RBDname2[i].equals(drawunable[j])) flag = false;
				}
				if (flag == true) {
					drawable[iable] = RBDname2[i];
					iable = iable + 1;
				}
			}

			//   	         for (int i=0;i<iable;++i)
			//	            {
			//	            	System.out.println("able\t"+drawable[i]);
			//	            }
			//	            for (int i=0;i<iunable;++i)
			//	            {
			//	            	System.out.println("unable\t"+drawunable[i]);
			//	            }
			//   	            System.out.println("oooo");

			int j = 0;
			while (iunable > 0) {
				for (int i = 0; i < iunable; ++i) {
					for (int jj = 0; jj < count; ++jj) {
						if (RBDid[jj].equals(drawunable[i])) {
							j = jj;
							;
						}
					}
					flag = true;
					for (int m = 0; m < iable; ++m) {
						for (int n = 0; n < iable; ++n) {
							//   	            			System.out.println(count+"aa"+ j+RBDname1[j]+RBDname2[j]+drawable[m]+drawable[n]);
							if (RBDname1[j].equals(drawable[m]) && RBDname2[j].equals(drawable[n])) {
								flag = false;
								continue;
							}
						}
					}
					if (flag == false) {
						drawable[iable] = RBDid[j];
						iable = iable + 1;
						drawunable[i] = drawunable[iunable - 1];
						iunable = iunable - 1;
					}
				}
			}
			//   	            for (int i=0;i<iable;++i)
			//   	            {
			//   	            	System.out.println("able\t"+drawable[i]);
			//   	            }
			//   	            for (int i=0;i<iunable;++i)
			//   	            {
			//   	            	System.out.println("unable\t"+drawunable[i]);
			//   	            }
			int n3 = 0;
			for (int i = 0; i < count; ++i) {
				if (RBDid[i] == drawable[iable - 1]) n3 = i;
			}

			this.XMLBranch(childElm, n3);
			/*
			 * List list = rootElm.elements("arch"); Iterator iter =
			 * list.iterator(); while (iter.hasNext()) { Element elmm =
			 * (Element) iter.next(); List listm = elmm.elements("node");
			 * System.out.println("ss"+elmm.getText()); Iterator iterm =
			 * listm.iterator(); while (iterm.hasNext()) { Element elm =
			 * (Element) iterm.next(); System.out.println("tt"+elm.getText());
			 * // Iterator iterator = elm.elementIterator("ID"); Attribute
			 * attribute = (Attribute) elm.attribute("ID"); // while
			 * (iterator.hasNext()) {
			 * System.out.println("ee"+attribute.getValue()+"set :"
			 * +id+"\t"+value); // Element titleElement = (Element)
			 * iterator.next(); // if (titleElement.getText().equals(id)) { if
			 * (attribute.getValue().toString().equalsIgnoreCase(id)) {
			 * System.out.println("yes"); System.out.println("set :"
			 * +id+"\t"+value); Attribute attribute2 = (Attribute)
			 * elm.attribute("probability"); attribute2.setValue(value); } else
			 * System.out.println("no"); // } }
			 * 
			 * }
			 */
			try {
				/** 锟斤拷document锟叫碉拷锟斤拷锟斤拷写锟斤拷锟侥硷拷锟斤拷 */
				XMLWriter writer = new XMLWriter(new FileWriter(new File(saFilePath)));
				writer.write(document);
				writer.close();
				/** 执锟叫成癸拷,锟借返锟斤拷1 */
				returnValue = 1;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void updatePanel(String item) {
		if (item == null) return;
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

	//锟斤拷锟斤拷锟角讹拷xml锟斤拷山峁癸拷锟絙ranch锟斤拷op锟斤拷map锟侥达拷锟斤拷

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

		if (selfTransitionList.size() > 0) this.writeSafetySpecification(selfTransitionList, nodeElement);

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

	void XMLBranch(Element element, int index) {
		Element parentElm = element.addElement("FailureEventListofArch");
		parentElm.addAttribute("name", RBDid[index]);
		parentElm.addAttribute("OperationType", RBDop[index]);
		boolean flag = true;
		for (int i = 0; i < count; ++i) {
			if (RBDid[i].equals(RBDname1[index])) {
				this.XMLBranch(parentElm, i);
				flag = false;
			}
		}
		if (flag == true) {
			this.XMLNode1(parentElm, index);
		}

		flag = true;
		for (int i = 0; i < count; ++i) {
			if (RBDid[i].equals(RBDname2[index])) {
				this.XMLBranch(parentElm, i);
				flag = false;
			}
		}
		if (flag == true) {
			this.XMLNode2(parentElm, index);
		}

	}

	void XMLNode1(Element element, int index) {
		Element parentElm = element.addElement("ArchFailure");
		String str = "";
		Set<Map.Entry<String, String>> set = idmap.entrySet();
		for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
			Map.Entry<String, String> entry = it.next();
			if (entry.getValue().equals(RBDname1[index])) str = entry.getKey();
		}
		parentElm.addAttribute("ArchFailureEventID", str);
		parentElm.addAttribute("ArchFailureEventname", RBDname1[index]);
	}

	void XMLNode2(Element element, int index) {
		Element parentElm = element.addElement("ArchFailure");
		String str = "";
		Set<Map.Entry<String, String>> set = idmap.entrySet();
		for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
			Map.Entry<String, String> entry = it.next();
			if (entry.getValue().equals(RBDname2[index])) str = entry.getKey();

		}
		parentElm.addAttribute("ArchFailureEventID", str);
		parentElm.addAttribute("ArchFailureEventname", RBDname2[index]);
	}

}
