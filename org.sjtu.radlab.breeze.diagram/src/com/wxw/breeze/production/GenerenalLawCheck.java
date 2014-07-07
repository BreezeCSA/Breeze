package com.wxw.breeze.production;

import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RootEditPart;
import org.eclipse.swt.widgets.Display;

import BreezeModel.diagram.part.BreezeDiagramEditor;

import com.goku.breeze.ui.console.ConsoleFactory;
import com.goku.breeze.ui.console.ConsolePrinter;
import com.goku.breeze.ui.console.ConsoleUtil;

public class GenerenalLawCheck {
	int num_node;//构建与链接件数目
	int num_com;//构建的数目
	int num_con;//连接件数目 
//	int left_num;//production左图数
//	int right_num;//production右图数
	int[][] data=new int[100][100];//描述图的结构邻接矩阵表示
	//Map node_port=new HashMap();
	node[] nd=new node[100];{
		for (int i=0; i<100; i++)
		{
		       nd[i] = new node();
		} 
	}
//	node[] production_left=new node[100];{
//		for (int i=0; i<100; i++)
//		{
//			 production_left[i] = new node();
//		} 
//	}
//	node[] production_right=new node[100];{
//		for (int i=0; i<100; i++)
//		{
//			 production_right[i] = new node();
//		} 
//	}
	edge[] ed=new edge[500];{
		for (int i=0; i<500; i++)
		{
			ed[i] = new edge();
		} 
	}
	private String file_path = null;;//文件路径
	

	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public int getNum_node() {
		return num_node;
	}
	public void setNum_node(int num_node) {
		this.num_node = num_node;
	}
	public int getNum_com() {
		return num_com;
	}
	public void setNum_com(int num_com) {
		this.num_com = num_com;
	}
	public int getNum_con() {
		return num_con;
	}
	public void setNum_con(int num_con) {
		this.num_con = num_con;
	}
	 
	public int[][] getData() {
		return data;
	}
	public void setData(int[][] data) {
		this.data = data;
	}
	public void set_data()
	{ 
	       Document document = getDocument();
	       traversalDocumentByIterator();
		
		
	}
	   public Element getRootElement() {
	       return getDocument().getRootElement();
	    }
    public Document getDocument() {
        SAXReader saxReader = new SAXReader();
        Document document = null;
            try {
				document = saxReader.read(file_path);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       
        return document;
     }
    public void traversalDocumentByIterator() {
    
    	
    	
    	HighLight_s hls=new HighLight_s(null);
    	hls.exe_cancle();
        Element root= getRootElement();
        Element arch1= (Element)root.elements().iterator().next();
    	int node_list=0; 
        for (Iterator ie = arch1.elementIterator(); ie.hasNext();) {
        	Element tal=(Element) ie.next(); 
        	if(tal.attribute("type")!=null){
        	if(tal.attribute("type").toString().indexOf("Breeze:Component")!=-1||tal.attribute("type").toString().indexOf("Breeze:Connector")!=-1){
        		num_node++;
        		num_com++;
        		nd[node_list]=new node();
        		nd[node_list].id=tal.attributeValue("id");
        		nd[node_list].name=tal.attributeValue("name");
        		nd[node_list].tr=tal.attributeValue("TR");
        		if(tal.attribute("type").toString().indexOf("Breeze:Component")!=-1)
        			nd[node_list].type=1;
        		else
        			nd[node_list].type=0;
        		nd[node_list].port_num=0;
        		List port_list=tal.elements("port");
        		int tras=0;
        		for(Iterator ie_port=port_list.iterator();ie_port.hasNext();)
        		{
        			Element port_trs=(Element)ie_port.next();
        			nd[node_list].pt[tras].id=port_trs.attributeValue("id"); 
        			if(port_trs.attributeValue("direction")!=null)
        				if(port_trs.attributeValue("direction").equals("inout"))
        				nd[node_list].pt[tras].direction=2;
	        			else
	        				nd[node_list].pt[tras].direction=1;
        			else
        				nd[node_list].pt[tras].direction=0;
        		 
        			tras++;	
        			
        		}
        		nd[node_list].port_num=tras;
        		node_list++; 
        		}
        	}
        	else{
        		if(tal.getName().toString().equals("edge"))
        		{
        			String sor_port=tal.attributeValue("source");
        			String tar_port=tal.attributeValue("target");  
        			int s_node_id = 0,s_port_id=0,t_node_id = 0,t_port_id=0;
        			for(int i=0;i<num_node;i++)
        			{
        				for(int j=0;j<nd[i].port_num;j++)
        				{
        					if(nd[i].pt[j].id.equals(sor_port))
        					{
        						s_node_id=i;
        						s_port_id=j;
        					}
        					else
        						if(nd[i].pt[j].id.equals(tar_port))
        						{
        							t_node_id=i;
            						t_port_id=j;
        						}
        				}
        				
        			}
        			int s_port_t=nd[s_node_id].pt[s_port_id].direction;//源端口类型
        			int t_port_t=nd[t_node_id].pt[t_port_id].direction;//目标端口类型
        			
        			
        			if((s_port_t==0&&t_port_t==0)||(s_port_t==1&&t_port_t==1)||(s_port_t==0&&t_port_t==2)||(s_port_t==1&&t_port_t==2)||(s_port_t==2&&t_port_t==0||(s_port_t==2&&t_port_t==1)))
        			{
        				HighLight_s h1=new HighLight_s(nd[s_node_id].pt[s_port_id].id);
        				HighLight_s h2=new HighLight_s(nd[t_node_id].pt[t_port_id].id);
        				h1.exe();
        				h2.exe();
        				String cmd="The port direction between "+nd[s_node_id].name+" and "+nd[t_node_id].name+" are mismatch.";
        				ConsoleUtil.printMessage(ConsoleFactory.getConsole(), cmd+ "\n");//Display.getDefault().asyncExec(new ConsolePrinter(cmd + "\n", ConsolePrinter.TYPE_MESSAGE));
        			}
        			else
        			{
	        			
        				if(nd[s_node_id].pt[s_port_id].direction==0||nd[s_node_id].pt[s_port_id].direction==2)
	        			{
	        				data[t_node_id][s_node_id]=1;
	        				
	        			}
	        			if(nd[t_node_id].pt[t_port_id].direction==0||nd[t_node_id].pt[t_port_id].direction==2)
	        			{
	        				data[s_node_id][t_node_id]=1;
	        				
	        			}
	        			
        			}
        			if(nd[s_node_id].type==nd[t_node_id].type)
        			{
        				String s_node_type,t_node_type;
        				if(nd[s_node_id].type==1)
        				{
        					s_node_type="Component";
        				}
        				else
        					s_node_type="Connector";
        				if(nd[t_node_id].type==1)
        				{
        					t_node_type="Component";
        				}
        				else
        					t_node_type="Connector";
        					
        				String cmd=s_node_type+" "+nd[s_node_id].name+" should not connect with "+t_node_type+" "+nd[t_node_id].name+" directly.";
        				ConsoleUtil.printMessage(ConsoleFactory.getConsole(), cmd+ "\n");//Display.getDefault().asyncExec(new ConsolePrinter(cmd + "\n", ConsolePrinter.TYPE_MESSAGE));
        				HighLight_s h1=new HighLight_s(nd[s_node_id].id);
        				HighLight_s h2=new HighLight_s(nd[t_node_id].id);
        				h1.exe();
        				h2.exe();
        			}
        			
        		}
        		
        	}
        	
        
        }
//   	 System.out.println("adfasdf");	
    	for(int i=0;i<node_list;i++)
		{
			if(nd[i].type==0)
			{
				if(nd[i].port_num!=2)
				{
					String cmd="The port of Connector "+nd[i].name+" are mismatch(There shoule be 2 ports attach to one connector!!).";
    				ConsoleUtil.printMessage(ConsoleFactory.getConsole(), cmd+ "\n");
    				HighLight_s h1=new HighLight_s(nd[i].id);
    				h1.exe();
    			}
				else{
				
						int d1=nd[i].pt[0].direction,d2=nd[i].pt[1].direction;
						if((d1==d2&&d1!=2)||(d1!=d2&&((d1==1&&d2==2)||(d1==0&&d2==2)||(d1==2&&d2==0)||(d1==2&&d2==1))))
						{
							String cmd="The port of Connector "+nd[i].name+" are mismatch(The ports should be one in and one out or both inout!!).";
		    				ConsoleUtil.printMessage(ConsoleFactory.getConsole(), cmd+ "\n");
		    				HighLight_s h1=new HighLight_s(nd[i].id);
		    				h1.exe();
		    			}
						
					
				}
			}
				
		}
        
        
   
        
        // 枚举根节点下所有子节点
//        for (Iterator ie = root.elementIterator(); ie.hasNext();) {
//            System.out.println("======");
//            Element element = (Element) ie.next();
//            System.out.println(element.getName());
//  
//            // 枚举属性
//            for (Iterator ia = element.attributeIterator(); ia.hasNext();) {
//               Attribute attribute = (Attribute) ia.next();
//               System.out.println(attribute.getName() + ":"
//                      + attribute.getData());
//            }
//            // 枚举当前节点下所有子节点
//            for (Iterator ieson = element.elementIterator(); ieson.hasNext();) {
//               Element elementSon = (Element) ieson.next();
//               System.out.println(elementSon.getName() + ":"
//                      + elementSon.getText());
//            }
//        }
        
     }
//    public void getElementList(Element element) { 
//        List elements = element.elements(); 
//        if (elements.size() == 0) { 
//            //没有子元素 
//            String xpath = element.getPath(); 
//            String value = element.getTextTrim();  
//        } else { 
//            //有子元素 
//            for (Iterator it = elements.iterator(); it.hasNext();) { 
//                Element elem = (Element) it.next(); 
//                //递归遍历 
//                getElementList(elem); 
//            } 
//        } 
//    } 
//}
}
