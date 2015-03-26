package com.wxw.breeze.production;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;











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
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gef.ui.actions.DeleteAction;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.actions.PromptingDeleteAction;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import BreezeModel.Node;
import BreezeModel.Port;
import BreezeModel.diagram.edit.parts.BreezeEditPart;
import BreezeModel.diagram.edit.parts.ComponentEditPart;
import BreezeModel.diagram.edit.parts.ConnectorEditPart;
import BreezeModel.diagram.edit.parts.LinkEditPart;
import BreezeModel.diagram.edit.parts.PortEditPart;
import BreezeModel.diagram.part.BreezeDiagramEditor;
import BreezeModel.impl.BreezeImpl;
import BreezeModel.impl.ComponentImpl;
import BreezeModel.impl.ConnectorImpl;
import BreezeModel.impl.LinkImpl;
import BreezeModel.impl.PortImpl;

public class NodeDelete extends DeleteAction{
	public NodeDelete(IEditorPart editor) {
		
		super(editor);
		
		// TODO Auto-generated constructor stub
	}

	public void delete_node_id(String node_id,int type) //1为构件 0为连接件
	{
					
		Object o=PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();		
		BreezeEditPart  testEditPart =(BreezeEditPart) ((BreezeDiagramEditor) o).getDiagramEditPart();			
		//TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(testEditPart.getEditingDomain());
		final View testView = (View) testEditPart.getModel();// 某个节点的view
		BreezeDiagramEditor diagramEditor = (BreezeDiagramEditor) o;
		final IDiagramGraphicalViewer viewer = diagramEditor.getDiagramGraphicalViewer();
		Object obb=viewer.getEditPartRegistry();
		if(type==1)
		{
		final List<ComponentEditPart> ooo=viewer.findEditPartsForElement(node_id, ComponentEditPart.class);//e.g., get with viewer.findEditPartsForElement() or viewer.getEditPartRegistry()		
		final PromptingDeleteAction pda=new PromptingDeleteAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart());	
		  TransactionalEditingDomain editingDomain = null;
	        // try adapting the workbench part
	        IWorkbenchPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
	        if (part != null) {
	        	IEditingDomainProvider edProvider = (IEditingDomainProvider) part
	        	.getAdapter(IEditingDomainProvider.class);

	        	if (edProvider != null) {
	        		EditingDomain domain = edProvider.getEditingDomain();

	        		if (domain instanceof TransactionalEditingDomain) {
	        			editingDomain = (TransactionalEditingDomain) domain;
	        		}
	        	}
	        }        
	        if (editingDomain != null) {
	        	final Command command[] = new Command[1];
	        	AbstractEMFOperation operation = new AbstractEMFOperation(
	        			editingDomain, DiagramUIMessages.DeleteCommand_Label) {

	        		protected IStatus doExecute(IProgressMonitor monitor,
	        				IAdaptable info)
	        		throws ExecutionException {
	        			command[0] = pda.createCommand(ooo);
	        			return CommandResult.newOKCommandResult().getStatus();

	        		}
	        	};
	        	try {
	        		operation.execute(null, null);
	        	} catch (ExecutionException e) {
	        		// do nothing
	        	}

	        	if (command[0] != null)
	        		execute(command[0]);
	        }
		}
		
		else 
			if(type==0)
			{
				final List<ConnectorEditPart> ooo=viewer.findEditPartsForElement(node_id, ConnectorEditPart.class);//e.g., get with viewer.findEditPartsForElement() or viewer.getEditPartRegistry()		
				final PromptingDeleteAction pda=new PromptingDeleteAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart());	
				  TransactionalEditingDomain editingDomain = null;
			        // try adapting the workbench part
			        IWorkbenchPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
			        if (part != null) {
			        	IEditingDomainProvider edProvider = (IEditingDomainProvider) part
			        	.getAdapter(IEditingDomainProvider.class);

			        	if (edProvider != null) {
			        		EditingDomain domain = edProvider.getEditingDomain();

			        		if (domain instanceof TransactionalEditingDomain) {
			        			editingDomain = (TransactionalEditingDomain) domain;
			        		}
			        	}
			        }        
			        if (editingDomain != null) {
			        	final Command command[] = new Command[1];
			        	AbstractEMFOperation operation = new AbstractEMFOperation(
			        			editingDomain, DiagramUIMessages.DeleteCommand_Label) {

			        		protected IStatus doExecute(IProgressMonitor monitor,
			        				IAdaptable info)
			        		throws ExecutionException {
			        			command[0] = pda.createCommand(ooo);
			        			return CommandResult.newOKCommandResult().getStatus();

			        		}
			        	};
			        	try {
			        		operation.execute(null, null);
			        	} catch (ExecutionException e) {
			        		// do nothing
			        	}

			        	if (command[0] != null)
			        		execute(command[0]);
			        }
				
			}
		
		

	}
	
	public void deleteNodes(List editparts)
    {
        CompoundCommand compoundCommand =new CompoundCommand("delete nodes");
        GroupRequest deleteReq = new GroupRequest(RequestConstants.REQ_DELETE);
         
        deleteReq.setEditParts(editparts);
        for (int i = 0; i < editparts.size(); i++) {
            EditPart object = (EditPart) editparts.get(i);
            Command deleteCmd = object.getCommand(deleteReq);
            if (deleteCmd != null)
                compoundCommand.add(deleteCmd);
        }
         Object llok=((ShapeNodeEditPart)editparts.get(0)).getDiagramEditDomain();
        ((ShapeNodeEditPart)editparts.get(0)).getDiagramEditDomain().getDiagramCommandStack().execute(compoundCommand);
    }
}
