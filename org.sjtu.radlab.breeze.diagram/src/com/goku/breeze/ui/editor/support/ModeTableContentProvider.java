package com.goku.breeze.ui.editor.support;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.goku.breeze.compiler.model.BreezeMode;
import com.goku.breeze.compiler.model.BreezeNode;

public class ModeTableContentProvider implements IStructuredContentProvider {
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		BreezeNode bn = (BreezeNode) inputElement;

		Object[] obj = new Object[bn.getModeList().size()];
		int num = 0;
		for (BreezeMode bm : bn.getModeList()) {
			obj[num++] = bm;
		}
		return obj;
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

}
