package com.goku.breeze.ui.editor.support;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.goku.breeze.compiler.model.BreezeModeTransition;

public class TransitionTableLabelProvider implements ITableLabelProvider {

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
		BreezeModeTransition bmt = (BreezeModeTransition) element;
		switch (columnIndex) {
		case 0:
			return bmt.getSourceState().getId();
		case 1:
			return bmt.getTargetState().getId();
		case 2:
			return bmt.getTrigger().getId();
		case 3:
			return bmt.getTriggerType();
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
