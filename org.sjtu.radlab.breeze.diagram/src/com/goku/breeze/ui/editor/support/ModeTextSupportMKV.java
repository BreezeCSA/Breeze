package com.goku.breeze.ui.editor.support;

import java.util.Map;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;

import com.goku.breeze.common.SafetyAttribute;
import com.goku.breeze.compiler.model.BreezeMode;
import com.goku.breeze.compiler.model.BreezeNode;
import com.goku.breeze.ui.editor.MKVModelEditor;

public class ModeTextSupportMKV extends EditingSupport {
	private TextCellEditor cellEditor = null;
	private final int columnIndex;
	private final ColumnViewer[] refreshViewer;
	private final MKVModelEditor sme;
	private final TransitionComboSupportMKV[] tcs;
	private final ColumnViewer viewer;

	public ModeTextSupportMKV(MKVModelEditor sme, ColumnViewer viewer, int columnIndex, ColumnViewer[] refreshViewer,
			TransitionComboSupportMKV[] tcs) {
		super(viewer);
		// TODO Auto-generated constructor stub
		this.viewer = viewer;
		this.sme = sme;
		this.tcs = tcs;
		this.refreshViewer = refreshViewer;
		this.cellEditor = new TextCellEditor((Composite) viewer.getControl());
		this.columnIndex = columnIndex;
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
		Map<String, Object> prop = bm.getProperties();
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
					for (TransitionComboSupportMKV tc : this.tcs)
						tc.updateInput();
				}
				this.sme.fireModelChange();
			}
			break;
		case 5:

			if (!value.toString().equals(prop.get(SafetyAttribute.MODE_HUMAN_FACTOR))) {
				prop.put(SafetyAttribute.MODE_HUMAN_FACTOR, value.toString());

				this.sme.fireModelChange();
			}
			break;
		case 6:

			if (!value.toString().equals(prop.get(SafetyAttribute.MODE_ECONOMY))) {
				prop.put(SafetyAttribute.MODE_ECONOMY, value.toString());

				this.sme.fireModelChange();
			}
			break;
		}
		this.viewer.refresh();
		for (ColumnViewer v : this.refreshViewer)
			v.refresh();
	}

}
