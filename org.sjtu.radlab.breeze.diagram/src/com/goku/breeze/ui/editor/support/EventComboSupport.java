package com.goku.breeze.ui.editor.support;

import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.goku.breeze.common.SafetyAttribute;
import com.goku.breeze.compiler.model.BreezeArch;
import com.goku.breeze.compiler.model.BreezeEvent;
import com.goku.breeze.ui.editor.ModelEditor;

public class EventComboSupport extends EditingSupport {
	private final BreezeArch arch;
	private ComboBoxViewerCellEditor cellEditor = null;
	private final int columnIndex;
	private final ModelEditor sme;
	private final ColumnViewer viewer;

	public EventComboSupport(ModelEditor sme, BreezeArch arch, ColumnViewer viewer, int columnIndex) {
		super(viewer);
		// TODO Auto-generated constructor stub
		this.viewer = viewer;
		this.sme = sme;
		this.columnIndex = columnIndex;
		this.arch = arch;
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
		BreezeEvent evt = (BreezeEvent) element;
		switch (this.columnIndex) {
		case 2:
			Object type = evt.getProperty(SafetyAttribute.EVENT_TYPE);
			if (type != null)
				return type.toString();
			break;
		case 3:
			Object gate = evt.getProperty(SafetyAttribute.EVENT_GATE);
			if (gate != null)
				return gate.toString();
			break;
		}
		return "";
	}

	@Override
	protected void setValue(Object element, Object value) {
		// TODO Auto-generated method stub
		BreezeEvent evt = (BreezeEvent) element;
		switch (this.columnIndex) {
		case 2:
			Object type = evt.getProperty(SafetyAttribute.EVENT_TYPE);
			if (type != null && !type.equals(value)) {
				evt.setProperty(SafetyAttribute.EVENT_TYPE, value.toString());
				this.sme.fireModelChange();
			}
			break;
		case 3:
			Object gate = evt.getProperty(SafetyAttribute.EVENT_GATE);
			if (gate != null && !gate.equals(value)) {
				evt.setProperty(SafetyAttribute.EVENT_GATE, value.toString());
				this.sme.fireModelChange();
			}
			break;
		}
		this.viewer.refresh();
	}

	public void updateInput() {
		switch (this.columnIndex) {
		case 2:
			this.cellEditor.setInput(new String[] { SafetyAttribute.EVENT_TYPE_ATMOIC, SafetyAttribute.EVENT_TYPE_COMPOSITE });
			break;
		case 3:
			this.cellEditor.setInput(new String[] { SafetyAttribute.EVENT_GATE_AND, SafetyAttribute.EVENT_GATE_OR });
			break;
		case 4:
			if (this.arch != null) {
				List<BreezeEvent> eventList = this.arch.getEventList();
				String[] eventIdAry = new String[eventList.size()];
				for (int i = 0; i < eventList.size(); ++i)
					eventIdAry[i] = eventList.get(i).getId();
				this.cellEditor.setInput(eventIdAry);
			}
		}
	}

}
