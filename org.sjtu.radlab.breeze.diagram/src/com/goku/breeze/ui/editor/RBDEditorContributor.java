package com.goku.breeze.ui.editor;

import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.EditorActionBarContributor;

public class RBDEditorContributor extends EditorActionBarContributor {

	private static final String[] TEXTEDITORS_ACTION_IDS = { ActionFactory.UNDO.getId(), ActionFactory.REDO.getId(),
			ActionFactory.COPY.getId(), ActionFactory.PASTE.getId(), ActionFactory.CUT.getId(), ActionFactory.FIND.getId() };

	private static final String[] WORKBENCH_ACTION_IDS = { ActionFactory.UNDO.getId(), ActionFactory.REDO.getId(),
			ActionFactory.COPY.getId(), ActionFactory.PASTE.getId() };

	public RBDEditorContributor() {
		// TODO Auto-generated constructor stub
	}

	private void hookGlobalSAModelActions(RBDEditor rbdEditor, IActionBars actionBars) {
	}

	@Override
	public void setActiveEditor(IEditorPart part) {
		RBDEditor editor = (RBDEditor) part;
	}

	public void setActivePage(RBDEditor rbdEditor, int pageIndex) {
		IActionBars actionBars = this.getActionBars();
		if (actionBars != null) {
			if (pageIndex == rbdEditor.getDefaultPageIndex()) {
				this.hookGlobalSAModelActions(rbdEditor, actionBars);
			} else if (pageIndex == rbdEditor.getSourcePageIndex()) {
			}
			actionBars.updateActionBars();
		}
	}

}
