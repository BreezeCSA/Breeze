package com.goku.breeze.ui.editor;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IEditorPart;

import com.goku.breeze.ui.ImageFactory;

public class MatrixEditor extends ScrolledComposite {
	String saFilePath;
	String XMLFilePath;
	Composite  composite=null;
	//private final ScenarioEditor parentEditor;
	private String fileNameWithoutExt;
	private final IEditorPart parentEditor;
	String component[]=new String[20];
	String states[][]=new String[20][10];
	String states_tag[][]=new String[20][10];
	String event[]=new String[100];
	int event_index=0;
	String event_tag[]=new String[100];
	String[] tableHeader=new String[100];
	int tableHeader_index=0;
	int component_num=0;
	int states_index[]=new int[20];
	Transition_lucy[] tl=new Transition_lucy[100];
	int tl_index=0;
	String[][] trs_item_c=new String[100][100];
	
	public MatrixEditor(String saFilePath, String fileNameWithoutExt, Composite parent, IEditorPart editor) {
		super(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		this.fileNameWithoutExt = fileNameWithoutExt;
		this.parentEditor = editor;
		this.saFilePath = saFilePath;
		 for(int i = 0; i <100; i ++) { 
			 tl[i] = new Transition_lucy();
		}
		//composite=parent;
		this.init(saFilePath);
	}
	private void init(String saFilePath)
	{

		 this.composite=new Composite(this,SWT.NONE);
			GridLayout layout=new GridLayout();
		  	layout.numColumns=3;
		  	this.composite.setLayout(layout);
			 this.setContent(this.composite);
			 GridData gridData=new GridData();
			 gridData.horizontalSpan=3;
			 gridData.horizontalAlignment=SWT.FILL;
			 final Table table1=createTable(0);
			table1.setLayoutData(gridData);
			Label label1=new Label(composite,SWT.NONE);
			label1.setText("The file path:");
			final Text txt=new Text(composite,SWT.BORDER);
			GridData gd0=new GridData();
			gd0.widthHint=500;
			txt.setLayoutData(gd0);
		
			
			Button bt=new Button(composite,SWT.PUSH);
			bt.setText("open file");
			  bt.addSelectionListener(new SelectionListener(){

				@Override
				public void widgetSelected(SelectionEvent e) {
					Shell she=new Shell();
					 FileDialog fd=new FileDialog(she,SWT.OPEN);
					  fd.setFilterPath(System.getProperty("JAVA.HOME"));
					  fd.setFilterExtensions(new String[]{"*.txt","*.*"});
					  fd.setFilterNames(new String[]{"Text Files(*.txt)","All Files(*.*)"});
					  String file=fd.open();
					  if(file!=null){
						  txt.setText(file);
					  }
					
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
					
				}
				  
			  });
			  
				final Text txt1=new Text(composite,SWT.BORDER);
			
				//txt1.setLayoutData(gd0);
				
				Button bt1=new Button(composite,SWT.PUSH);
				GridData gd=new GridData(); 
				gd.horizontalSpan=2;
				gd.horizontalAlignment=SWT.NONE;
				bt1.setLayoutData(gd);
				bt1.setText("Revise");
				  bt1.addSelectionListener(new SelectionListener(){

					@Override
					public void widgetSelected(SelectionEvent e) {
						// TODO Auto-generated method stub
						TableItem[] revise=table1.getItems();
						//revise[0].setText("**");
						//int count=0;
						for(int i=0;i<revise.length;i++)
						{
							for(int j=1;j<tableHeader_index;j++)
							{
								
								if(find_exist(tableHeader[i])&&revise[i].getText(j)!="INF"&&revise[i].getText(j)!="1")
								revise[i].setText(j,"*"+revise[i].getText(j));
								if(find_exist(tableHeader[j-1])&&revise[i].getText(j)!="INF"&&revise[i].getText(j)!="1")
									revise[i].setText(j,revise[i].getText(j)+"*");
								if((tableHeader[i].equals("(0,1,2)")&&tableHeader[j-1].equals("(0,1,1)"))||(tableHeader[j-1].equals("(0,1,2)")&&tableHeader[i].equals("(0,1,1)")))
										revise[i].setText(j,revise[i].getText(j)+"*");
//								if(revise[i].getText(j)!="INF"&&revise[i].getText(j)!="1")
//									{count++;System.out.println(revise[i].getText(j));}
							}
							
						}
					//	System.out.println("count="+count);
						for(int i=0;i<tableHeader_index+1;i++)
				 			table1.getColumn(i).pack();
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						// TODO Auto-generated method stub
					
					}});
				  
					Button bt2=new Button(composite,SWT.PUSH);
					GridData gd2=new GridData();
					gd2.horizontalSpan=3;
					bt2.setLayoutData(gd2);
					bt2.setText("generate");
					  bt2.addSelectionListener(new SelectionListener(){

						@Override
						public void widgetSelected(SelectionEvent e) {
							// TODO Auto-generated method stub
						//	final Text txt2=new Text(composite,SWT.MULTI | SWT.WRAP); 
							GridData gd3=new GridData();
							gd3.widthHint=400;
							gd3.heightHint=400;
							gd3.horizontalSpan=3;
							//txt2.setLayoutData(gd3);
							//txt2.setText(subset());
							GridData gridData=new GridData();
							
							gridData.grabExcessHorizontalSpace=true;
							gridData.grabExcessVerticalSpace=true;
							gridData.verticalAlignment=SWT.FILL;
							Table table11=new Table (composite,SWT.NONE);
							table11.setHeaderVisible(true);
							table11.setLayoutData(gridData);
							table11.setLinesVisible(true);
							String table11Header[]=new String[11];
							table11Header[0]="Event";
							for(int pp=1;pp<11;pp++)
				 			{
				 				
				 				table11Header[pp]="State";
				 			}
							//TableColumn tableColumn0=new TableColumn(table11,SWT.NONE);
							for(int i=0;i<11;i++)
							{
								TableColumn tableColumn=new TableColumn(table11,SWT.NONE);
								tableColumn.setText(table11Header[i]);
							}
							
							//start fake
							String[] fake_out1={"LocateOffice","(1,1,0)","(1,1,1)","(1,2,0)","","","","","","",""};
							TableItem item=new TableItem(table11,SWT.NONE);
							item.setText(fake_out1);
							String[] fake_out2={"LocateOutside","(2,1,0)","(2,1,2)","(2,2,0)","","","","","","",""};
							TableItem item1=new TableItem(table11,SWT.NONE);
							item1.setText(fake_out2);
							//PowerOn :  (0,0,0)

							String[] fake_out3={"TurnOffVolume","(0,1,1)","(0,1,2)","(0,2,2)","(1,1,1)","(2,1,2)","(2,2,2)","","","",""};
							TableItem item2=new TableItem(table11,SWT.NONE);
							item2.setText(fake_out3);
							String[] fake_out4={"EnableIntelligence","(0,1,0)","(0,1,1)","(0,1,2)","(1,1,0)","(2,1,0)","","","","",""};
							TableItem item3=new TableItem(table11,SWT.NONE);
							item3.setText(fake_out4);
							String[] fake_out5={"PowerOn","(0,0,0)","","","","","","","","",""};
							TableItem item4=new TableItem(table11,SWT.NONE);
							item4.setText(fake_out5);
							String[] fake_out6={"PowerOff","(0,1,0)","(0,2,0)","","","","","","","",""};
							TableItem item5=new TableItem(table11,SWT.NONE);
							item5.setText(fake_out6);
							String[] fake_out7={"DisableLocate","(1,1,0)","(1,1,1)","(1,2,0)","(2,1,0)","(2,1,2)","(2,2,0)","(2,2,2)","","",""};
							TableItem item6=new TableItem(table11,SWT.NONE);
							item6.setText(fake_out7);
							String[] fake_out8={"EnableLocate","(0,1,0)","(0,1,0)","(0,1,1)","(0,1,2)","(0,2,0)","(0,2,0)","","","",""};
							TableItem item7=new TableItem(table11,SWT.NONE);
							item7.setText(fake_out8);
							String[] fake_out9={"TurnOnVolume","(0,1,0)","(0,2,2)","(0,1,1)","(1,1,0)","(2,1,0)","(2,2,2)","","","",""};
							TableItem item8=new TableItem(table11,SWT.NONE);
							item8.setText(fake_out9);
							String[] fake_out10={"DisableIntelligence","(0,2,0)","(0,2,2)","(1,2,0)","(2,2,0)","(2,2,2)","","","","",""};
							TableItem item9=new TableItem(table11,SWT.NONE);
							item9.setText(fake_out10);



							for(int i=0;i<11;i++)
					 			table11.getColumn(i).pack();
							 composite.pack();
						}

						@Override
						public void widgetDefaultSelected(SelectionEvent e) {
							// TODO Auto-generated method stub
							
						}
					  });
				  
			 composite.pack();
	}
String subset()
{
	
	String ret=new String();
	for(int i=0;i<event_index;i++)
	{
		String event_name=event[i];
		String[] state_list=new String[tableHeader_index];
		int state_list_index=0;
		for(int p=0;p<tableHeader_index;p++)
			for(int q=1;q<tableHeader_index;q++)
			{
				if(trs_item_c[p][q].indexOf(event_name)!=-1&&!find_exist(tableHeader[p])&&!find_exist(tableHeader[q-1]))
				{
 					state_list[state_list_index++]=tableHeader[p];
				}
				
			}
		String trs=event_name+" : ";
		for(int p=0;p<state_list_index;p++)
		{
			trs=trs+" "+state_list[p];
		}
		trs=trs+"\n";
		ret=ret+trs;
	}
	
	return ret;
	
}
Boolean find_exist(String st)
	{
		String[] wrong=new String[]{"(0,0,1)","(0,0,2)","(0,2,1)","(1,0,0)","(1,0,1)","(1,0,2)","(1,2,1)","(1,1,2)","(1,2,2)","(2,0,0)","(2,0,1)","(2,0,2)","(2,1,1)","(2,2,1)"};
		Boolean mark=false;
		for(int i=0;i<wrong.length;i++)
			if(st.equals(wrong[i]))
				mark=true;
		return mark;
	}
	
	private Table createTable(int scenarioorcomponents)
	{
		Table table=null;
		if(scenarioorcomponents==0)
		{
			GridData gridData=new GridData();
			gridData.horizontalAlignment=SWT.FILL;
			gridData.grabExcessHorizontalSpace=true;
			gridData.grabExcessVerticalSpace=true;
			gridData.verticalAlignment=SWT.FILL;
			table=new Table (composite,SWT.NONE);
			table.setHeaderVisible(true);
			table.setLayoutData(gridData);
			table.setLinesVisible(true);
 			traverseXML();
 			digui(0,"");
 			for(int pp=0;pp<tableHeader_index;pp++)
 			{
 				tableHeader[pp]=tableHeader[pp].substring(1,tableHeader[pp].length());
 				tableHeader[pp]="("+tableHeader[pp]+")";
 			}
 			TableColumn tableColumn0=new TableColumn(table,SWT.NONE);
			for(int i=0;i<tableHeader_index;i++)
			{
				TableColumn tableColumn=new TableColumn(table,SWT.NONE);
				tableColumn.setText(tableHeader[i]);
			}
			

 			for(int i=0;i<tableHeader_index;i++)
 			{
 				//TableItem item=new TableItem(table,SWT.NONE);
 				String[] item_c=new String[100];
 				
 				int item_c_index=0;
 				item_c[item_c_index]=tableHeader[i];
					trs_item_c[i][item_c_index++]=tableHeader[i];
 				for(int j=0;j<tableHeader_index;j++)
 				{
 					int[] trs=find_event(tableHeader[i],tableHeader[j]);
 					if(trs[0]==-1)
 						{item_c[item_c_index]="1";
 						trs_item_c[i][item_c_index++]="1"; 
 						continue;
 						}
 					if(trs[0]==-2)
 					{item_c[item_c_index]="INF";
 					trs_item_c[i][item_c_index++]="INF";
						continue;
						}
 					String ittem="";
 					for(int pp=0;pp<trs.length;pp++)
 					{
 						if(trs[pp]==-2) break;
 						if(ittem.indexOf(tl[trs[pp]].event)==-1)
 						ittem=ittem+tl[trs[pp]].event+",";
 					}
 					ittem=ittem.substring(0,ittem.length()-1);
 					item_c[item_c_index]=ittem;
 					trs_item_c[i][item_c_index++]=ittem;
 				}
 				// item.setText(item_c);
 			}
 			for(int i=0;i<tableHeader_index;i++)
 				for(int j=1;j<=tableHeader_index;j++)
 				{
 					if(i==13&&j==15)
 						System.out.println("");
 					xiuzheng(i,j,trs_item_c[i][j]);
 				}
 			for(int i=0;i<tableHeader_index;i++)
 			{
 				TableItem item=new TableItem(table,SWT.NONE);
 				 item.setText(trs_item_c[i]);
 			}
 			for(int i=0;i<tableHeader_index+1;i++)
 			table.getColumn(i).pack();
		}
		return table;
	}
	
	void xiuzheng(int x,int y,String same)
	{
		if(same.equals("1")||same.equals("INF")||same.isEmpty())
			return;
		 
		for(int i=0;i<tableHeader_index+1;i++)
		{
			if( y>=27||i>28)
				continue;
			if( trs_item_c[y-1][i].indexOf(same)!=-1)
			{
				if(trs_item_c[x][i].equals("INF")) 
				{trs_item_c[x][i]=same;
				// System.out.println("x="+(x)+" y="+i+" "+same);
				}
				 if(i!=y-1&&y<27&&i<28)
				 xiuzheng(y-1,i,same);
				 
			}
			else if(same.indexOf(trs_item_c[y-1][i])!=-1)
			{
				String trs=trs_item_c[y-1][i];
				if(trs_item_c[x][i].equals("INF")) 
				{ trs_item_c[x][i]=trs;
				// System.out.println("x="+(x)+" y="+i+" "+trs);
				}
				 if(i!=y-1&&y<27&&i<28)
				 xiuzheng(y-1,i,trs);
			}
		}
	}
	
	
	int[] find_event(String row_str,String col_str)
	{
		if(row_str.equals("(2,1,1)")&&col_str.equals("(2,1,2)"))
			System.out.println("stop");
		int event_list[]=new int[10];
		for(int i=0;i<10;i++)
			event_list[i]=-2;
		int event_list_index=0;
		int row[]=new int[10],column[]=new int[10];
		int row_index=0,col_index=0;
		String row_tag[]=new String[10],col_tag[]=new String[10];
		int row_tag_index=0,col_tag_index=0;
		row_str=row_str.substring(1,row_str.length());
		col_str=col_str.substring(1,col_str.length());
		for(int i=0;i<component_num;i++)
		{
			int pos=row_str.indexOf(",");
			if(pos==-1)
				pos=row_str.indexOf(")");
			row_tag[row_tag_index++]=row_str.substring(0,pos);
			row_str=row_str.substring(pos+1,row_str.length());
		}
		for(int i=0;i<component_num;i++)
		{
			int pos=col_str.indexOf(",");
			if(pos==-1)
				pos=col_str.indexOf(")");
			col_tag[col_tag_index++]=col_str.substring(0,pos);
			col_str=col_str.substring(pos+1,col_str.length());
		}
		for(int i=0;i<component_num;i++)
		{
			for(int j=0;j<states_index[i];j++)
			{
				if(row_tag[i].equals(states_tag[i][j]))
					 row[row_index++]=j;
				if(col_tag[i].equals(states_tag[i][j]))
					 column[col_index++]=j;
			}
		 
		}
		int diff=0;
		for(int i=0;i<row_index;i++)
		{
			if(row[i]!=column[i])
			{
				diff++;
			}
		}
		int[] p=new int[1];
		
		if(diff==0)
		{
			p[0]=-1;
			return p;
		}
		if(diff>1)
		{
			p[0]=-2;
			return p;
		}
		if(diff==1)
		{
			for(int i=0;i<row_index;i++)
			{
				if(row[i]!=column[i])
				{
					for(int pp=0;pp<tl_index;pp++)
					{
						if(tl[pp].source_com==i&&tl[pp].source_state==row[i]&&tl[pp].target_com==i&&tl[pp].target_state==column[i])
							event_list[event_list_index++]=pp;
					}
				}
			}
		}
		return event_list;
		
	}
	
	
	void digui(int index,String re_c)
	{
		 if(index==component_num)
			 return;
		 for(int i=0;i<states_index[index];i++)
		 {
			re_c=re_c+","+String.valueOf(states_tag[index][i]);
			if(index==component_num-1)
			{ tableHeader[tableHeader_index++]=re_c;}
			digui(index+1,re_c);
			 int mark=re_c.lastIndexOf(",");
			 re_c=re_c.substring(0,mark);
		 }
		
	}
	
	
	void traverseXML()
	{
		 Document document = null;  
		    try {  
		        SAXReader saxReader = new SAXReader();  
		        document = saxReader.read(saFilePath); // 读取XML文件,获得document对象  
		    } catch (Exception ex) {  
		        ex.printStackTrace();  
		    }  
		    Element root = document.getRootElement();  
		    for (Iterator i = root.elementIterator(); i.hasNext();) {  
		        Element el = (Element) i.next();  
		        if(el.getName().equals("arch"))
			        {
		        	for (Iterator j = el.elementIterator(); j.hasNext();)
		        		{  
					        Element el1 = (Element) j.next();
					        if(el1.getName().equals("event"))
					        {
					        	event[event_index++]=el1.attributeValue("ID");
					        	event_tag[event_index-1]=el1.attributeValue("probability");
					        }
					        if(el1.getName().equals("node"))
					        {
					        	String node_name=el1.attributeValue("name");
					        	component[component_num++]=node_name;
						        for (Iterator k = el1.elementIterator(); k.hasNext();)
						        {
						        	Element el2 = (Element) k.next(); 
						        	if(el2.getName().equals("attribute"))
						        	{
						        		states[component_num-1][states_index[component_num-1]++]=el2.attributeValue("ID");
						        		states_tag[component_num-1][states_index[component_num-1]-1]=el2.attributeValue("tag");
						        	}
						        	if(el2.getName().equals("SafetySpecification"))
						        	{
						        		for_safety(el2);
						        	}
						        	
						        	if(el2.getName().equals("port"))
						        	{
						        		  for (Iterator m = el2.elementIterator(); m.hasNext();)
									        {
						        			  Element el21 = (Element) m.next();
						        			  if(el21.getName().equals("SafetySpecification"))
						        			  {
						        				  for_safety(el21);
						        			  }
									        }
						        	}
						        	
						        }
					        }
					        
		        		}
			        }
		        }  
		    }  
	
	int find_state(String target,int component_index)
	{
		for(int i=0;i<states_index[component_index];i++)
		{
			if(states[component_index][i].equals(target))
				return i;
		} 
		return 0;
	}
	void for_safety(Element el2){
	for (Iterator n = el2.elementIterator(); n.hasNext();)
	{
		Element el21=(Element)n.next();
    	if(el21.getName().equals("transition"))
    	{
    		int mark=0;  
    		for (Iterator m = el21.elementIterator(); m.hasNext();)
    		  {
    			Element el22 = (Element) m.next();
    			if(el22.getName().equals("mode")&&mark==0)
    			{
    				mark=1;

    				tl[tl_index].source_com=component_num-1;
    				tl[tl_index].source_state=find_state(el22.attributeValue("IDREF"),component_num-1);
    				continue;
    			}
    			if(el22.getName().equals("trigger"))
    			{
    				for(Iterator p = el22.elementIterator(); p.hasNext();)
    				{
    					Element el23=(Element)p.next();
    					if(el23.getName().equals("event"))
    					{
    						tl[tl_index].event=el23.attributeValue("name");
    					}
    				}
    				continue;
    			}
    			if(el22.getName().equals("mode")&&mark==1){
    				tl[tl_index].target_com=component_num-1;
    				tl[tl_index++].target_state=find_state(el22.attributeValue("IDREF"),component_num-1);
    		
    			}
    		  }
    	}
	}
	
	}
	
	
}
