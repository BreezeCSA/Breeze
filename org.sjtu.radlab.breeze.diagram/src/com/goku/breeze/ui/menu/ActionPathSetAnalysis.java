package com.goku.breeze.ui.menu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.action.IAction;
import org.eclipse.ui.PlatformUI;

import com.goku.breeze.common.ListenerExtractSafetyAttr;
import com.goku.breeze.common.SafetyAttribute;
import com.goku.breeze.compiler.event.ParseListener;
import com.goku.breeze.compiler.main.BreezeXMLParser;
import com.goku.breeze.compiler.model.BreezeArch;
import com.goku.breeze.compiler.model.BreezeEvent;
import com.goku.breeze.ui.dialog.DlgFtaAnalysis;
import com.goku.breeze.ui.editor.SafetyEditor;
import com.goku.breeze.util.FtaUtil;

public class ActionPathSetAnalysis extends ActionFilesSelection {
	private BreezeArch arch;

	private List<Set<String>> findEqualSet(String inputFilePath) {
		try {
			BreezeXMLParser bxp = new BreezeXMLParser(new File(inputFilePath),
					new ParseListener[] { new ListenerExtractSafetyAttr() }, null);

			this.arch = bxp.getTopArch();

			for (BreezeEvent event : this.arch.getEventList()) {
				if (SafetyAttribute.EVENT_GATE_AND.equals(event.getProperty(SafetyAttribute.EVENT_GATE)))
					event.setProperty(SafetyAttribute.EVENT_GATE, SafetyAttribute.EVENT_GATE_OR);
				else event.setProperty(SafetyAttribute.EVENT_GATE, SafetyAttribute.EVENT_GATE_AND);
			}

			BreezeEvent maxHeightEvent = FtaUtil.constructFtaTree(this.arch);

			List<Set<String>> equalSet = FtaUtil.getEqualSet(maxHeightEvent, this.arch);
			return equalSet;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void run(IAction action) {
		if (this.getAbsolutePath() == null || this.getSelectedFileNames() == null)
			return;
		for (int i = 0; i < this.getSelectedFileNames().length; ++i) {
			if (this.getSelectedFileNames()[i] != null) {
				String fileName = this.getAbsolutePath() + this.getSelectedFileNames()[i];
				if (fileName.endsWith(SafetyEditor.FILE_EXTENSION)) {
					List<Set<BreezeEvent>> eventSet = new ArrayList<Set<BreezeEvent>>();
					List<Set<String>> eqSet = this.findEqualSet(fileName);

					for (Set<String> set : eqSet) {
						Set<BreezeEvent> evtSet = new HashSet<BreezeEvent>();
						for (String id : set) {
							evtSet.add(this.arch.getEvent(id));
						}
						eventSet.add(evtSet);
					} //
					try {
						FileOutputStream fos = new FileOutputStream("/home/goku/arch");
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						oos.writeObject(this.arch);
						oos.close();
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					//

					DlgFtaAnalysis dlg = new DlgFtaAnalysis(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
							DlgFtaAnalysis.TYPE_PATH_SET, eventSet);
					dlg.open();
					break;
				}
			}
		}
	}
}
