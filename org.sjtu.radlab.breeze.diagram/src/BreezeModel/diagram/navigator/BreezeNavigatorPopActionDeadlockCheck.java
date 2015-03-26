package BreezeModel.diagram.navigator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class BreezeNavigatorPopActionDeadlockCheck implements
		IObjectActionDelegate {

	
	private String absPath = null;
	private String[] selectedFileNames = null;
	private IWorkbenchPart targetPart = null;

	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		if (this.absPath != null && this.selectedFileNames != null) {
			for (int i = 0; i < this.selectedFileNames.length; ++i) {
				if (this.selectedFileNames[i] != null) {
					this.selectedFileNames[i] = this.absPath + this.selectedFileNames[i];
				}
			}
		}

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
			if (obj == null)
				return;
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

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// TODO Auto-generated method stub
		this.targetPart = targetPart;
	}

}
