package com.goku.breeze.ui.editor;

import java.io.File;
import java.io.FileInputStream;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.editors.text.TextFileDocumentProvider;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.IDocumentProvider;

import com.goku.breeze.ui.console.ConsoleFactory;
import com.goku.breeze.ui.console.ConsoleUtil;

public class XMLViewer extends Composite {
	private IDocument doc = null;
	SourceViewer viewer;

	public XMLViewer(Composite parent, Font font, FileEditorInput fei) {
		super(parent, SWT.NONE);
		// TODO Auto-generated constructor stub
		this.setLayoutData(new GridData(GridData.FILL_BOTH));
		GridLayout gl = new GridLayout();
		this.setLayout(gl);
		this.viewer = new SourceViewer(this, null, SWT.V_SCROLL | SWT.H_SCROLL | SWT.READ_ONLY);
		this.viewer.getTextWidget().setLayoutData(new GridData(GridData.FILL_BOTH));
		if (font != null)
			this.viewer.getTextWidget().setFont(font);

		IDocumentProvider provider = new TextFileDocumentProvider();
		try {
			provider.connect(fei.getFile());
			this.doc = provider.getDocument(fei.getFile());
		} catch (Exception e) {
			e.printStackTrace();
		}

		IDocumentPartitioner partitioner = new FastPartitioner(new RuleBasedPartitionScanner(), new String[0]);
		partitioner.connect(this.doc);
		this.doc.setDocumentPartitioner(partitioner);
		this.viewer.setDocument(this.doc);

		// configure viewer
		this.viewer.configure(new XMLSourceConfiguration());
	}

	public void triggerChange(String filePath) {
		File file = new File(filePath);
		try {
			FileInputStream fis = new FileInputStream(file);
			byte[] buf = new byte[10240];
			int len = 0;
			StringBuffer sb = new StringBuffer();
			while ((len = fis.read(buf)) > 0) {
				sb.append(new String(buf, 0, len));
			}
			fis.close();
			this.doc.set(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			ConsoleUtil.printError(ConsoleFactory.getConsole(), "Can't open file:" + filePath);
		}

		this.viewer.refresh();
	}
}
