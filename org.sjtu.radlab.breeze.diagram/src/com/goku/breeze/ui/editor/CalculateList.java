package com.goku.breeze.ui.editor;

import java.util.*;

public class CalculateList {
	
//	public static void main(String[] args)
//	{
//		CalculateList cl = new CalculateList();
//		
//		XmlParse xp = new XmlParse();
//		LinkedList list = xp.getRelation("res/RBDFailureExample.xml","res/SA_RBD.xml");
//		LinkedList prolist = (LinkedList) list.get(0);
//		
//		double failure = cl.Cal(prolist);
//		
//		System.out.println(failure);
//		
//	}
	
	public double Cal(LinkedList list)
	{
		double reliability = 1 ;
		if(hasList(list))
		{
			double temp = 1;
			Iterator it = list.iterator();
			String type = null;
			LinkedList<Double> tmplist = new LinkedList<Double>();
			while(it.hasNext())
			{
				Object obj = it.next();
				if(obj instanceof String)
					type = obj.toString();
				if(obj instanceof Double)
				{
					tmplist.add((double)obj);
				}
			}
			if(type.equalsIgnoreCase("and"))
			{
				temp = calAnd(tmplist);
			}
			else if(type.equalsIgnoreCase("or"))
			{
				temp = calOr(tmplist);
			}
			reliability = temp;
		//	System.out.println("reliability"+ reliability);
		}
		else
		{
			LinkedList tmplist = new LinkedList<>();
			Iterator it = list.iterator();
			while(it.hasNext())
			{
				Object obj = it.next();
				if(obj instanceof LinkedList)
				{
					tmplist.add(Cal((LinkedList)obj));
				}
				else
					tmplist.add(obj);	
			}
			reliability = Cal(tmplist);
		}
		return reliability;
	}
	
	
	private double calOr(LinkedList<Double> tmplist)
	{
	
		double result = 1;
		Iterator it = tmplist.iterator();
		while(it.hasNext())
		{
			double temp = 1-(double) it.next();
			result = result*temp;
		}
		return 1-result;
	}

	private double calAnd(LinkedList<Double> tmplist)
	{
		double result = 1;
		Iterator it = tmplist.iterator();
		while(it.hasNext())
		{
			double temp =(double) it.next();
			result = result * temp;
		}
		return result;
	}

	boolean hasList(LinkedList list)
	{
		Iterator it = list.iterator();
		while(it.hasNext())
		{
			if(it.next() instanceof LinkedList)
			{
				return false;
			}
		}
		return true;
	}
}
