package com.goku.breeze.ui.editor.support;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.goku.breeze.compiler.model.BreezeMode;
import com.goku.breeze.ui.editor.ModelEditor;

public class ModeComboSupport extends EditingSupport {
	private static final String[] ModeType = new String[] { BreezeMode.TYPE_INITIAL, BreezeMode.TYPE_NORMAL, };
	private ComboBoxViewerCellEditor cellEditor = null;
	private final ModelEditor sme;
	private final ColumnViewer viewer;

	public ModeComboSupport(ModelEditor sme, ColumnViewer viewer) {

		super(viewer);
		// TODO Auto-generated constructor stub
		this.viewer = viewer;
		this.sme = sme;
		this.cellEditor = new ComboBoxViewerCellEditor((Composite) this.getViewer().getControl(), SWT.READ_ONLY);
		this.cellEditor.setLabelProvider(new LabelProvider());
		this.cellEditor.setContenProvider(new ArrayContentProvider());
		this.cellEditor.setInput(ModeType);
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
		BreezeMode mode = (BreezeMode) element;
		return mode.getType();
	}

	@Override
	protected void setValue(Object element, Object value) {
		// TODO Auto-generated method stub
		BreezeMode mode = (BreezeMode) element;
		if (!value.toString().equals(mode.getType())) {
			mode.setType(value.toString());
			this.sme.fireModelChange();
		}
		this.viewer.refresh();
	}

}
