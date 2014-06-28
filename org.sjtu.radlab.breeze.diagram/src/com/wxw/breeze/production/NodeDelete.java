package com.wxw.breeze.production;

import java.util.List;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.PlatformUI;

import BreezeModel.diagram.edit.parts.BreezeEditPart;
import BreezeModel.diagram.edit.parts.ComponentEditPart;
import BreezeModel.diagram.edit.parts.ConnectorEditPart;
import BreezeModel.diagram.edit.parts.LinkEditPart;
import BreezeModel.diagram.part.BreezeDiagramEditor;
import BreezeModel.impl.BreezeImpl;
import BreezeModel.impl.ComponentImpl;
import BreezeModel.impl.ConnectorImpl;
import BreezeModel.impl.LinkImpl;

public class NodeDelete {
	public void delete_node_id(String node_id,int type) //1为构件 0为连接件
	{
					
		Object o=PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();		
		BreezeEditPart  testEditPart =(BreezeEditPart) ((BreezeDiagramEditor) o).getDiagramEditPart();			
		TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(testEditPart.getEditingDomain());
		final View testView = (View) testEditPart.getModel();// 某个节点的view
		BreezeDiagramEditor diagramEditor = (BreezeDiagramEditor) o;
		IDiagramGraphicalViewer viewer = diagramEditor.getDiagramGraphicalViewer();
		Object obb=viewer.getEditPartRegistry();
		if(type==1)
		{
		final List<ComponentEditPart> ooo=viewer.findEditPartsForElement(node_id, ComponentEditPart.class);//e.g., get with viewer.findEditPartsForElement() or viewer.getEditPartRegistry()		
		ComponentEditPart lep=ooo.get(0);
		final View l_view=(View)lep.getModel();
		final ComponentImpl l_impl=(ComponentImpl) l_view.getElement();
		final BreezeImpl breezeImpl = (BreezeImpl) testView.getElement(); // 某个节点的model
		domain.getCommandStack().execute(new RecordingCommand(domain) {
							protected void doExecute() {
								ViewUtil.destroy(l_view); // 删除此节点的view  ViewUtil.destroy(ViewUtil.getChildByIdStr(testView, lep.));
								breezeImpl.getArch().getEdge().remove(l_impl); // 删除此节点的model
							}
						});
	
		}
		else if(type==0)
		{
			final List<ConnectorEditPart> ooo=viewer.findEditPartsForElement(node_id, ConnectorEditPart.class);//e.g., get with viewer.findEditPartsForElement() or viewer.getEditPartRegistry()		
			ConnectorEditPart lep=ooo.get(0);
			final View l_view=(View)lep.getModel();
			final ConnectorImpl l_impl=(ConnectorImpl) l_view.getElement();
			final BreezeImpl breezeImpl = (BreezeImpl) testView.getElement(); // 某个节点的model
			domain.getCommandStack().execute(new RecordingCommand(domain) {
								protected void doExecute() {
									ViewUtil.destroy(l_view); // 删除此节点的view  ViewUtil.destroy(ViewUtil.getChildByIdStr(testView, lep.));
									breezeImpl.getArch().getEdge().remove(l_impl); // 删除此节点的model
								}
							});
			
			
		}
	}
}
