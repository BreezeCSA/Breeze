package com.goku.breeze.ui.sheet;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.goku.breeze.model.BreezeSafetyEvent;
import com.goku.breeze.model.BreezeSafetyMode;
import com.goku.breeze.model.BreezeSafetyTransition;

public class BreezeSafetyTableContentProvider implements IStructuredContentProvider {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		List list = (List) inputElement;
		if (list.size() == 0)
			return new Object[0];
		else {
			Object obj = list.get(0);
			if (obj instanceof BreezeSafetyMode) {
				Object[] ret = new Object[list.size()];
				int i = 0;
				for (Object modeObj : list) {
					BreezeSafetyMode mode = (BreezeSafetyMode) modeObj;
					ret[i++] = new String[] { mode.getId(), mode.getDescription() };
				}
				return ret;
			} else if (obj instanceof BreezeSafetyEvent) {
				Object[] ret = new Object[list.size()];
				int i = 0;
				for (Object eventObj : list) {
					BreezeSafetyEvent event = (BreezeSafetyEvent) eventObj;
					ret[i++] = new String[] { event.getId(), event.getDescription() };
				}
				return ret;
			} else if (obj instanceof BreezeSafetyTransition) {
				Object[] ret = new Object[list.size()];
				int i = 0;
				for (Object transObj : list) {
					ret[i++] = transObj;
				}
				return ret;
			}
		}
		return new Object[0];
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}
}
