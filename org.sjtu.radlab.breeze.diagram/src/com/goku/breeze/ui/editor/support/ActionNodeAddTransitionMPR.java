package com.goku.breeze.ui.editor.support;

import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.MessageBox;

import com.goku.breeze.compiler.model.BreezeEvent;
import com.goku.breeze.compiler.model.BreezeMode;
import com.goku.breeze.compiler.model.BreezeModeTransition;
import com.goku.breeze.compiler.model.BreezeNode;
import com.goku.breeze.ui.editor.MPRModelEditor;

public class ActionNodeAddTransitionMPR implements SelectionListener {
	private final BreezeNode node;
	private final MPRModelEditor sme;
	private final TableViewer viewer;

	public ActionNodeAddTransitionMPR(MPRModelEditor sme, BreezeNode node, TableViewer viewer) {
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
		List<BreezeModeTransition> tranList = this.node.getTransition();
		if (tranList.size() > 0) {
			BreezeModeTransition bmt = tranList.get(tranList.size() - 1);
			BreezeMode src = bmt.getSourceState(), tgt = bmt.getTargetState();
			BreezeEvent event = bmt.getTrigger();
			tranList.add(new BreezeModeTransition(null, src, tgt, event, BreezeModeTransition.TRIGGER_TYPE_SELF));
		} else {
			if (this.node.getModeList().size() > 0 && this.node.getParent().getEventList().size() > 0) {
				BreezeMode mode = this.node.getModeList().get(0);
				BreezeEvent evt = this.node.getParent().getEventList().get(0);
				tranList.add(new BreezeModeTransition(null, mode, mode, evt, BreezeModeTransition.TRIGGER_TYPE_SELF));
			} else {
				MessageBox msgDlg = new MessageBox(this.viewer.getControl().getShell(), SWT.ICON_INFORMATION | SWT.OK);
				msgDlg.setMessage("You should define mode and event first");
				msgDlg.setText("Transition:");
				msgDlg.open();
			}
		}
		this.viewer.refresh();
		this.sme.fireModelChange();
	}
}
