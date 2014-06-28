package com.goku.breeze.ui.editor.support;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.goku.breeze.compiler.model.BreezeArch;
import com.goku.breeze.compiler.model.BreezeEvent;

public class EventTableContentProvider implements IStructuredContentProvider {
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	@Override
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		BreezeArch arch = (BreezeArch) inputElement;

		Object[] objs = new BreezeEvent[arch.getEventList().size()];
		int count = 0;
		for (BreezeEvent event : arch.getEventList()) {
			objs[count++] = event;
		}
		return objs;
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

}
