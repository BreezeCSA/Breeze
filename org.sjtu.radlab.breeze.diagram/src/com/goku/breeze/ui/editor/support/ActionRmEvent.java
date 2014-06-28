package com.goku.breeze.ui.editor.support;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import com.goku.breeze.compiler.model.BreezeArch;
import com.goku.breeze.compiler.model.BreezeEvent;
import com.goku.breeze.compiler.model.BreezeModeTransition;
import com.goku.breeze.compiler.model.BreezeNode;
import com.goku.breeze.ui.editor.ModelEditor;

public class ActionRmEvent implements SelectionListener {
	private final BreezeArch container;
	private final EventComboSupport[] ecs;
	private final ModelEditor sme;
	private final TableViewer tbViewer;

	public ActionRmEvent(ModelEditor sme, BreezeArch container, TableViewer tbViewer, EventComboSupport[] ecs) {
		this.container = container;
		this.tbViewer = tbViewer;
		this.ecs = ecs;
		this.sme = sme;
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		StructuredSelection selection = (StructuredSelection) this.tbViewer.getSelection();
		Object obj = selection.getFirstElement();
		if (obj instanceof BreezeEvent) {
			BreezeEvent event = (BreezeEvent) obj;
			this.container.removeEvent(event.getId());
			Map<String, BreezeNode> nodes = this.container.getNodeList();
			for (String key : nodes.keySet()) {
				BreezeNode node = nodes.get(key);
				List<BreezeModeTransition> tList = node.getTransition();
				for (int i = tList.size() - 1; i >= 0; --i) {
					if (tList.get(i).getTrigger().getNumber().equals(event.getNumber())) {
						tList.remove(i);
					}
				}
			}

		}
		this.tbViewer.refresh();
		this.sme.fireModelChange();

		if (this.ecs != null) {
			for (EventComboSupport sup : this.ecs) {
				sup.updateInput();
			}
		}

	}

}
