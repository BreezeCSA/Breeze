package com.goku.breeze.ui.editor.support;

import java.util.List;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import com.goku.breeze.compiler.model.BreezeModeTransition;
import com.goku.breeze.compiler.model.BreezeNode;
import com.goku.breeze.ui.editor.RBDModelEditor;

public class ActionNodeRmTransitionRBD implements SelectionListener {
	private final BreezeNode node;
	private final RBDModelEditor sme;
	private final TableViewer viewer;

	public ActionNodeRmTransitionRBD(RBDModelEditor sme, BreezeNode node, TableViewer viewer) {
		this.node = node;
		this.sme = sme;
		this.viewer = viewer;
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		List<BreezeModeTransition> list = this.node.getTransition();
		StructuredSelection selection = (StructuredSelection) this.viewer.getSelection();
		Object obj = selection.getFirstElement();
		if (obj instanceof BreezeModeTransition) {
			BreezeModeTransition bmt = (BreezeModeTransition) obj;
			list.remove(bmt);
		}
		this.viewer.refresh();
		this.sme.fireModelChange();
	}

}
