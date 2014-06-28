package com.goku.breeze.ui.editor.support;

import java.util.HashSet;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;

import com.goku.breeze.common.SafetyAttribute;
import com.goku.breeze.compiler.main.BreezeXMLParser;
import com.goku.breeze.compiler.model.BreezeArch;
import com.goku.breeze.compiler.model.BreezeEvent;
import com.goku.breeze.ui.editor.ModelEditor;

public class EventTextSupport extends EditingSupport {
	private final BreezeArch arch;
	private final BreezeXMLParser bxp;
	private TextCellEditor cellEditor = null;
	private final int columnIndex;
	private final ModelEditor sme;
	private final ColumnViewer viewer;

	public EventTextSupport(ModelEditor sme, ColumnViewer viewer, int columnIndex, BreezeXMLParser bxp, BreezeArch arch) {

		super(viewer);
		// TODO Auto-generated constructor stub
		this.viewer = viewer;
		this.sme = sme;
		this.bxp = bxp;
		this.arch = arch;

		this.columnIndex = columnIndex;
		this.cellEditor = new TextCellEditor((Composite) viewer.getControl());
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
		BreezeEvent event = (BreezeEvent) element;
		switch (this.columnIndex) {
		case 0:
			return event.getId();
		case 1:
			return event.getProbability().toString();
		}
		return "";
	}

	@Override
	protected void setValue(Object element, Object value) {
		// TODO Auto-generated method stub
		BreezeEvent event = (BreezeEvent) element;
		switch (this.columnIndex) {
		case 0:
			Object input = this.viewer.getInput();
			if (input instanceof BreezeArch) {
				BreezeArch arch = (BreezeArch) input;
				boolean dup = false;
				for (BreezeEvent evt : arch.getEventList()) {
					if (value.toString().equals(evt.getId())) {
						dup = true;
					}
				}
				if (dup) break;
				if (!value.toString().equals(event.getId())) {
					if (arch != null) {
						for (BreezeEvent evt : arch.getEventList()) {
							Object children = evt.getProperty(SafetyAttribute.EVENT_CHILDREN);
							if (children != null) {
								HashSet<String> set = (HashSet<String>) children;
								if (set.contains(event.getId())) {
									set.remove(event.getId());
									set.add(value.toString());
								}
							}
						}
					}

					arch.renameEvent(event.getId(), value.toString());
					event.setEventNumber(this.bxp.getFactory().nextEventNumber(value.toString()));
					this.sme.fireModelChange();
				}
			}
			break;
		case 1:

			if (!event.getProbability().equals(value.toString())) {
				this.sme.fireModelChange();
				event.setProbability(value.toString());

			}
			break;
		}
		this.viewer.refresh();
	}

}
