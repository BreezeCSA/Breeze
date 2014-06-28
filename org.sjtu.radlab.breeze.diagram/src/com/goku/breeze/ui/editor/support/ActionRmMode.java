package com.goku.breeze.ui.editor.support;

import java.util.List;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import com.goku.breeze.compiler.model.BreezeMode;
import com.goku.breeze.compiler.model.BreezeModeTransition;
import com.goku.breeze.compiler.model.BreezeNode;
import com.goku.breeze.ui.editor.ModelEditor;

public class ActionRmMode implements SelectionListener {
	private final BreezeNode node;
	private final TableViewer[] refreshViewer;
	private final ModelEditor sme;
	private final TableViewer viewer;

	public ActionRmMode(ModelEditor sme, BreezeNode bn, TableViewer viewer, TableViewer[] refreshViewer) {
		this.node = bn;
		this.sme = sme;
		this.viewer = viewer;
		this.refreshViewer = refreshViewer;
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		StructuredSelection selection = (StructuredSelection) this.viewer.getSelection();
		Object obj = selection.getFirstElement();
		if (obj instanceof BreezeMode) {
			BreezeMode bm = (BreezeMode) obj;
			this.node.removeMode(bm.getId());
			List<BreezeModeTransition> tList = this.node.getTransition();
			for (int i = tList.size() - 1; i >= 0; --i) {
				if (tList.get(i).getSourceState().getId().equals(bm.getId())
						|| tList.get(i).getTargetState().getId().equals(bm.getId())) tList.remove(i);
			}
		}
		this.viewer.refresh();
		for (TableViewer v : this.refreshViewer)
			v.refresh();
		this.sme.fireModelChange();
	}

}
