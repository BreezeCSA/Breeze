package com.goku.breeze.ui.menu;

import java.io.File;
import java.util.HashMap;

import org.eclipse.jface.action.IAction;
import org.eclipse.ui.PlatformUI;

import com.goku.breeze.compiler.main.BreezeXMLParser;
import com.goku.breeze.compiler.model.BreezeArch;
import com.goku.breeze.ui.dialog.DlgFmeaLog;
import com.goku.breeze.ui.editor.SafetyEditor;
import com.goku.breeze.util.FmeaUtil;

public class ActionFmea extends ActionFilesSelection {

	public ActionFmea() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		if (this.getAbsolutePath() == null || this.getSelectedFileNames() == null) return;

		for (int i = 0; i < this.getSelectedFileNames().length; ++i) {
			if (this.getSelectedFileNames()[i] != null) {
				String fileName = this.getAbsolutePath() + this.getSelectedFileNames()[i];
				if (fileName.endsWith(SafetyEditor.FILE_EXTENSION)) {
					BreezeXMLParser bxp = new BreezeXMLParser(new File(fileName), null, null);
					BreezeArch arch = bxp.getTopArch();
					HashMap<String, String> idNameMap = new HashMap<String, String>();
					for (String nodeId : arch.getNodeList().keySet())
						idNameMap.put(nodeId, arch.getNodeList().get(nodeId).getName());

					DlgFmeaLog dlg = new DlgFmeaLog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
							FmeaUtil.compareField, this.getAbsolutePath(), idNameMap, null);
					dlg.open();
				}
			}
		}
	}

}
