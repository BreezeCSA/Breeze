package com.goku.breeze.ui.editor.support;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.goku.breeze.compiler.model.BreezeModeTransition;
import com.goku.breeze.compiler.model.BreezeNode;
import com.goku.breeze.compiler.model.BreezePort;

public class TransitionTableContentProvider implements IStructuredContentProvider {
	public static final int TYPE_NODE = 1;
	public static final int TYPE_PORT = 0;
	private final BreezePort port;
	private final int type;

	public TransitionTableContentProvider(int type, BreezePort port) {
		this.type = type;
		this.port = port;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	@Override
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		BreezeNode node = (BreezeNode) inputElement;
		List<BreezeModeTransition> list = node.getTransition();
		List<BreezeModeTransition> selfTgr = new LinkedList<BreezeModeTransition>();
		for (BreezeModeTransition tran : list) {
			if (this.type == TYPE_NODE && tran.getPort() == null || this.type == TYPE_PORT && tran.getPort() != null
					&& tran.getPort() == this.port)
				selfTgr.add(tran);
		}
		Object[] obj = new Object[selfTgr.size()];
		int num = 0;
		for (BreezeModeTransition tran : selfTgr) {
			obj[num++] = tran;
		}
		return obj;
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

}
