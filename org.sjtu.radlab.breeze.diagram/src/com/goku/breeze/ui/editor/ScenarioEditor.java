package com.goku.breeze.ui.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;

public class ScenarioEditor extends MultiPageEditorPart {
	public static final String FILE_EXTENSION = "scenario";
	public static final String ID = "com.goku.breeze.editor.ScenarioEditor";

	private String scenarioFilePath = null;
	private ScenarioModelEditor defaultEditor = null;
	private int defaultPageIndex, sourcePageIndex;
	private boolean isPageModified = false;
	private XMLViewer textEditor = null;

	public ScenarioEditor() {
		// TODO Auto-generated constructor stub
	}

	private void createDefaultPage() {
		IEditorInput iei = this.getEditorInput();
		if (iei instanceof FileEditorInput) {
			FileEditorInput fei = (FileEditorInput) iei;
			this.scenarioFilePath = fei.getFile().getLocation().toString();
		}
		String name = iei.getName();
		if (name.endsWith(FILE_EXTENSION)) {
			name = name.substring(0, name.length() - FILE_EXTENSION.length() - 1);
		}
		this.defaultEditor = new ScenarioModelEditor(this.scenarioFilePath,name, this.getContainer(),this);

		this.defaultPageIndex = this.addPage(this.defaultEditor);
		this.setPageText(this.defaultPageIndex, "Scenario Modeling");
	}

	@Override
	protected void createPages() {
		// TODO Auto-generated method stub
		this.createDefaultPage();
		this.createSourcePage();
		this.setPartName(this.getEditorInput().getName());
		this.setTitleToolTip(this.getEditorInput().getToolTipText());
	}

	private void createSourcePage() {
		this.textEditor = new XMLViewer(this.getContainer(), new Font(Display.getCurrent(), "Consolas", 12, SWT.NORMAL),
				(FileEditorInput) this.getEditorInput());
		this.sourcePageIndex = this.addPage(this.textEditor);
		this.setPageText(this.sourcePageIndex, "Source");
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		this.defaultEditor.doSave();

		this.textEditor.triggerChange(this.scenarioFilePath);
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
