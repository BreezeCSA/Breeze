package com.goku.breeze.ui.menu;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;

import com.goku.breeze.common.BreezeGk;
import com.goku.breeze.ui.editor.CorrectnessEditor;
import com.goku.breeze.ui.editor.DeadlockEditor;
import com.goku.breeze.ui.editor.ScenarioEditor;
import com.goku.breeze.util.BreezeModel2SA;
import com.goku.breeze.util.InitModelUtil;

public class InitScenario extends ActionInitModel {

	public InitScenario() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		String scenarioModelFile = InitModelUtil.copy(this.getSelectedPath(), BreezeGk.SCENARIO);
		if (scenarioModelFile == null) return;

		String safetyFileString = scenarioModelFile.substring(0, scenarioModelFile.length() - "breeze".length())
				+ScenarioEditor.FILE_EXTENSION ;
		BreezeModel2SA instance = new BreezeModel2SA();
		try {
			instance.convert(scenarioModelFile, safetyFileString);
			ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MessageBox msgDlg = new MessageBox(Display.getCurrent().getActiveShell(), SWT.ICON_INFORMATION | SWT.OK);
			msgDlg.setMessage("Can't initialize model file");
			msgDlg.setText("Initialization:");
			msgDlg.open();
			return;
		}

	}

}