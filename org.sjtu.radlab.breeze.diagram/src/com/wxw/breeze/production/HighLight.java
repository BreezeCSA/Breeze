package com.wxw.breeze.production;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class HighLight {
	String file_path;
	String element_id;
	 Document document = null;
	public HighLight(String file_path, String element_id) {
		super();
		this.file_path = file_path;
		this.element_id = element_id;
	}
	public void exe() throws IOException
	{
		file_path=file_path+"_diagram";
		SAXReader saxReader = new SAXReader();
       
            try {
				document = saxReader.read(file_path);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            Element root=document.getRootElement();
            find_element(root);
            XMLWriter writer = new XMLWriter(new FileWriter(new File(file_path)));      
            writer.write(document);      
            writer.close();      
//            FileWriter out = new FileWriter(file_path);
//            document.write(out); 
            
	}
	private void find_element(Element element){

	List nodes = element.elements("children");   
    for (Iterator it = nodes.iterator(); it.hasNext();) {      
        Element elm = (Element) it.next();      
        List nodes_sec=elm.elements("element");
        Boolean mark=false;
        String type=null;
        if(!nodes_sec.isEmpty())
        {
        	Element tp=(Element)nodes_sec.iterator().next();
        	System.out.println(tp.attributeValue("href").toString());
        	if(tp.attributeValue("href").toString().indexOf(element_id)!=-1)
        	{
        		mark=true;
        	}
        	;
        	if(tp.attributeValue("type").toString().indexOf("Link")!=-1)
        		type="Link";
        	else
        		type="Others";
        }
        if(mark)
        {
        	if(type=="Others")
        	elm.addAttribute("fillColor", "255");
        	else
            elm.addAttribute("fillColor", "255");
        	mark=false;
        }	
    	}
    for (Iterator it = element.elementIterator(); it.hasNext();) {      
    		find_element((Element)it.next());
    	}
	}
}
