package com.goku.breeze.ui.dialog.support;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.goku.breeze.compiler.main.BreezeXMLParser;
import com.goku.breeze.compiler.model.BreezeArch;
import com.goku.breeze.compiler.model.BreezeNode;

public class ArchTreeContentProvider implements ITreeContentProvider {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] getChildren(Object parentElement) {
		// TODO Auto-generated method stub
		System.out.println("get children");
		if (parentElement instanceof BreezeXMLParser) {
			return new Object[] { ((BreezeXMLParser) parentElement).getTopArch() };
		} else if (parentElement instanceof BreezeArch) {
			BreezeArch arch = (BreezeArch) parentElement;
			return arch.getNodeList().values().toArray();
		} else if (parentElement instanceof BreezeNode) {
			BreezeNode node = (BreezeNode) parentElement;
			return node.getModeList().toArray();
		}
		return new Object[0];
	}

	@Override
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		System.out.println("get element");
		System.out.println(inputElement);
		if (inputElement instanceof BreezeXMLParser) {
			return new Object[] { ((BreezeXMLParser) inputElement).getTopArch() };
		} else if (inputElement instanceof BreezeArch) {
			BreezeArch arch = (BreezeArch) inputElement;
			return arch.getNodeList().values().toArray();
		} else if (inputElement instanceof BreezeNode) {
			BreezeNode node = (BreezeNode) inputElement;
			return node.getModeList().toArray();
		}
		return new Object[0];
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		// TODO Auto-generated method stub
		if (element instanceof BreezeXMLParser)
			return true;
		else if (element instanceof BreezeArch) {
			return ((BreezeArch) element).getNodeList().size() > 0;
		} else if (element instanceof BreezeNode) {
			return ((BreezeNode) element).getModeList().size() > 0;
		}
		return false;
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

}
