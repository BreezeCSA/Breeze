package com.wxw.breeze.production;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import BreezeModel.Component;
import BreezeModel.Connector;
import BreezeModel.Port;
import BreezeModel.diagram.edit.parts.ComponentEditPart;
import BreezeModel.diagram.edit.parts.ConnectorEditPart;
import BreezeModel.diagram.edit.parts.PortEditPart;
import BreezeModel.diagram.part.BreezeDiagramEditor;
import BreezeModel.impl.ConnectorImpl;
import BreezeModel.impl.NodeTemplateImpl;

import com.goku.breeze.ui.console.ConsoleFactory;
import com.goku.breeze.ui.console.ConsoleUtil;

public class StyleCheck implements IObjectActionDelegate {

	private IPath selectedPath; 
	
	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		
		
		int mark_of_wrong=0;
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
		
		
		architecture arc=new architecture(arch1); 
		arc.getStyle(style1);
		String[] node_type=new String[arc.num_node];
		for(int i=0;i<arc.num_node;i++)
		{
			if(arc.nd[i].type==0)
			{
				node_type[i]="connector";
				continue;
			}
			for(int j=0;j<arc.st.nt_num;j++)
			{
				String tem=null;
				if(arc.st.nt[j].id.equals(arc.nd[i].tr))
				{
					if(arc.st.nt[j].att.containsKey("Template Type"))
					{
						  
						String st =arc.st.nt[j].att.get("Template Type");   		 	
						node_type[i]=st;
					}
				}
			}
		}
		HighLight_s h=new HighLight_s(" ");
		 h.exe_cancle();
		for(int i=0;i<arc.num_node;i++)
		{
			for(int j=0;j<arc.num_node;j++)
			{
				for(int k=0;k<arc.num_node;k++)
				{
				if(arc.data[i][j]==1&&arc.data[j][k]==1)
				{
					if(node_type[i].equals("client")&&node_type[j].equals("connector")&&node_type[k].equals("client"))
					{
						mark_of_wrong=1;
						String cmd="The client "+arc.nd[i].name+" and client "+arc.nd[k].name+" should not connected directedly";
	    				ConsoleUtil.printMessage(ConsoleFactory.getConsole(), cmd+ "\n");
	    				HighLight_s h1=new HighLight_s(arc.nd[i].id);
	    				h1.exe();
	    				HighLight_s h2=new HighLight_s(arc.nd[k].id);
	    				h2.exe();
						
					}
					
					if(node_type[i].equals("client")&&node_type[j].equals("connector")&&node_type[k].equals("server"))
					{
						mark_of_wrong=1;
						String cmd="The port direction between client"+arc.nd[i].name+" and server "+arc.nd[k].name+" is wrong!";
	    				ConsoleUtil.printMessage(ConsoleFactory.getConsole(), cmd+ "\n");
	    				HighLight_s h1=new HighLight_s(arc.nd[i].id);
	    				h1.exe();
	    				HighLight_s h2=new HighLight_s(arc.nd[k].id);
	    				h2.exe();
						
					}
					
				}
				}
			}
		} 
		
		//here for port matching
		for(int jj=0;jj<arc.num_node;jj++)
		 {
			Object o=PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();		
			BreezeDiagramEditor diagramEditor = (BreezeDiagramEditor) o;
			IDiagramGraphicalViewer viewer = diagramEditor.getDiagramGraphicalViewer();
			if(arc.nd[jj].type==0)
			{
				
			List<ConnectorEditPart> ooo=viewer.findEditPartsForElement(arc.nd[jj].id, ConnectorEditPart.class);
			Connector connector = (Connector) (((View) ooo.get(0).getModel()).getElement());
			NodeTemplateImpl nti=(NodeTemplateImpl) connector.getTR();
			List<Port> pl=nti.getPort();
			 Iterator<Port> it1 = pl.iterator();
			 int mark1=0;
			while(it1.hasNext())
			{
				Port trs=it1.next();
				mark1=0;
				for(int pit=0;pit<arc.nd[jj].port_num;pit++)
				{
					
					String dir=trs.getDirection().toString();
					
				//	System.out.println("StyleCheck matching");
					
					 if(arc.nd[jj].pt[pit].name!=null&&arc.nd[jj].pt[pit].name.equals(trs.getName()))
					 {
						 if(arc.nd[jj].pt[pit].direction==0)
						 {
							 if(dir.equals("in"))
							 {
								 mark1=1;
							 }
						 }
						 if(arc.nd[jj].pt[pit].direction==1)
						 {
							 if(dir.equals("out"))
							 {
								 mark1=1;
							 }
						 }
						 if(arc.nd[jj].pt[pit].direction==2)
						 {
							 if(dir.equals("inout"))
							 {
								 mark1=1;
							 }
						 }
					 }
				}
				if(mark1==0)
					break;
			}
			if(it1.hasNext()||mark1==0)
			{
				mark_of_wrong=1;
				String cmd="The ports of connector \""+arc.nd[jj].name+"\" are not match its template!";
				ConsoleUtil.printMessage(ConsoleFactory.getConsole(), cmd+ "\n");
				HighLight_s h1=new HighLight_s(arc.nd[jj].id);
				h1.exe();
			}
			
			}
			else
			{
				List<ComponentEditPart> ooo=viewer.findEditPartsForElement(arc.nd[jj].id, ComponentEditPart.class);
				Component component = (Component) (((View) ooo.get(0).getModel()).getElement());
				NodeTemplateImpl nti=(NodeTemplateImpl) component.getTR();
				List<Port> pl=nti.getPort();
				 Iterator<Port> it1 = pl.iterator();
				 int mark1=0;
				while(it1.hasNext())
				{
					Port trs=it1.next();
					mark1=0;
					for(int pit=0;pit<arc.nd[jj].port_num;pit++)
					{
						
						String dir=trs.getDirection().toString();
						
					//	System.out.println("StyleCheck matching");
						
						 if(arc.nd[jj].pt[pit].name!=null&&arc.nd[jj].pt[pit].name.equals(trs.getName()))
						 {
							 if(arc.nd[jj].pt[pit].direction==0)
							 {
								 if(dir.equals("in"))
								 {
									 mark1=1;
								 }
							 }
							 if(arc.nd[jj].pt[pit].direction==1)
							 {
								 if(dir.equals("out"))
								 {
									 mark1=1;
								 }
							 }
							 if(arc.nd[jj].pt[pit].direction==2)
							 {
								 if(dir.equals("inout"))
								 {
									 mark1=1;
								 }
							 }
						 }
					}
					if(mark1==0)
						break;
				}
				if(it1.hasNext()||mark1==0)
				{
					mark_of_wrong=1;
					String cmd="The ports of component \""+arc.nd[jj].name+"\" are not match its template!";
    				ConsoleUtil.printMessage(ConsoleFactory.getConsole(), cmd+ "\n");
    				HighLight_s h1=new HighLight_s(arc.nd[jj].id);
    				h1.exe();
				}
				}
			//	NodeTemplateImpl nti=(NodeTemplateImpl) component.getTR();
		 }
		if(mark_of_wrong==0)
		{
			String cmd="The nodes of the architecture match the style requirements!";
			ConsoleUtil.printMessage(ConsoleFactory.getConsole(), cmd+ "\n");
			
		}
			
	}


	 public Document getDocument() {
	        SAXReader saxReader = new SAXReader();
	        Document document = null;
	            try {
					document = saxReader.read(selectedPath.toString());
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	       
	        return document;
	     }
	  public Element getRootElement() {
	       return getDocument().getRootElement();
	    }
	
	
	
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if (!selection.isEmpty() && selection instanceof TreeSelection) {
			TreeSelection treeSelection = (TreeSelection) selection;
			TreePath[] paths = treeSelection.getPaths();
			for (int i = 0; i < paths.length; ++i) {
				if (paths[i] != null) {
					Object obj = paths[i].getLastSegment();
					if (obj instanceof org.eclipse.core.internal.resources.File) {
						this.selectedPath = ((org.eclipse.core.internal.resources.File) obj).getRawLocation();
					} else continue;
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
				this.selectedPath = file.getRawLocation();
			}
		}
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// TODO Auto-generated method stub

	}

}