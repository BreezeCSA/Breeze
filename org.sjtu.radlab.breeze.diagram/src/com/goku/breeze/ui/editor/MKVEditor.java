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
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;

public class MKVEditor extends MultiPageEditorPart {
	public static final String FILE_EXTENSION = "mkv_model";
	public static final String ID = "com.goku.breeze.editor.MKVEditor";

	private MPRModelEditor default2Editor = null;
	private MKVModelEditor defaultEditor = null;
	private int defaultPageIndex, sourcePageIndex;

	private boolean isPageModified = false;

	private String safetyFilePath = null;
	private XMLViewer textEditor = null;

	public MKVEditor() {
		// TODO Auto-generated constructor stub
	}

	private void createDefaultPage() {
		IEditorInput iei = this.getEditorInput();
		String mkvFileName = null;
		if (iei instanceof FileEditorInput) {
			FileEditorInput fei = (FileEditorInput) iei;
			mkvFileName = fei.getFile().getLocation().toString();
		}
		String name = iei.getName();
		if (name.endsWith(".mkv_model")) {
			name = name.substring(0, name.length() - ".mkv_model".length());
		}
		this.defaultEditor = new MKVModelEditor(mkvFileName, name, this.getContainer(), this);

		this.defaultPageIndex = this.addPage(this.defaultEditor);
		//	this.defaultPageIndex = this.addPage(new RBDModelEditor(null,null,this.getContainer(),null));
		this.setPageText(this.defaultPageIndex, "Original DTMC");
	}

	@Override
	protected void createPages() {
		// TODO Auto-generated method stub
		System.out.print("qq");
		this.createDefaultPage();
		this.createResultPage();
		this.createSourcePage();
		this.setPartName(this.getEditorInput().getName());
		this.setTitleToolTip(this.getEditorInput().getToolTipText());
	}

	private void createResultPage() {
		IEditorInput iei = this.getEditorInput();
		String mprFileName = null;
		if (iei instanceof FileEditorInput) {
			FileEditorInput fei = (FileEditorInput) iei;
			mprFileName = fei.getFile().getLocation().toString();
		}
		String name = iei.getName();
		if (name.endsWith(".mkv_model")) {
			name = name.substring(0, name.length() - ".mkv_model".length());
		}
		this.default2Editor = new MPRModelEditor(mprFileName, name, this.getContainer(), this);

		this.defaultPageIndex = this.addPage(this.default2Editor);
		//	this.defaultPageIndex = this.addPage(new RBDModelEditor(null,null,this.getContainer(),null));
		this.setPageText(this.defaultPageIndex, "Reliability Result");
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
		if (propId == IEditorPart.PROP_DIRTY) this.isPageModified = this.isDirty();
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
		if (!wasDirty) this.firePropertyChange(IEditorPart.PROP_DIRTY);
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
		else if (active == this.sourcePageIndex) this.textEditor.setFocus();
	}

}
