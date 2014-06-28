package com.goku.breeze.ui.editor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

import com.goku.breeze.common.ListenerExtractPortName;
import com.goku.breeze.common.ListenerExtractSafetyAttr;
import com.goku.breeze.common.SafetyAttribute;
import com.goku.breeze.compiler.event.ParseListener;
import com.goku.breeze.compiler.exception.BaseException;
import com.goku.breeze.compiler.main.BreezeXMLParser;
import com.goku.breeze.compiler.model.BreezeArch;
import com.goku.breeze.compiler.model.BreezeConnection;
import com.goku.breeze.compiler.model.BreezeConnection.Connection;
import com.goku.breeze.compiler.model.BreezeEvent;
import com.goku.breeze.compiler.model.BreezeMode;
import com.goku.breeze.compiler.model.BreezeModeTransition;
import com.goku.breeze.compiler.model.BreezeNode;
import com.goku.breeze.compiler.model.BreezeObject;
import com.goku.breeze.compiler.model.BreezePort;
import com.goku.breeze.ui.ImageFactory;
import com.goku.breeze.ui.console.ConsoleFactory;
import com.goku.breeze.ui.console.ConsoleUtil;
import com.goku.breeze.ui.dialog.DlgFmeaLog;
import com.goku.breeze.ui.dialog.DlgModeInfo;
import com.goku.breeze.ui.dialog.DlgSelectEventChildren;
import com.goku.breeze.ui.editor.support.ActionAddEvent;
import com.goku.breeze.ui.editor.support.ActionAddMode;
import com.goku.breeze.ui.editor.support.ActionNodeAddTransition;
import com.goku.breeze.ui.editor.support.ActionNodeRmTransition;
import com.goku.breeze.ui.editor.support.ActionPortAddTransition;
import com.goku.breeze.ui.editor.support.ActionPortRmTransition;
import com.goku.breeze.ui.editor.support.ActionRmEvent;
import com.goku.breeze.ui.editor.support.ActionRmMode;
import com.goku.breeze.ui.editor.support.EventComboSupport;
import com.goku.breeze.ui.editor.support.EventTableContentProvider;
import com.goku.breeze.ui.editor.support.EventTableLabelProvider;
import com.goku.breeze.ui.editor.support.EventTextSupport;
import com.goku.breeze.ui.editor.support.ModeComboSupport;
import com.goku.breeze.ui.editor.support.ModeTableContentProvider;
import com.goku.breeze.ui.editor.support.ModeTableLabelProvider;
import com.goku.breeze.ui.editor.support.ModeTextSupport;
import com.goku.breeze.ui.editor.support.TransitionComboSupport;
import com.goku.breeze.ui.editor.support.TransitionTableContentProvider;
import com.goku.breeze.ui.editor.support.TransitionTableLabelProvider;
import com.goku.breeze.util.DiagramEditorSelectionHelper;
import com.goku.breeze.util.FmeaUtil;
import com.goku.breeze.util.FtaUtil;

public class ModelEditor extends ScrolledComposite {
	private Map<String, BreezeObject> allObjects;

	private BreezeXMLParser bxp;

	private final Color colorBlue = new Color(Display.getCurrent(), 0, 0, 255);
	private final Color colorWhite = new Color(Display.getCurrent(), 255, 255, 255);

	private ComboViewer componentSelectViewer;
	private Composite composite;
	private BreezeNode currentEditNode;
	private String editorType = null;
	private Table eventTable, modeTable, transitionTable;
	private TableViewer eventTableViewer, modeTableViewer, transitionTableViewer;
	private final String fileNameWithoutExt;

	private Composite panel = null;

	private final IEditorPart parentEditor;

	private final String saFilePath;

	public ModelEditor(String saFilePath, String fileNameWithoutExt, Composite parent, IEditorPart editor) {
		super(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		// TODO Auto-generated constructor stub
		this.fileNameWithoutExt = fileNameWithoutExt;
		this.parentEditor = editor;
		this.saFilePath = saFilePath;

		if (editor instanceof SafetyEditor)
			this.editorType = SafetyEditor.ID;
		else if (editor instanceof CorrectnessEditor)
			this.editorType = CorrectnessEditor.ID;
		else this.editorType = "";

		this.init(saFilePath);
	}

	public void doSave() {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("breeze");
		BreezeArch arch = this.bxp.getTopArch();
		Element archElement = root.addElement(BreezeObject.TYPE_ARCH);
		archElement.addAttribute(BreezeObject.ATTR_ID, arch.getId());
		archElement.addAttribute(BreezeObject.ATTR_NAME, arch.getName());

		this.writeEvents(archElement, arch.getEventList());

		Map<String, BreezeNode> nodeMap = arch.getNodeList();
		for (String nodeId : nodeMap.keySet()) {
			BreezeNode node = nodeMap.get(nodeId);
			this.writeNode(node, archElement);
		}

		BreezeConnection connection = arch.getConnectionGraph();
		this.writeConnection(connection, archElement);

		try {
			FileWriter writer = new FileWriter(new File(this.saFilePath));
			XMLWriter xmlWriter = new XMLWriter(writer, OutputFormat.createPrettyPrint());
			xmlWriter.write(doc);
			xmlWriter.close();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Button drawButton(Composite parentComposite, Image img, String txt) {
		Button btn = new Button(parentComposite, SWT.PUSH | SWT.CENTER);
		if (img != null)
			btn.setImage(img);
		else btn.setText(txt);
		return btn;
	}

	private void drawDivider() {
		Label lb = new Label(this.panel, SWT.SEPARATOR | SWT.HORIZONTAL);
		lb.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
	}

	private void drawLabel(String txt) {
		Label lb0 = new Label(this.panel, SWT.NONE);
		lb0.setText(txt);
		GridData gd = new GridData(SWT.BEGINNING, SWT.FILL, true, false, 1, 1);
		lb0.setForeground(this.colorBlue);
		gd.verticalIndent = 20;
		lb0.setLayoutData(gd);
	}

	private void drawPanelCommonsAfter(int height) {
		this.panel.redraw();
		this.panel.layout(true);
		this.setMinSize(this.composite.computeSize(SWT.DEFAULT, height));
		this.layout(true);
	}

	private void drawPanelCommonsBefore() {
		Control[] children = this.panel.getChildren();
		for (Control child : children)
			child.dispose();
	}

	private void drawPanelForArch(BreezeArch arch) {
		this.drawPanelCommonsBefore();

		this.drawLabel("Event");
		this.eventTable = this.drawTable();

		Object[] ret = null;
		if (this.editorType.equals(SafetyEditor.ID)) {
			ret = this.drawTableViewer(this.eventTable, new String[] { "ID", "Importance", "Type", "Gate", "Children" },
					new int[] { 100, 100, 100, 80, 100 });
		} else if (this.editorType.equals(CorrectnessEditor.ID)) {
			ret = this.drawTableViewer(this.eventTable, new String[] { "ID", "Probability" }, new int[] { 200, 100 });
		}

		this.eventTableViewer = (TableViewer) ret[0];
		this.eventTableViewer.setContentProvider(new EventTableContentProvider());
		this.eventTableViewer.setLabelProvider(new EventTableLabelProvider());
		this.eventTableViewer.setInput(arch);

		this.eventTableViewer.getTable().addListener(SWT.MouseDown, new Listener() {

			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				Point p = new Point(event.x, event.y);

				ViewerCell cell = ModelEditor.this.eventTableViewer.getCell(p);
				if (cell != null && cell.getColumnIndex() == 4) {
					StructuredSelection selection = (StructuredSelection) ModelEditor.this.eventTableViewer.getSelection();
					if (selection.size() != 1) return;
					BreezeEvent be = (BreezeEvent) selection.getFirstElement();
					DlgSelectEventChildren dlg = new DlgSelectEventChildren(PlatformUI.getWorkbench().getActiveWorkbenchWindow()
							.getShell(), ModelEditor.this.bxp, be);

					dlg.open();
					if (dlg.isDataChange()) {
						be.setProbability(FtaUtil.calculateProbability(be, ModelEditor.this.bxp.getTopArch()));
						ModelEditor.this.fireModelChange();
					}
					ModelEditor.this.eventTableViewer.refresh();
				}
			}

		});
		TableViewerColumn[] cols = (TableViewerColumn[]) ret[1];
		cols[0].setEditingSupport(new EventTextSupport(this, this.eventTableViewer, 0, this.bxp, arch));

		cols[1].setEditingSupport(new EventTextSupport(this, this.eventTableViewer, 1, this.bxp, arch));

		if (this.editorType.equals(SafetyEditor.ID)) {
			EventComboSupport[] ecs = new EventComboSupport[] { new EventComboSupport(this, arch, this.eventTableViewer, 2),
					new EventComboSupport(this, arch, this.eventTableViewer, 3) };

			cols[2].setEditingSupport(ecs[0]);
			cols[3].setEditingSupport(ecs[1]);
		}

		Composite btnComposite = new Composite(this.panel, SWT.NONE);
		btnComposite.setLayout(new RowLayout());
		Button btnAdd = this.drawButton(btnComposite, ImageFactory.getImageAdd(), "Add");
		Button btnRm = this.drawButton(btnComposite, ImageFactory.getImageDelete(), "Delete");
		btnAdd.addSelectionListener(new ActionAddEvent(this, arch, this.bxp, this.eventTableViewer, null));
		btnRm.addSelectionListener(new ActionRmEvent(this, arch, this.eventTableViewer, null));

		this.drawPanelCommonsAfter(400);
	}

	private void drawPanelForNode(BreezeNode node) {
		this.drawPanelCommonsBefore();

		this.currentEditNode = node;

		// section "Mode"
		this.drawLabel("Mode");
		this.modeTable = this.drawTable();
		Object[] ret = null;

		if (this.editorType.equals(CorrectnessEditor.ID)) {
			ret = this.drawTableViewer(this.modeTable, new String[] { "Mode", "Type" }, new int[] { 220, 180 });
		} else {
			ret = this.drawTableViewer(this.modeTable, new String[] { "Mode", "Function", "Occurrence", "Severity", "Detection",
					"Human Factor", "Economy", "RPN", "Effect", "Cause", "Advice", "Deadline", "Measure" }, new int[] { 120, 180,
					80, 80, 80, 80, 80, 80, 180, 180, 180, 180, 180 });
		}

		String[] propKey = new String[] { "", SafetyAttribute.MODE_FUNCTION, SafetyAttribute.MODE_OCCURRENCE,
				SafetyAttribute.MODE_SEVERITY, SafetyAttribute.MODE_DETECTION, SafetyAttribute.MODE_HUMAN_FACTOR,
				SafetyAttribute.MODE_ECONOMY, SafetyAttribute.MODE_RPN, SafetyAttribute.MODE_EFFECT, SafetyAttribute.MODE_CAUSE,
				SafetyAttribute.MODE_ADVICE, SafetyAttribute.MODE_DEADLINE, SafetyAttribute.MODE_MEASURE };

		this.modeTableViewer = (TableViewer) ret[0];
		this.modeTableViewer.setContentProvider(new ModeTableContentProvider());
		this.modeTableViewer.setLabelProvider(new ModeTableLabelProvider(this.editorType, propKey));

		this.modeTableViewer.setInput(node);

		if (this.editorType.equals(SafetyEditor.ID)) {
			this.modeTableViewer.getTable().addListener(SWT.Selection, new Listener() {

				@Override
				public void handleEvent(Event event) {
					// TODO Auto-generated method stub
					StructuredSelection selection = (StructuredSelection) ModelEditor.this.modeTableViewer.getSelection();
					if (selection.size() != 1) return;

					BreezeMode mode = (BreezeMode) selection.getFirstElement();

					DlgModeInfo dlg = new DlgModeInfo(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), mode);
					if (dlg.open() == IDialogConstants.OK_ID && dlg.isDirty()) {
						Map<String, String> md = dlg.getModifyData();
						for (String key : md.keySet()) {
							mode.setProperty(key, md.get(key));
						}

						int rpn = FmeaUtil.calculateRPN(mode);
						mode.setProperty(SafetyAttribute.MODE_RPN, rpn + "");

						ModelEditor.this.fireModelChange();
						ModelEditor.this.modeTableViewer.refresh();
					}
				}

			});
		}

		TableViewerColumn[] modeCol = (TableViewerColumn[]) ret[1];

		Composite btnComposite = new Composite(this.panel, SWT.NONE);
		btnComposite.setLayout(new RowLayout());
		Button btnAddMode = this.drawButton(btnComposite, ImageFactory.getImageAdd(), "Add");
		Button btnRmMode = this.drawButton(btnComposite, ImageFactory.getImageDelete(), "Delete");

		if (this.editorType.equals(SafetyEditor.ID)) {
			Button btnApply = this.drawButton(btnComposite, ImageFactory.getImageLog(), "LOG");
			Button btnOpenLog = this.drawButton(btnComposite, ImageFactory.getImageHome(), "Table");

			btnApply.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					SafetyEditor se = (SafetyEditor) ModelEditor.this.parentEditor;
					if (se.isDirty()) {
						MessageBox dlg = new MessageBox(ModelEditor.this.getShell(), SWT.OK);
						dlg.setMessage("Please Save First");
						dlg.open();
						return;
					}

					int pos = ModelEditor.this.saFilePath.length() - 1;
					for (; ModelEditor.this.saFilePath.charAt(pos) != '/'; --pos)
						;

					FmeaUtil.applyFmeaChange(ModelEditor.this.saFilePath.substring(0, pos + 1), ModelEditor.this.currentEditNode);
				}
			});

			btnOpenLog.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					int pos = ModelEditor.this.saFilePath.length() - 1;
					for (; ModelEditor.this.saFilePath.charAt(pos) != '/'; --pos)
						;

					String path = ModelEditor.this.saFilePath.substring(0, pos + 1);

					Map<String, BreezeNode> nodeList = ModelEditor.this.currentEditNode.getParent().getNodeList();
					Map<String, String> idNameMap = new HashMap<String, String>();
					for (String nodeId : nodeList.keySet()) {
						idNameMap.put(nodeId, nodeList.get(nodeId).getName());
					}

					DlgFmeaLog dlg = new DlgFmeaLog(ModelEditor.this.getShell(), FmeaUtil.compareField, path, idNameMap,
							ModelEditor.this.currentEditNode.getId());
					dlg.open();
				}
			});
		}

		this.drawDivider();

		// section "transition"
		this.drawLabel("Transition");
		this.transitionTable = this.drawTable();
		ret = this.drawTableViewer(this.transitionTable, new String[] { "Source", "Target", "Trigger", "TriggerType" },
				new int[] { 180, 180, 180, 100 });
		this.transitionTableViewer = (TableViewer) ret[0];

		this.transitionTableViewer.setContentProvider(new TransitionTableContentProvider(
				TransitionTableContentProvider.TYPE_NODE, null));
		this.transitionTableViewer.setLabelProvider(new TransitionTableLabelProvider());
		this.transitionTableViewer.setInput(node);
		TableViewerColumn[] cols = (TableViewerColumn[]) ret[1];
		TransitionComboSupport[] es = new TransitionComboSupport[] {
				new TransitionComboSupport(this, this.transitionTableViewer, 0, node),
				new TransitionComboSupport(this, this.transitionTableViewer, 1, node),
				new TransitionComboSupport(this, this.transitionTableViewer, 2, node),
				new TransitionComboSupport(this, this.transitionTableViewer, 3, node) };
		cols[0].setEditingSupport(es[0]);
		cols[1].setEditingSupport(es[1]);
		cols[2].setEditingSupport(es[2]);
		cols[3].setEditingSupport(es[3]);

		Composite btnComposite2 = new Composite(this.panel, SWT.NONE);
		btnComposite2.setLayout(new RowLayout());

		Button btnAddTran = this.drawButton(btnComposite2, ImageFactory.getImageAdd(), "Add");
		Button btnRmTran = this.drawButton(btnComposite2, ImageFactory.getImageDelete(), "Delete");
		btnAddTran.addSelectionListener(new ActionNodeAddTransition(this, node, this.transitionTableViewer));
		btnRmTran.addSelectionListener(new ActionNodeRmTransition(this, node, this.transitionTableViewer));

		if (this.editorType.equals(CorrectnessEditor.ID)) {
			modeCol[1].setEditingSupport(new ModeComboSupport(this, this.modeTableViewer));
			modeCol[0].setEditingSupport(new ModeTextSupport(this, this.modeTableViewer, 0,
					new ColumnViewer[] { this.transitionTableViewer }, new TransitionComboSupport[] { es[0], es[1] }));
			btnRmMode.addSelectionListener(new ActionRmMode(this, node, this.modeTableViewer,
					new TableViewer[] { this.transitionTableViewer }));
			btnAddMode.addSelectionListener(new ActionAddMode(this, node, this.modeTableViewer, new EditingSupport[] { es[0],
					es[1] }));
		} else {
			btnRmMode.addSelectionListener(new ActionRmMode(this, node, this.modeTableViewer, null));
			btnAddMode.addSelectionListener(new ActionAddMode(this, node, this.modeTableViewer, propKey));
		}

		this.drawPanelCommonsAfter(800);
	}

	private void drawPanelForPort(BreezePort port) {
		this.drawPanelCommonsBefore();

		// section "transition"
		this.drawLabel("Transition");
		this.transitionTable = this.drawTable();
		Object[] ret = this.drawTableViewer(this.transitionTable, new String[] { "Source", "Target", "Trigger", "TriggerType" },
				new int[] { 180, 180, 180, 100 });
		this.transitionTableViewer = (TableViewer) ret[0];
		this.transitionTableViewer.setContentProvider(new TransitionTableContentProvider(
				TransitionTableContentProvider.TYPE_PORT, port));
		this.transitionTableViewer.setLabelProvider(new TransitionTableLabelProvider());
		this.transitionTableViewer.setInput(port.getOwner());

		TableViewerColumn[] cols = (TableViewerColumn[]) ret[1];
		EditingSupport[] es = new EditingSupport[] {
				new TransitionComboSupport(this, this.transitionTableViewer, 0, port.getOwner()),
				new TransitionComboSupport(this, this.transitionTableViewer, 1, port.getOwner()),
				new TransitionComboSupport(this, this.transitionTableViewer, 2, port.getOwner()),
				new TransitionComboSupport(this, this.transitionTableViewer, 3, port.getOwner()) };
		cols[0].setEditingSupport(es[0]);
		cols[1].setEditingSupport(es[1]);
		cols[2].setEditingSupport(es[2]);
		cols[3].setEditingSupport(es[3]);

		Composite btnComposite = new Composite(this.panel, SWT.NONE);
		btnComposite.setLayout(new RowLayout());
		Button btnAddTran = this.drawButton(btnComposite, ImageFactory.getImageAdd(), "Add");
		Button btnRmTran = this.drawButton(btnComposite, ImageFactory.getImageDelete(), "Delete");
		btnAddTran.addSelectionListener(new ActionPortAddTransition(this, port, this.transitionTableViewer));
		btnRmTran.addSelectionListener(new ActionPortRmTransition(this, port, this.transitionTableViewer));

		this.drawPanelCommonsAfter(800);
	}

	private Table drawTable() {
		Table tb = new Table(this.panel, SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.BORDER);
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 10);
		tb.setLayoutData(gd);
		tb.setHeaderVisible(true);
		tb.setLinesVisible(true);
		return tb;
	}

	private Object[] drawTableViewer(Table tb, String[] headers, int[] lengths) {
		TableViewer tv = new TableViewer(tb);
		TableViewerColumn[] cols = new TableViewerColumn[headers.length];
		for (int i = 0; i < headers.length; ++i) {
			cols[i] = new TableViewerColumn(tv, SWT.NONE);
			cols[i].getColumn().setWidth(lengths[i]);
			cols[i].getColumn().setText(headers[i]);
		}
		return new Object[] { tv, cols };
	}

	private void extractAllBreezeObject(BreezeArch top, Map<String, BreezeObject> map) {
		Map<String, BreezeNode> nodeMap = top.getNodeList();
		for (String nodeKey : nodeMap.keySet()) {
			BreezeNode bn = nodeMap.get(nodeKey);
			map.put(nodeKey, bn);
			Map<String, BreezePort> portMap = bn.getPortMap();
			for (String portId : portMap.keySet())
				map.put(portId, portMap.get(portId));
		}
	}

	public void fireModelChange() {
		if (this.parentEditor instanceof SafetyEditor)
			((SafetyEditor) this.parentEditor).modelModified();
		else if (this.parentEditor instanceof CorrectnessEditor) ((CorrectnessEditor) this.parentEditor).modelModified();
	}

	private void init(String saFilePath) {
		BreezeArch topArch = null;
		this.allObjects = new HashMap<String, BreezeObject>();
		if (saFilePath != null) {

			if (this.editorType.equals(SafetyEditor.ID)) {
				this.bxp = new BreezeXMLParser(new File(saFilePath), new ParseListener[] { new ListenerExtractSafetyAttr(),
						new ListenerExtractPortName() }, null);
			} else if (this.editorType.equals(CorrectnessEditor.ID)) {
				this.bxp = new BreezeXMLParser(new File(saFilePath), new ParseListener[] { new ListenerExtractPortName() }, null);
			}

			topArch = this.bxp.getTopArch();
			if (topArch != null) {
				this.extractAllBreezeObject(topArch, this.allObjects);
				this.allObjects.put(topArch.getId(), topArch);
			} else {
				// display error
				List<BaseException> excepList = this.bxp.getExceptionList();
				for (BaseException be : excepList)
					ConsoleUtil.printError(ConsoleFactory.getConsole(),
							"line " + be.getLineNumber() + ",column " + be.getColumnNumber() + ":" + be.getMessage() + "\n");
			}
		}

		this.setExpandHorizontal(true);
		this.setExpandVertical(true);

		this.composite = new Composite(this, SWT.NONE);
		this.setContent(this.composite);
		this.composite.setBackground(this.colorWhite);
		this.composite.setBackgroundMode(SWT.INHERIT_DEFAULT);

		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		this.composite.setLayout(layout);

		Label lb0 = new Label(this.composite, SWT.NONE);
		lb0.setText("Component");
		lb0.setLayoutData(new GridData(SWT.BEGINNING, SWT.FILL, true, false, 1, 1));

		this.componentSelectViewer = new ComboViewer(this.composite, SWT.READ_ONLY);
		this.componentSelectViewer.setContentProvider(ArrayContentProvider.getInstance());
		this.componentSelectViewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof BreezeArch) {
					return ((BreezeArch) element).getName();
				} else if (element instanceof BreezeNode)
					return ((BreezeNode) element).getName();
				else if (element instanceof BreezePort)
					return ((BreezePort) element).getProperty(BreezeObject.ATTR_NAME).toString();
				return "";
			}
		});
		this.componentSelectViewer.setInput(this.allObjects.values().toArray());

		/*
		 * add listener listen to diagram editor selection change
		 */
		DiagramEditorSelectChangeListener listener = new DiagramEditorSelectChangeListener(this, this.componentSelectViewer);
		DiagramEditorSelectionHelper.setListener(this.fileNameWithoutExt, listener);

		this.componentSelectViewer.getCombo().setLayoutData(new GridData(SWT.BEGINNING, SWT.FILL, true, false, 1, 1));
		this.componentSelectViewer.getCombo().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent select) {
				ISelection selection = ModelEditor.this.componentSelectViewer.getSelection();
				if (selection instanceof StructuredSelection) {
					Object obj = ((StructuredSelection) selection).getFirstElement();
					ModelEditor.this.updatePanel(obj);
				}
			}
		});

		this.panel = new Composite(this.composite, SWT.NONE);
		this.panel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		GridLayout panelLayout = new GridLayout();
		panelLayout.numColumns = 1;
		this.panel.setLayout(panelLayout);

		this.setMinSize(this.composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

	public void updatePanel(Object obj) {
		if (obj == null) return;
		if (obj instanceof BreezeArch)
			this.drawPanelForArch((BreezeArch) obj);
		else if (obj instanceof BreezePort)
			this.drawPanelForPort((BreezePort) obj);
		else if (obj instanceof BreezeNode) this.drawPanelForNode((BreezeNode) obj);
	}

	private void writeConnection(BreezeConnection connection, Element archElement) {
		Map<String, List<Connection>> edges = connection.getConnectionMap();

		for (String key : edges.keySet()) {
			List<Connection> l = edges.get(key);
			for (Connection con : l) {
				Element edgeElement = archElement.addElement(BreezeObject.TYPE_PORT_CONNECTION);
				edgeElement.addAttribute(BreezeObject.ATTR_ID, "");
				edgeElement.addAttribute(BreezeObject.ATTR_SOURCE, con.sourceNode.getId());
				edgeElement.addAttribute(BreezeObject.ATTR_SOURCE_PORT, con.sourcePort.getId());
				edgeElement.addAttribute(BreezeObject.ATTR_TARGET, con.targetNode.getId());
				edgeElement.addAttribute(BreezeObject.ATTR_TARGET_PORT, con.targetPort.getId());
				edgeElement.addAttribute(BreezeObject.ATTR_TYPE, BreezeConnection.CONNECTION_TYPE_DIRECTED);
			}
		}
	}

	private void writeEvents(Element archElement, List<BreezeEvent> eventList) {
		for (BreezeEvent event : eventList) {
			Element eventElement = archElement.addElement(BreezeObject.TYPE_EVENT);
			eventElement.addAttribute(BreezeObject.ATTR_ID, event.getId());
			eventElement.addAttribute(BreezeObject.ATTR_PROBABILITY, event.getProbability().toString());

			if (this.editorType.equals(SafetyEditor.ID)) {
				Object type = event.getProperty(SafetyAttribute.EVENT_TYPE);
				Object gate = event.getProperty(SafetyAttribute.EVENT_GATE);
				Object parent = event.getProperty(SafetyAttribute.EVENT_CHILDREN);
				if (type == null) type = "";
				if (gate == null) gate = "";
				if (parent == null)
					parent = "";
				else {
					HashSet<String> set = (HashSet<String>) parent;
					StringBuilder sb = new StringBuilder();
					for (String child : set)
						sb.append(child).append(',');
					if (set.size() > 0) sb.deleteCharAt(sb.length() - 1);
					parent = sb.toString();
				}
				eventElement.addAttribute(SafetyAttribute.EVENT_TYPE, type.toString());
				eventElement.addAttribute(SafetyAttribute.EVENT_GATE, gate.toString());
				eventElement.addAttribute(SafetyAttribute.EVENT_CHILDREN, parent.toString());
			}
		}
	}

	private void writeNode(BreezeNode node, Element archElement) {
		Element nodeElement = archElement.addElement(BreezeObject.TYPE_NODE);
		nodeElement.addAttribute(BreezeObject.ATTR_ID, node.getId());
		nodeElement.addAttribute(BreezeObject.ATTR_NAME, node.getName());

		// write attributes
		for (BreezeMode mode : node.getModeList()) {
			Element modeElement = nodeElement.addElement(BreezeObject.TYPE_ATTRIBUTE);
			modeElement.addAttribute(BreezeObject.ATTR_ID, mode.getId());
			modeElement.addAttribute(BreezeObject.ATTR_KEY, BreezeObject.TYPE_MODE);
			modeElement.addAttribute(BreezeObject.ATTR_VALUE, mode.getType());

			// write safety Attribute
			if (this.editorType.equals(SafetyEditor.ID)) {
				Map<String, Object> prop = mode.getProperties();
				if (prop != null) {
					for (String safetyAttr : prop.keySet())
						modeElement.addAttribute(safetyAttr, prop.get(safetyAttr).toString());
				}
			}
		}

		List<BreezeModeTransition> list = node.getTransition();
		Map<String, List<BreezeModeTransition>> portTransitionMap = new HashMap<String, List<BreezeModeTransition>>();
		List<BreezeModeTransition> selfTransitionList = new ArrayList<BreezeModeTransition>();
		// classify transition according to port of transition
		for (BreezeModeTransition bmt : list) {
			if (bmt.getPort() != null) {
				List<BreezeModeTransition> l = portTransitionMap.get(bmt.getPort().getId());
				if (l == null) {
					l = new LinkedList<BreezeModeTransition>();
				}
				l.add(bmt);
				portTransitionMap.put(bmt.getPort().getId(), l);
			} else {
				// write safety specification not attach to port
				selfTransitionList.add(bmt);
			}
		}

		if (selfTransitionList.size() > 0) this.writeSafetySpecification(selfTransitionList, nodeElement);

		Map<String, BreezePort> portMap = node.getPortMap();
		for (String portId : portMap.keySet()) {
			List<BreezeModeTransition> l = portTransitionMap.get(portId);
			Element portElement = nodeElement.addElement(BreezeObject.TYPE_PORT);
			BreezePort port = node.getPortMap().get(portId);
			if (port != null) {
				portElement.addAttribute(BreezeObject.ATTR_ID, port.getId());
				portElement.addAttribute(BreezeObject.ATTR_TYPE, port.getType());
				portElement.addAttribute(BreezeObject.ATTR_NAME, "" + port.getProperty(BreezeObject.ATTR_NAME).toString());
			}

			// port safety specification
			if (l != null) {
				this.writeSafetySpecification(l, portElement);
			}
		}
	}

	private void writeSafetySpecification(List<BreezeModeTransition> transitionList, Element element) {
		Element speciElement = element.addElement(BreezeObject.TYPE_SAFATY_SPECIFICATION);
		for (BreezeModeTransition bmt : transitionList) {
			Element tranElement = speciElement.addElement(BreezeObject.TYPE_TRANSITION);
			Element src = tranElement.addElement(BreezeObject.TYPE_MODE);
			src.addAttribute(BreezeObject.ATTR_NAME, BreezeObject.ATTR_SOURCE);
			src.addAttribute(BreezeObject.ATTR_REF, bmt.getSourceState().getId());

			Element tgr = tranElement.addElement(BreezeObject.TYPE_TRIGGER);
			tgr.addAttribute(BreezeObject.ATTR_TYPE, bmt.getTriggerType());

			Element evt = tgr.addElement(BreezeObject.TYPE_EVENT);
			evt.addAttribute(BreezeObject.ATTR_REF, bmt.getTrigger().getId());
			evt.addAttribute(BreezeObject.ATTR_NAME, bmt.getTrigger().getId());

			Element tgt = tranElement.addElement(BreezeObject.TYPE_MODE);
			tgt.addAttribute(BreezeObject.ATTR_NAME, BreezeObject.ATTR_TARGET);
			tgt.addAttribute(BreezeObject.ATTR_REF, bmt.getTargetState().getId());
		}
	}

}
