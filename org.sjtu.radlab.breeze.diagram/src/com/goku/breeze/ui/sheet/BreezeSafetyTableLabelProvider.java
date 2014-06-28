package com.goku.breeze.ui.sheet;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.goku.breeze.model.BreezeSafetyTransition;

public class BreezeSafetyTableLabelProvider implements ITableLabelProvider {
	private final String type;

	public BreezeSafetyTableLabelProvider(String type) {
		this.type = type;
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		if ("mode".equals(this.type)) {
			String[] data = (String[]) element;
			return data[columnIndex];
		} else if ("event".equals(this.type)) {
			String[] data = (String[]) element;
			return data[columnIndex];
		} else if ("transition".equals(this.type)) {
			BreezeSafetyTransition bst = (BreezeSafetyTransition) element;
			switch (columnIndex) {
			case 0:
				return bst.getId();
			case 1:
				return bst.getSource();
			case 2:
				return bst.getTarget();
			case 3:
				return bst.getTrigger();
			}
		}
		return null;
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

}
