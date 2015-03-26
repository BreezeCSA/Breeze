package BreezeModel.diagram.navigator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import BreezeModel.diagram.part.BreezeDiagramEditor;

import com.goku.breeze.ui.console.ConsoleFactory;
import com.goku.breeze.ui.console.ConsoleUtil;
import com.wxw.breeze.production.GSPNTrs;
import com.wxw.breeze.production.ProudctionModel;
import com.wxw.breeze.production.architecture;
import com.wxw.breeze.production.edge;
import com.wxw.breeze.production.production;

public class BreezeNavigatorPopActionGenGSPN implements IObjectActionDelegate {

	private String absPath = null;
	private String[] selectedFileNames = null;
	private IWorkbenchPart targetPart = null;
	public String[][] states=new String[100][100];//构件状态
	public int[] sta_num=new int[100];
	public GSPNTrs[][] internal_tras_event=new GSPNTrs[100][100];//构件状态迁移事件
	public GSPNTrs[][] external_tras_event=new GSPNTrs[100][100];
	public int[] tras_num=new int[100];
	public int[] ext_num=new int[100];
	public architecture arc;
	public double[][] internal_lambda=new double[100][100],external_lambda=new double[100][100];//事件参数
	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		if (this.absPath != null && this.selectedFileNames != null) {
			for (int i = 0; i < this.selectedFileNames.length; ++i) {
				if (this.selectedFileNames[i] != null) {
					this.selectedFileNames[i] = this.absPath + this.selectedFileNames[i];
				}
			}
		}
		if(selectedFileNames[0].indexOf("GSPN")!=-1)
		{
			generate();
		}
		else
		{
			ConsoleUtil.printMessage(ConsoleFactory.getConsole(), "This is not a GSPN file!!");//Display.getDefault().asyncExec(new ConsolePrinter(cmd + "\n", ConsolePrinter.TYPE_MESSAGE));
    		
		}
	}
	
	 public Document getDocument1() {
	        SAXReader saxReader = new SAXReader();
	        Document document = null;
	            try {
					document = saxReader.read(selectedFileNames[0]);
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	       
	        return document;
	     }
	  public Element getRootElement() {
	       return getDocument1().getRootElement();
	    }
	  public Element addPlace(Element root,String id,double pos_x,double pos_y,String name,int initialMark)
	  {
		  root.addAttribute("id", id);
		 Element graphics=root.addElement("graphics");
		 Element position=graphics.addElement("position");
		 position.addAttribute("x", Double.toString(pos_x));
		 position.addAttribute("y", Double.toString(pos_y));
		 Element name1=root.addElement("name");
		 Element value=name1.addElement("value");
		 value.addText(name);
		 Element name_graphics=name1.addElement("graphics");
		 Element offset=name_graphics.addElement("offset");
		 offset.addAttribute("x", "0.0");
		 offset.addAttribute("y", "0.0");
		 Element initialMarking=root.addElement("initialMarking");
		 Element init_value=initialMarking.addElement("value");
		 init_value.addText("Default,"+String.valueOf(initialMark));
		 Element init_value_graphics=initialMarking.addElement("graphics");
		 Element init_value_offset=init_value_graphics.addElement("offset");
		 init_value_offset.addAttribute("x", "0.0");
		 init_value_offset.addAttribute("y", "0.0");
		 Element capacity=root.addElement("capacity");
		 Element cap_value=capacity.addElement("value");
		 cap_value.addText("0");
		 return root;
	  }
	  public Element addTransition(Element root,String id,double pos_x,double pos_y,String name,String timed1,double rate1)
	  {
		  root.addAttribute("id", id);
		  Element graphics=root.addElement("graphics"); 
			 Element position=graphics.addElement("position");
			 position.addAttribute("x", Double.toString(pos_x));
			 position.addAttribute("y", Double.toString(pos_y));
			 Element name1=root.addElement("name");
			 Element value=name1.addElement("value");
			 value.addText(name);
			 Element name_graphics=name1.addElement("graphics");
			 Element offset=name_graphics.addElement("offset");
			 offset.addAttribute("x", "0.0");
			 offset.addAttribute("y", "0.0");
			 Element orientation=root.addElement("orientation");
			 Element ori_value=orientation.addElement("value");
			 ori_value.addText("0");
			 Element rate=root.addElement("rate");
			 Element rate_value=rate.addElement("value");
			 rate_value.addText(Double.toString(rate1));
			 Element timed=root.addElement("timed");
			 Element timed_value=timed.addElement("value");
			 timed_value.addText(timed1);
			 Element infiniteServer=root.addElement("infiniteServer");
			 Element infin_value=infiniteServer.addElement("value");
			 infin_value.addText("false");
			 Element priority=root.addElement("priority");
			 Element priority_value=priority.addElement("value");
			 priority_value.addText("1");	 
		  return root;
	  }
	  public Element addArc(Element root,String id,String source,String target)
	  {
		  root.addAttribute("id", id);
		  root.addAttribute("source", source);
		  root.addAttribute("target", target);
		  Element graphics=root.addElement("graphics"); 
		  Element inscription=root.addElement("inscription"); 
		  Element ins_value=inscription.addElement("value");
		 
		  ins_value.addText("Default,1");
		  Element ins_graphics=inscription.addElement("graphics");
		  Element tagged=root.addElement("tagged");
		  Element tag_value=tagged.addElement("value");
		  tag_value.addText("false");
		  Element archpath1=root.addElement("archpath");
		  archpath1.addAttribute("id", "000");
		  archpath1.addAttribute("x", "0");
		  archpath1.addAttribute("y", "0");
		  archpath1.addAttribute("curvePoint", "false");
		  Element archpath2=root.addElement("archpath");
		  archpath2.addAttribute("id", "001");
		  archpath2.addAttribute("x", "0");
		  archpath2.addAttribute("y", "0");
		  archpath2.addAttribute("curvePoint", "false");
		  Element type1=root.addElement("type");
		  type1.addAttribute("value", "normal");
		  return root;
	  }
	  public  Document getDocument2(){  
	        Document document = DocumentHelper.createDocument();  
	        //生成一个接点  
	        Element pnml = document.addElement("pnml");  
	        //生成root的一个接点  
	        Element net = pnml.addElement("net");  
	        //生产category的一个接点  
//	        Element id = category.addElement("id");  
//	        //生成id里面的参数值  
//	        id.addAttribute("name", "id");  
//	        //生成id里面的值  
//	        id.addText("1");  
	        net.addAttribute("id","Net-One");
	        net.addAttribute("type","P/T net");
	        Element token=net.addElement("token");
	        token.addAttribute("id", "Default");
	        token.addAttribute("enabled", "true");
	        token.addAttribute("red", "0");
	        token.addAttribute("green", "0");
	        token.addAttribute("blue", "0"); 
	        int pos_ii=0;
	        for(int pla_i=0;pla_i<arc.num_node;pla_i++)
	        {
	        	if(sta_num[pla_i]!=0)
	        		for(int sta_i=0;sta_i<sta_num[pla_i];sta_i++)
	        		{
	        		 	Element place=net.addElement("place");
	        		 	if(states[pla_i][sta_i].indexOf("active")!=-1)
	        		 	place=addPlace(place,arc.nd[pla_i].name+"_"+states[pla_i][sta_i],(pos_ii++)*55,0,arc.nd[pla_i].name+"_"+states[pla_i][sta_i],1);
	        		 	else
	        		 		place=addPlace(place,arc.nd[pla_i].name+"_"+states[pla_i][sta_i],(pos_ii++)*55,0,arc.nd[pla_i].name+"_"+states[pla_i][sta_i],0);
	        		 
	        		}
	        }
	        pos_ii=0;
	        for(int arc_i=0;arc_i<arc.num_node;arc_i++)
	        	{
		        	if(tras_num[arc_i]!=0)
		        	
		        	for(int trs_i=0;trs_i<tras_num[arc_i];trs_i++)
		        	{
		        		Element transition=net.addElement("transition");
		        		GSPNTrs obj_trs=internal_tras_event[arc_i][trs_i];
		        		transition=addTransition(transition,String.valueOf(arc.nd[arc_i].name)+"_"+obj_trs.id,(pos_ii++)*55.0,55.0,String.valueOf(arc.nd[arc_i].name)+"_"+obj_trs.id,"true",internal_lambda[arc_i][trs_i]);
		        		
		        	}
		        	if(ext_num[arc_i]!=0)
		        		for(int ext_i=0;ext_i<ext_num[arc_i];ext_i++)
		        		{
			        		Element transition=net.addElement("transition");
			        		GSPNTrs obj_trs=external_tras_event[arc_i][ext_i];
			        		transition=addTransition(transition,String.valueOf(arc.nd[arc_i].name)+"_"+obj_trs.id,(pos_ii++)*55.0,55.0,String.valueOf(arc.nd[arc_i].name)+"_"+obj_trs.id,"false",external_lambda[arc_i][ext_i]);
			        		
		        		}
	        	}
	        
	        for(int arc_i=0;arc_i<arc.num_node;arc_i++)
        	{
	        	if(tras_num[arc_i]!=0)
	        	
	        	for(int trs_i=0;trs_i<tras_num[arc_i];trs_i++)
	        	{ 

	        		 Element arc_ele=net.addElement("arc");
	        		 Element arc_ele1=net.addElement("arc");
		        	 GSPNTrs obj_trs=internal_tras_event[arc_i][trs_i];
		        	 String str1=arc.nd[obj_trs.source_node].name+"_"+obj_trs.source_state;
		        	 String str2=String.valueOf(arc.nd[arc_i].name)+"_"+obj_trs.id;
		        	 if(str1.indexOf("MQT_Taylor_Hospital")!=-1||str2.indexOf("MQT_Taylor_Hospital")!=-1)
		        	 {
		        		 int ppp=1;
		        		 ppp=2;
		        	 }
		        	 arc_ele=addArc(arc_ele,arc.nd[obj_trs.source_node].name+"_"+obj_trs.source_state+" to "+String.valueOf(arc.nd[arc_i].name)+"_"+obj_trs.id,arc.nd[obj_trs.source_node].name+"_"+obj_trs.source_state,String.valueOf(arc.nd[arc_i].name)+"_"+obj_trs.id);
		        	 arc_ele1=addArc(arc_ele1,String.valueOf(arc.nd[arc_i].name)+"_"+obj_trs.id+" to "+arc.nd[obj_trs.target_node].name+"_"+obj_trs.target_state,String.valueOf(arc.nd[arc_i].name)+"_"+obj_trs.id,arc.nd[obj_trs.target_node].name+"_"+obj_trs.target_state);
	        	}
	        	if(ext_num[arc_i]!=0)
	        		for(int ext_i=0;ext_i<ext_num[arc_i];ext_i++)
	        		{
	        			 Element arc_ele=net.addElement("arc");
		        		 Element arc_ele1=net.addElement("arc");
		        		 Element arc_ele2=net.addElement("arc");
			        	 GSPNTrs obj_trs=external_tras_event[arc_i][ext_i];
			        	 arc_ele=addArc(arc_ele,arc.nd[obj_trs.source_node].name+"_"+obj_trs.source_state+" to "+String.valueOf(arc.nd[arc_i].name)+"_"+obj_trs.id,arc.nd[obj_trs.source_node].name+"_"+obj_trs.source_state,String.valueOf(arc.nd[arc_i].name)+"_"+obj_trs.id);					        
			         	 arc_ele1=addArc(arc_ele1,String.valueOf(arc.nd[arc_i].name)+"_"+obj_trs.id+" to "+arc.nd[obj_trs.target_node].name+"_"+obj_trs.target_state,String.valueOf(arc.nd[arc_i].name)+"_"+obj_trs.id,arc.nd[obj_trs.target_node].name+"_"+obj_trs.target_state);
			         	 arc_ele2=addArc(arc_ele2,arc.nd[obj_trs.ext_source].name+"_"+obj_trs.ext_state+" to "+String.valueOf(arc.nd[arc_i].name)+"_"+obj_trs.id,arc.nd[obj_trs.ext_source].name+"_"+obj_trs.ext_state, String.valueOf(arc.nd[arc_i].name)+"_"+obj_trs.id);
		        
	        		}
        	}
	        
	        return document;  
	    }  
	void generate()
	{
		//System.out.println("test");
		ProudctionModel fr=new ProudctionModel();
		String trs=selectedFileNames[0];
		fr.setFile_path(trs);
	 	fr.traversalDocumentByProductionID("");
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
		arc=new architecture(arch1);
		Boolean mmark=true;
		for(int i=0;i<fr.pru_num;i++)
		{
			production pod=new production(fr.pru[i]);
			
			try{
			if(fr.pru[i].left.num_node==1&&fr.pru[i].right.num_node==1)
			{
				if(!fr.pru[i].left.nd[0].name.equals(fr.pru[i].right.nd[0].name))
				{
					mmark=false;
				}
				Map<String,String> att_trs=fr.pru[i].left.nd[0].att;
				int nd_in_sa=0,nd_in_sa11=0;
				for(int pp=0;pp<arc.num_node;pp++)
				{
					if(fr.pru[i].left.nd[0].name.equals(arc.nd[pp].name))
					{
						nd_in_sa=pp;
					}
					if(fr.pru[i].right.nd[0].name.equals(arc.nd[pp].name))
					{
						nd_in_sa11=pp;
					}
				}

				   Boolean mark_s=false;
				   for(int i_s=0;i_s<sta_num[nd_in_sa];i_s++)
					   if(att_trs.get("state").equals(states[nd_in_sa][i_s]))
						   mark_s=true;
				   if(!mark_s)
					states[nd_in_sa][sta_num[nd_in_sa]++]=att_trs.get("state");
					 internal_lambda[nd_in_sa][tras_num[nd_in_sa]]=Double.valueOf(att_trs.get("lambda"));
					 String ttes=att_trs.get("event");
					 internal_tras_event[nd_in_sa][tras_num[nd_in_sa]]=new GSPNTrs();
		    		 internal_tras_event[nd_in_sa][tras_num[nd_in_sa]].id=new String(att_trs.get("event"));
		    		 internal_tras_event[nd_in_sa][tras_num[nd_in_sa]].source_state=new String(fr.pru[i].left.nd[0].att.get("state"));
		    		 internal_tras_event[nd_in_sa][tras_num[nd_in_sa]].source_node=nd_in_sa;
		    		 internal_tras_event[nd_in_sa][tras_num[nd_in_sa]].target_state=new String(fr.pru[i].right.nd[0].att.get("state"));
		    		 internal_tras_event[nd_in_sa][tras_num[nd_in_sa]++].target_node=nd_in_sa11;
		    		 
			}
			if(fr.pru[i].left.num_node==3&&fr.pru[i].right.num_node==3)
			{
					int nd_in_sa1=0,nd_in_sa2=0,nd_in_sa3=0,com1,com2 = 0,con1 = 0,com_1,com_2 = 0,con_1 = 0;
					for(int i_iter=0;i_iter<3;i_iter++)
					{
						if(fr.pru[i].left.nd[i_iter].type==0)
							con1=i_iter;
						if(fr.pru[i].right.nd[i_iter].type==0)
							con_1=i_iter;
					}
					for(int jj=0;jj<fr.pru[i].left.num_node;jj++)
					{
						if(fr.pru[i].left.data[con1][jj]==1)
							com2=jj;
					}
					for(int jj=0;jj<fr.pru[i].right.num_node;jj++)
					{
						if(fr.pru[i].right.data[con_1][jj]==1)
							com_2=jj;
					}
					com1=3-com2-con1;
					com_1=3-com_2-con_1;
					for(int jj=0;jj<arc.num_node;jj++)
					{
						if(fr.pru[i].left.nd[com1].name.equals(arc.nd[jj].name))
						{
							nd_in_sa1=jj;
						}
						if(fr.pru[i].left.nd[com2].name.equals(arc.nd[jj].name))
						{
							nd_in_sa2=jj;
						}
						if(fr.pru[i].left.nd[com1].name.equals(arc.nd[jj].name))
						{
							nd_in_sa3=jj;
						}
					}
					Map<String,String> att_trs=fr.pru[i].left.nd[con1].att;
					Map<String,String> att_trs1=fr.pru[i].left.nd[com2].att;
					Map<String,String> att_trs2=fr.pru[i].right.nd[com_2].att;
					Map<String,String> att_trs3=fr.pru[i].left.nd[com1].att;
					Boolean mmmark1=false,mmmark2=false;
					for(int pp=0;pp<sta_num[nd_in_sa1];pp++)
						{
						if(states[nd_in_sa2][pp].equals(att_trs1.get("state")))
							mmmark1=true;
						if(states[nd_in_sa2][pp].equals(att_trs2.get("state")))
							mmmark2=true;
						}
					if(!mmmark1&&att_trs1!=null)
						states[nd_in_sa2][sta_num[nd_in_sa2]++]=att_trs1.get("state");	  
					if(!mmmark2&&att_trs2!=null)
						states[nd_in_sa2][sta_num[nd_in_sa2]++]=att_trs2.get("state");	  
					external_tras_event[nd_in_sa2][ext_num[nd_in_sa2]]=new GSPNTrs();
					external_tras_event[nd_in_sa2][ext_num[nd_in_sa2]].ext_source=nd_in_sa3;
					external_tras_event[nd_in_sa2][ext_num[nd_in_sa2]].ext_state=att_trs3.get("state");
			    	external_tras_event[nd_in_sa2][ext_num[nd_in_sa2]].id=new String(att_trs.get("event"));
			    	external_tras_event[nd_in_sa2][ext_num[nd_in_sa2]].source_state=new String(fr.pru[i].left.nd[com2].att.get("state"));
					external_tras_event[nd_in_sa2][ext_num[nd_in_sa2]].source_node=nd_in_sa2;
					external_tras_event[nd_in_sa2][ext_num[nd_in_sa2]].target_state=new String(fr.pru[i].right.nd[com_2].att.get("state"));	
					external_tras_event[nd_in_sa2][ext_num[nd_in_sa2]++].target_node=nd_in_sa2;			
					external_lambda[nd_in_sa2][ext_num[nd_in_sa2]-1]=Double.valueOf(att_trs.get("fixed rate"));
							   
				
			}
			}
			catch(Exception ex)
			{
				if(ex instanceof java.lang.NullPointerException)
				{
					MessageBox msgDlg = new MessageBox(Display.getCurrent().getActiveShell(), SWT.ICON_INFORMATION | SWT.OK);
					msgDlg.setMessage("The production of the GSPN file is not well formed!");
					msgDlg.setText("Initialization:");
					msgDlg.open();
				
				}
			}
		}
		   try {
			for(int i_s=0;i_s<arc.num_node;i_s++)
			{
				
				for(int j_s=0;j_s<ext_num[i_s];j_s++)
				{
					GSPNTrs trs_s=external_tras_event[i_s][j_s];
					for(int p_s=0;p_s<ext_num[i_s];p_s++)
					{
						if(trs_s==external_tras_event[i_s][p_s])
						{
							external_tras_event[i_s][p_s]=null;
							for(int q_s=p_s;q_s<ext_num[i_s]-1;q_s++)
							{
								external_tras_event[i_s][q_s]=external_tras_event[i_s][q_s+1];
							}
							ext_num[i_s]--;
						}
					}
				}
			}
			String outFile=selectedFileNames[0];
			outFile=outFile.substring(0,outFile.length()-18)+".gspn_model";
			//读取文件  
	        FileWriter fileWriter = new FileWriter(outFile);  
	        //设置文件编码  
	        OutputFormat xmlFormat = OutputFormat.createPrettyPrint();  
	        xmlFormat.setEncoding("ISO-8859-1");  
	        //创建写文件方法  
	        XMLWriter xmlWriter = new XMLWriter(fileWriter,xmlFormat);  
	        //写入文件  
	        xmlWriter.write(getDocument2());		
	        //关闭  
	        xmlWriter.close();  
         
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  

	}
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		if (!selection.isEmpty() && selection instanceof TreeSelection) {
			TreeSelection treeSelection = (TreeSelection) selection;
			TreePath[] paths = treeSelection.getPaths();
			this.selectedFileNames = new String[paths.length];
			for (int i = 0; i < paths.length; ++i) {
				if (paths[i] != null) {
					Object obj = paths[i].getLastSegment();
					if (obj instanceof org.eclipse.core.internal.resources.File) {
						this.selectedFileNames[i] = ((org.eclipse.core.internal.resources.File) obj).getName();
					} else this.selectedFileNames[i] = null;
				}
			}
		}

		if (selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
			Object obj = ssel.getFirstElement();
			if (obj == null)
				return;
			IFile file = (IFile) Platform.getAdapterManager().getAdapter(obj, IFile.class);

			if (file == null) {
				if (obj instanceof IAdaptable) {
					file = (IFile) ((IAdaptable) obj).getAdapter(IFile.class);
				}
			}

			if (file != null) {
				this.absPath = file.getLocation().removeLastSegments(1).addTrailingSeparator().toString();
			}
		}
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// TODO Auto-generated method stub
		this.targetPart = targetPart;
	}

}
