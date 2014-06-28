package com.goku.breeze.ui.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.source.CompositeRuler;
import org.eclipse.jface.text.source.LineNumberRulerColumn;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.TextFileDocumentProvider;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.IDocumentProvider;

public class XMLEditor extends EditorPart {
	public final static String FILE_EXTENSION = "breeze";
	public final static String ID = "com.goku.breeze.ui.editor.XMLEditor";

	public XMLEditor() {
		super();
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		CompositeRuler ruler = new CompositeRuler();
		LineNumberRulerColumn lnrc = new LineNumberRulerColumn();
		lnrc.setForeground(new Color(Display.getCurrent(), new RGB(153, 153, 153)));
		ruler.addDecorator(0, lnrc);
		SourceViewer viewer = new SourceViewer(parent, ruler, SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER | SWT.MULTI
				| SWT.FULL_SELECTION | SWT.READ_ONLY);
		viewer.getTextWidget().setLayoutData(new GridData(GridData.FILL_BOTH));
		viewer.getTextWidget().setFont(new Font(Display.getCurrent(), "Consolas", 12, SWT.NORMAL));

		FileEditorInput fei = (FileEditorInput) this.getEditorInput();
		IDocumentProvider provider = new TextFileDocumentProvider();
		IDocument doc = null;
		try {
			provider.connect(fei.getFile());
			doc = provider.getDocument(fei.getFile());
		} catch (Exception e) {
			e.printStackTrace();
		}

		IDocumentPartitioner partitioner = new FastPartitioner(new RuleBasedPartitionScanner(), new String[0]);
		partitioner.connect(doc);
		doc.setDocumentPartitioner(partitioner);
		viewer.setDocument(doc);

		// configure viewer
		viewer.configure(new XMLSourceConfiguration());
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		// TODO Auto-generated method stub
		this.setSite(site);
		this.setInput(input);
		this.setPartName(input.getName());
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
