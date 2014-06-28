package com.goku.breeze.ui.editor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
import org.eclipse.jface.viewers.ColumnViewer;
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
import com.goku.breeze.ui.editor.support.ActionAddModeMKV;
import com.goku.breeze.ui.editor.support.ActionNodeAddTransitionMKV;
import com.goku.breeze.ui.editor.support.ActionNodeRmTransitionMKV;
import com.goku.breeze.ui.editor.support.ActionRmModeMKV;
import com.goku.breeze.ui.editor.support.ModeComboSupportMKV;
import com.goku.breeze.ui.editor.support.ModeTableContentProvider;
import com.goku.breeze.ui.editor.support.ModeTableLabelProvider;
import com.goku.breeze.ui.editor.support.ModeTextSupportMKV;
import com.goku.breeze.ui.editor.support.TransitionComboSupportMKV;
import com.goku.breeze.ui.editor.support.TransitionTableContentProvider;
import com.goku.breeze.ui.editor.support.TransitionTableLabelProvider;

public class MKVModelEditor extends ScrolledComposite {
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

				modifyXMLFile(p.getId(), newValue);
				idGen();
				idGenp();
				idGenv();
				idGenc1();
				idGenc2();
				idGenc3();

			} else {
				throw new RuntimeException("锟斤拷锟斤拷锟斤拷锟斤拷:" + property);
			}
			this.tv.update(p, null);
		}

	}

	public static class MyCellModifier2 implements ICellModifier {
		public static String[] NAMES = { "11" };
		private final TableViewer tv;

		public MyCellModifier2(TableViewer tv) {
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
			People2 p = (People2) element;
			if (property.equals("name2")) {
				return new Integer(this.getNameIndex(p.getName2()));
			} else if (property.equals("prop")) {
				return p.getProp();
			}
			throw new RuntimeException("error column name : " + property);
		}

		@Override
		public void modify(Object element, String property, Object value) {
			//    	System.out.println("aaaa");
			TableItem item = (TableItem) element;
			People2 p = (People2) item.getData();
			if (property.equals("name1")) {
				Integer comboIndex = (Integer) value;
				if (comboIndex.intValue() == -1) {
					return;
				}
				String newName = NAMES[comboIndex.intValue()];
				p.setName2(newName);
			} else if (property.equals("prop")) {
				String newValue = (String) value;
				if (newValue.equals("")) {
					return;
				}
				p.setProp(newValue);

				modifyXMLFile2(p.getID(), newValue);
				idGen();
				idGenp();
				idGenv();
				idGenc1();
				idGenc2();
				idGenc3();

			} else {
				throw new RuntimeException("锟斤拷锟斤拷锟斤拷锟斤拷:" + property);
			}
			this.tv.update(p, null);
		}

	}

	static class People {
		public static List getPeople() {
			List list = new ArrayList();
			ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "RBD enddd");
			int n = 30, i = 0;
			Set<Map.Entry<String, String>> set = idmap.entrySet();
			Set<Map.Entry<String, String>> setp = idmapp.entrySet();
			Set<Map.Entry<String, String>> setv = idmapv.entrySet();
			int nodeSum = 0;
			Iterator<Map.Entry<String, String>> itp = setp.iterator();
			Iterator<Map.Entry<String, String>> itv = setv.iterator();
			for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
				i = i + 1;
				itp.hasNext();
				itv.hasNext();
				ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "RBD enddddd11");
				Map.Entry<String, String> entry = it.next();
				ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "RBD enddddd22");
				Map.Entry<String, String> entryp = itp.next();
				Map.Entry<String, String> entryv = itv.next();
				ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "RBD enddddd33");

				ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "\n" + entry.getKey() + "  \t" + entry.getValue() + "\n");
				nodeSum = nodeSum + 1;
				ConsoleUtil
						.printMessage(ConsoleFactory.getConsole(), "\np" + entryp.getKey() + "  \t" + entryp.getValue() + "\n");

				ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "RBD end");

				String id = entry.getKey();
				String name = entry.getValue();
				String prop = entryp.getValue();
				String iv = entryv.getValue();
				People people = new People(id, name, prop);
				if (iv.contentEquals("0")) list.add(people);
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

	static class People2 {
		public static List getPeople() {
			List list = new ArrayList();
			int n = 30, i = 0;
			Set<Map.Entry<String, String>> set = idmapc1.entrySet();
			Set<Map.Entry<String, String>> setp = idmapc2.entrySet();
			Set<Map.Entry<String, String>> setv = idmapc3.entrySet();
			int nodeSum = 0;
			Iterator<Map.Entry<String, String>> itp = setp.iterator();
			Iterator<Map.Entry<String, String>> itv = setv.iterator();
			for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
				i = i + 1;
				itp.hasNext();
				itv.hasNext();
				ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "RBD enddddd11");
				Map.Entry<String, String> entry = it.next();
				ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "RBD enddddd22");
				Map.Entry<String, String> entryp = itp.next();
				Map.Entry<String, String> entryv = itv.next();
				ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "RBD enddddd33");

				ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "\n" + entry.getKey() + "  \t" + entry.getValue() + "\n");
				nodeSum = nodeSum + 1;
				ConsoleUtil
						.printMessage(ConsoleFactory.getConsole(), "\np" + entryp.getKey() + "  \t" + entryp.getValue() + "\n");

				ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "RBD end");

				String id = entry.getKey();
				String name = entry.getValue();
				String prop = entryp.getValue();
				String iv = entryv.getValue();
				People2 people = new People2(id, name, prop, iv);
				if (prop.contentEquals("fault")) {
				} else {
					ProbabiltyCount = ProbabiltyCount + 1;
					list.add(people);
				}
			}
			return list;
		}

		private String ID;
		private String name1;
		private String name2;
		private String prop;

		public People2(String ID, String name1, String name2, String prop) {
			this.ID = ID;
			this.name1 = name1;
			this.name2 = name2;
			this.prop = prop;
		}

		public String getID() {
			return this.ID;
		}

		public String getName1() {
			return this.name1;
		}

		public String getName2() {
			return this.name2;
		}

		public String getProp() {
			return this.prop;
		}

		public void setID(String ID) {
			this.ID = ID;
		}

		public void setName1(String name1) {
			this.name1 = name1;
		}

		public void setName2(String name2) {
			this.name2 = name2;
		}

		public void setProp(String prop) {
			this.prop = prop;
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
		private static final int NAME1 = 1;
		public static final Sorter2 NAME1_ASC = new Sorter2(NAME1);
		public static final Sorter2 NAME1_DESC = new Sorter2(-NAME1);

		private static final int NAME2 = 2;
		public static final Sorter2 NAME2_ASC = new Sorter2(NAME2);
		public static final Sorter2 NAME2_DESC = new Sorter2(-NAME2);
		private static final int PROP = 3;
		public static final Sorter2 PROP_ASC = new Sorter2(PROP);
		public static final Sorter2 PROP_DESC = new Sorter2(-PROP);

		private final int sortType;

		private Sorter2(int sortType) {
			this.sortType = sortType;
		}

		@Override
		public int compare(Viewer viewer, Object e1, Object e2) {
			People2 p1 = (People2) e1;
			People2 p2 = (People2) e2;
			switch (this.sortType) {
			case NAME1: {
				String l1 = p1.getName1();
				String l2 = p2.getName1();
				return l1.compareTo(l2);
			}
			case -NAME1: {
				String l1 = p1.getName1();
				String l2 = p2.getName1();
				return l2.compareTo(l1);
			}
			case NAME2: {
				String s1 = p1.getName2();
				String s2 = p2.getName2();
				return s1.compareTo(s2);
			}
			case -NAME2: {
				String s1 = p1.getName2();
				String s2 = p2.getName2();
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
					return p.getName1().toString();
				} else if (columnIndex == 1) {
					return p.getName2();
				} else if (columnIndex == 2) {
					return p.getProp();
				}
			}
			return null;
		}
	}

	private static Map<String, BreezeObject> allObjects;

	private static Map<String, String> idmap = new HashMap<String, String>();

	private static Map<String, String> idmapc1 = new HashMap<String, String>();
	//锟斤拷锟斤拷锟角讹拷xml锟斤拷锟絜dge锟斤拷id锟斤拷targetName锟斤拷map锟侥达拷锟斤拷
	private static Map<String, String> idmapc2 = new HashMap<String, String>();
	//锟斤拷锟斤拷锟角讹拷xml锟斤拷锟絜dge锟斤拷id锟斤拷probability锟斤拷map锟侥达拷锟斤拷
	private static Map<String, String> idmapc3 = new HashMap<String, String>();

	//锟斤拷锟斤拷锟角讹拷xml锟斤拷锟絥ode锟斤拷id锟斤拷probability锟斤拷map锟侥达拷锟斤拷
	private static Map<String, String> idmapp = new HashMap<String, String>();

	//锟斤拷锟斤拷锟角讹拷xml锟斤拷锟絥ode锟斤拷id锟斤拷isVirtual锟斤拷map锟侥达拷锟斤拷
	private static Map<String, String> idmapv = new HashMap<String, String>();

	static int ProbabiltyCount = 0;

	static private String saFilePath;

	public static void idGen() {
		int s = 0;
		File file = new File(saFilePath);
		//	System.out.println("11");
		//	System.out.println(saFilePath);
		Map<String, String> mapEleList = new HashMap<String, String>();
		try {
			mapEleList = parseXml(file);

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
						System.out.println("yes");
						Attribute attribute2 = elm.attribute("probability");
						attribute2.setValue(value);
					} else System.out.println("no");
					//	  }
				}

			}
			try {
				/** 锟斤拷document锟叫碉拷锟斤拷锟斤拷写锟斤拷锟侥硷拷锟斤拷 */
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

	public static void modifyXMLFile2(String id, String value) {
		int returnValue = 0;
		try {
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(new File(saFilePath));

			Element rootElm = document.getRootElement();
			List list = rootElm.elements("arch");
			Iterator iter = list.iterator();
			while (iter.hasNext()) {
				Element elmm = (Element) iter.next();
				List listm = elmm.elements("edge");

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
						System.out.println("yes");

						Attribute attribute2 = elm.attribute("probability");
						attribute2.setValue(value);
					} else {
						System.out.println("no");
					}
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

	//锟斤拷锟斤拷锟角讹拷xml锟斤拷锟絥ode锟斤拷id锟斤拷name锟斤拷map锟侥达拷锟斤拷

	private final Color colorBlack = new Color(Display.getCurrent(), 0, 0, 0);

	private final Color colorBlue = new Color(Display.getCurrent(), 0, 0, 255);

	private final Color colorRed = new Color(Display.getCurrent(), 255, 0, 0);

	private final Color colorWhite = new Color(Display.getCurrent(), 255, 255, 255);

	private final Combo componentSelect = null;

	private Composite composite;

	private Table eventTable, modeTable, transitionTable;

	private TableViewer eventTableViewer, modeTableViewer, transitionTableViewer;

	private final String fileNameWithoutExt;

	private Table methodTable;

	private TableViewer methodTableViewer;

	private Composite panel = null;

	private final MKVEditor parentEditor;

	public MKVModelEditor(String saFilePath, String fileNameWithoutExt, Composite parent, MKVEditor rbdEditor) {
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

	//锟斤拷锟斤拷为锟斤拷锟斤拷锟绞撅拷暮锟斤拷锟�	
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
		modeCol[1].setEditingSupport(new ModeComboSupportMKV(this, this.modeTableViewer));

		Button btnAddMode = this.drawButton("Add");
		Button btnRmMode = this.drawButton("Delete");

		this.drawDivider();

		// section "transition"
		this.drawLabel("Transition");
		this.transitionTable = this.drawTable();
		ret = this.drawTableViewer(this.transitionTable, new String[] { "Source", "Target", "Trigger" }, new int[] { 180, 180,
				180 });
		this.transitionTableViewer = (TableViewer) ret[0];

		this.transitionTableViewer.setContentProvider(new TransitionTableContentProvider(
				TransitionTableContentProvider.TYPE_NODE, null));
		this.transitionTableViewer.setLabelProvider(new TransitionTableLabelProvider());
		this.transitionTableViewer.setInput(node);
		TableViewerColumn[] cols = (TableViewerColumn[]) ret[1];
		TransitionComboSupportMKV[] es = new TransitionComboSupportMKV[] {
				new TransitionComboSupportMKV(this, this.transitionTableViewer, 0, node),
				new TransitionComboSupportMKV(this, this.transitionTableViewer, 1, node),
				new TransitionComboSupportMKV(this, this.transitionTableViewer, 2, node) };
		cols[0].setEditingSupport(es[0]);
		cols[1].setEditingSupport(es[1]);
		cols[2].setEditingSupport(es[2]);

		Button btnAddTran = this.drawButton("Add");
		Button btnRmTran = this.drawButton("Delete");
		btnAddTran.addSelectionListener(new ActionNodeAddTransitionMKV(this, node, this.transitionTableViewer));
		btnRmTran.addSelectionListener(new ActionNodeRmTransitionMKV(this, node, this.transitionTableViewer));

		modeCol[0].setEditingSupport(new ModeTextSupportMKV(this, this.modeTableViewer, 0,
				new ColumnViewer[] { this.transitionTableViewer }, new TransitionComboSupportMKV[] { es[0], es[1] }));
		btnRmMode.addSelectionListener(new ActionRmModeMKV(node, this.modeTableViewer,
				new TableViewer[] { this.transitionTableViewer }));
		btnAddMode.addSelectionListener(new ActionAddModeMKV(this, node, this.modeTableViewer, new EditingSupport[] { es[0],
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

		idGen();

		idGenv();
		idGenp();
		idGenc1();
		idGenc2();
		idGenc3();

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

		lb0.setText("DTMC configuration\n\n");

		lb0.setLayoutData(new GridData(SWT.BEGINNING, SWT.FILL, true, false, 1, 1));

		this.panel = new Composite(this.composite, SWT.NONE);
		this.panel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		GridLayout panelLayout = new GridLayout();
		panelLayout.numColumns = 1;
		this.panel.setLayout(panelLayout);

		Label lb1 = new Label(this.panel, SWT.None);
		lb1.setText("Component reliability");

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
		//		        锟斤拷锟斤拷锟铰硷拷锟斤拷锟斤拷锟斤拷

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

		tableViewer.setColumnProperties(new String[] { "id", "name", "prop" });
		CellEditor[] cellEditor = new CellEditor[3];
		cellEditor[0] = null;
		cellEditor[1] = null;
		//		        cellEditor[1] = new ComboBoxCellEditor(tableViewer.getTable(),MyCellModifier.NAMES,SWT.READ_ONLY);
		cellEditor[2] = new TextCellEditor(tableViewer.getTable());
		//ConsoleUtil.printMessage(ConsoleFactory.getConsole(), cellEditor[2].getValue()+"\n");    

		tableViewer.setCellEditors(cellEditor);
		ICellModifier modifier = new MyCellModifier(tableViewer);
		tableViewer.setCellModifier(modifier);

		//锟斤拷锟斤拷锟斤拷锟斤拷锟紺omponent reliability锟斤拷拇锟斤拷锟�
		Label lb2 = new Label(this.panel, SWT.None);
		lb2.setText("Transition probabilitiy");

		//锟斤拷锟斤拷锟斤拷锟斤拷锟絋ransition probabilitiy锟斤拷拇锟斤拷锟�		

		Set<Map.Entry<String, String>> set1 = idmapc1.entrySet();
		int nodeSum1 = 0;
		for (Iterator<Map.Entry<String, String>> it = set1.iterator(); it.hasNext();) {
			Map.Entry<String, String> entry = it.next();

			ConsoleUtil.printMessage(ConsoleFactory.getConsole(), entry.getKey() + "  \t" + entry.getValue() + "\n");
			nodeSum1 = nodeSum1 + 1;
		}
		ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "RBD end");

		for (Iterator<Map.Entry<String, String>> iter = set1.iterator(); iter.hasNext();) {
			int ii = 1;
			if (ii == 1) {
				Map.Entry<String, String> entry1 = iter.next();
				BreezeObject obj = this.allObjects.get(entry1.getKey());
				//if (obj instanceof BreezeNode) this.drawPanelForNode((BreezeNode)obj);
				ii = 0;
			}
		}
		Table table2;
		final TableViewer tableViewer2 = new TableViewer(this.panel, SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL
				| SWT.H_SCROLL);

		table2 = tableViewer2.getTable();
		table2.setLinesVisible(true);
		table2.setHeaderVisible(true);
		table2.setBounds(97, 79, 373, 154);

		final TableColumn newColumnTableColumn2 = new TableColumn(table2, SWT.NONE);
		newColumnTableColumn2.setWidth(150);
		newColumnTableColumn2.setText("Source name");

		newColumnTableColumn2.addSelectionListener(new SelectionAdapter() {
			boolean asc = true;

			@Override
			public void widgetSelected(SelectionEvent e) {
				tableViewer2.setSorter(this.asc ? Sorter2.NAME1_ASC : Sorter2.NAME2_DESC);
				this.asc = !this.asc;
			}
		});

		final TableColumn newColumnTableColumn2_1 = new TableColumn(table2, SWT.NONE);
		newColumnTableColumn2_1.setWidth(150);
		newColumnTableColumn2_1.setText("Target name");

		newColumnTableColumn2_1.addSelectionListener(new SelectionAdapter() {
			boolean asc = true;

			@Override
			public void widgetSelected(SelectionEvent e) {
				tableViewer2.setSorter(this.asc ? Sorter2.NAME2_ASC : Sorter2.NAME2_DESC);
				this.asc = !this.asc;
			}
		});

		final TableColumn newColumnTableColumn2_2 = new TableColumn(table2, SWT.NONE);
		newColumnTableColumn2_2.setWidth(100);
		newColumnTableColumn2_2.setText("Probability");

		newColumnTableColumn2_2.addSelectionListener(new SelectionAdapter() {
			boolean asc = true;

			@Override
			public void widgetSelected(SelectionEvent e) {
				tableViewer2.setSorter(this.asc ? Sorter2.PROP_ASC : Sorter2.PROP_DESC);
				this.asc = !this.asc;
			}
		});
		//锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
		tableViewer2.setContentProvider(new ContentProvider2());
		//锟斤拷锟矫憋拷签锟斤拷
		tableViewer2.setLabelProvider(new TableLabelProvider2());
		//锟斤拷锟斤拷菁锟斤拷细锟絫ableView
		tableViewer2.setInput(People2.getPeople());
		//锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟酵憋拷签锟斤拷锟斤拷锟杰达拷setInput锟矫碉拷锟斤拷锟斤拷菁锟斤拷戏纸锟斤拷锟斤拷示锟斤拷锟斤拷锟揭拷锟斤拷锟捷★拷锟斤拷锟斤拷一锟斤拷锟斤拷锟酵碉拷mvc锟斤拷实锟斤拷.

		tableViewer2.setColumnProperties(new String[] { "name1", "name2", "prop" });
		CellEditor[] cellEditor2 = new CellEditor[3];
		cellEditor2[0] = null;
		cellEditor2[1] = null;
		//				        cellEditor[1] = new ComboBoxCellEditor(tableViewer.getTable(),MyCellModifier.NAMES,SWT.READ_ONLY);
		cellEditor2[2] = new TextCellEditor(tableViewer2.getTable());
		//ConsoleUtil.printMessage(ConsoleFactory.getConsole(), cellEditor[2].getValue()+"\n");    

		tableViewer2.setCellEditors(cellEditor2);
		ICellModifier modifier2 = new MyCellModifier2(tableViewer2);
		tableViewer2.setCellModifier(modifier2);

		//		for (int i=0;i<ProbabiltyCount;++i)
		//		{
		//			List list = new ArrayList();
		//		       ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "RBD enddd");
		//	        int n = 30,i=0;
		//			Set<Map.Entry<String, String>> set = idmap.entrySet();
		//			Set<Map.Entry<String, String>> setp = idmapp.entrySet();
		//			Set<Map.Entry<String, String>> setv = idmapv.entrySet();
		//			int nodeSum = 0;
		//			Iterator<Map.Entry<String, String>> itp = setp.iterator();
		//			Iterator<Map.Entry<String, String>> itv = setv.iterator();
		//		    for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
		//		    	i=i+1;
		//		    	 itp.hasNext();
		//		    	 itv.hasNext();
		//			       ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "RBD enddddd11");
		//	        	        Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
		//					       ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "RBD enddddd22");
		//	        	        Map.Entry<String, String> entryp = (Map.Entry<String, String>) itp.next();
		//	        	        Map.Entry<String, String> entryv = (Map.Entry<String, String>) itv.next();
		//					       ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "RBD enddddd33");
		//			            
		//			            ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "\n"+entry.getKey() + "  \t" + entry.getValue()+"\n");    
		//			            nodeSum = nodeSum + 1;
		//			            ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "\np"+entryp.getKey() + "  \t" + entryp.getValue()+"\n");    
		//			        
		//				       ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "RBD end");
		//	        	
		//	        	String id = entry.getKey() ;
		//	            String name = entry.getValue();
		//	            String prop = entryp.getValue();
		//	            String iv = entryv.getValue();
		//		}

		//锟斤拷锟斤拷锟斤拷锟斤拷锟叫ｏ拷锟斤拷锟斤拷值拇锟斤拷锟�	

		Button button = new Button(this.panel, SWT.PUSH);
		button.setText("Save and Check Structure Correctness");

		final Label lb3 = new Label(this.panel, SWT.None);
		lb3.setText("                                                                                           ");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Set<Map.Entry<String, String>> set1 = idmapc1.entrySet();
				Set<Map.Entry<String, String>> set3 = idmapc3.entrySet();

				boolean flag = true;
				String[] tags = new String[100];
				double[] tagp = new double[100];

				int count = 0;
				Iterator<Map.Entry<String, String>> itc3 = set3.iterator();
				for (Iterator<Map.Entry<String, String>> itc1 = set1.iterator(); itc1.hasNext();) {
					itc3.hasNext();
					Map.Entry<String, String> entryc1 = itc1.next();
					Map.Entry<String, String> entryc3 = itc3.next();
					flag = true;
					for (int i = 0; i < count; ++i) {
						if (tags[i].contentEquals(entryc1.getValue())) {
							flag = false;
							tagp[i] = tagp[i] + Double.parseDouble(entryc3.getValue());
							//							System.out.println(entryc3.getValue());
						}
					}
					if (flag == true) {
						tags[count] = entryc1.getValue();
						tagp[count] = Double.parseDouble(entryc3.getValue());
						count = count + 1;
					}
				}
				for (int i = 0; i < count; ++i) {
					if (tagp[i] != 1) {
						lb3.setText("Error at : " + tags[i] + " of " + Double.toString(tagp[i]));
						lb3.setForeground(MKVModelEditor.this.colorRed);
						return;
					}
				}
				Set<Map.Entry<String, String>> set11 = idmap.entrySet();
				Set<Map.Entry<String, String>> set33 = idmapp.entrySet();

				for (Iterator<Map.Entry<String, String>> itc11 = set11.iterator(); itc11.hasNext();) {
					Map.Entry<String, String> entryc1 = itc11.next();
					//					System.out.println(entryc1.getKey());
					for (Iterator<Map.Entry<String, String>> itc33 = set33.iterator(); itc33.hasNext();) {
						Map.Entry<String, String> entryc3 = itc33.next();
						if (entryc1.getKey().contentEquals(entryc3.getKey())) {
							//						System.out.println(entryc1.getKey()+" "+entryc1.getValue()+" "+entryc3.getKey()+" "+entryc3.getValue());
							if (Double.parseDouble(entryc3.getValue()) > 1 || Double.parseDouble(entryc3.getValue()) < 0) {
								lb3.setText("Error at : " + entryc1.getValue() + ". Reliability value " + entryc3.getValue()
										+ " is not valid.");
								lb3.setForeground(MKVModelEditor.this.colorRed);
								return;
							}
						}
					}
				}
				lb3.setText("No error is found.");
				lb3.setForeground(MKVModelEditor.this.colorBlack);
			}

		});
		this.setMinSize(this.composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
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

}
