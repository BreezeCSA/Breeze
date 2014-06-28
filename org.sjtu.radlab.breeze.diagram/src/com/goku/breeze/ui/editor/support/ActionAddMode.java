package com.goku.breeze.ui.editor.support;

import java.util.Map;

import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import com.goku.breeze.compiler.model.BreezeMode;
import com.goku.breeze.compiler.model.BreezeNode;
import com.goku.breeze.ui.editor.ModelEditor;

public class ActionAddMode implements SelectionListener {
	private EditingSupport[] es;
	private final BreezeNode node;
	private String[] propKey = null;
	private final ModelEditor sme;
	private final TableViewer viewer;

	public ActionAddMode(ModelEditor sme, BreezeNode container, TableViewer modeTableViewer, EditingSupport[] cols) {
		this.node = container;
		this.viewer = modeTableViewer;
		this.es = cols;
		this.sme = sme;
	}

	public ActionAddMode(ModelEditor sme, BreezeNode container, TableViewer modeTableViewer, String[] propKey) {
		this.node = container;
		this.viewer = modeTableViewer;
		this.sme = sme;
		this.propKey = propKey;
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
		String[] nameList = new String[this.node.getModeList().size()];
		int count = 0;
		for (BreezeMode md : this.node.getModeList()) {
			nameList[count++] = md.getId();
		}
		String id = SupportUtil.getDifferentName(nameList);

		BreezeMode bm = new BreezeMode(id, BreezeMode.TYPE_NORMAL, true);

		if (this.propKey != null) {
			Map<String, Object> prop = bm.getProperties();
			for (String key : this.propKey)
				if (key.length() > 0) prop.put(key, "");
			bm.setType(BreezeMode.TYPE_FAILURE);
		}

		this.node.addStates(bm);

		// refresh mode in transition
		if (this.es != null) {
			for (EditingSupport edi : this.es) {
				TransitionComboSupport mcs = (TransitionComboSupport) edi;
				mcs.updateInput();
			}

		}
		this.viewer.refresh();
		this.sme.fireModelChange();
	}
}
