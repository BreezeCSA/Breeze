package com.goku.breeze.ui.editor.support;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.goku.breeze.compiler.model.BreezeArch;
import com.goku.breeze.compiler.model.BreezeEvent;
import com.goku.breeze.compiler.model.BreezeMode;
import com.goku.breeze.compiler.model.BreezeModeTransition;
import com.goku.breeze.compiler.model.BreezeNode;
import com.goku.breeze.ui.editor.MKVModelEditor;

public class TransitionComboSupportMKV extends EditingSupport {
	private ComboBoxViewerCellEditor cellEditor = null;
	private final int columnIndex;
	private String[] input;
	private final BreezeNode node;
	private final MKVModelEditor sme;
	private final ColumnViewer viewer;

	public TransitionComboSupportMKV(MKVModelEditor sme, ColumnViewer viewer, int columnIndex, BreezeNode node) {
		super(viewer);
		// TODO Auto-generated constructor stub
		this.viewer = viewer;
		this.node = node;
		this.sme = sme;
		this.columnIndex = columnIndex;
		this.cellEditor = new ComboBoxViewerCellEditor((Composite) this.getViewer().getControl(), SWT.READ_ONLY);
		this.cellEditor.setLabelProvider(new LabelProvider());
		this.cellEditor.setContenProvider(new ArrayContentProvider());
		this.updateInput();
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
		BreezeModeTransition bmt = (BreezeModeTransition) element;
		switch (this.columnIndex) {
		case 0:
			return bmt.getSourceState().getId();
		case 1:
			return bmt.getTargetState().getId();
		case 2:
			return bmt.getTrigger().getId();
		}
		return "";
	}

	private boolean isValidComboValue(String value) {
		if (this.input == null || this.input.length == 0)
			return false;
		for (Object str : this.input)
			if (value.equals(str.toString()))
				return true;
		return false;
	}

	public void setCellEditorInput(String[] input) {
		this.cellEditor.setInput(input);
		this.cellEditor.getViewer().refresh();
	}

	@Override
	protected void setValue(Object element, Object value) {
		// TODO Auto-generated method stub
		BreezeModeTransition bmt = (BreezeModeTransition) element;
		switch (this.columnIndex) {
		case 0:
			if (!bmt.getSourceState().getId().equals(value.toString())) {
				BreezeMode src = this.node.getMode(value.toString());
				bmt.setSourceState(src);
				this.sme.fireModelChange();
			}
			break;
		case 1:
			if (!bmt.getTargetState().getId().equals(value.toString())) {
				BreezeMode tgt = this.node.getMode(value.toString());
				bmt.setTargetState(tgt);
				this.sme.fireModelChange();
			}
			break;
		case 2:
			if (!bmt.getTrigger().getId().equals(value.toString())) {
				for (BreezeEvent evt : this.node.getParent().getEventList()) {
					if (evt.getId().equals(value.toString())) {
						bmt.setTrigger(new BreezeEvent(evt.getId(), evt.getNumber(), evt.getProbability()));
						this.sme.fireModelChange();
						break;
					}
				}
			}
			break;
		case 3:
			if (!value.toString().equals(bmt.getTriggerType())) {
				bmt.setTriggerType(value.toString());
				this.sme.fireModelChange();
			}
			break;
		}
		this.viewer.refresh();
	}

	public void updateInput() {
		if (this.columnIndex == 0 || this.columnIndex == 1) {
			this.input = new String[this.node.getModeList().size()];
			int len = 0;
			for (BreezeMode bm : this.node.getModeList()) {
				this.input[len++] = bm.getId();
			}
		} else if (this.columnIndex == 2) {
			BreezeArch arch = this.node.getParent();
			int len = 0;
			this.input = new String[arch.getEventList().size()];
			for (BreezeEvent event : arch.getEventList()) {
				this.input[len++] = event.getId();
			}
		} else if (this.columnIndex == 3) {
			this.input = new String[] { BreezeModeTransition.TRIGGER_TYPE_SELF, BreezeModeTransition.TRIGGER_TYPE_SEND,
					BreezeModeTransition.TRIGGER_TYPE_RECEIVE };
		}
		this.cellEditor.setInput(this.input);
		this.cellEditor.getViewer().refresh();
	}
}
