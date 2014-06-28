package com.goku.breeze.ui.view;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.internal.resources.File;
import org.eclipse.core.internal.resources.Folder;
import org.eclipse.core.internal.resources.Project;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import BreezeModel.diagram.part.BreezeDiagramEditorPlugin;

import com.goku.breeze.common.BreezeGk;
import com.goku.breeze.compiler.model.BreezeArch;
import com.goku.breeze.model.TraceNode;
import com.goku.breeze.ui.ImageFactory;
import com.goku.breeze.ui.navigator.BreezeExplorer;
import com.goku.breeze.ui.view.support.TraceContentProvider;
import com.goku.breeze.ui.view.support.TraceLabelProvider;
import com.goku.breeze.util.GetNuSMVTrace;

public class ViewNuSMV extends ViewPart {
	public final static String ID = "com.goku.breeze.ui.view.ViewNuSMV";
	private Button btnPrev, btnNext, btnHide;
	private TraceContentProvider contentProvider;
	private List<TraceNode> inputList = null;
	private Combo pageSelect;
	private TreeViewer treeViewer;

	public ViewNuSMV() {
		// TODO Auto-generated constructor stub

		IViewPart ivp = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.findView("com.goku.breeze.ui.navigator.view");
		if (ivp instanceof BreezeExplorer) {
			BreezeExplorer explorer = (BreezeExplorer) ivp;
			explorer.getCommonViewer().addSelectionChangedListener(new ISelectionChangedListener() {

				@SuppressWarnings("restriction")
				@Override
				public void selectionChanged(SelectionChangedEvent event) {
					// TODO Auto-generated method stub
					ISelection obj = event.getSelection();
					if (obj instanceof TreeSelection) {
						TreeSelection selection = (TreeSelection) obj;
						Object item = selection.getFirstElement();
						IProject iProject = null;

						if (item instanceof File) {
							File file = (File) item;
							iProject = file.getProject();
						} else if (item instanceof Folder) {
							Folder folder = (Folder) item;
							iProject = folder.getProject();
						} else if (item instanceof Project) {
							Project project = (Project) item;
							iProject = project.getProject();
						}

						boolean set = false;

						if (iProject != null) {
							try {
								String archPath = iProject.getLocation().append(BreezeGk.CORRECTNESS).append("arch").toOSString();
								String tracePath = iProject.getLocation().append(BreezeGk.CORRECTNESS).toString();
								FileInputStream fis0 = new FileInputStream(archPath);
								ObjectInputStream ois = new ObjectInputStream(fis0);
								BreezeArch arch = (BreezeArch) ois.readObject();
								ois.close();
								fis0.close();

								java.io.File file = new java.io.File(tracePath);
								if (file.exists() && file.isDirectory()) {
									String[] fileList = file.list();
									for (String fl : fileList) {
										if (fl.endsWith("smvout")) {
											ViewNuSMV.this.setInput(iProject.getLocation().append(BreezeGk.CORRECTNESS)
													.append(fl).toOSString(), arch);
											set = true;
											break;
										}
									}
								}

							} catch (Exception e) {
								e.printStackTrace();
							}

							if (!set) ViewNuSMV.this.setInput(null, null);
						}
					}
				}

			});
		}

	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		TabFolder tabFolder = new TabFolder(parent, SWT.NONE);

		TabItem itemTrace = new TabItem(tabFolder, SWT.NULL);
		itemTrace.setText("Trace");
		Composite traceTab = this.createTraceTab(tabFolder);
		itemTrace.setControl(traceTab);

		TabItem itemSimulation = new TabItem(tabFolder, SWT.NONE);
		itemSimulation.setText("Simulation");
		Composite simulationTab = new Composite(tabFolder, SWT.NONE);
		itemSimulation.setControl(simulationTab);
	}

	private Composite createTraceTab(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.numColumns = 4;
		composite.setLayout(gl);
		this.pageSelect = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
		this.pageSelect.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ViewNuSMV.this.selectStep(ViewNuSMV.this.pageSelect.getSelectionIndex());
			}
		});

		GridData psgd = new GridData();
		psgd.widthHint = 60;
		this.pageSelect.setLayoutData(psgd);

		this.btnPrev = new Button(composite, SWT.FLAT | SWT.PUSH);
		this.btnPrev.setImage(ImageFactory.getImagePrev());

		this.btnPrev.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = ViewNuSMV.this.pageSelect.getSelectionIndex();
				if (index > 0) ViewNuSMV.this.selectStep(index - 1);
			}
		});

		// 
		this.btnNext = new Button(composite, SWT.FLAT | SWT.PUSH);

		this.btnNext.setImage(ImageFactory.getImageNext());

		this.btnNext.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = ViewNuSMV.this.pageSelect.getSelectionIndex();
				if (index < ViewNuSMV.this.pageSelect.getItemCount() - 1) ViewNuSMV.this.selectStep(index + 1);
			}
		});

		this.btnHide = new Button(composite, SWT.FLAT | SWT.PUSH);
		Image hideImage = BreezeDiagramEditorPlugin.getBundledImageDescriptor("icons/obj16/hide.png").createImage();
		this.btnHide.setImage(hideImage);

		Tree tree = new Tree(composite, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		tree.setHeaderVisible(false);
		tree.setLinesVisible(true);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));
		this.treeViewer = new TreeViewer(tree);

		TreeColumn column0 = new TreeColumn(tree, SWT.LEFT);
		column0.setWidth(350);
		TreeColumn column1 = new TreeColumn(tree, SWT.LEFT);
		column1.setWidth(100);

		this.contentProvider = new TraceContentProvider();
		this.treeViewer.setContentProvider(this.contentProvider);
		this.treeViewer.setLabelProvider(new TraceLabelProvider());

		return composite;
	}

	private void getAllTraceNode(TraceNode tn, List<TraceNode> container) {
		container.add(tn);
		if (tn.getChildren() == null) return;
		for (TraceNode child : tn.getChildren())
			this.getAllTraceNode(child, container);
	}

	private void selectStep(int step) {
		Object[] visibleElements = this.treeViewer.getVisibleExpandedElements();
		Set<String> visibleNodeId = new HashSet<String>();
		for (Object visibleElement : visibleElements) {
			visibleNodeId.add(((TraceNode) visibleElement).getId());
		}
		int horizontalScrollValue = this.treeViewer.getTree().getHorizontalBar().getSelection();

		this.contentProvider.setStep(step);
		this.pageSelect.select(step);

		if (step == 0)
			this.btnPrev.setEnabled(false);
		else this.btnPrev.setEnabled(true);
		if (step == this.pageSelect.getItemCount() - 1)
			this.btnNext.setEnabled(false);
		else this.btnNext.setEnabled(true);
		this.treeViewer.refresh();

		List<TraceNode> tnList = new LinkedList<TraceNode>();
		this.getAllTraceNode(this.inputList.get(step), tnList);
		List<TraceNode> currentVisible = new LinkedList<TraceNode>();
		for (TraceNode tn : tnList) {
			if (visibleNodeId.contains(tn.getId())) {
				currentVisible.add(tn);
			}
		}
		currentVisible.add(this.inputList.get(step));
		this.treeViewer.setExpandedElements(currentVisible.toArray());
		this.treeViewer.getTree().getHorizontalBar().setSelection(horizontalScrollValue);
		this.treeViewer.refresh();
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}

	public void setInput(String inputFilePath, BreezeArch arch) {

		if (inputFilePath == null || arch == null) {
			this.pageSelect.removeAll();
			this.treeViewer.setInput(null);
			this.treeViewer.refresh();
			return;
		}

		this.inputList = GetNuSMVTrace.getTraceNodeList(inputFilePath, arch);
		this.pageSelect.removeAll();
		for (int i = 1; i <= this.inputList.size(); ++i)
			this.pageSelect.add("" + i);
		this.pageSelect.select(0);
		this.treeViewer.setInput(this.inputList);
		this.treeViewer.expandAll();

		this.pageSelect.redraw();

		this.treeViewer.refresh();
	}
}
