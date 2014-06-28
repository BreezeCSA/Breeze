package com.goku.breeze.ui.editor;

import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.EditorActionBarContributor;

public class DTMCEditorContributor extends EditorActionBarContributor {

	private static final String[] TEXTEDITORS_ACTION_IDS = { ActionFactory.UNDO.getId(), ActionFactory.REDO.getId(),
			ActionFactory.COPY.getId(), ActionFactory.PASTE.getId(), ActionFactory.CUT.getId(), ActionFactory.FIND.getId() };

	private static final String[] WORKBENCH_ACTION_IDS = { ActionFactory.UNDO.getId(), ActionFactory.REDO.getId(),
			ActionFactory.COPY.getId(), ActionFactory.PASTE.getId() };

	public DTMCEditorContributor() {
		// TODO Auto-generated constructor stub
	}

	private void hookGlobalSAModelActions(DTMCEditor dtmcEditor, IActionBars actionBars) {
	}

	@Override
	public void setActiveEditor(IEditorPart part) {
		DTMCEditor dtmcEditor = (DTMCEditor) part;
	}

	public void setActivePage(DTMCEditor dtmcEditor, int pageIndex) {
		IActionBars actionBars = this.getActionBars();
		if (actionBars != null) {
			if (pageIndex == dtmcEditor.getDefaultPageIndex()) {
				this.hookGlobalSAModelActions(dtmcEditor, actionBars);
			} else if (pageIndex == dtmcEditor.getSourcePageIndex()) {
			}
			actionBars.updateActionBars();
		}
	}

}
