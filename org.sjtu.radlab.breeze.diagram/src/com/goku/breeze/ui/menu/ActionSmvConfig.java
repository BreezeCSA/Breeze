package com.goku.breeze.ui.menu;

import java.io.File;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.ui.PlatformUI;

import com.goku.breeze.common.ListenerExtractPortName;
import com.goku.breeze.compiler.event.ParseListener;
import com.goku.breeze.compiler.main.BreezeXMLParser;
import com.goku.breeze.ui.dialog.DlgSmvRunConfiguration;
import com.goku.breeze.util.smv.SMVCheckDeadlock;

public class ActionSmvConfig extends ActionFilesSelection {

	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		if (this.getAbsolutePath() == null || this.getSelectedFileNames() == null)
			return;
		for (int i = 0; i < this.getSelectedFileNames().length; ++i) {
			if (this.getSelectedFileNames() != null) {
				String fileName = this.getAbsolutePath() + this.getSelectedFileNames()[i];
				BreezeXMLParser bxp = new BreezeXMLParser(new File(fileName),
						new ParseListener[] { new ListenerExtractPortName() }, null);
				DlgSmvRunConfiguration dlg = new DlgSmvRunConfiguration(PlatformUI.getWorkbench().getActiveWorkbenchWindow()
						.getShell(), bxp);
				dlg.open();

				if (dlg.getReturnCode() == IDialogConstants.OK_ID) {
					Map<String, Set<String>> specList = dlg.getCheckNode();
					SMVCheckDeadlock ck = new SMVCheckDeadlock(fileName, this.getAbsolutePath(), specList);
					ck.start();
				}
			}
		}
	}

}
