package com.goku.breeze.ui.editor.support;

import java.util.Map;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.goku.breeze.common.SafetyAttribute;
import com.goku.breeze.compiler.model.BreezeMode;
import com.goku.breeze.ui.editor.ModelEditor;

public class ModeOSDComboSupport extends EditingSupport {
	private static final String[] OSDRange = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
	private ComboBoxViewerCellEditor cellEditor = null;
	private final int columnIndex;

	private final ModelEditor sme;
	private final ColumnViewer viewer;

	public ModeOSDComboSupport(ModelEditor sme, ColumnViewer viewer, int columnIndex) {

		super(viewer);
		// TODO Auto-generated constructor stub
		this.viewer = viewer;
		this.sme = sme;
		this.cellEditor = new ComboBoxViewerCellEditor((Composite) this.getViewer().getControl(), SWT.READ_ONLY);
		this.cellEditor.setLabelProvider(new LabelProvider());
		this.cellEditor.setContenProvider(new ArrayContentProvider());
		this.cellEditor.setInput(OSDRange);
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
		BreezeMode mode = (BreezeMode) element;
		Map<String, Object> prop = mode.getProperties();
		if (prop == null) return "";
		Object o = prop.get(SafetyAttribute.MODE_OCCURRENCE);
		Object s = prop.get(SafetyAttribute.MODE_SEVERITY);
		Object d = prop.get(SafetyAttribute.MODE_DETECTION);
		switch (this.columnIndex) {
		case 2:
			if (o != null) return o.toString();
			break;
		case 3:
			if (s != null) return s.toString();
			break;
		case 4:
			if (d != null) return d.toString();
			break;
		}
		return "";
	}

	@Override
	protected void setValue(Object element, Object value) {
		// TODO Auto-generated method stub
		BreezeMode mode = (BreezeMode) element;

		Map<String, Object> prop = mode.getProperties();

		boolean change = false;
		switch (this.columnIndex) {
		case 2:

			if (!value.toString().equals(prop.get(SafetyAttribute.MODE_OCCURRENCE))) {
				prop.put(SafetyAttribute.MODE_OCCURRENCE, value.toString());
				change = true;
			}
			break;
		case 3:

			if (!value.toString().equals(prop.get(SafetyAttribute.MODE_SEVERITY))) {
				prop.put(SafetyAttribute.MODE_SEVERITY, value.toString());

				change = true;
			}
			break;
		case 4:

			if (!value.toString().equals(prop.get(SafetyAttribute.MODE_DETECTION))) {
				prop.put(SafetyAttribute.MODE_DETECTION, value.toString());

				change = true;
			}
			break;
		}

		if (change) {
			this.sme.fireModelChange();
			this.viewer.refresh();
		}
	}
}
