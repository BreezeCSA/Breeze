package com.goku.breeze.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class RBDXMLParser {
	//
	//	public static void main(String[] agrs)
	//	{
	//		XmlParse xp = new XmlParse();
	//	//	Map<String, String> map = xp.getProbability("res/SA_RBD.xml");
	//		LinkedList list = xp.getRelation("res/RBDt7FailureExample.xml","res/test7.rbd_model");

	//list.removeLast();
	//		Iterator it = list.iterator();
	//		while(it.hasNext())
	//		{
	//			System.out.println(it.next());
	//		}
	//		

	//		LinkedList list1 = xp.getProbability("res/SA_RBD.xml");
	//		Iterator it = list1.iterator();
	//		while(it.hasNext())
	//		{
	//			System.out.println(it.next());
	//		}

	//	}

	private LinkedList getName(Element elment, String SApath) {
		// TODO Auto-generated method stub
		LinkedList nameList = new LinkedList<>();
		LinkedList saList = this.getProbability(SApath);
		Map<String, String> nameMap = (Map<String, String>) saList.get(1);

		List<Element> subList = elment.elements();
		for (Element element : subList) {
			String type = element.attributeValue("OperationType");
			if (type != null) {

				nameList.add(this.getName(element, SApath));
			} else {
				//	String type1 = element.getParent().attributeValue("OperationType");
				String nodeid = element.attributeValue("ArchFailureEventID");
				nameList.add(nameMap.get(nodeid));

			}
		}

		return nameList;
	}

	/*
	 * parse the SA.xml file to get the probability of every node
	 */

	//
	public LinkedList getNode(Element elment, String SApath) {
		LinkedList saList = this.getProbability(SApath);
		Map<String, String> proMap = (Map<String, String>) saList.get(0);

		LinkedList list = new LinkedList<>();
		//list.add(type);
		List<Element> subList = elment.elements();

		for (Element element : subList) {
			String type = element.attributeValue("OperationType");
			if (type != null) {

				String type1 = element.getParent().attributeValue("OperationType");
				list.add(type1);
				list.add(this.getNode(element, SApath));
			} else {
				String type1 = element.getParent().attributeValue("OperationType");
				String nodeid = element.attributeValue("ArchFailureEventID");
				String probability = proMap.get(nodeid);
				double pro = Double.parseDouble(probability);
				list.add(type1);
				list.add(pro);
			}
		}
		return list;
	}

	public LinkedList getProbability(String SApath)

	//	杩斿洖鍚玭odeID-probablity鍜宯odeID-name鐨刲ist	
	{

		LinkedList list = new LinkedList<>();
		String nodeid, probability, nodename;
		TreeMap<String, String> proMap = new TreeMap<String, String>();
		TreeMap<String, String> nameMap = new TreeMap<String, String>();
		list.add(proMap);
		list.add(nameMap);

		Element rootElement = this.getRoot(SApath);
		Element parentElement = rootElement.element("arch");
		List<Element> nodelist = parentElement.elements("node");

		for (Element node : nodelist) {
			nodeid = node.attributeValue("ID");

			probability = node.attributeValue("probability");
			nodename = node.attributeValue("name");

			proMap.put(nodeid, probability);
			nameMap.put(nodeid, nodename);

		}
		return list;
	}

	/*
	 * parse the Failure.xml to get the relation between different nodes
	 */
	public LinkedList getRelation(String RBDpath, String SApath) {
		LinkedList resultlist = new LinkedList<>();

		LinkedList probabilitylist = new LinkedList<>();//瀛樺偍node鐨勫け鏁堢巼鍙婂叾涔嬮棿鐨勫叧绯�		

		LinkedList namelist = new LinkedList<>();
		resultlist.add(probabilitylist);
		resultlist.add(namelist);

		LinkedList saList = this.getProbability(SApath);

		Map<String, String> proMap = (Map<String, String>) saList.get(0); //nodeID鍜宲ro鐨勮〃
		Map<String, String> nameMap = (Map<String, String>) saList.get(1); //nodeID鍜宯ame鐨勮〃

		Element rootElement = this.getRoot(RBDpath);
		Element root2Element = rootElement.element("RBDFailureAnalysis");
		Element parentElement = root2Element.element("ArchFailureList");
		Element element = parentElement.element("FailureEventListofArch");

		String operationType = element.attributeValue("OperationType");//鏁翠釜绯荤粺鐨勬暣浣撳叧绯�		

		probabilitylist.add(operationType);

		List<Element> elementList = element.elements();
		for (Element e : elementList) {
			String type = e.attributeValue("OperationType");

			if (type == null) //娌℃湁鍒嗘敮 鏋勪欢鑷繁鐨勯棶棰�鍐欏叆nodename鍜宲ro鏁板�
			{
				String nodeid = e.attributeValue("ArchFailureEventID"); // Failed/NodeA鐨勫悗闈㈤偅鍧桸odeA

				String probability = proMap.get(nodeid);
				double pro = Double.parseDouble(probability);
				String nodename = nameMap.get(nodeid);
				probabilitylist.add(pro);
				namelist.add(nodename);

			} else //鏈夊垎鏀�			

			{
				LinkedList tmplist = new LinkedList<>();
				probabilitylist.add(tmplist);
				tmplist.add(type);
				LinkedList prolist = this.getNode(e, SApath);
				tmplist.add(prolist);

				LinkedList namelist1 = new LinkedList<>();
				namelist1 = this.getName(e, SApath);
				namelist.add(namelist1);

			}
		}

		return resultlist;
	}

	/*
	 * get rootelement of the .xml file
	 */
	public Element getRoot(String path) {
		Element rootElement = null;
		try {

			InputStream inputStream = new FileInputStream(path);
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(inputStream);
			rootElement = document.getRootElement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rootElement;
	}
}
