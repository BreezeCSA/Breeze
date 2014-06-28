package com.goku.breeze.ui.editor.support;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;

import com.goku.breeze.compiler.model.BreezeMode;
import com.goku.breeze.compiler.model.BreezeNode;
import com.goku.breeze.ui.editor.ModelEditor;

public class ModeTextSupport extends EditingSupport {
	private TextCellEditor cellEditor = null;
	private final int columnIndex;
	private final ColumnViewer[] refreshViewer;

	private final ModelEditor sme;
	private final TransitionComboSupport[] tcs;
	private final ColumnViewer viewer;

	public ModeTextSupport(ModelEditor sme, ColumnViewer viewer, int columnIndex, ColumnViewer[] refreshViewer,

	TransitionComboSupport[] tcs) {
		super(viewer);
		// TODO Auto-generated constructor stub
		this.viewer = viewer;
		this.sme = sme;
		this.refreshViewer = refreshViewer;
		this.cellEditor = new TextCellEditor((Composite) viewer.getControl());
		this.columnIndex = columnIndex;
		this.tcs = tcs;
	}

	@Override
	protected boolean canEdit(Object element) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		// TODO Auto-generated method stub
		return this.cellEditor;
	}

	@Override
	protected Object getValue(Object element) {
		// TODO Auto-generated method stub
		BreezeMode bm = (BreezeMode) element;

		switch (this.columnIndex) {
		case 0:
			return bm.getId();

		}
		return "";
	}

	@Override
	protected void setValue(Object element, Object value) {
		// TODO Auto-generated method stub
		BreezeMode bm = (BreezeMode) element;

		switch (this.columnIndex) {
		case 0:
			Object input = this.getViewer().getInput();
			BreezeNode bn = (BreezeNode) input;
			boolean dup = false;
			for (BreezeMode mode : bn.getModeList()) {
				String modeId = mode.getId();
				if (modeId.equals(value.toString())) {
					dup = true;
					break;
				}
			}
			if (dup) break;
			if (!value.toString().equals(bm.getId())) {
				bn.renameMode(bm.getId(), value.toString());
				if (this.tcs != null) {
					for (TransitionComboSupport tc : this.tcs)
						tc.updateInput();
				}
				this.sme.fireModelChange();
			}
			break;

		}
		this.viewer.refresh();
		if (this.refreshViewer != null) {
			for (ColumnViewer v : this.refreshViewer)
				v.refresh();
		}

	}

}
