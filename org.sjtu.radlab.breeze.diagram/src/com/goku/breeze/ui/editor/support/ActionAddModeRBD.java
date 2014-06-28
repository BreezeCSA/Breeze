package com.goku.breeze.ui.editor.support;

import java.util.Map;

import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import com.goku.breeze.common.SafetyAttribute;
import com.goku.breeze.compiler.model.BreezeMode;
import com.goku.breeze.compiler.model.BreezeNode;
import com.goku.breeze.ui.editor.RBDModelEditor;

public class ActionAddModeRBD implements SelectionListener {
	private final EditingSupport[] es;
	private final BreezeNode node;
	private final RBDModelEditor sme;
	private final TableViewer viewer;

	public ActionAddModeRBD(RBDModelEditor sme, BreezeNode container, TableViewer modeTableViewer, EditingSupport[] cols) {
		this.node = container;
		this.viewer = modeTableViewer;
		this.es = cols;
		this.sme = sme;
	}

	private char getDifferentChar(char ch) {
		if (Character.isLetter(ch))
			return '0';
		else return 'a';
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		String id = "";
		for (BreezeMode mode : this.node.getModeList()) {
			String modeId = mode.getId();
			if (modeId.length() <= id.length())
				id += "a";
			else id += this.getDifferentChar(modeId.charAt(id.length()));
		}
		if (id.length() == 0) id = "b";
		id = "mode_" + id;
		BreezeMode bm = new BreezeMode(id, BreezeMode.TYPE_NORMAL, true);
		Map<String, Object> prop = bm.getProperties();
		prop.put(SafetyAttribute.MODE_DETECTION, "1");
		prop.put(SafetyAttribute.MODE_OCCURRENCE, "1");
		prop.put(SafetyAttribute.MODE_SEVERITY, "1");
		prop.put(SafetyAttribute.MODE_HUMAN_FACTOR, "");
		prop.put(SafetyAttribute.MODE_ECONOMY, "");

		this.node.addStates(bm);

		// refresh mode in transition
		for (EditingSupport edi : this.es) {
			TransitionComboSupport mcs = (TransitionComboSupport) edi;
			mcs.updateInput();
		}
		this.viewer.refresh();
		this.sme.fireModelChange();
	}
}
