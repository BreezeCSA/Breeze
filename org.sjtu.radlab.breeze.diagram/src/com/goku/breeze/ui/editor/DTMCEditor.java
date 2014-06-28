package com.goku.breeze.ui.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorActionBarContributor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.texteditor.ITextEditor;

public class DTMCEditor extends MultiPageEditorPart {
	private DTMCModelEditor defaultEditor = null;
	private DTMCGraphModelEditor default2Editor = null;
	private DTMCResultModelEditor default3Editor = null;
	public static final String FILE_EXTENSION = "dtmc_model";
	public static final String ID = "com.goku.breeze.editor.DTMCEditor";
	private int defaultPageIndex, sourcePageIndex;

	private boolean isPageModified = false;

	private String safetyFilePath = null;
	private XMLViewer textEditor = null;

	public DTMCEditor() {
		// TODO Auto-generated constructor stub
	}

	private void createDefaultPage() {
		IEditorInput iei = this.getEditorInput();
		String dtmcFileName = null;
		if (iei instanceof FileEditorInput) {
			FileEditorInput fei = (FileEditorInput) iei;
			dtmcFileName = fei.getFile().getLocation().toString();
		}
		String name = iei.getName();
		if (name.endsWith(".mkv_model")) {
			name = name.substring(0, name.length() - ".mkv_model".length());
		}
		this.defaultEditor = new DTMCModelEditor(dtmcFileName, name, this.getContainer(), this);

		this.defaultPageIndex = this.addPage(this.defaultEditor);
	//	this.defaultPageIndex = this.addPage(new RBDModelEditor(null,null,this.getContainer(),null));
		this.setPageText(this.defaultPageIndex, "Extended DTMC");
	}

	
	@Override
	protected void createPages() {
		// TODO Auto-generated method stub
		this.createDefaultPage();
		this.createResultPage();
		this.createGraphPage();
		this.createSourcePage();
		this.setPartName(this.getEditorInput().getName());
		this.setTitleToolTip(this.getEditorInput().getToolTipText());
	}
	
	private void createResultPage() {
		IEditorInput iei = this.getEditorInput();
		String dtmcGFileName = null;
		if (iei instanceof FileEditorInput) {
			FileEditorInput fei = (FileEditorInput) iei;
			dtmcGFileName = fei.getFile().getLocation().toString();
		}
		String name = iei.getName();
		if (name.endsWith(".dtmc_model")) {
			name = name.substring(0, name.length() - ".dtmc_model".length());
		}
		this.default3Editor = new DTMCResultModelEditor(dtmcGFileName, name, this.getContainer(), this);

		this.defaultPageIndex = this.addPage(this.default3Editor);
	//	this.defaultPageIndex = this.addPage(new RBDModelEditor(null,null,this.getContainer(),null));
		this.setPageText(this.defaultPageIndex, "Reliability Result");
	}
	
	private void createGraphPage() {
		IEditorInput iei = this.getEditorInput();
		String dtmcGFileName = null;
		if (iei instanceof FileEditorInput) {
			FileEditorInput fei = (FileEditorInput) iei;
			dtmcGFileName = fei.getFile().getLocation().toString();
		}
		String name = iei.getName();
		if (name.endsWith(".dtmc_model")) {
			name = name.substring(0, name.length() - ".dtmc_model".length());
		}
		this.default2Editor = new DTMCGraphModelEditor(dtmcGFileName, name, this.getContainer(), this);

		this.defaultPageIndex = this.addPage(this.default2Editor);
	//	this.defaultPageIndex = this.addPage(new RBDModelEditor(null,null,this.getContainer(),null));
		this.setPageText(this.defaultPageIndex, "Graph");
	}
	
	
	private void createSourcePage() {
		this.textEditor = new XMLViewer(this.getContainer(), new Font(Display.getCurrent(), "Consolas", 10, SWT.NORMAL),
				(FileEditorInput) this.getEditorInput());
		this.sourcePageIndex = this.addPage(this.textEditor);
		this.setPageText(this.sourcePageIndex, "Source");
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		this.defaultEditor.doSave();

		this.textEditor.triggerChange(this.safetyFilePath);
		this.isPageModified = false;
		this.firePropertyChange(IEditorPart.PROP_DIRTY);
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	public int getDefaultPageIndex() {
		return this.defaultPageIndex;
	}

	public int getSourcePageIndex() {
		return this.sourcePageIndex;
	}

	@Override
	protected void handlePropertyChange(int propId) {
		if (propId == IEditorPart.PROP_DIRTY)
			this.isPageModified = this.isDirty();
		super.firePropertyChange(propId);
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		super.init(site, input);
	}

	@Override
	public boolean isDirty() {
		return this.isPageModified || super.isDirty();
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	public void modelModified() {
		boolean wasDirty = this.isDirty();
		this.isPageModified = true;
		if (!wasDirty)
			this.firePropertyChange(IEditorPart.PROP_DIRTY);
	}

	@Override
	protected void pageChange(int newPageIndex) {
		if (newPageIndex == this.defaultPageIndex) {

		} else if (newPageIndex == this.sourcePageIndex) {

		}
		IEditorActionBarContributor contributor = this.getEditorSite().getActionBarContributor();
//		if (contributor instanceof SafetyEditorContributor) {
//			((SafetyEditorContributor) contributor).setActivePage(this, newPageIndex);
//		}
		super.pageChange(newPageIndex);
	}

	@Override
	public void setFocus() {
		int active = this.getActivePage();
		if (active == this.defaultPageIndex)
			this.defaultEditor.setFocus();
		else if (active == this.sourcePageIndex)
			this.textEditor.setFocus();
	}

}
