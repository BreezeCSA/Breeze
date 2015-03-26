package com.goku.breeze.ui.editor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.goku.breeze.ui.ImageFactory;
import com.wxw.breeze.production.ProudctionModel;
import com.wxw.breeze.production.architecture;
import com.wxw.breeze.production.component;
import com.wxw.breeze.production.node;


public class ScenarioModelEditor extends ScrolledComposite {
	String saFilePath;
	String XMLFilePath;
	Composite  composite;
	private final ScenarioEditor parentEditor;

	node[] comlist=new node[100];
	int list_i=0;
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
			table=new Table (composite,SWT.CHECK);
			table.setHeaderVisible(true);
			table.setLayoutData(gridData);
			table.setLinesVisible(true);
			String[] tableHeader={"Scenario name","Scenario ID","ParallelScenario"};
			for(int i=0;i<tableHeader.length;i++)
			{
				TableColumn tableColumn=new TableColumn(table,SWT.NONE);
				tableColumn.setText(tableHeader[i]);
			}

	
			for(int i=0;i<tableHeader.length;i++)
			table.getColumn(i).pack();
		}
		else
			if(scenarioorcomponents==1)//生成构件list
			{
				GridData gridData=new GridData();
				gridData.horizontalAlignment=SWT.FILL;
				gridData.grabExcessHorizontalSpace=true;
				gridData.grabExcessVerticalSpace=true;
				gridData.verticalAlignment=SWT.FILL;
				table=new Table (composite,SWT.CHECK);
				table.setHeaderVisible(true);
				table.setLayoutData(gridData);
				table.setLinesVisible(true);
				String[] tableHeader={"Component name","Component ID"};
				for(int i=0;i<tableHeader.length;i++)
				{
					TableColumn tableColumn=new TableColumn(table,SWT.NONE);
					tableColumn.setText(tableHeader[i]);
				}

		
				for(int i=0;i<tableHeader.length;i++)
				table.getColumn(i).pack();
			}
			else
				if(scenarioorcomponents==2)//生成构件list
				{
					GridData gridData=new GridData();
					gridData.horizontalAlignment=SWT.FILL;
					gridData.grabExcessHorizontalSpace=true;
					gridData.grabExcessVerticalSpace=true;
					gridData.verticalAlignment=SWT.FILL;
					table=new Table (composite,SWT.CHECK);
					table.setHeaderVisible(true);
					table.setLayoutData(gridData);
					table.setLinesVisible(true);
					String[] tableHeader={"Message name","ID","Type","Source","Target","Vector Clock"};
					for(int i=0;i<tableHeader.length;i++)
					{
						TableColumn tableColumn=new TableColumn(table,SWT.NONE);
						tableColumn.setText(tableHeader[i]);
					}

			
					for(int i=0;i<tableHeader.length;i++)
					table.getColumn(i).pack();
				}
		return table;
		
	}
	public ScenarioModelEditor(String saFilePath, String fileNameWithoutExt, Composite parent, ScenarioEditor scenarioEditor) {
		super(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		this.saFilePath=saFilePath;
		parentEditor=scenarioEditor;
	
		String sa_path=saFilePath.substring(0, saFilePath.length()-9)+".breeze";
		architecture trs=componentlist(sa_path);
		for(int i=0;i<trs.num_node;i++)
		{
			if(trs.nd[i].type==1)
			comlist[list_i++]=trs.nd[i];
		}
		init();
		
		
		// TODO Auto-generated constructor stub
	}
	private void init()
	{
		

		
		
		composite=new Composite(this,SWT.NONE);
		final Button btnNewButton = new Button(composite, SWT.PUSH);//SWT.NONE默认是PUSH
	      btnNewButton.setText("Generate ADL");
	    final Button btnNewButton1 = new Button(composite, SWT.PUSH);//SWT.NONE默认是PUSH
	      btnNewButton1.setText("Generate UML");
		GridLayout layout=new GridLayout();
	
		
	  	layout.numColumns=1;
		composite.setLayout(layout);
		final Table table1=createTable(0);
		GridData gd=new GridData();
		gd.heightHint=100;
		gd.widthHint=500;
		table1.setLayoutData(gd);
		
		ToolBar toolBar=new ToolBar(composite,SWT.FLAT);
		final ToolItem add=new ToolItem(toolBar,SWT.PUSH);
		add.setText("add scenario");
		final ToolItem delete=new ToolItem(toolBar,SWT.PUSH);
		delete.setText("delete scenario");
		add.setImage(ImageFactory.getImageAdd());
		delete.setImage(ImageFactory.getImageDelete());
		final ArrayList<Table> result1=new ArrayList();
		final ArrayList<Table> result2=new ArrayList();
		
	
		final ArrayList<Control> controls = new ArrayList();
		final ArrayList<TableEditor> controls1 = new ArrayList();
		final ArrayList<Label> controls2=new ArrayList();
		final ArrayList<Table> controls3=new ArrayList();
		final ArrayList<ToolBar> controls4=new ArrayList();
		final ArrayList<Label> controls5=new ArrayList();
		final ArrayList<Table> controls6=new ArrayList();
		final ArrayList<ToolBar> controls7=new ArrayList();
		Listener listener= new Listener(){

			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				//TableEditor[] store=new TableEditor[100];
				int index=0;
				if(event.widget==add)
				{
					
					/*component list*/
					final Label lb=new Label(composite, SWT.NONE);
					lb.setText("Component List of Scenario                      ");
					lb.setSize(5,20);
					lb.setVisible(true);
					controls2.add(lb);
					
					final Table table2=createTable(1);
					GridData gd=new GridData();
					gd.heightHint=100;
				 	table2.setLayoutData(gd);
					table2.setVisible(true);
					TableItem item=new TableItem(table1,SWT.NONE);
					controls3.add(table2);
					table2.pack();
					result1.add(table2);
					ToolBar toolBar1=new ToolBar(composite,SWT.FLAT);
					final ToolItem add1=new ToolItem(toolBar1,SWT.PUSH);
					add1.setText("add component");
					final ToolItem delete1=new ToolItem(toolBar1,SWT.PUSH);
					delete1.setText("delete component");
					add1.setImage(ImageFactory.getImageAdd());
					delete1.setImage(ImageFactory.getImageDelete());
					controls4.add(toolBar1);

					
					final ArrayList<CCombo> controls8=new ArrayList(); //存储components list内容以备删除
					
					Listener listener1= new Listener(){ //listener of add1(components list)

						@Override
						public void handleEvent(Event event) {
							// TODO Auto-generated method stub
							if(event.widget==add1)
							{
								final TableItem item1=new TableItem(table2,SWT.NONE);
								TableItem[] items=table2.getItems();
								for(int i=items.length-1;i<items.length;i++)
								{
									final TableEditor c_list_editor=new TableEditor(table2);
									final CCombo c_list_combo=new CCombo(table2,SWT.NONE);
									controls8.add(c_list_combo);
									for(int h_i=0;h_i<list_i;h_i++)
									{
										c_list_combo.add(comlist[h_i].name);
									}
									c_list_combo.setText(items[i].getText(0));
									c_list_editor.grabHorizontal=true;
									c_list_editor.setEditor(c_list_combo, items[i], 0);
									c_list_combo.addModifyListener(new ModifyListener(){

										@Override
										public void modifyText(ModifyEvent e) {
											// TODO Auto-generated method stub
											c_list_editor.getItem().setText(0, c_list_combo.getText());
											for(int h_i=0;h_i<list_i;h_i++)
											{
												if(c_list_combo.getText().equals(comlist[h_i].name))
													item1.setText(1, comlist[h_i].id);
											}
										}
										
									});
								}
								
							}
							else
								if(event.widget==delete1)
								{
									TableItem[] items=table2.getItems();
									for(int i=0;i<items.length;i++)
									{
										if(!items[i].getChecked())
											continue;
										int index=table2.indexOf(items[i]);
										if(index<0) continue;
										
											controls8.get(index).dispose();
											controls8.remove(index);
										table2.remove(index);
									}
								}
						}};
					
					add1.addListener(SWT.Selection, listener1);
					delete1.addListener(SWT.Selection, listener1);
					
					/*message list*/
					
					
					final Label lb1=new Label(composite, SWT.NONE);
					lb1.setText("Message List of Scenario                      ");
					lb1.setSize(5,20);
					lb1.setVisible(true);
					controls5.add(lb1);
					
					final Table table3=createTable(2);
					gd.heightHint=120;
					gd.widthHint=500;
				 	table3.setLayoutData(gd);
					table3.setVisible(true);
					//TableItem item1=new TableItem(table3,SWT.NONE);
					controls6.add(table3);
					result2.add(table3);
					ToolBar toolBar2=new ToolBar(composite,SWT.FLAT);
					final ToolItem add2=new ToolItem(toolBar2,SWT.PUSH);
					add2.setText("add message");
					final ToolItem delete2=new ToolItem(toolBar2,SWT.PUSH);
					delete2.setText("delete message");
					add2.setImage(ImageFactory.getImageAdd());
					delete2.setImage(ImageFactory.getImageDelete());
					controls7.add(toolBar2);
					
				
					final ArrayList<TableEditor> controls91=new ArrayList(); //存储message list内容以备删除
					final ArrayList<Text> controls101=new ArrayList();
					final ArrayList<TableEditor> controls9=new ArrayList(); //存储message list内容以备删除
					final ArrayList<Text> controls10=new ArrayList();
					final ArrayList<TableEditor> controls11=new ArrayList();
					final ArrayList<CCombo> controls12=new ArrayList();
					final ArrayList<TableEditor> controls13=new ArrayList();
					final ArrayList<CCombo> controls14=new ArrayList();
					final ArrayList<TableEditor> controls15=new ArrayList();
					final ArrayList<CCombo> controls16=new ArrayList();
					final ArrayList<TableEditor> controls17=new ArrayList(); //存储message list内容以备删除
					final ArrayList<Text> controls18=new ArrayList();
					Listener listener2= new Listener(){ //listener of add1(message list)

						@Override
						public void handleEvent(Event event) {
							// TODO Auto-generated method stub
							if(event.widget==add2)
							{
								
								final TableItem item_m=new TableItem(table3,SWT.NONE);
								TableItem[] items_m=table3.getItems();
								for(int i=items_m.length-1;i<items_m.length;i++)
								{
									final TableEditor editor_m1=new TableEditor(table3);
									
									final Text text_m1=new  Text(table3,SWT.NONE);
								
									text_m1.setText(items_m[i].getText(0));
								 	editor_m1.grabHorizontal=true;
									editor_m1.setEditor(text_m1, items_m[i], 0);
									controls91.add(editor_m1);
									controls101.add(text_m1);
									
									text_m1.addModifyListener(new ModifyListener(){
										public void modifyText(ModifyEvent e){
											editor_m1.getItem().setText(0, text_m1.getText());
										}
									});
									
									final TableEditor editor_m2=new TableEditor(table3);
									
									final Text text_m2=new  Text(table3,SWT.NONE);
								
									text_m2.setText(items_m[i].getText(1));
								 	editor_m2.grabHorizontal=true;
									editor_m2.setEditor(text_m2, items_m[i], 1);
									controls9.add(editor_m2);
									controls10.add(text_m2);
									
									text_m2.addModifyListener(new ModifyListener(){
										public void modifyText(ModifyEvent e){
											editor_m2.getItem().setText(1, text_m2.getText());
										}
									});
									final TableEditor c_list_editor=new TableEditor(table3);
									final CCombo c_list_combo=new CCombo(table3,SWT.NONE);
									c_list_combo.add("request");
									c_list_combo.add("reply");
									c_list_combo.setText(items_m[i].getText(2));
									c_list_editor.grabHorizontal=true;
									c_list_editor.setEditor(c_list_combo, items_m[i], 2);
									c_list_combo.addModifyListener(new ModifyListener(){

										@Override
										public void modifyText(ModifyEvent e) {
											// TODO Auto-generated method stub
											c_list_editor.getItem().setText(2, c_list_combo.getText());
											
										}
										
									});
									controls11.add(c_list_editor);
									controls12.add(c_list_combo);
									
									final TableEditor c_list_editor1=new TableEditor(table3);
									final CCombo c_list_combo1=new CCombo(table3,SWT.NONE); 
									for(int m_i=0;m_i<table2.getItemCount();m_i++)
									{
										c_list_combo1.add(table2.getItem(m_i).getText(0));
									}
									c_list_combo1.setText(items_m[i].getText(3));
									c_list_editor1.grabHorizontal=true;
									c_list_editor1.setEditor(c_list_combo1, items_m[i], 3);
									c_list_combo1.addModifyListener(new ModifyListener(){

										@Override
										public void modifyText(ModifyEvent e) {
											// TODO Auto-generated method stub
											c_list_editor1.getItem().setText(3, c_list_combo1.getText());
											
										}
										
									});
									controls13.add(c_list_editor1);
									controls14.add(c_list_combo1);
									
									final TableEditor c_list_editor2=new TableEditor(table3);
									final CCombo c_list_combo2=new CCombo(table3,SWT.NONE); 
									for(int m_i=0;m_i<table2.getItemCount();m_i++)
									{
										c_list_combo2.add(table2.getItem(m_i).getText(0));
									}
									c_list_combo2.setText(items_m[i].getText(4));
									c_list_editor2.grabHorizontal=true;
									c_list_editor2.setEditor(c_list_combo2, items_m[i], 4);
									c_list_combo2.addModifyListener(new ModifyListener(){

										@Override
										public void modifyText(ModifyEvent e) {
											// TODO Auto-generated method stub
											c_list_editor2.getItem().setText(4, c_list_combo2.getText());
											
										}
										
									});
									controls15.add(c_list_editor2);
									controls16.add(c_list_combo2);
									
									final TableEditor editor_m3=new TableEditor(table3);
									
									final Text text_m3=new  Text(table3,SWT.NONE);
								
									text_m3.setText(items_m[i].getText(5));
								 	editor_m3.grabHorizontal=true;
									editor_m3.setEditor(text_m3, items_m[i],5);
									controls17.add(editor_m3);
									controls18.add(text_m3);
									text_m3.addModifyListener(new ModifyListener(){
										public void modifyText(ModifyEvent e){
											editor_m2.getItem().setText(5, text_m3.getText());
										}
									});

									
								}
								
							}
							else
								if(event.widget==delete2)
								{
									TableItem[] items=table3.getItems();
									for(int i=0;i<items.length;i++)
									{
										if(!items[i].getChecked())
											continue;
										int index=table3.indexOf(items[i]);
										if(index<0) continue;
										
											controls91.get(index).dispose();
											controls91.remove(index);
											controls101.get(index).dispose();
											controls101.remove(index);

											controls9.get(index).dispose();
											controls9.remove(index);
											controls10.get(index).dispose();
											controls10.remove(index);
											controls11.get(index).dispose();
											controls11.remove(index);
											controls12.get(index).dispose();
											controls12.remove(index);
											controls13.get(index).dispose();
											controls13.remove(index);
											controls14.get(index).dispose();
											controls14.remove(index);
											controls15.get(index).dispose();
											controls15.remove(index);
											controls16.get(index).dispose();
											controls16.remove(index);
											controls17.get(index).dispose();
											controls17.remove(index);
											controls18.get(index).dispose();
											controls18.remove(index);
										table3.remove(index);
									}
								}
						}};
						add2.addListener(SWT.Selection, listener2);
						delete2.addListener(SWT.Selection, listener2);
					
					
					TableItem[] items=table1.getItems();
					for(int i=items.length-1;i<items.length;i++)
					{
						int pp=items.length;
						final TableEditor editor=new TableEditor(table1);					
						final Text text=new  Text(table1,SWT.NONE);
						text.setText(items[i].getText(0));
						editor.grabHorizontal=true;
						editor.setEditor(text, items[i], 0);
						text.addModifyListener(new ModifyListener(){
							public void modifyText(ModifyEvent e){
								editor.getItem().setText(0, text.getText());
								
								lb.setText("Component List of Scenario \""+text.getText()+"\"");
								lb1.setText("Message List of Scenario \""+text.getText()+"\"");
							}
						});
						controls.add(text);
						controls1.add(editor);
						final TableEditor editor1=new TableEditor(table1);
						
						final Text text1=new  Text(table1,SWT.NONE);
					
						text1.setText(items[i].getText(0));
					 	editor1.grabHorizontal=true;
						editor1.setEditor(text1, items[i], 1);
						text1.addModifyListener(new ModifyListener(){
							public void modifyText(ModifyEvent e){
								editor1.getItem().setText(1, text1.getText());
							}
						});
						controls.add(text1);
						controls1.add(editor1);
						
						final TableEditor editor2=new TableEditor(table1);		
						final Text text2=new  Text(table1,SWT.NONE);		
						text2.setText(items[i].getText(0));
						editor2.grabHorizontal=true;
						editor2.setEditor(text2, items[i], 2);
						text2.addModifyListener(new ModifyListener(){
							public void modifyText(ModifyEvent e){
								editor2.getItem().setText(2, text2.getText());
							}
						});
						controls.add(text2);
						controls1.add(editor2);
					}
		
					
	
					composite.layout();
					composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				
				}
				else if(event.widget==delete)
				{
					TableItem[] items=table1.getItems();
					for(int i=0;i<items.length;i++){
						if(!items[i].getChecked())
							continue;
						int index1=table1.indexOf(items[i]);
						if(index1<0)
							continue;
					for(int p=0;p<3;p++)
					{
					controls.get(i*3+p).dispose();			
					controls1.get(i*3+p).dispose();		
					}
					for(int p=0;p<3;p++)
					{
						controls.remove(i*3);
						controls1.remove(i*3);
					}
						table1.remove(index1);
						
						controls2.get(index1).dispose();
						controls2.remove(index1);
						controls3.get(index1).dispose();
						controls3.remove(index1);
						result1.get(index1).dispose();
						result1.remove(index1);
						controls4.get(index1).dispose();
						controls4.remove(index1);
						controls5.get(index1).dispose();
						controls5.remove(index1);
						controls6.get(index1).dispose();
						controls6.remove(index1);
						result2.get(index1).dispose();
						result2.remove(index1);
						controls7.get(index1).dispose();
						controls7.remove(index1);
						composite.pack();
						
 					}
					
					
				}
				
			}
			
		};
		add.addListener(SWT.Selection, listener);
		delete.addListener(SWT.Selection, listener);
	
		  btnNewButton.addSelectionListener(new SelectionListener(){
		      

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
					// btnNewButton.setText("按到我了");
				}

				@Override
				public void widgetSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
					File f = new File(saFilePath);
					FileWriter fw;
					try {
						fw = new FileWriter(f);
						fw.write("<?xml version=\"1.0\" encoding=\"GBK\"?>\n<scenariolist>\n</scenariolist>");
						fw.close();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					Document new_doc=getDocument(saFilePath);
					XMLWriter writer = null;// 声明写XML的对象
				//	System.out.println(saFilePath);
			        OutputFormat format = OutputFormat.createPrettyPrint();
			        format.setEncoding("GBK");// 设置XML文件的编码格式
					TableItem[] items1=table1.getItems();
					Element root= new_doc.getRootElement();
					for(int i=0;i<items1.length;i++){
						String s_name=items1[i].getText(0);
						String s_id=items1[i].getText(1);
						String s_parallel=items1[i].getText(2);
						Element i_scenario= root.addElement("scenario");
						i_scenario.addAttribute("name", s_name);
						i_scenario.addAttribute("id", s_id);
						Element com_list=i_scenario.addElement("componentlist");
							Table com=result1.get(i);			
							if(com.getItemCount()!=0) {
								TableItem[] items_com=com.getItems();
								for(int p=0;p<items_com.length;p++){
									String com_name=items_com[p].getText(0);
									String com_id=items_com[p].getText(1);
									Element component=com_list.addElement("component");
									component.addAttribute("id", com_id);
									component.addAttribute("name", com_name);
								}
							}
						Element mes_list=i_scenario.addElement("messagelist");
						Table com1=result2.get(i);			
							if(com1.getItemCount()!=0) {
								TableItem[] items_com=com1.getItems();
								for(int p=0;p<items_com.length;p++){
									String mes_name=items_com[p].getText(0);
									String mes_id=items_com[p].getText(1);
									String mes_type=items_com[p].getText(2);
									String mes_source=items_com[p].getText(3);
									String mes_target=items_com[p].getText(4);
									String mes_vector=items_com[p].getText(5);
									Element message=mes_list.addElement("message");
									message.addAttribute("id", mes_id);
									message.addAttribute("name", mes_name);
									message.addAttribute("type", mes_type);
									message.addAttribute("source", mes_source);
									message.addAttribute("target", mes_target);
									Element vectorcolock=message.addElement("vectorclock");
									vectorcolock.addAttribute("timestamp", mes_vector);
								}
							}
							
						String[] vector=new String[20];
						vector=handlevector(s_parallel,items1.length);
						Element parallelscenario= i_scenario.addElement("parallelscenario");
						for(int j=0;j<items1.length;j++)
							  {
								Element maxparallelnum = parallelscenario.addElement("maxparallelnum");
								maxparallelnum.addAttribute("scenarioID", items1[j].getText(1));
								maxparallelnum.addAttribute("num", vector[j]);
								
							  }
								
					}
					
					 try {
						writer = new XMLWriter(new FileWriter(saFilePath), format);
						writer.write(new_doc);
						writer.close();
					 } catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

				}});
		
		  btnNewButton1.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				String UML_filepath=saFilePath.substring(0,saFilePath.length()-8)+"xml";
				File f = new File(UML_filepath);
				FileWriter fw;
				try {
					fw = new FileWriter(f);
					fw.write("<?xml version=\"1.0\" encoding=\"windows-1252\"?>\n<XMI xmi.version=\"1.1\" xmlns:UML=\"omg.org/UML1.3\" timestamp=\"2014-11-27 13:26:49\">\n<XMI.header>\n<XMI.documentation>\n<XMI.exporter>Enterprise Architect</XMI.exporter>\n<XMI.exporterVersion>2.5</XMI.exporterVersion>\n</XMI.documentation>\n</XMI.header>\n</XMI>");
					fw.close();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				Document new_doc=getDocument(UML_filepath);
				XMLWriter writer = null;// 声明写XML的对象
		        OutputFormat format = OutputFormat.createPrettyPrint();
		        format.setEncoding("GBK");// 设置XML文件的编码格式
				TableItem[] items1=table1.getItems();
				Element root= new_doc.getRootElement();
				Element content=root.addElement("XMI.content");
				Element difference=root.addElement("XMI.difference");
				Element extensions=root.addElement("XMI.extensions");
				extensions.addAttribute("xmi.extender", "Enterprise Architect 2.5");
				Element model=content.addElement("UML:Model");
				model.addAttribute("name", "EA Model"); model.addAttribute("xmi.id", "MX_EAID_1"); 
				Element ownedElement=model.addElement("UML:Namespace.ownedElement");
				Element class_o=ownedElement.addElement("UML:Class");class_o.addAttribute("name", "EARootClass");
				class_o.addAttribute("xmi.id", "EAID_2");class_o.addAttribute("isRoot", "true");class_o.addAttribute("isLeaf", "false");class_o.addAttribute("isAbstract", "false");
				Element package_c=ownedElement.addElement("UML:Package");
				package_c.addAttribute("name", "FromBreeze");package_c.addAttribute("xmi.id", "EAPK_1");package_c.addAttribute("isRoot", "false");package_c.addAttribute("isLeaf", "false");package_c.addAttribute("isAbstract", "false");package_c.addAttribute("visibility", "public");
				package_c.addElement("UML:ModelElement.taggedValue");
				Element namespace=package_c.addElement("UML:Namespace.ownedElement");
				Element collaboration=namespace.addElement("UML:Collaboration");
				collaboration.addAttribute("xmi.id", "EAID_3");collaboration.addAttribute("name", "Collaborations");
				Element namespace2=collaboration.addElement("UML:Namespace.ownedElement");
				for(int ii=0;ii<result1.size();ii++)
				{
					Table trs=result1.get(ii);
					
					TableItem[] items_ii=trs.getItems();
					for(int jj=0;jj<items_ii.length;jj++)
					{
						String com_name=items_ii[jj].getText(0);
						Element cla_role=namespace2.addElement("UML:ClassifierRole");
						cla_role.addAttribute("name", com_name);
						cla_role.addAttribute("xmi.id", "EAID_1"+com_name);
						cla_role.addAttribute("visibility", "public");
						cla_role.addAttribute("base", "EAID_2");
						Element taggedvalue=cla_role.addElement("UML:ModelElement.taggedValue");
						Element in_taggedvalue=taggedvalue.addElement("UML:TaggedValue");
						in_taggedvalue.addAttribute("tag", "ea_stype");
						in_taggedvalue.addAttribute("value", "Sequence");
						
					}
				}
				Element col_interaction=collaboration.addElement("UML:Collaboration.interaction");
				Element UML_Interaction=col_interaction.addElement("UML:Interaction");
				UML_Interaction.addAttribute("xmi.id", "EAID_4");
				UML_Interaction.addAttribute("name", "EAID_4");
				Element inter_mes=UML_Interaction.addElement("UML:Interaction.message");
				
				String[][] partial=new String[20][20];
				int[] size_y=new int[20];
				int[] com_num=new int[20];
				for(int ii=0;ii<result2.size();ii++)
				{
					Table trs=result2.get(ii);
					TableItem[] items_ii=trs.getItems();
					Table trs1=result1.get(ii);
					com_num[ii]=trs1.getItems().length;
					
					size_y[ii]=items_ii.length;
					for(int jj=0;jj<items_ii.length;jj++)
					{
						partial[ii][jj]=items_ii[jj].getText(5); 
					}
				}
				int[][] int_par=new int[20][20];
				 int_par=partialcom(partial,result2.size(),size_y,com_num);
				for(int ii=0;ii<result2.size();ii++)
				{
					
					Table trs=result2.get(ii);
					TableItem[] items_ii=trs.getItems();
					int seqno=0;
					for(int jj=0;jj<items_ii.length;jj++)
					{
						String mes_name=items_ii[jj].getText(0);
						String sor_name=items_ii[jj].getText(3);
						String tar_name=items_ii[jj].getText(4);
						String type=items_ii[jj].getText(2);
						Element message=inter_mes.addElement("UML:Message");
						message.addAttribute("name", mes_name);
						message.addAttribute("xmi.id", "EAID_5"+mes_name);
						message.addAttribute("visibility", "public");
						message.addAttribute("sender", "EAID_1"+sor_name);
						message.addAttribute("receiver", "EAID_1"+tar_name);
						Element mod_tag=message.addElement("UML:ModelElement.taggedValue");
						Element taggedvalue_m=mod_tag.addElement("UML:TaggedValue");
						taggedvalue_m.addAttribute("tag", "ea_type");
						taggedvalue_m.addAttribute("value", "Sequence");
						Element taggedvalue_m1=mod_tag.addElement("UML:TaggedValue");
						taggedvalue_m1.addAttribute("tag", "seqno");
						taggedvalue_m1.addAttribute("value",String.valueOf(int_par[ii][jj]));
						if(type.equals("reply"))
							{
								Element taggedvalue_m2=mod_tag.addElement("UML:TaggedValue");
								taggedvalue_m2.addAttribute("tag", "privatedata4");
							
								taggedvalue_m2.addAttribute("value","1");
							}
						
					}
				}
	
					Table tr=table1;
					Element diagram=content.addElement("UML:Diagram");
					for(int qq=0;qq<tr.getItemCount();qq++){
						String sce_name=tr.getItem(qq).getText(0);
						diagram.addAttribute("name", sce_name);
						diagram.addAttribute("xmi.id", "EAID_6"+sce_name);
						diagram.addAttribute("diagramType", "Sequence");
						diagram.addAttribute("owner", "EAPK_1");
						diagram.addAttribute("toolName", "Enterprise Architect 2.5");
						Element mod_tagg=diagram.addElement("UML:ModelElement.taggedValue");
						Element tag_val=mod_tagg.addElement("UML:TaggedValue");
						tag_val.addAttribute("tag", "type");
						tag_val.addAttribute("value", "Sequence");
						Element dia_ele=diagram.addElement("UML:Diagram.element");
						for(int mm=0;mm<result1.get(qq).getItemCount();mm++)
						{
							String com_name=result1.get(qq).getItem(mm).getText(0);
							Element mm_dia=dia_ele.addElement("UML:DiagramElement");
							mm_dia.addAttribute("geometry", "Left="+mm*50+";Top=50;"+"Right="+mm*100+";Bottom=471;");
							mm_dia.addAttribute("subject", "EAID_1"+com_name);
							mm_dia.addAttribute("seqno", String.valueOf(mm+1));
							mm_dia.addAttribute("style", ";");
							
						}
						for(int mm=0;mm<result2.get(qq).getItemCount();mm++)
						{
							String mes_name=result2.get(qq).getItem(mm).getText(0);
							Element mm_dia=dia_ele.addElement("UML:DiagramElement");
							mm_dia.addAttribute("geometry", "SX=0;SY=0;EX=0;EY=0;Path=;");
							mm_dia.addAttribute("subject", "EAID_5"+mes_name);
							mm_dia.addAttribute("style", ";");
							mm_dia.addAttribute("Hidden", "0;");
							
						}
					}
					
				
				
				
				 try {
						writer = new XMLWriter(new FileWriter(UML_filepath), format);
						writer.write(new_doc);
						writer.close();
					 } catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
			  
		  }); 
		  
		composite.layout();
		composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		this.setContent(composite);
		
		
	}
	public void fireModelChange() {
		this.parentEditor.modelModified();
	}

	public void doSave() {
		// TODO Auto-generated method stub
		
	}
	private String[] handlevector(String vec,int count)
	{
		String[]  store=new String[20];
		int pos=0,i=0,mark=0;
		while(i<count)
		{
			pos=vec.indexOf(",");
			if(pos==-1||pos>vec.length())
				pos=vec.length();
			store[i]=vec.substring(0,pos);
			if(pos!=vec.length())
				vec=vec.substring(pos+1,vec.length());
			
			
			i++;
		}
		return store;
	}
	
	 public Document getDocument(String file_path) {
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
	private architecture componentlist(String file_path)
	{
		ArrayList<component> clist=new ArrayList();
		ProudctionModel pm=new ProudctionModel();
		pm.setFile_path(file_path);
		Element root=pm.getRootElement();
		 Element arch1 = null;
		 architecture arc=new architecture();
		 int mark=1;
		 for (Iterator ie = root.elementIterator(); ie.hasNext();) {
			 if(mark==1)
			  {
				 arch1=(Element) ie.next();
				 mark=0;
			  }
	        } 
		 arc=new architecture(arch1);
		return arc;
	}
	private int[][] partialcom(String[][] vector,int size_x,int[] size_y,int[] component_num)
	{
		int[][] result=new int[20][20];
		for(int i=0;i<size_x;i++)
			{
			
				
				for(int p=0;p<size_y[i];p++){
					int order=0;
						for(int j=0;j<size_y[i];j++)
							{
								if(j==p)
									continue;
								if(compare(vector[i][p],vector[i][j],component_num[i]))
									order++;
							}
					result[i][p]=order;
					}
					
				
			}
		return result;
	}
	private Boolean compare(String vec1,String vec2,int component_num) //vec1>vec2 return true else return false
	{
		String[] to_int1=handlevector(vec1,component_num);
		String[] to_int2=handlevector(vec2,component_num);
		Boolean mark=true;
		int zhenshu=0,fushu=0,lin=0;
		for(int i=0;i<component_num;i++)
		{
			 int result = to_int1[i].compareTo(to_int2[i]);
			if(result>0)
				zhenshu++;
				else
					if(result==0)
						lin++;
						else
							fushu++;
		}
		if(fushu==0&&lin!=component_num)
		return true;
		else
			return false;
	}
	
}
