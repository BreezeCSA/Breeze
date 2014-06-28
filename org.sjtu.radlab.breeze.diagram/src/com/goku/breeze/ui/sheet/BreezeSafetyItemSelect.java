package com.goku.breeze.ui.sheet;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.goku.breeze.model.BreezeSafetyTransition;

public class BreezeSafetyItemSelect extends EditingSupport {
	private ComboBoxViewerCellEditor cellEditor = null;
	private final String type;
	private ColumnViewer viewer = null;

	public BreezeSafetyItemSelect(ColumnViewer viewer, String[] input, String type) {
		super(viewer);
		// TODO Auto-generated constructor stub
		this.cellEditor = new ComboBoxViewerCellEditor((Composite) this.getViewer().getControl(), SWT.READ_ONLY);
		this.cellEditor.setLabelProvider(new LabelProvider());
		this.cellEditor.setContenProvider(new ArrayContentProvider());
		this.cellEditor.setInput(input);
		this.type = type;
		this.viewer = viewer;
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
		System.out.println("get:" + element);
		BreezeSafetyTransition trans = (BreezeSafetyTransition) element;
		if ("source".equals(this.type)) {
			return trans.getSource();
		} else if ("target".equals(this.type)) {
			return trans.getTarget();
		} else if ("event".equals(this.type)) {
			return trans.getTrigger();
		}
		return null;
	}

	@Override
	protected void setValue(Object element, Object value) {
		// TODO Auto-generated method stub
		System.out.println("new :" + value + " old:" + element.getClass());
		BreezeSafetyTransition trans = (BreezeSafetyTransition) element;
		if ("source".equals(this.type)) {
			trans.setSource(value.toString());
		} else if ("target".equals(this.type)) {
			trans.setTarget(value.toString());
		} else if ("event".equals(this.type)) {
			trans.setTrigger(value.toString());
		}
		this.viewer.refresh(true);
	}

}
