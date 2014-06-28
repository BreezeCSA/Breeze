package com.wxw.breeze.production;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.PlatformUI;

import BreezeModel.diagram.edit.commands.LinkCreateCommand;
import BreezeModel.diagram.edit.parts.BreezeEditPart;
import BreezeModel.diagram.edit.parts.ArchContentCompartmentEditPart;
import BreezeModel.diagram.edit.parts.ArchEditPart;
import BreezeModel.diagram.edit.parts.ArchNameEditPart;
import BreezeModel.diagram.edit.parts.ComponentEditPart;
import BreezeModel.diagram.edit.parts.ConnectorEditPart;
import BreezeModel.diagram.edit.parts.LinkEditPart;
import BreezeModel.diagram.edit.parts.PortEditPart; 
import BreezeModel.diagram.part.BreezeDiagramEditor;
import BreezeModel.impl.BreezeImpl;
import BreezeModel.impl.LinkImpl;

import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
public class HighLight_s {
String element_id; 
public String getElement_id() {
	return element_id;
}
public void setElement_id(String element_id) {
	this.element_id = element_id;
}
public HighLight_s(String element_id) {
this.element_id = element_id;
} 
public void exe()
{
		 	
			Object o=PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			if(o instanceof BreezeDiagramEditor){			
			EObject list =((DiagramDocumentEditor) o).getDiagram().getElement();
			RootEditPart rootEditPart = ((DiagramEditor) o).getDiagramEditPart().getRoot();
			EList<EObject> elist = list.eContents(); 
			List<EditPart> parts = rootEditPart.getChildren();
			for (EditPart ep : parts) {
				List<ArchEditPart> d_parts=ep.getChildren();
				for(Object aep : d_parts)
				{
					if(aep instanceof ArchEditPart){
					List<ArchEditPart> d_d_parts=((ArchEditPart)aep).getChildren();
					
					for(Object anep : d_d_parts)
					{
						if(anep instanceof ArchContentCompartmentEditPart)
						{
							List<AbstractBorderedShapeEditPart> d_d_d_parts=((ArchContentCompartmentEditPart) anep).getChildren();
							for(AbstractBorderedShapeEditPart cep : d_d_d_parts)
 							{
							
							 if(cep instanceof ComponentEditPart)
								if(((ComponentEditPart) cep).getElementGuid().toString().equals(element_id))
							 	{
							 		RGB rgb=new RGB(255,0,0); 
									Color cl=new Color(null,rgb);
									((ComponentEditPart) cep).setBackgroundColor(cl);

							 	}
							 if(cep instanceof ConnectorEditPart)
								 if(((ConnectorEditPart) cep).getElementGuid().toString().equals(element_id))
								 	{
								 		RGB rgb=new RGB(255,0,0); 
										Color cl=new Color(null,rgb);
										((ConnectorEditPart) cep).setBackgroundColor(cl);

								 	}
							
							 List<Object> d_d_d_d_parts=cep.getChildren();
							 for(Object pep : d_d_d_d_parts)	
							 {
								if(pep instanceof PortEditPart)
								 if(((PortEditPart)pep).getElementGuid().toString().equals(element_id))
								 {
									 RGB rgb=new RGB(255,0,0); 
										Color cl=new Color(null,rgb);
										((PortEditPart)pep).setBackgroundColor(cl);
								 }
							 }
							}
							
						}
						
					}
					}
				}
			}
			
			}
}

public void exe_cancle()
{

			Object o=PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			//Object ppp=PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();

			
			
			
//			BreezeEditPart  testEditPart =(BreezeEditPart) ((BreezeDiagramEditor) o).getDiagramEditPart();			
//			TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(testEditPart.getEditingDomain());
//			final View testView = (View) testEditPart.getModel();// ĳ���ڵ��view
//			BreezeDiagramEditor diagramEditor = (BreezeDiagramEditor) o;
//			IDiagramGraphicalViewer viewer = diagramEditor.getDiagramGraphicalViewer();
//			Object obb=viewer.getEditPartRegistry();
//			
//			final List<LinkEditPart> ooo=viewer.findEditPartsForElement("_Wr9e0PoiEeOLY_r3ye6FbA", LinkEditPart.class);//e.g., get with viewer.findEditPartsForElement() or viewer.getEditPartRegistry()		
//			LinkEditPart lep=ooo.get(0);
//			final View l_view=(View)lep.getModel();
//			final LinkImpl l_impl=(LinkImpl) l_view.getElement();
//			final BreezeImpl breezeImpl = (BreezeImpl) testView.getElement(); // ĳ���ڵ��model
//			domain.getCommandStack().execute(new RecordingCommand(domain) {
//								protected void doExecute() {
//									ViewUtil.destroy(l_view); // ɾ���˽ڵ��view  ViewUtil.destroy(ViewUtil.getChildByIdStr(testView, lep.));
//								 	breezeImpl.getArch().getEdge().remove(l_impl); // ɾ���˽ڵ��model
//								}
//							});
			
			
			
			if(o instanceof BreezeDiagramEditor){
				
			RootEditPart rootEditPart = ((DiagramEditor) o).getDiagramEditPart().getRoot();
		//	LinkEditPart lp=new LinkEditPart(rootEditPart);
			List<EditPart> parts = rootEditPart.getChildren();
			for (EditPart ep : parts) {


//				BreezeDiagramEditor diagramEditor = (BreezeDiagramEditor) o;
//				IDiagramGraphicalViewer viewer = diagramEditor.getDiagramGraphicalViewer();
//				Object obb=viewer.getEditPartRegistry();
//				List<LinkEditPart> ooo=viewer.findEditPartsForElement("_Wr9e0PoiEeOLY_r3ye6FbA", LinkEditPart.class);//e.g., get with viewer.findEditPartsForElement() or viewer.getEditPartRegistry()
//				LinkEditPart editpart=ooo.get(0);
//				EditingDomain editingDomain = diagramEditor.getEditingDomain();
//				Object obj=editingDomain.getChildren(Object.class);
//				editingDomain.getCommandStack().execute(RemoveCommand.create(editingDomain, editpart.getModel()));
				
				
				//TransactionalEditingDomain domain = TransactionUtil.getEditingDomain((BreezeDiagramEditor) o);
//				BreezeEditPart  testEditPart =(BreezeEditPart) ((BreezeDiagramEditor) o).getDiagramEditPart();
//				
//				TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(testEditPart.getEditingDomain()); // ͼ��model��editingDomain
//				final View testView = (View) testEditPart.getModel(); // ���������Ľڵ��View

//				BreezeImpl breezeImpl = (BreezeImpl) testView.getElement(); // ���������Ľڵ��model
//				 final List<Object> incomingList = testView.getTransientChildren(); // �˽ڵ��ϵ���������
//				int incomingSize = incomingList.size();
//				for (int j = incomingSize - 1; j >= 0; j--) {
//					domain.getCommandStack().execute(new RecordingCommand(domain) {
//						protected void doExecute() {
//							Edge edge = (Edge) incomingList.get(j); // ���ߵ�view
//							if (edge.getElement() instanceof LinkImpl) {
//				                             LinkImpl linkImpl = (LinkImpl) edge.getElement(); // ���ߵ�model
//							     ViewUtil.destroy(edge); // ɾ�����ߵ�view
//							     xxxDiagramImpl.getLinks().remove(linkImpl); // ɾ�����ߵ�model
//							}
//					}
//					}
//					);
//				}
				
				List<ArchEditPart> d_parts=ep.getChildren(); 
				for(Object aep : d_parts)
				{
					if(aep instanceof ArchEditPart){
					List<ArchEditPart> d_d_parts=((ArchEditPart)aep).getChildren();
				
					for(Object anep : d_d_parts)
					{
						if(anep instanceof ArchContentCompartmentEditPart)
						{
//							
							
							List<AbstractBorderedShapeEditPart> d_d_d_parts=((ArchContentCompartmentEditPart) anep).getChildren();
							int countt=0;
							Object oo1,oo2;
							for(AbstractBorderedShapeEditPart cep : d_d_d_parts)
 							{
								
								if(countt==0)
									{
									oo1=cep;
									countt++;
									}
								else oo2=cep;
								
							
							 if(cep instanceof ComponentEditPart)
							 {
								 
							 		RGB rgb=new RGB(255,255,255); 
									Color cl=new Color(null,rgb);
									((ComponentEditPart) cep).setBackgroundColor(cl);
							 }
							 if(cep instanceof ConnectorEditPart)
								
								 	{
										RGB rgb=new RGB(255,255,255); 
										Color cl=new Color(null,rgb);
										((ConnectorEditPart) cep).setBackgroundColor(cl);

								 	}
							
							 List<Object> d_d_d_d_parts=cep.getChildren();
							 for(Object pep : d_d_d_d_parts)	
							 {
								if(pep instanceof PortEditPart)
							
								 {
									RGB rgb=new RGB(255,255,255);  
										Color cl=new Color(null,rgb);
										((PortEditPart)pep).setBackgroundColor(cl);
								 }
							 }
							}
						//	Object oob=new LinkCreateCommand((BreezeDiagramEditor)o,oo1,oo2);
						}
						
				      }
					}
				}
			}
			
			}
}
}