package com.goku.breeze.ui.dialog;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.goku.breeze.common.SafetyAttribute;
import com.goku.breeze.compiler.model.BreezeMode;
import com.goku.breeze.compiler.model.BreezeObject;

public class DlgModeInfo extends TitleAreaDialog {
	private class TextListener implements ModifyListener {
		private final String key;

		public TextListener(String key) {
			this.key = key;
		}

		@Override
		public void modifyText(ModifyEvent e) {
			// TODO Auto-generated method stub
			String data = "";
			if (e.widget instanceof Text) {
				data = ((Text) e.widget).getText();
			} else {
				data = ((Combo) e.widget).getText();
			}
			DlgModeInfo.this.modifyData.put(this.key, data);
			DlgModeInfo.this.isDirty = true;
		}

	}

	private boolean isDirty = false;
	private final BreezeMode mode;
	private final Map<String, String> modifyData = new HashMap<String, String>();

	public DlgModeInfo(Shell parentShell, BreezeMode mode) {
		super(parentShell);
		// TODO Auto-generated constructor stub
		this.setShellStyle(this.getShellStyle() | SWT.RESIZE);
		this.mode = mode;
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("FMEA");
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		this.setTitle("Failure Mode Infomation");
		this.setMessage("Input function, cause, effect, and O, S, D of mode");
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.marginHeight = this.convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
		gl.marginWidth = this.convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
		gl.verticalSpacing = this.convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
		gl.horizontalSpacing = this.convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
		gl.numColumns = 4;

		composite.setLayout(gl);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		applyDialogFont(composite);

		Label lb0 = new Label(composite, SWT.NONE);
		lb0.setText("Mode");
		Text txt0 = new Text(composite, SWT.BORDER);
		txt0.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1));
		txt0.setText(this.mode.getId());
		txt0.addModifyListener(new TextListener(BreezeObject.ATTR_ID));

		int heightHint = 75;
		GC gc = new GC(txt0);
		try {
			gc.setFont(txt0.getFont());
			org.eclipse.swt.graphics.FontMetrics fm = gc.getFontMetrics();
			heightHint = 3 * fm.getHeight();
		} finally {
			gc.dispose();
		}

		String[] labels = new String[] { "Function", "Effect", "Cause", "Advice", "Measure", "Deadline" };
		String[] keys = new String[] { SafetyAttribute.MODE_FUNCTION, SafetyAttribute.MODE_EFFECT, SafetyAttribute.MODE_CAUSE,
				SafetyAttribute.MODE_ADVICE, SafetyAttribute.MODE_MEASURE, SafetyAttribute.MODE_DEADLINE };
		boolean[] useHeightHint = new boolean[] { true, true, true, true, true, false };

		for (int i = 0; i < labels.length; ++i) {
			Label lb = new Label(composite, SWT.NONE);

			Text txt = null;

			lb.setText(labels[i]);

			if (useHeightHint[i]) {
				lb.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 3));
				txt = new Text(composite, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
			} else txt = new Text(composite, SWT.BORDER);

			GridData gd = null;
			if (useHeightHint[i]) {
				gd = new GridData(SWT.FILL, SWT.FILL, true, false, 3, 3);
				gd.heightHint = heightHint;
			} else gd = new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1);
			txt.setLayoutData(gd);
			txt.setText(this.mode.getProperty(keys[i]).toString());
			txt.addModifyListener(new TextListener(keys[i]));
		}

		String[] cbItem = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
		labels = new String[] { "Occurrence", "Severity", "Detection", "Economy", "Human Factor" };
		keys = new String[] { SafetyAttribute.MODE_OCCURRENCE, SafetyAttribute.MODE_SEVERITY, SafetyAttribute.MODE_DETECTION,
				SafetyAttribute.MODE_ECONOMY, SafetyAttribute.MODE_HUMAN_FACTOR };

		for (int i = 0; i < labels.length; ++i) {
			Label lb = new Label(composite, SWT.NONE);
			lb.setText(labels[i]);
			Combo cb = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
			cb.setItems(cbItem);
			cb.setText(this.mode.getProperty(keys[i]).toString());
			cb.addModifyListener(new TextListener(keys[i]));
		}

		return composite;

	}

	public Map<String, String> getModifyData() {
		return this.modifyData;
	}

	public boolean isDirty() {
		return this.isDirty;
	}
}
