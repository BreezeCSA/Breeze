package com.goku.breeze.ui.menu;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class ActionFilesSelection implements IObjectActionDelegate {
	private String absPath = null;
	private String[] selectedFileNames = null;
	private final IWorkbenchPart targetPart = null;

	public String getAbsolutePath() {
		return this.absPath;
	}

	public String[] getSelectedFileNames() {
		return this.selectedFileNames;
	}

	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		if (!selection.isEmpty() && selection instanceof TreeSelection) {
			TreeSelection treeSelection = (TreeSelection) selection;
			TreePath[] paths = treeSelection.getPaths();
			this.selectedFileNames = new String[paths.length];
			for (int i = 0; i < paths.length; ++i) {
				if (paths[i] != null) {
					Object obj = paths[i].getLastSegment();
					if (obj instanceof org.eclipse.core.internal.resources.File) {
						this.selectedFileNames[i] = ((org.eclipse.core.internal.resources.File) obj).getName();
					} else this.selectedFileNames[i] = null;
				}
			}
		}

		if (selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
			Object obj = ssel.getFirstElement();
			if (obj == null) return;
			if (obj instanceof org.eclipse.core.internal.resources.Project) {
				IProject project = (IProject) Platform.getAdapterManager().getAdapter(obj, IProject.class);
				if (project == null) {
					project = (IProject) ((IAdaptable) obj).getAdapter(IProject.class);
				}
				if (project != null) {
					this.absPath = project.getLocation().toString();
				}
			} else if (obj instanceof org.eclipse.core.internal.resources.File) {
				IFile file = (IFile) Platform.getAdapterManager().getAdapter(obj, IFile.class);

				if (file == null) {
					if (obj instanceof IAdaptable) {
						file = (IFile) ((IAdaptable) obj).getAdapter(IFile.class);
					}
				}

				if (file != null) {
					this.absPath = file.getLocation().removeLastSegments(1).addTrailingSeparator().toString();
				}
			}
		}
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// TODO Auto-generated method stub

	}

}
