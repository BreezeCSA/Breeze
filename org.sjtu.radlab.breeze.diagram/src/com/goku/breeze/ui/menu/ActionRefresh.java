package com.goku.breeze.ui.menu;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;

public class ActionRefresh extends ActionFilesSelection {

	public ActionRefresh() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(IAction action) {
		if (this.getAbsolutePath() == null) return;
		int start = this.getAbsolutePath().length() - 1;
		while (start >= 0 && this.getAbsolutePath().charAt(start--) != '/')
			;
		String prjName = this.getAbsolutePath().substring(start + 1);
		try {
			ResourcesPlugin.getWorkspace().getRoot().getProject(prjName).refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
