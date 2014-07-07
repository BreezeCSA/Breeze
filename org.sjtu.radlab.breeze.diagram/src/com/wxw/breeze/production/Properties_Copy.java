package com.wxw.breeze.production;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.DefaultAttribute;

public class Properties_Copy {
	public String sor_id,sor_pro,tar_id,tar_pro;
	String file_path; 
	Boolean mar;
	public Element sor_ele=null,tar_ele=null;
	Document document = null;
	public Properties_Copy(String sor_id, String sor_pro, String tar_id,
			String tar_pro) {
		super();
		this.sor_id = sor_id;
		this.sor_pro = sor_pro;
		this.tar_id = tar_id;
		this.tar_pro = tar_pro;
		this.file_path = BreezeModel.diagram.part.BreezeDiagramEditor.getSelectedPath();
		this.mar=false;
	}
	public void exe() throws IOException
	{
		//file_path=file_path+"_diagram";
		SAXReader saxReader = new SAXReader();
       
            try {
				document = saxReader.read(file_path);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            Element root=document.getRootElement();
            replace_att(root);
            file_path=file_path.substring(6, file_path.length());
            XMLWriter writer = new XMLWriter(new FileWriter(new File(file_path)));      
            writer.write(document);      
            writer.close();      
            
	}
	
	@SuppressWarnings("deprecation")
	public void replace_att(Element element)
	{
		if(mar==true) return;
		List nodes = element.elements("children");   
		if(sor_ele!=null&&tar_ele!=null)
		{
			 List lss=tar_ele.attributes();
			 Boolean mmark=false;
			 for(Iterator pp=lss.iterator();pp.hasNext();)
			 {
				 Object obj= pp.next();
				 if(obj instanceof DefaultAttribute)
				 {
					 String str=((DefaultAttribute)obj).getName();
					if(str.equals(tar_pro))
						mmark=true;
				 }
				 
				 
			 }
			 mar=true;
			if(!mmark)
				tar_ele.addAttribute(sor_pro, sor_ele.attributeValue(sor_pro));
			else
				tar_ele.setAttributeValue(tar_pro, sor_ele.attributeValue(sor_pro));
			return;
		}
	    for (Iterator it = nodes.iterator(); it.hasNext();) {      
	        Element elm = (Element) it.next();      
	        List nodes_sec=elm.elements("element");
	       // Boolean mark=false;
	        String type=null;
	        if(!nodes_sec.isEmpty())
	        {
	        	Element tp=(Element)nodes_sec.iterator().next();
	        	System.out.println(tp.attributeValue("href").toString());
	        	if(tp.attributeValue("href").toString().indexOf(sor_id)!=-1)
	        	{
	        		sor_ele=elm;
	        	}
	        	if(tp.attributeValue("href").toString().indexOf(tar_id)!=-1)
	        	{
	        		tar_ele=elm;
	        	}
	        }
	       
	    }
	    for (Iterator it = element.elementIterator(); it.hasNext();) {      
	    		replace_att((Element)it.next());
	    	}
		
	}
	
	
	
//	public void replace_att(Element element)
//	{
//		  List nodes = element.elements("children");  
//		  Element sor_ele=null,tar_ele=null;
//		  for (Iterator it = nodes.iterator(); it.hasNext();) {      
//		        Element elm = (Element) it.next();      
//		        List nodes_sec=elm.elements("element");
//		        Boolean mark=false;
//		        String type=null;
//		        if(!nodes_sec.isEmpty())
//		        {
//		        	Element tp=(Element)nodes_sec.iterator().next();
//		        	List nodes_c= elm.elements("children");
//		        	for(Iterator j = nodes_c.iterator(); j.hasNext();)
//		        	{
//		        		Element ele_c=(Element)j.next();
//		        		List nodes_c_c=ele_c.elements("children");
//		        		for(Iterator p = nodes_c_c.iterator(); p.hasNext();)
//		        		{
//		        			Element ele_c_c=(Element)p.next();
//		        			if(ele_c_c)
//		        			System.out.println("pro_copy"); 
//		        		}
//			        //	System.out.println("pro_copy"); 
//		        	}
//		        	System.out.println("pro_copy"); 
////		        	if(tp.attributeValue("href").toString().indexOf(element_id)!=-1)
////		        	{
////		        		mark=true;
////		        	}
//		        	
//		        	if(tp.attributeValue("type").toString().indexOf("Link")!=-1)
//		        		type="Link";
//		        	else
//		        		type="Others";
//		        }
////		        if(mark)
////		        {
////		        	if(type=="Others")
////		        	elm.addAttribute("fillColor", "255");
////		        	else
////		            elm.addAttribute("fillColor", "255");
////		        	mark=false;
////		        }	
//		    	}
//		
//	}
	
}
