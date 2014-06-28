package com.goku.breeze.ui.dialog.support;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.goku.breeze.util.FmeaUtil.FmeaLogItem;

public class FmeaLogTableLabelProvider implements ITableLabelProvider {

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
		FmeaLogItem item = (FmeaLogItem) element;
		switch (columnIndex) {
		case 0:
			return item.index + "";
		case 1:
			return item.date;
		case 2:
			return item.modeId;
		case 3:
			return item.field;
		case 4:
			return item.oldValue;
		case 5:
			return item.newValue;
		case 6:
			return item.newRPN + "(" + item.oldRPN + ")";
		}
		return "";
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
