package com.wxw.breeze.production;

import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.goku.breeze.ui.console.ConsoleFactory;
import com.goku.breeze.ui.console.ConsoleUtil;

public class architecture {
	public int num_node;//构件与链接件数目
	public int num_com;//构件的数目
	public int num_con;//连接件的数目
	public int edge_num;//边的数目
	public int[][] data=new int[100][100];//描述左图的结构邻接矩阵表示
	public node[] nd=new node[100];{ //存储每个node的具体内容
		for (int i=0; i<100; i++)
		{
			nd[i] = new node();
		} 
	}
	public edge[] ed=new edge[500];{//存储每个edge的具体内容
		for (int i=0; i<500; i++)
		{
			ed[i] = new edge();
		} 
	}
	//public String file_path = null;//文件路径	
//	public Document doc;
	public style st;
	public Element arch1;
	public Element style;
	public architecture(Element arch1) {
		super();
		//this.doc = doc;
		this.arch1 = arch1;
		getArchModel();
		//System.out.println("over");
	}
	
	public architecture() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void getStyle(Element tal)
	{

		if(tal.getName().toString().equals("style"))
			{
				for (Iterator ie_s = tal.elementIterator(); ie_s.hasNext();) {
					Element tal_s=(Element) ie_s.next();
					if(tal_s.getName().toString().equals("arch"))
					{	NodeTemplate[] ntp=new NodeTemplate[50];{ //存储每个node的具体内容
						for (int i=0; i<50; i++)
						{
							ntp[i] = new NodeTemplate();
						} 
					}
					
						int ntp_num=0;
						for (Iterator ie_s_a = tal_s.elementIterator(); ie_s_a.hasNext();) 
						{
							Element tal_s_a=(Element) ie_s_a.next();
							ntp[ntp_num].name="dafdff";
							ntp[ntp_num].name=tal_s_a.attributeValue("name");
							ntp[ntp_num].id=tal_s_a.attributeValue("id");
							if(tal_s_a.attributeValue("type")==null)
								ntp[ntp_num].type="COMPONENT";
							else
								ntp[ntp_num].type=tal_s_a.attributeValue("type");
							int att_num=0;
							for(Iterator ie_s_a_a = tal_s_a.elementIterator(); ie_s_a_a.hasNext();)
							{
								Element tal_s_a_a=(Element) ie_s_a_a.next();
								ntp[ntp_num].att.put(tal_s_a_a.attributeValue("key"), tal_s_a_a.attributeValue("value"));
							}
							
							
							ntp_num++;
							
						}
						st=new style(ntp,ntp_num);
					}
				}
			}
	}
	public void getArchModel()
	{
		int node_list=0;
		int false_mark=1;
    	for (Iterator ie = arch1.elementIterator(); ie.hasNext();) {
    		Element tal=(Element) ie.next(); 
        	if(tal.attribute("type")!=null){
        	if(tal.attribute("type").toString().indexOf("Breeze:Component")!=-1||tal.attribute("type").toString().indexOf("Breeze:Connector")!=-1){
        		num_node++;
        		if(tal.attribute("type").toString().indexOf("Breeze:Component")!=-1)
        		num_com++;
        		else
        			num_con++;
        		nd[node_list]=new node();
        		nd[node_list].id=tal.attributeValue("id");
        		nd[node_list].name=tal.attributeValue("name");
        		nd[node_list].tr=tal.attributeValue("TR");
        		if(tal.attributeValue("availability")!=null)
        			nd[node_list].availability=tal.attributeValue("availability");
        		else
        			nd[node_list].availability="Undetermined";
        		if(tal.attribute("type").toString().indexOf("Breeze:Component")!=-1)
        			nd[node_list].type=1;
        		else
        			nd[node_list].type=0;
        		nd[node_list].port_num=0;
        		List att_list=tal.elements("attribute");
        		for(Iterator ie_att=att_list.iterator();ie_att.hasNext();)
        		{
        			Element att_trs=(Element)ie_att.next();
        			//nd[node_list].att.put("1","2");
        			nd[node_list].att.put(att_trs.attributeValue("key"), att_trs.attributeValue("value"));
        		}
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
        			
        			if(port_trs.attributeValue("name")!=null)
        			{
        				nd[node_list].pt[tras].name=port_trs.attributeValue("name");
        			}
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
        			ed[edge_num].id=tal.attributeValue("id");
        			ed[edge_num].source_port=sor_port;
        			ed[edge_num].target_port=tar_port;
        			int s_node_id = 0,s_port_id=0,t_node_id = 0,t_port_id=0;
        			for(int i=0;i<num_node;i++)
        			{
        				for(int j=0;j<nd[i].port_num;j++)
        				{
        					if(nd[i].pt[j].id.equals(sor_port))
        					{
        						s_node_id=i;
        						s_port_id=j;
        						ed[edge_num].source_node=nd[i].id;
        					}
        					else
        						if(nd[i].pt[j].id.equals(tar_port))
        						{
        							t_node_id=i;
            						t_port_id=j;
            						ed[edge_num].target_node=nd[i].id;
        						}
        				}
        				
        			}
        			int s_port_t=nd[s_node_id].pt[s_port_id].direction;//源端口类型
        			int t_port_t=nd[t_node_id].pt[t_port_id].direction;//目标端口类型
        			
        			if((s_port_t==0&&t_port_t==0)||(s_port_t==1&&t_port_t==1)||(s_port_t==0&&t_port_t==2)||(s_port_t==1&&t_port_t==2)||(s_port_t==2&&t_port_t==0||(s_port_t==2&&t_port_t==1)))
        			{
        				false_mark=0;
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
        			
        		
        			for(int i=0;i<edge_num;i++)
        			{
        				 		
        				String s_n_id=ed[edge_num].source_node;
        				String t_n_id=ed[edge_num].target_node;
        				int s_n_node = 0,t_n_node = 0;
        				for(int j=0;j<num_node;j++)
        				{
        					if(s_n_id==null||nd[j].id==null)
        						System.out.println("asdfdsf");
        					
        					if(nd[j].id.equals(s_n_id))
        						s_n_node=j;
        					if(nd[j].id.equals(t_n_id))
        						t_n_node=j;
        				}
        				if(data[t_n_node][s_n_node]==1)
        				{
        					String trs=null;
        					trs=ed[edge_num].source_node;
        					ed[edge_num].source_node=ed[edge_num].target_node;
        					ed[edge_num].target_node=trs;
        					
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
        				false_mark=0;
        			}
        			edge_num++;
        		}
        	
        		
        		
        		
        	}
        }
//    	if(false_mark==1)
//    		ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "Please check if the architecture accords with common constrain"+ "\n"); 
	}
}
