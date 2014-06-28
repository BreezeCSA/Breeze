package com.goku.breeze.ui.editor.support;

import java.util.List;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import com.goku.breeze.compiler.model.BreezeModeTransition;
import com.goku.breeze.compiler.model.BreezePort;
import com.goku.breeze.ui.editor.ModelEditor;

public class ActionPortRmTransition implements SelectionListener {
	private final BreezePort port;
	private final ModelEditor sme;
	private final TableViewer viewer;

	public ActionPortRmTransition(ModelEditor sme, BreezePort port, TableViewer viewer) {
		this.port = port;
		this.viewer = viewer;
		this.sme = sme;
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		List<BreezeModeTransition> list = this.port.getOwner().getTransition();
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
