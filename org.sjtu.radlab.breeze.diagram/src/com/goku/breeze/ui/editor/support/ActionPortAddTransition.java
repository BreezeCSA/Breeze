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
import com.goku.breeze.compiler.model.BreezePort;
import com.goku.breeze.ui.editor.ModelEditor;

public class ActionPortAddTransition implements SelectionListener {
	private final BreezePort port;
	private final ModelEditor sme;
	private final TableViewer viewer;

	public ActionPortAddTransition(ModelEditor sme, BreezePort port, TableViewer viewer) {
		this.port = port;
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
		List<BreezeModeTransition> tranList = this.port.getOwner().getTransition();
		if (tranList.size() > 0) {
			BreezeModeTransition bmt = tranList.get(tranList.size() - 1);
			BreezeMode src = bmt.getSourceState(), tgt = bmt.getTargetState();
			BreezeEvent event = bmt.getTrigger();
			tranList.add(new BreezeModeTransition(this.port, src, tgt, event, BreezeModeTransition.TRIGGER_TYPE_RECEIVE));
		} else {
			if (this.port.getOwner().getModeList().size() > 0 && this.port.getOwner().getParent().getEventList().size() > 0) {
				BreezeMode mode = this.port.getOwner().getModeList().get(0);
				BreezeEvent evt = this.port.getOwner().getParent().getEventList().get(0);
				tranList.add(new BreezeModeTransition(this.port, mode, mode, evt, BreezeModeTransition.TRIGGER_TYPE_RECEIVE));
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
