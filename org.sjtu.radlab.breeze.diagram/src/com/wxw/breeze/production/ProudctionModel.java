package com.wxw.breeze.production;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Vector;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;

import BreezeModel.diagram.edit.parts.LinkEditPart;
import BreezeModel.diagram.part.BreezeDiagramEditor;

import com.goku.breeze.ui.console.ConsoleFactory;
import com.goku.breeze.ui.console.ConsoleUtil;

public class ProudctionModel {
	String production_id;
	public architecture arc;
	public production[] pru=new production[10];
	public int pru_num;
	public String getProduction_id() {
		return production_id;
	}
	public void setProduction_id(String production_id) {
		this.production_id = production_id;
	}
	private String file_path = null;//文件路径
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
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
	  public Element getRootElement() {
	       return getDocument().getRootElement();
	    }
	 public void traversalDocumentByProductionID(String pro_id) {
		 Element root= getRootElement();
		 int mark=1;
		 Element arch1 = null,style1 = null;
		 for (Iterator ie = root.elementIterator(); ie.hasNext();) {
			 if(mark==1)
			  {
				 arch1=(Element) ie.next();
				 mark=0;
			  }
			 else
				 style1=(Element) ie.next();
	        } 
		 mark=1;
	        //Element style1= (Element)((Iterator) root.elements().iterator().next()).next();
		 	
		 arc=new architecture(arch1);
		 pru_num=0;
		 for (Iterator ie = style1.elementIterator(); ie.hasNext();) {
	    		Element tal=(Element) ie.next();
	    		Element left = null,right = null;
	    		if(tal.getQName().getName().equals("production"))
	    		{
	    		
	    			 mark=1;
	    			for (Iterator ie1 = tal.elementIterator(); ie1.hasNext();) {
	    				 if(mark==1)
	    				  {
	    					 left=(Element) ie1.next();
	    					 mark=0;
	    				  }
	    				 else
	    					 right=(Element) ie1.next();
	    		        } 
//		    		if(left==null)
//		    			{
//		    				
//		    			}
	    			 pru[pru_num]=new production(new architecture(left),new architecture(right));
	    			 pru[pru_num].id=tal.attributeValue("id");
	    			 pru_num++;
	    		}
	    		
	    	}
		// System.out.println(" ");
		 production_exe(pro_id);
	 }
	 public void production_exe(String production_id)
	 {		 Boolean left_match=false;
		 int ts_pro=-1;
		 for(int i=0;i<pru_num;i++)
		 {
			 if(pru[i].id.toString().equals(production_id))
				 ts_pro=i;
			 
		 }
		 if(!(ts_pro>=0))
			 return;
		int[][] mmark=new int[100][100];//每行第一个数标记个数
		for(int i=0;i<pru[ts_pro].left.num_node;i++)
		{
		
				for(int j=0;j<arc.num_node;j++)
				{
					if(check_template_proper(arc.nd[j],pru[ts_pro].left.nd[i]))
					{
						mmark[i][0]++;
						mmark[i][mmark[i][0]]=j;
					}
					
				}
			
		}
		int num_p=1,pru_left_num=pru[ts_pro].left.num_node;
		for(int i=0;i<pru[ts_pro].left.num_node;i++)			
			num_p=num_p*mmark[i][0];
		for(int i=0;i<num_p;i++)
		{ 
			int[] ddata=new int[pru_left_num];
			int ttra=i;
			for(int j=0;j<=pru_left_num-1;j++)
			{
				int mul=1;
				for(int p=j+1;p<pru_left_num;p++)
					mul=mul*mmark[p][0];
				
				ddata[j]=ttra/mul;
				ttra=ttra%mul;
			}
			Boolean left_com=true;
			for(int p=0;p<pru[ts_pro].left.num_node;p++)
			{
				for(int q=0;q<pru[ts_pro].left.num_node;q++)
					if(p!=q)
					if(pru[ts_pro].left.data[p][q]!=arc.data[mmark[p][ddata[p]+1]][mmark[q][ddata[q]+1]])
					{
						left_com=false;
					}
			}
			if(left_com)
			{
				pru[ts_pro]=production_reorder(pru[ts_pro]);
				if(!left_right_com(pru[ts_pro]))
				{				
    				ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "The left and right graph are not match"+ "\n");//Display.getDefault().asyncExec(new ConsolePrinter(cmd + "\n", ConsolePrinter.TYPE_MESSAGE));
    		
				}
				if(left_right_com(pru[ts_pro])&&pru[ts_pro].left.num_com==pru[ts_pro].right.num_com&&pru[ts_pro].right.num_con==pru[ts_pro].right.num_con)
				{
					int nnum=pru[ts_pro].left.num_node;
					for(int ii=0;ii<nnum;ii++)
						for(int jj=0;jj<nnum;jj++)
						{
							if(pru[ts_pro].left.data[ii][jj]!=pru[ts_pro].right.data[ii][jj])
							{
								if(pru[ts_pro].left.data[ii][jj]==1&&pru[ts_pro].right.data[ii][jj]==0)
								{
									LineDelete_s lds=new LineDelete_s(arc.nd[mmark[ii][ddata[ii]+1]].id,arc.nd[mmark[jj][ddata[jj]+1]].id,arc);
									lds.exe();
								}
								else
									if(pru[ts_pro].left.data[ii][jj]==0&&pru[ts_pro].right.data[ii][jj]==1)
									{															
										port so_port=null,tr_port=null;
										String s_node_id,t_node_id;
										s_node_id=pru[ts_pro].right.nd[ii].id;
										t_node_id=pru[ts_pro].right.nd[jj].id;
									
										for(int iii=0;iii<pru[ts_pro].right.edge_num;iii++)
										{
											if(s_node_id.equals(pru[ts_pro].right.ed[iii].source_node)&&t_node_id.equals(pru[ts_pro].right.ed[iii].target_node))
											{
												
												for(int pp=0;pp<pru[ts_pro].right.nd[ii].port_num;pp++)
												{
													if(pru[ts_pro].right.nd[ii].pt[pp].id.equals(pru[ts_pro].right.ed[iii].source_port))
														so_port=pru[ts_pro].right.nd[ii].pt[pp];
//													
													
												}
												for(int pp=0;pp<pru[ts_pro].right.nd[jj].port_num;pp++)
												{
												if(pru[ts_pro].right.nd[jj].pt[pp].id.equals(pru[ts_pro].right.ed[iii].target_port))
													tr_port=pru[ts_pro].right.nd[jj].pt[pp];
												}
											}
											
										}
										port s_port_use = null,t_port_use = null;
										for(int iii=0;iii<arc.nd[mmark[ii][ddata[ii]+1]].port_num;iii++)
										{
											if(arc.nd[mmark[ii][ddata[ii]+1]].pt[iii].direction==so_port.direction&&arc.nd[mmark[ii][ddata[ii]+1]].pt[iii].multiaccess==so_port.multiaccess)
											{
												s_port_use=arc.nd[mmark[ii][ddata[ii]+1]].pt[iii];
											}
												
											
										}
										for(int iii=0;iii<arc.nd[mmark[jj][ddata[jj]+1]].port_num;iii++)
										{
											if(arc.nd[mmark[jj][ddata[jj]+1]].pt[iii].direction==tr_port.direction&&arc.nd[mmark[jj][ddata[jj]+1]].pt[iii].multiaccess==tr_port.multiaccess)
											{
												t_port_use=arc.nd[mmark[jj][ddata[jj]+1]].pt[iii];
											}
										}
										LineAdd_s las=new LineAdd_s(s_port_use,t_port_use,arc);
										las.exe();
									}
							}
							
						}
					
				}
				
				else
				{
					if(pru[ts_pro].left.num_node==1&&pru[ts_pro].right.num_node==0)
					{
						
						String del_node_id=arc.nd[mmark[0][1]].id;
						//String del_node_id=pru[ts_pro].left.nd[0].id;
						int type=pru[ts_pro].left.nd[0].type;
						NodeDelete nd=new NodeDelete();
						nd.delete_node_id(del_node_id, type);
						
					}
					else
						if(pru[ts_pro].left.num_node==0&&pru[ts_pro].right.num_node==1)
						{
//							NodeAdd nad=new NodeAdd();
//							nad.node_from=pru[ts_pro].right.nd[0];
//							nad.drawLink();
							NodeAdd_s  nad=new NodeAdd_s(pru[ts_pro].right.nd[0]);
							nad.exe();
						}
					
					
				}
				
				
			} 
			
			
			
			
			
			
		}
		 
		
	 }
//	 public String find_edge_id(String source_node_id,String target_node_id)
//	 {
//		
//			//int s_node_id = 0,s_port_id=0,t_node_id = 0,t_port_id=0;
//			for(int i=0;i<arc.num_node;i++)
//			{
//				if(arc.nd[i].id.equals("source_node_id"))
//				{	
//					for(int j=0;j<arc.nd[i].port_num;j++)
//					{
//						String s_port_id,t_port_id;
//						for(int p=0;p<arc.edge_num;p++)
//						{
//							if(arc.ed[p].source_node.equals(arc.nd[j]))
//							
//						}
//					}
//					for(int j=0;j<arc.nd[i].port_num;j++)
//					{
//						
//					}
//				}
//			}
//		 
//	 }
	 public Boolean left_right_com(production pp)//验证左右子图是否匹配
	 {
		 int mark[]=new int[pp.left.num_node];
		 if(pp.left.num_node!=pp.right.num_node||pp.left.num_com!=pp.left.num_com)
			 return false;
			 
		 for(int i=0;i<pp.left.num_node;i++)
		 {
			 int j;
			 for( j=0;j<pp.right.num_node;j++)
			 {
				 if(check_node_proper(pp.left.nd[i],pp.right.nd[j])&&mark[j]==0)
				 {
					 mark[j]=1;
					 break;
				 }
			 }
			 if(j==pp.right.num_node)
				 return false;
		 }
		 return true;
	 }
	 public production production_reorder(production pp)
	 {
		 //production trs=new production(pp);
		 production prd;
		 int count=0;
		 int[][] tem_matrix=new int[pp.right.num_node+1][pp.right.num_node+1];
		 for(int ii=0;ii<pp.right.num_node+1;ii++)
			{
			tem_matrix[ii][0]=ii;
			tem_matrix[0][ii]=ii;
			}
		 int in_count=0;
		 for(int i=0;i<pp.left.num_node;i++)
		 {
			 node nd=new node();
			 nd=pp.left.nd[i];
			
			 for(int j=0;j<pp.right.num_node;j++)
			 {
				 if(check_node_proper(pp.left.nd[i],pp.right.nd[j]))
				 {
					
					prd=pp;
					node trs=new node();
					trs=prd.right.nd[in_count];//交换node中的节点j到正确的位置in_count
					prd.right.nd[in_count]=prd.right.nd[j];
					prd.right.nd[j]=trs;
					
					
					int ts_1;					//交换i,j行列
					ts_1=tem_matrix[in_count+1][0];
					tem_matrix[in_count+1][0]=tem_matrix[j+1][0];
					tem_matrix[j+1][0]=ts_1;
					ts_1=tem_matrix[0][in_count+1];
					tem_matrix[0][in_count+1]=tem_matrix[0][j+1];
					tem_matrix[0][j+1]=ts_1;
					j=j;
					in_count++;
				 }
			 }
			 
		 }
		 for(int ii=1;ii<pp.right.num_node+1;ii++)
			for(int jj=1;jj<pp.right.num_node+1;jj++)
				{
					tem_matrix[ii][jj]=pp.right.data[tem_matrix[ii][0]-1][tem_matrix[0][jj]-1];
				}
		 for(int ii=0;ii<pp.right.num_node;ii++)
			for(int jj=0;jj<pp.right.num_node;jj++)
				{
					//tem_matrix[ii][jj]=pp.right.data[tem_matrix[ii][0]][tem_matrix[0][jj]];
					pp.right.data[ii][jj]=tem_matrix[ii+1][jj+1];
				}
		 return pp;
		 
	 }
	 public Boolean check_node_proper(node n1,node n2)//production_reorder使用
	 {
		 if(n1.type==0&&n2.type==0)
		 {
			 if(n1.availability.equals(n2.availability))
			 return true;
			 else return false;
		 }
		 if(n1.port_num!=n2.port_num)
			 return false;
		 int[] s_mark=new int[n1.port_num];
		 for(int p=0;p<n1.port_num;p++)
		 {
			 Boolean mmark=false;
			 for(int q=0;q<n2.port_num;q++)
				 if(n1.pt[p].direction==n2.pt[q].direction&&n1.pt[p].multiaccess==n2.pt[q].multiaccess&&s_mark[q]==0)
				 {
					 mmark=true;
					 s_mark[q]=1;
					 break;
				 }
			 if(mmark==false)
				 return false;
		 }
		 if(n1.type!=n2.type||!(n1.tr.equals(n2.tr)))//判断构件模板是否相同
		 {
			 return false;
		 }
//		 if(n1.availability!=n2.availability)//判断构件状态是否一致
//		 {
//			 return false;
//		 }
//		 
		 Iterator<Entry<String, String>> iter = n1.att.entrySet().iterator();
		 while (iter.hasNext()) {
			 Entry<String, String> entry;
			 String key=null;
		     entry = iter.next();
		     key=entry.getKey(); 
		     Iterator<Entry<String, String>> iter1 = n2.att.entrySet().iterator();
		     int tag=0;
		     while(iter1.hasNext()){
		    	Entry<String, String>  entry_2=iter1.next();
				if(entry_2.getKey().equals(key))
					{
						tag=1;
					}
				    	 
		     }
		     if(tag==0)
		    	 return false;
		 }
		 return true;
		 
	 }
	 public Boolean check_template_proper(node n1,node n2)//n1为architecture中的n2为production重的
	 {
	
		 if(n1.type==0&&n2.type==0)
			 return true;
		 if(n1.port_num!=n2.port_num)
			 return false;
		 int[] s_mark=new int[n1.port_num];
		 for(int p=0;p<n1.port_num;p++)
		 {
			 Boolean mmark=false;
			 for(int q=0;q<n2.port_num;q++)
				 if(n1.pt[p].direction==n2.pt[q].direction&&n1.pt[p].multiaccess==n2.pt[q].multiaccess&&s_mark[q]==0)
				 {
					 mmark=true;
					 s_mark[q]=1;
					 break;
				 }
			 if(mmark==false)
				 return false;
		 }
		 if(n1.type!=n2.type||!(n1.tr.equals(n2.tr)))//判断构件模板是否相同
		 {
			 return false;
		 }
		 if(!n1.availability.equals(n2.availability))//判断构件状态是否一致
		 {
			 return false;
		 }
			 Iterator<Entry<String, String>> iter = n2.att.entrySet().iterator();
			 while (iter.hasNext()) {
				 Entry<String, String> entry;
				 int min_bound,max_bound;
				 String key=null;
			     entry = iter.next();
			     int pos=entry.getValue().indexOf("|");
			     key=entry.getKey();
			     min_bound = Integer.parseInt(entry.getValue().substring(0,pos));
			     max_bound=Integer.parseInt(entry.getValue().substring(pos+1,entry.getValue().length()));			     
			     Iterator<Entry<String, String>> iter1 = n1.att.entrySet().iterator();
			     int tag=n2.att.size();
			     while(iter1.hasNext()){
			    	 Entry<String, String> entry1;
			    	 entry1=iter1.next();
			    	 String key1 = entry1.getKey();
			    	 if(key1.equals(key))
			    	 {
			    		 
			    		 int val=Integer.parseInt(entry1.getValue());
			    		 if(val>=min_bound&&val<=max_bound)
			    		 {
			    			 tag--;
			    		 }
			    	 }
			     }
			     if(tag!=0)
			    	 return false;
			 }
			 
			
		 
		 return true;
	 }

}
