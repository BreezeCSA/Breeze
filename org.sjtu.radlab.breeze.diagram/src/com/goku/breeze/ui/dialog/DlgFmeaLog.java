package com.goku.breeze.ui.dialog;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import com.goku.breeze.ui.ImageFactory;
import com.goku.breeze.ui.dialog.support.FmeaLogTableContentProvider;
import com.goku.breeze.ui.dialog.support.FmeaLogTableLabelProvider;
import com.goku.breeze.util.FmeaUtil;
import com.goku.breeze.util.FmeaUtil.FmeaLogItem;

public class DlgFmeaLog extends Dialog {
	private String currentNodeId;
	private int currentPageNum = 0;
	private final Map<String, List<FmeaLogItem>> dataMap;
	private final Set<String> filterSet = new HashSet<String>();
	private final Map<String, String> idNameMap;
	private TableViewer logTableViewer;

	private Button next;
	private ComboViewer nodeSelect;
	private Combo pageSelect;
	private int pageTotalNum = 0;
	private final String path;
	private Button prev;
	private final String[] propKeys;

	private FmeaLogTableContentProvider provider;

	public DlgFmeaLog(Shell parentShell, String[] propKeys, String path, Map<String, String> idNameMap, String currentNodeId) {
		super(parentShell);
		// TODO Auto-generated constructor stub
		this.propKeys = propKeys;
		this.path = path;
		this.dataMap = FmeaUtil.getInstance().getChangeLog(path);
		this.currentNodeId = currentNodeId;
		this.idNameMap = idNameMap;

		if (!this.dataMap.containsKey(currentNodeId) && this.dataMap.size() > 0)
			currentNodeId = this.dataMap.keySet().toArray()[0].toString();
		this.setShellStyle(SWT.CLOSE | SWT.MODELESS | SWT.BORDER | SWT.TITLE);
		super.setBlockOnOpen(false);
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("FMEA Analysis");
	}

	@Override
	protected Button createButton(Composite parent, int id, String label, boolean defaultButton) {
		if (id == IDialogConstants.OK_ID) {
			Button btn = super.createButton(parent, id, label, defaultButton);
			btn.setText("Close");
			return btn;
		}
		return null;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite baseComposite = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.marginHeight = this.convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
		gl.marginWidth = this.convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
		gl.verticalSpacing = this.convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
		gl.horizontalSpacing = this.convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);

		baseComposite.setLayout(gl);
		baseComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Composite headerComposite = new Composite(baseComposite, SWT.NONE);
		headerComposite.setLayout(new RowLayout());

		this.nodeSelect = new ComboViewer(headerComposite, SWT.DROP_DOWN | SWT.READ_ONLY);
		this.nodeSelect.setContentProvider(ArrayContentProvider.getInstance());
		this.nodeSelect.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				String name = DlgFmeaLog.this.idNameMap.get(element);
				if (name == null) name = "";
				return name;
			}
		});

		this.nodeSelect.setInput(this.dataMap.keySet().toArray());

		this.nodeSelect.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// TODO Auto-generated method stub
				StructuredSelection selection = (StructuredSelection) event.getSelection();
				DlgFmeaLog.this.currentNodeId = selection.getFirstElement().toString();
				DlgFmeaLog.this.setNewInput();
			}

		});

		Button btnRpnChart = new Button(headerComposite, SWT.PUSH);
		Image imgChart = ImageFactory.getImageChart();
		if (imgChart != null)
			btnRpnChart.setImage(imgChart);
		else btnRpnChart.setText("Chart");

		btnRpnChart.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DlgFmeaRpnChart dlg = new DlgFmeaRpnChart(DlgFmeaLog.this.getShell(), DlgFmeaLog.this.path,
						DlgFmeaLog.this.currentNodeId);
				dlg.open();
			}
		});

		Composite filter = new Composite(baseComposite, SWT.NONE);
		filter.setLayout(new RowLayout());
		for (String propKey : this.propKeys) {
			Button chkb = new Button(filter, SWT.CHECK);
			chkb.setText(propKey);
			chkb.setSelection(true);
			chkb.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
					Button btn = (Button) e.widget;
					if (btn.getSelection()) {
						DlgFmeaLog.this.filterSet.add(btn.getText());
					} else DlgFmeaLog.this.filterSet.remove(btn.getText());
					DlgFmeaLog.this.setNewInput();
				}

			});

			this.filterSet.add(propKey);
		}

		Table tb = new Table(baseComposite, SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.BORDER);
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd.heightHint = 252;
		tb.setLayoutData(gd);
		tb.setHeaderVisible(true);
		tb.setLinesVisible(true);

		this.logTableViewer = new TableViewer(tb);

		String[] header = new String[] { "Index", "Date", "Mode", "Field", "Old Value", "New Value", "RPN(old)" };
		int[] lengths = new int[] { 50, 150, 100, 100, 150, 150, 100 };
		TableViewerColumn[] cols = new TableViewerColumn[header.length];
		for (int i = 0; i < header.length; ++i) {
			cols[i] = new TableViewerColumn(this.logTableViewer, SWT.NONE);
			cols[i].getColumn().setWidth(lengths[i]);
			cols[i].getColumn().setText(header[i]);
		}

		this.provider = new FmeaLogTableContentProvider();
		this.logTableViewer.setContentProvider(this.provider);
		this.logTableViewer.setLabelProvider(new FmeaLogTableLabelProvider());

		Composite naviComposite = new Composite(baseComposite, SWT.NONE);
		naviComposite.setLayout(new RowLayout());

		this.prev = new Button(naviComposite, SWT.PUSH | SWT.CENTER);
		this.prev.setImage(ImageFactory.getImagePrev());

		this.next = new Button(naviComposite, SWT.PUSH | SWT.CENTER);
		this.next.setImage(ImageFactory.getImageNext());

		this.pageSelect = new Combo(naviComposite, SWT.DROP_DOWN | SWT.READ_ONLY);
		RowData rd = new RowData();
		rd.width = 60;
		this.pageSelect.setLayoutData(rd);

		this.pageSelect.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DlgFmeaLog.this.currentPageNum = DlgFmeaLog.this.pageSelect.getSelectionIndex();
				DlgFmeaLog.this.setNewPageNumber();
			}
		});

		this.prev.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (DlgFmeaLog.this.currentPageNum > 0) {
					--DlgFmeaLog.this.currentPageNum;
				}
				DlgFmeaLog.this.setNewPageNumber();
			}
		});

		this.next.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (DlgFmeaLog.this.currentPageNum < DlgFmeaLog.this.pageTotalNum - 1) ++DlgFmeaLog.this.currentPageNum;
				DlgFmeaLog.this.setNewPageNumber();
			}
		});

		this.setNewInput();

		return baseComposite;
	}

	private void setNewInput() {
		List<FmeaLogItem> log = this.dataMap.get(this.currentNodeId);

		this.currentPageNum = 0;
		this.provider.setPageNumber(this.currentPageNum);
		this.logTableViewer.setInput(new Object[] { this.filterSet, log });
		this.pageTotalNum = this.provider.getTotalPageNumber();

		this.pageSelect.removeAll();
		for (int i = 0; i < this.pageTotalNum; ++i)
			this.pageSelect.add("" + i);
		this.pageSelect.select(this.currentPageNum);

		this.logTableViewer.refresh();
	}

	private void setNewPageNumber() {
		this.prev.setEnabled(DlgFmeaLog.this.currentPageNum > 0);
		this.next.setEnabled(DlgFmeaLog.this.currentPageNum < DlgFmeaLog.this.pageTotalNum - 1);

		this.pageSelect.select(this.currentPageNum);

		DlgFmeaLog.this.provider.setPageNumber(DlgFmeaLog.this.currentPageNum);
		DlgFmeaLog.this.logTableViewer.refresh();
	}
}
