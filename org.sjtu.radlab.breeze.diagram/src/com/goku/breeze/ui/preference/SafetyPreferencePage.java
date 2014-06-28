package com.goku.breeze.ui.preference;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import BreezeModel.diagram.part.BreezeDiagramEditorPlugin;

public class SafetyPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {
	public static final String NUSMV_PATH_DEFAULT_VALUE = "/home/goku/NuSMV-Breeze/bin/NuSMV";
	public static final String NUSMV_PATH_PREFERENCE = "NuSMV_PATH";

	public static void initDefaults(IPreferenceStore store) {
		store.setDefault(NUSMV_PATH_PREFERENCE, NUSMV_PATH_DEFAULT_VALUE);
	}

	private Text smvPath;

	@Override
	protected Control createContents(Composite parent) {
		// TODO Auto-generated method stub
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 5;
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.grabExcessHorizontalSpace = true;
		composite.setLayoutData(gd);
		composite.setLayout(layout);

		Label lb0 = new Label(composite, SWT.LEFT);
		lb0.setText("NuSMV");
		lb0.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));

		this.smvPath = new Text(composite, SWT.BORDER);
		this.smvPath.setText(BreezeDiagramEditorPlugin.getInstance().getPreferenceStore().getString(NUSMV_PATH_PREFERENCE));
		this.smvPath.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		Button btnScan = new Button(composite, SWT.PUSH);
		btnScan.setText("Browse..");
		btnScan.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				FileDialog dlg = new FileDialog(SafetyPreferencePage.this.getShell());
				dlg.setText("Select NuSMV");
				String path = dlg.open();
				if (path != null) {
					SafetyPreferencePage.this.smvPath.setText(path);
				}
			}
		});
		btnScan.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));

		return composite;
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub
		this.setPreferenceStore(BreezeDiagramEditorPlugin.getInstance().getPreferenceStore());
	}

	@Override
	protected void performDefaults() {
		String defaultPath = BreezeDiagramEditorPlugin.getInstance().getPreferenceStore().getDefaultString(NUSMV_PATH_PREFERENCE);
		this.smvPath.setText(defaultPath);
	}

	@Override
	public boolean performOk() {
		BreezeDiagramEditorPlugin.getInstance().getPreferenceStore().setValue(NUSMV_PATH_PREFERENCE, this.smvPath.getText());
		return super.performOk();
	}

}
