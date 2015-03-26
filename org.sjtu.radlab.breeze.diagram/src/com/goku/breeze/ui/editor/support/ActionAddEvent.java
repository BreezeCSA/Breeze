package com.goku.breeze.ui.editor.support;

import java.util.HashSet;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import com.goku.breeze.common.SafetyAttribute;
import com.goku.breeze.compiler.main.BreezeXMLParser;
import com.goku.breeze.compiler.model.BreezeArch;
import com.goku.breeze.compiler.model.BreezeEvent;
import com.goku.breeze.ui.editor.ModelEditor;

public class ActionAddEvent implements SelectionListener {
	private final BreezeXMLParser bxp;
	private final BreezeArch container;

	private final EventComboSupport[] ecs;
	private final TableViewer eventTableViewer;
	private final ModelEditor sme;

	public ActionAddEvent(ModelEditor sme, BreezeArch container, BreezeXMLParser bxp, TableViewer eventTableViewer,
			EventComboSupport[] ecs) {
		this.container = container;
		this.bxp = bxp;
		this.sme = sme;
		this.ecs = ecs;
		this.eventTableViewer = eventTableViewer;
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
		// generate different id of event

		String[] nameList = new String[this.container.getEventList().size()];
		int count = 0;
		for (BreezeEvent evt : this.container.getEventList()) {
			nameList[count++] = evt.getId();
		}
		String id = SupportUtil.getDifferentName(nameList);
		BreezeEvent event = new BreezeEvent(id, this.bxp.getFactory().nextEventNumber(id), "50");

		event.setProperty(SafetyAttribute.EVENT_GATE, "");
		event.setProperty(SafetyAttribute.EVENT_TYPE, SafetyAttribute.EVENT_TYPE_ATMOIC);
		event.setProperty(SafetyAttribute.EVENT_CHILDREN, new HashSet<String>());

		this.container.addEvent(event);
		this.eventTableViewer.refresh();
		this.sme.fireModelChange();

		if (this.ecs != null) {
			for (EventComboSupport sup : this.ecs) {
				sup.updateInput();
			}
		}

	}
}
