package com.goku.breeze.ui.sheet;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.goku.breeze.model.BreezeSafetyEvent;
import com.goku.breeze.model.BreezeSafetyMode;
import com.goku.breeze.model.BreezeSafetyTransition;

public class BreezeSafetySection extends AbstractPropertySection {
	TableViewer eventTbViewer = null;
	TableViewer modeTbViewer = null;
	TableViewer tranTbViewer = null;

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		Composite composite = this.getWidgetFactory().createFlatFormComposite(parent);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		composite.setLayout(layout);

		Label lbMode = new Label(composite, SWT.NONE);
		lbMode.setText("Mode");
		lbMode.setLayoutData(new GridData(SWT.BEGINNING, SWT.FILL, true, false, 1, 1));
		Table modeTable = new Table(composite, SWT.FULL_SELECTION | SWT.V_SCROLL);
		modeTable.setLayoutData(new GridData(SWT.BEGINNING, SWT.FILL, true, false, 1, 10));
		modeTable.setHeaderVisible(true);
		modeTable.setLinesVisible(true);

		this.modeTbViewer = new TableViewer(modeTable);
		TableViewerColumn modeIdColumn = new TableViewerColumn(this.modeTbViewer, SWT.NONE);
		modeIdColumn.getColumn().setWidth(200);
		modeIdColumn.getColumn().setText("ID");
		TableViewerColumn modeDescColumn = new TableViewerColumn(this.modeTbViewer, SWT.NONE);
		modeDescColumn.getColumn().setWidth(300);
		modeDescColumn.getColumn().setText("Description");
		this.modeTbViewer.setContentProvider(new BreezeSafetyTableContentProvider());
		this.modeTbViewer.setLabelProvider(new BreezeSafetyTableLabelProvider("mode"));

		Label lbEvent = new Label(composite, SWT.NONE);
		lbEvent.setText("Event");
		lbEvent.setLayoutData(new GridData(SWT.BEGINNING, SWT.FILL, true, false, 1, 1));
		Table eventTable = new Table(composite, SWT.NONE);
		this.eventTbViewer = new TableViewer(eventTable);
		eventTable.setLayoutData(new GridData(SWT.BEGINNING, SWT.FILL, true, false, 1, 10));
		eventTable.setHeaderVisible(true);
		eventTable.setLinesVisible(true);
		TableViewerColumn eventIdColumn = new TableViewerColumn(this.eventTbViewer, SWT.NONE);
		eventIdColumn.getColumn().setText("ID");
		eventIdColumn.getColumn().setWidth(200);
		TableViewerColumn eventDescColumn = new TableViewerColumn(this.eventTbViewer, SWT.NONE);
		eventDescColumn.getColumn().setText("Description");
		eventDescColumn.getColumn().setWidth(300);
		this.eventTbViewer.setContentProvider(new BreezeSafetyTableContentProvider());
		this.eventTbViewer.setLabelProvider(new BreezeSafetyTableLabelProvider("event"));

		Label lbTran = new Label(composite, SWT.NONE);
		lbTran.setText("Transition");
		lbTran.setLayoutData(new GridData(SWT.BEGINNING, SWT.FILL, true, false, 1, 1));
		Table transTable = new Table(composite, SWT.NONE);
		transTable.setLayoutData(new GridData(SWT.BEGINNING, SWT.FILL, true, false, 1, 10));
		transTable.setHeaderVisible(true);
		transTable.setLinesVisible(true);
		this.tranTbViewer = new TableViewer(transTable);
		TableViewerColumn transIdColumn = new TableViewerColumn(this.tranTbViewer, SWT.NONE);
		transIdColumn.getColumn().setText("ID");
		transIdColumn.getColumn().setWidth(100);
		TableViewerColumn transSrcColumn = new TableViewerColumn(this.tranTbViewer, SWT.NONE);
		transSrcColumn.getColumn().setText("Source");
		transSrcColumn.getColumn().setWidth(130);
		EditingSupport eSup0 = new BreezeSafetyItemSelect(transSrcColumn.getViewer(), new String[] { "initial", "internal",
				"failure" }, "source");
		transSrcColumn.setEditingSupport(eSup0);
		TableViewerColumn transTgtColumn = new TableViewerColumn(this.tranTbViewer, SWT.NONE);
		transTgtColumn.getColumn().setText("Target");
		transTgtColumn.getColumn().setWidth(130);
		EditingSupport eSup1 = new BreezeSafetyItemSelect(transTgtColumn.getViewer(), new String[] { "initial", "internal",
				"failure" }, "target");
		transTgtColumn.setEditingSupport(eSup1);
		TableViewerColumn transEvtColumn = new TableViewerColumn(this.tranTbViewer, SWT.NONE);
		transEvtColumn.getColumn().setText("Trigger");
		transEvtColumn.getColumn().setWidth(130);
		EditingSupport eSup2 = new BreezeSafetyItemSelect(transEvtColumn.getViewer(), new String[] { "event0", "event1",
				"event2", "event3" }, "event");
		transEvtColumn.setEditingSupport(eSup2);
		this.tranTbViewer.setContentProvider(new BreezeSafetyTableContentProvider());
		this.tranTbViewer.setLabelProvider(new BreezeSafetyTableLabelProvider("transition"));

	}

	@Override
	public void refresh() {

	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		List<BreezeSafetyMode> modeList = new LinkedList<BreezeSafetyMode>();
		BreezeSafetyMode m0 = new BreezeSafetyMode("initial", "This is initial mode");
		BreezeSafetyMode m1 = new BreezeSafetyMode("internal", "This is internal mode");
		BreezeSafetyMode m2 = new BreezeSafetyMode("failure", "This is failure mode");
		modeList.add(m0);
		modeList.add(m1);
		modeList.add(m2);
		this.modeTbViewer.setInput(modeList);

		List<BreezeSafetyEvent> eventList = new LinkedList<BreezeSafetyEvent>();
		BreezeSafetyEvent e0 = new BreezeSafetyEvent("event0", "This is event 0");
		BreezeSafetyEvent e1 = new BreezeSafetyEvent("event1", "This is event 1");
		BreezeSafetyEvent e2 = new BreezeSafetyEvent("event2", "This is event 2");
		BreezeSafetyEvent e3 = new BreezeSafetyEvent("event3", "This is event 3");
		eventList.add(e0);
		eventList.add(e1);
		eventList.add(e2);
		eventList.add(e3);
		this.eventTbViewer.setInput(eventList);

		List<BreezeSafetyTransition> tranList = new LinkedList<BreezeSafetyTransition>();
		BreezeSafetyTransition t0 = new BreezeSafetyTransition("t0", "initial", "internal", "event0");
		BreezeSafetyTransition t1 = new BreezeSafetyTransition("t1", "initial", "failure", "event1");
		BreezeSafetyTransition t2 = new BreezeSafetyTransition("t2", "failure", "internal", "event2");
		BreezeSafetyTransition t3 = new BreezeSafetyTransition("t3", "failure", "initial", "event3");
		tranList.add(t0);
		tranList.add(t1);
		tranList.add(t2);
		tranList.add(t3);
		this.tranTbViewer.setInput(tranList);
	}
}
