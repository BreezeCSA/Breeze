package com.wxw.breeze.production;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
 
















import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.workspace.AbstractEMFOperation;
import org.eclipse.gef.GraphicalViewer;  
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint; 
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequestFactory;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import BreezeModel.diagram.edit.parts.ArchContentCompartmentEditPart;
import BreezeModel.diagram.edit.parts.ArchEditPart;
import BreezeModel.diagram.edit.parts.BreezeEditPart;
import BreezeModel.diagram.edit.parts.ComponentEditPart;
import BreezeModel.diagram.edit.parts.ConnectorEditPart;
import BreezeModel.diagram.part.BreezeDiagramEditor;
import BreezeModel.diagram.part.BreezeDiagramEditorPlugin;
import BreezeModel.impl.ComponentImpl;



public class NodeAdd_s {
	public node node_from;

 
	public NodeAdd_s(node node_from) {
		super();
		this.node_from = node_from;
	}
	public void drawLink_com(final ComponentEditPart sourceModuleEditPart) throws ExecutionException {
	//public void drawLink_com() throws ExecutionException {
        IElementType type = BreezeModel.diagram.providers.BreezeElementTypes.Component_3005 ; // 连接线类型
        CreateElementRequest createConnectionRequest  = new CreateElementRequest(type);
        Object o=PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();		
		BreezeEditPart  testEditPart =(BreezeEditPart) ((BreezeDiagramEditor) o).getDiagramEditPart();			
		   CreateViewRequest createViewRequest= CreateViewRequestFactory.getCreateShapeRequest(type, BreezeDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			ArchEditPart aep=(ArchEditPart) testEditPart.getChildren().get(0);
			ArchContentCompartmentEditPart ace=(ArchContentCompartmentEditPart) aep.getChildren().get(1);
           org.eclipse.gef.commands.Command createCommand = ace.getCommand(createViewRequest); 
           ace.getDiagramEditDomain().getDiagramCommandStack().execute(createCommand );
           ace=(ArchContentCompartmentEditPart) aep.getChildren().get(1);
           ComponentEditPart last=(ComponentEditPart) ace.getChildren().get(ace.getChildren().size()-1);
           final Object oo=((View)((ComponentEditPart) (ace.getChildren().get(ace.getChildren().size()-1))).getModel()).getElement();
           //(ComponentImpl)oo
       //  ((ComponentImpl)oo).setTR(((ComponentImpl)(((View)sourceModuleEditPart.getModel()).getElement())).getTR());
          
          
           IWorkbenchPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
           TransactionalEditingDomain editingDomain = null;
           EditingDomain domain = null;
           if (part != null) {
	        	IEditingDomainProvider edProvider = (IEditingDomainProvider) part
	        	.getAdapter(IEditingDomainProvider.class);

	        	if (edProvider != null) {
	        		 domain = edProvider.getEditingDomain();

	        		if (domain instanceof TransactionalEditingDomain) {
	        			editingDomain = (TransactionalEditingDomain) domain;
	        		}
	        	}
	        }        
           editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
               @Override
               protected void doExecute() {
            	   ((ComponentImpl)oo).eSet(4, ((ComponentImpl)(((View)sourceModuleEditPart.getModel()).getElement())).getTR());
            	   //Do anything
               }
           });
           
           
         //  ((ComponentImpl)oo).eSet(4, ((ComponentImpl)(((View)sourceModuleEditPart.getModel()).getElement())).getTR());
           System.out.println("hah");
           
           
           
           
		
		 
 }

	public void drawLink_con(ConnectorEditPart sourceModuleEditPart) throws ExecutionException {

        IElementType type = BreezeModel.diagram.providers.BreezeElementTypes.Connector_3003 ; // 连接线类型
        CreateElementRequest createConnectionRequest  = new CreateElementRequest(type);
        Object o=PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();		
		BreezeEditPart  testEditPart =(BreezeEditPart) ((BreezeDiagramEditor) o).getDiagramEditPart();			
		   CreateViewRequest createViewRequest= CreateViewRequestFactory.getCreateShapeRequest(type, BreezeDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			ArchEditPart aep=(ArchEditPart) testEditPart.getChildren().get(0);
			ArchContentCompartmentEditPart ace=(ArchContentCompartmentEditPart) aep.getChildren().get(1);
           org.eclipse.gef.commands.Command createCommand = ace.getCommand(createViewRequest); 
           ace.getDiagramEditDomain().getDiagramCommandStack().execute(createCommand );
           
		
		 
 }


	public void exe()
	{
		
		
		
		Object o=PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();		
		BreezeEditPart  testEditPart =(BreezeEditPart) ((BreezeDiagramEditor) o).getDiagramEditPart();			
		BreezeDiagramEditor diagramEditor = (BreezeDiagramEditor) o;
		GraphicalViewer bgc=diagramEditor.getDiagramGraphicalViewer(); 
		
		IDiagramGraphicalViewer viewer = diagramEditor.getDiagramGraphicalViewer();
		HashMap map=(HashMap) viewer.getEditPartRegistry();
		Iterator iter = map.entrySet().iterator();
		Object key,val = null;
		
		if(node_from.type==1)
		{
			while (iter.hasNext()) { 
			    Map.Entry entry = (Map.Entry) iter.next(); 
			    key = entry.getKey(); 
			    val = entry.getValue(); 
			    if(val instanceof ComponentEditPart)
			    	break;
			} 
			ComponentEditPart lep=new ComponentEditPart((View) ((ComponentEditPart)val).getModel());
			List<ComponentEditPart> ooo=viewer.findEditPartsForElement(node_from.id, ComponentEditPart.class);
	          try {
				drawLink_com(ooo.get(0));
	        	//  drawLink_com();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			while (iter.hasNext()) { 
			    Map.Entry entry = (Map.Entry) iter.next(); 
			    key = entry.getKey(); 
			    val = entry.getValue(); 
			    if(val instanceof ConnectorEditPart)
			    	break;
			} 
			ConnectorEditPart lep=new ConnectorEditPart((View) ((ConnectorEditPart)val).getModel());
			List<ConnectorEditPart> ooo=viewer.findEditPartsForElement(node_from.id, ConnectorEditPart.class);
	          try {
				drawLink_con(ooo.get(0));
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
//	public void exe()
//	{
//			 	
//				Object o=PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
//				if(o instanceof BreezeDiagramEditor){			
//				EObject list =((DiagramDocumentEditor) o).getDiagram().getElement();
//				RootEditPart rootEditPart = ((DiagramEditor) o).getDiagramEditPart().getRoot();
//				EList<EObject> elist = list.eContents(); 
//				List<EditPart> parts = rootEditPart.getChildren();
//				for (EditPart ep : parts) {
//					List<ArchEditPart> d_parts=ep.getChildren();
//					for(Object aep : d_parts)
//					{
//						if(aep instanceof ArchEditPart){
//						List<ArchEditPart> d_d_parts=((ArchEditPart)aep).getChildren();
//						
//						for(Object anep : d_d_parts)
//						{
//							if(anep instanceof ArchContentCompartmentEditPart)
//							{
//								List<AbstractBorderedShapeEditPart> d_d_d_parts=((ArchContentCompartmentEditPart) anep).getChildren();
//								for(AbstractBorderedShapeEditPart cep : d_d_d_parts)
//	 							{
//								
//								if(cep instanceof ComponentEditPart)
//									if(((ComponentEditPart) cep).getElementGuid().toString().equals(element_id))
//								 	{
//								 		RGB rgb=new RGB(255,0,0); 
//										Color cl=new Color(null,rgb);
//										((ComponentEditPart) cep).setBackgroundColor(cl);
//
//								 	}
//								 if(cep instanceof ConnectorEditPart)
//									 if(((ConnectorEditPart) cep).getElementGuid().toString().equals(element_id))
//									 	{
//									 		RGB rgb=new RGB(255,0,0); 
//											Color cl=new Color(null,rgb);
//											((ConnectorEditPart) cep).setBackgroundColor(cl);
//
//									 	}
//								
//								 List<Object> d_d_d_d_parts=cep.getChildren();
//								 for(Object pep : d_d_d_d_parts)	
//								 {
//									if(pep instanceof PortEditPart)
//									 if(((PortEditPart)pep).getElementGuid().toString().equals(element_id))
//									 {
//										 RGB rgb=new RGB(255,0,0); 
//											Color cl=new Color(null,rgb);
//											((PortEditPart)pep).setBackgroundColor(cl);
//									 }
//								 }
//								}
//								
//							}
//							
//						}
//						}
//					}
//				}
//				
//				}
//	}
//}
