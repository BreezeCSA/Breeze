package com.goku.breeze.ui.editor;

import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.EditorActionBarContributor;

public class SafetyEditorContributor extends EditorActionBarContributor {

	private static final String[] TEXTEDITORS_ACTION_IDS = { ActionFactory.UNDO.getId(), ActionFactory.REDO.getId(),
			ActionFactory.COPY.getId(), ActionFactory.PASTE.getId(), ActionFactory.CUT.getId(), ActionFactory.FIND.getId() };

	private static final String[] WORKBENCH_ACTION_IDS = { ActionFactory.UNDO.getId(), ActionFactory.REDO.getId(),
			ActionFactory.COPY.getId(), ActionFactory.PASTE.getId() };

	public SafetyEditorContributor() {
		// TODO Auto-generated constructor stub
	}

	private void hookGlobalSAModelActions(SafetyEditor editor, IActionBars actionBars) {
	}

	@Override
	public void setActiveEditor(IEditorPart part) {
		SafetyEditor editor = (SafetyEditor) part;
	}

	public void setActivePage(SafetyEditor editor, int pageIndex) {
		IActionBars actionBars = this.getActionBars();
		if (actionBars != null) {
			if (pageIndex == editor.getDefaultPageIndex()) {
				this.hookGlobalSAModelActions(editor, actionBars);
			} else if (pageIndex == editor.getSourcePageIndex()) {
			}
			actionBars.updateActionBars();
		}
	}

}
