package com.goku.breeze.ui.dialog;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.goku.breeze.compiler.main.BreezeXMLParser;
import com.goku.breeze.compiler.model.BreezeArch;
import com.goku.breeze.compiler.model.BreezeMode;
import com.goku.breeze.compiler.model.BreezeNode;
import com.goku.breeze.ui.dialog.support.ArchLabelProvider;
import com.goku.breeze.ui.dialog.support.ArchTreeContentProvider;

public class DlgSmvRunConfiguration extends TitleAreaDialog {
	private Button btnFlag = null;
	private BreezeXMLParser bxp = null;
	private final HashMap<String, Set<String>> checkNode = new HashMap<String, Set<String>>();
	private CheckboxTreeViewer treeViewer = null;

	public DlgSmvRunConfiguration(Shell parentShell, BreezeXMLParser arch) {
		super(parentShell);
		// TODO Auto-generated constructor stub
		this.bxp = arch;

		BreezeArch ta = arch.getTopArch();
		for (String nodeId : ta.getNodeList().keySet()) {
			BreezeNode node = ta.getNodeList().get(nodeId);
			for (BreezeMode mode : node.getModeList())
				mode.setProperty("$node$", nodeId);
		}
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("Smv Run Configuration");
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		this.setTitle("NuSMV Configuration");
		this.setMessage("Select components to be checked");
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.marginHeight = this.convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
		gl.marginWidth = this.convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
		gl.verticalSpacing = this.convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
		gl.horizontalSpacing = this.convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);

		composite.setLayout(gl);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		applyDialogFont(composite);

		this.btnFlag = new Button(composite, SWT.CHECK);
		this.btnFlag.setText("Custom Specification");

		this.treeViewer = new CheckboxTreeViewer(composite, SWT.BORDER);
		this.treeViewer.setContentProvider(new ArchTreeContentProvider());
		this.treeViewer.setLabelProvider(new ArchLabelProvider());
		this.treeViewer.setInput(this.bxp);
		this.treeViewer.getTree().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		this.treeViewer.addCheckStateListener(new ICheckStateListener() {

			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				// TODO Auto-generated method stub
				Object element = event.getElement();
				boolean checked = DlgSmvRunConfiguration.this.treeViewer.getChecked(element);

				Map<String, BreezeNode> nodeMap = DlgSmvRunConfiguration.this.bxp.getTopArch().getNodeList();

				if (element instanceof BreezeArch) {
					DlgSmvRunConfiguration.this.checkNode.clear();
					for (String key : nodeMap.keySet()) {
						BreezeNode node = nodeMap.get(key);
						DlgSmvRunConfiguration.this.treeViewer.setChecked(node, checked);
						Set<String> modeSet = new HashSet<String>();
						for (BreezeMode mode : node.getModeList()) {
							DlgSmvRunConfiguration.this.treeViewer.setChecked(mode, checked);
							if (checked)
								modeSet.add(mode.getId());
						}
						if (checked) {
							DlgSmvRunConfiguration.this.checkNode.put(node.getId(), modeSet);
						}
					}
				} else if (element instanceof BreezeNode) {
					BreezeNode node = (BreezeNode) element;
					DlgSmvRunConfiguration.this.checkNode.remove(node.getId());
					Set<String> modeSet = new HashSet<String>();
					for (BreezeMode mode : node.getModeList()) {
						DlgSmvRunConfiguration.this.treeViewer.setChecked(mode, checked);
						if (checked)
							modeSet.add(mode.getId());
					}
					if (checked) {
						DlgSmvRunConfiguration.this.checkNode.put(node.getId(), modeSet);
					}
				} else if (element instanceof BreezeMode) {
					BreezeMode mode = (BreezeMode) element;
					Set<String> modeSet = DlgSmvRunConfiguration.this.checkNode.get(mode.getProperty("$node$"));
					if (modeSet == null) {
						modeSet = new HashSet<String>();
						DlgSmvRunConfiguration.this.checkNode.put((String) mode.getProperty("$node$"), modeSet);
					}

					if (checked)
						modeSet.add(mode.getId());
					else modeSet.remove(mode.getId());
				}
			}

		});

		return composite;
	}

	public HashMap<String, Set<String>> getCheckNode() {
		return this.checkNode;
	}

	@Override
	protected Point getInitialSize() {
		return new Point(540, 480);
	}

}
