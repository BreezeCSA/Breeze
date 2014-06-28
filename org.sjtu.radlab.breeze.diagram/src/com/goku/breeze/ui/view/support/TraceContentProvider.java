package com.goku.breeze.ui.view.support;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.goku.breeze.model.TraceNode;

public class TraceContentProvider implements ITreeContentProvider {
	private int step = 0;

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] getChildren(Object parentElement) {
		// TODO Auto-generated method stub
		if (parentElement instanceof TraceNode) {
			TraceNode tn = (TraceNode) parentElement;
			if (tn.getChildren() != null) {
				return tn.getChildren().toArray();
			}
		}
		return new Object[0];
	}

	@Override
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		if (inputElement instanceof List) {
			List<TraceNode> list = (List) inputElement;
			return new Object[] { list.get(this.step) };
		} else {
			System.out.println(inputElement.getClass());
		}
		return new Object[0];
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		TraceNode tn = (TraceNode) element;
		return tn.getParent();
	}

	@Override
	public boolean hasChildren(Object element) {
		// TODO Auto-generated method stub
		if (element instanceof TraceNode) {
			TraceNode tn = (TraceNode) element;
			return tn.getChildren() != null && tn.getChildren().size() > 0;
		}
		return false;
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

	public void setStep(int number) {
		this.step = number;
	}

}
