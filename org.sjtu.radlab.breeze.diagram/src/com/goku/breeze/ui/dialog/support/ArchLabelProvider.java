package com.goku.breeze.ui.dialog.support;

import org.eclipse.jface.viewers.LabelProvider;

import com.goku.breeze.compiler.model.BreezeArch;
import com.goku.breeze.compiler.model.BreezeMode;
import com.goku.breeze.compiler.model.BreezeNode;

public class ArchLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof BreezeArch) {
			return ((BreezeArch) element).getName();
		} else if (element instanceof BreezeNode)
			return ((BreezeNode) element).getName();
		else if (element instanceof BreezeMode)
			return ((BreezeMode) element).getId();
		return element.toString();
	}
}
