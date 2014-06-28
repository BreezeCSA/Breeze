package com.goku.breeze.ui.dialog;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import com.goku.breeze.common.SafetyAttribute;
import com.goku.breeze.compiler.main.BreezeXMLParser;
import com.goku.breeze.compiler.model.BreezeArch;
import com.goku.breeze.compiler.model.BreezeEvent;

public class DlgSelectEventChildren extends Dialog {
	private BreezeArch arch = null;
	private Set<String> children = null;
	private BreezeEvent eventObject = null;
	private boolean isDataChange = false;
	private Table table = null;

	public DlgSelectEventChildren(Shell parentShell, BreezeXMLParser bxp, BreezeEvent eventObject) {
		super(parentShell);
		// TODO Auto-generated constructor stub
		this.arch = bxp.getTopArch();
		this.eventObject = eventObject;
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("Select children");
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

	@SuppressWarnings("unchecked")
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.marginHeight = this.convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
		gl.marginWidth = this.convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
		gl.verticalSpacing = this.convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
		gl.horizontalSpacing = this.convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);

		composite.setLayout(gl);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		applyDialogFont(composite);

		this.table = new Table(composite, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL);
		this.table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		this.children = (Set<String>) this.eventObject.getProperty(SafetyAttribute.EVENT_CHILDREN);

		if (this.children == null) {
			this.children = new HashSet<String>();
			this.eventObject.setProperty(SafetyAttribute.EVENT_CHILDREN, this.children);
		}

		if (this.arch != null) {
			for (BreezeEvent event : this.arch.getEventList()) {
				if (this.eventObject.getId().equals(event.getId()))
					continue;
				TableItem item = new TableItem(this.table, SWT.NONE);
				item.setText(event.getId());
				if (this.children.contains(event.getId()))
					item.setChecked(true);

			}
		}

		this.table.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				if (event.detail == SWT.CHECK) {
					TableItem item = (TableItem) event.item;
					if (item != null) {
						if (item.getChecked())
							DlgSelectEventChildren.this.children.add(item.getText());
						else DlgSelectEventChildren.this.children.remove(item.getText());
					}
					DlgSelectEventChildren.this.isDataChange = true;
				}
			}

		});

		return composite;
	}

	@Override
	protected Point getInitialSize() {
		return new Point(540, 480);
	}

	public boolean isDataChange() {
		return this.isDataChange;
	}

}
