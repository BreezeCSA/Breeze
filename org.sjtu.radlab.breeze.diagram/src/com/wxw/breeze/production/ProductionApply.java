package com.wxw.breeze.production;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import BreezeModel.diagram.edit.parts.ProductionEditPart;
import BreezeModel.diagram.part.BreezeDiagramEditor;

public class ProductionApply implements IObjectActionDelegate {
	ProductionEditPart sel;
//	private URI selectedPath;
	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		ProudctionModel fr=new ProudctionModel();
		String trs=BreezeDiagramEditor.getSelectedPath();
		trs=trs.substring(0, trs.length()-8);
		fr.setFile_path(trs);
	 	fr.setProduction_id(sel.getElementGuid());
	 	fr.traversalDocumentByProductionID(sel.getElementGuid());
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
	if(selection instanceof StructuredSelection)
		if(((StructuredSelection)selection).getFirstElement() instanceof ProductionEditPart)
			{
				sel=(ProductionEditPart) ((StructuredSelection)selection).getFirstElement();
				
	
				
			}
	//	fr.setProduction_id(production_id);
		
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// TODO Auto-generated method stub
			}

}
