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
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramColorRegistry;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.FillStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
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
			if(o instanceof BreezeDiagramEditor){
				
			RootEditPart rootEditPart = ((DiagramEditor) o).getDiagramEditPart().getRoot();
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
									((ComponentEditPart) cep).refresh();
							 }
							 if(cep instanceof ConnectorEditPart)
								
								 	{
										((ConnectorEditPart) cep).refresh();
								 	}
							
							 List<Object> d_d_d_d_parts=cep.getChildren();
							 for(Object pep : d_d_d_d_parts)	
							 {
								if(pep instanceof PortEditPart)
							
								 {
										((PortEditPart)pep).refresh();
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
}