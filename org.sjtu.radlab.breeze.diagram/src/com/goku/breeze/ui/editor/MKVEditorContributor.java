package com.goku.breeze.ui.editor;

import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.EditorActionBarContributor;

public class MKVEditorContributor extends EditorActionBarContributor {

	private static final String[] TEXTEDITORS_ACTION_IDS = { ActionFactory.UNDO.getId(), ActionFactory.REDO.getId(),
			ActionFactory.COPY.getId(), ActionFactory.PASTE.getId(), ActionFactory.CUT.getId(), ActionFactory.FIND.getId() };

	private static final String[] WORKBENCH_ACTION_IDS = { ActionFactory.UNDO.getId(), ActionFactory.REDO.getId(),
			ActionFactory.COPY.getId(), ActionFactory.PASTE.getId() };

	public MKVEditorContributor() {
		// TODO Auto-generated constructor stub
	}

	private void hookGlobalSAModelActions(MKVEditor mkvEditor, IActionBars actionBars) {
	}

	@Override
	public void setActiveEditor(IEditorPart part) {
		MKVEditor editor = (MKVEditor) part;
	}

	public void setActivePage(MKVEditor mkvEditor, int pageIndex) {
		IActionBars actionBars = this.getActionBars();
		if (actionBars != null) {
			if (pageIndex == mkvEditor.getDefaultPageIndex()) {
				this.hookGlobalSAModelActions(mkvEditor, actionBars);
			} else if (pageIndex == mkvEditor.getSourcePageIndex()) {
			}
			actionBars.updateActionBars();
		}
	}

}
