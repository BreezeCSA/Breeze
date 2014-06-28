package com.wxw.breeze.production;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.PlatformUI;

import BreezeModel.diagram.edit.parts.ArchContentCompartmentEditPart;
import BreezeModel.diagram.edit.parts.ArchEditPart;
import BreezeModel.diagram.edit.parts.BreezeEditPart;
import BreezeModel.diagram.edit.parts.ComponentEditPart;
import BreezeModel.diagram.edit.parts.ConnectorEditPart;
import BreezeModel.diagram.edit.parts.LinkEditPart;
import BreezeModel.diagram.edit.parts.PortEditPart;
import BreezeModel.diagram.part.BreezeDiagramEditor;
import BreezeModel.impl.BreezeImpl;
import BreezeModel.impl.LinkImpl;

public class LineDelete_s {
	public String source_id,target_id;	
	public architecture arc;


	public LineDelete_s(String source_id, String target_id, architecture arc) {
		super();
		this.source_id = source_id;
		this.target_id = target_id;
		this.arc = arc;
	}


	public void exe()
	{
		String edge_id=null;
		for(int i=0;i<arc.edge_num;i++)
		{
			if(arc.ed[i].source_node.equals(source_id)&&arc.ed[i].target_node.equals(target_id))
			{
				edge_id=arc.ed[i].id;
			}
			
		}
		
		
		Object o=PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();		
		BreezeEditPart  testEditPart =(BreezeEditPart) ((BreezeDiagramEditor) o).getDiagramEditPart();			
		TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(testEditPart.getEditingDomain());
		final View testView = (View) testEditPart.getModel();// 某个节点的view
		BreezeDiagramEditor diagramEditor = (BreezeDiagramEditor) o;
		IDiagramGraphicalViewer viewer = diagramEditor.getDiagramGraphicalViewer();
		Object obb=viewer.getEditPartRegistry();
		
		final List<LinkEditPart> ooo=viewer.findEditPartsForElement(edge_id, LinkEditPart.class);//e.g., get with viewer.findEditPartsForElement() or viewer.getEditPartRegistry()		
		LinkEditPart lep=ooo.get(0);
		final View l_view=(View)lep.getModel();
		final LinkImpl l_impl=(LinkImpl) l_view.getElement();
		final BreezeImpl breezeImpl = (BreezeImpl) testView.getElement(); // 某个节点的model
		domain.getCommandStack().execute(new RecordingCommand(domain) {
							protected void doExecute() {
								ViewUtil.destroy(l_view); // 删除此节点的view  ViewUtil.destroy(ViewUtil.getChildByIdStr(testView, lep.));
								breezeImpl.getArch().getEdge().remove(l_impl); // 删除此节点的model
							}
						});
	
	
	}

}
