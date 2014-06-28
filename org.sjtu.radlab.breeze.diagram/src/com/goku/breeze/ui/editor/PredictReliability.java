package com.goku.breeze.ui.editor;

import java.util.Iterator;
import java.util.LinkedList;

import com.goku.breeze.util.RBDXMLParser;

/*
 * an abstract class for various reliability class 
 */
public abstract class PredictReliability {

	public abstract double getReliability(String f1, String f2);

}

/*
 * ReliabilityBlockDiagram class to predict reliability use RBD(Reliability
 * Block Diagram)
 */
class ReliabilityBlockDiagram extends PredictReliability {

	//list值
	public double Cal(LinkedList list) //锟斤拷锟斤拷一锟斤拷list
	{
		Iterator iter = list.iterator();
		//		System.out.println("Cal starts");

		while (iter.hasNext()) {

			String type2 = null;
			Object obj2 = iter.next();
			type2 = obj2.toString();
			//			System.out.println("iter\t" + type2);
		}

		double reliability = 1;
		if (this.hasList(list)) {
			double temp = 1;
			Iterator it = list.iterator();
			String type = null;
			LinkedList<Double> tmplist = new LinkedList<Double>();
			while (it.hasNext()) {
				Object obj = it.next();
				if (obj instanceof String) type = obj.toString();
				if (obj instanceof Double) {
					tmplist.add((double) obj);
				}
			}

			//			System.out.println("type\t" + type);
			if (type.equalsIgnoreCase("and")) //锟斤拷锟斤拷执锟叫⌒�
			{
				temp = this.calAnd(tmplist);
			} else if (type.equalsIgnoreCase("or")) {
				temp = this.calOr(tmplist);
			}
			reliability = temp;
			//	System.out.println("reliability"+ reliability);
		} else {
			LinkedList tmplist = new LinkedList<>();
			Iterator it = list.iterator();
			while (it.hasNext()) {
				Object obj = it.next();
				if (obj instanceof LinkedList) {
					tmplist.add(this.Cal((LinkedList) obj));
				} else tmplist.add(obj);
			}
			reliability = this.Cal(tmplist);
		}
		return reliability;
	}

	private double calAnd(LinkedList<Double> tmplist) //锟斤拷锟斤拷锟斤拷 p1*p2
	{
		double result = 1;
		Iterator it = tmplist.iterator();
		while (it.hasNext()) {
			double temp = (double) it.next();
			result = result * temp;
		}
		return result;
	}

	private double calOr(LinkedList<Double> tmplist) //锟斤拷锟斤拷锟斤拷 1-锟斤拷1-p1锟斤拷锟斤拷1-p2锟斤拷..

	{

		double result = 1;
		Iterator it = tmplist.iterator();
		while (it.hasNext()) {
			double temp = 1 - (double) it.next();
			result = result * temp;
		}
		return 1 - result;
	}

	@Override
	public double getReliability(String f1, String f2) {
		// TODO Auto-generated method stub
		ReliabilityBlockDiagram rbd = new ReliabilityBlockDiagram();
		RBDXMLParser xp = new RBDXMLParser();
		LinkedList list = xp.getRelation(f1, f2);

		LinkedList prolist = (LinkedList) list.get(0);
		LinkedList namelist = (LinkedList) list.get(1);

		for (Object obj : namelist)
			System.out.println(obj);

		double failure = rbd.Cal(prolist);
		//		String name = rbd.printList(namelist);
		//	System.out.println(name);
		//	name.trim();
		//		String[] list11 = name.split(" ");
		//		for(String s:list11)
		//		{
		//			if(!s.equals(" "))
		//				System.out.println(s.trim());
		//		}

		return failure;
	}

	boolean hasList(LinkedList list) {
		Iterator it = list.iterator();
		while (it.hasNext()) {
			if (it.next() instanceof LinkedList) {
				return false;
			}
		}
		return true;
	}

	public String printList(LinkedList list) {
		//	LinkedList namelist = new LinkedList<>();
		String name = "";
		if (this.hasList(list)) {
			for (Object obj : list)
				//		namelist.add(obj);
				name = name.concat(obj.toString());
		} else {
			LinkedList templist = new LinkedList<>();
			Iterator it = list.iterator();
			while (it.hasNext()) {
				Object obj = it.next();
				if (obj instanceof LinkedList)

					templist.add(this.printList((LinkedList) obj));
				else templist.add(obj);
			}
			name = name.concat(this.printList(templist));
		}
		return name;
	}
}