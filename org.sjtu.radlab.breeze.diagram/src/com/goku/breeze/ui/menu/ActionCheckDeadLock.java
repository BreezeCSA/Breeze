package com.goku.breeze.ui.menu;

import org.eclipse.jface.action.IAction;

import com.goku.breeze.ui.editor.CorrectnessEditor;
import com.goku.breeze.util.smv.SMVCheckDeadlock;

public class ActionCheckDeadLock extends ActionFilesSelection {

	@Override
	public void run(IAction action) {
		if (this.getAbsolutePath() == null || this.getSelectedFileNames() == null) {
			return;
		}

		for (int i = 0; i < this.getSelectedFileNames().length; ++i) {
			if (this.getSelectedFileNames()[i] != null) {
				String fileName = this.getAbsolutePath() + this.getSelectedFileNames()[i];
				if (fileName.endsWith(CorrectnessEditor.FILE_EXTENSION)) {
					SMVCheckDeadlock thread = new SMVCheckDeadlock(fileName, this.getAbsolutePath(), null);
					thread.start();
					break;
				}
			}
		}

	}
}
