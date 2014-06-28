package com.goku.breeze.ui.menu;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.goku.breeze.common.BreezeGk;
import com.goku.breeze.ui.editor.SafetyEditor;
import com.goku.breeze.util.BreezeModel2SA;
import com.goku.breeze.util.InitModelUtil;

public class InitProduction implements IObjectActionDelegate {//Î´Íê´ýÐø¡£¡£¡£¡£¡£¡£
	private IPath selectedPath = null;
	private IWorkbenchPart targetPart = null;

	@Override
	public void run(IAction action) {
		String ProductionFile = InitModelUtil.copy(this.selectedPath, BreezeGk.PRODUCTION);
		if (ProductionFile == null)
			return;
		String ProductionFileString = ProductionFile.substring(0, ProductionFile.length() - "breeze".length())
				+ "production";
//		File fl=new File(ProductionFile+"_diagram");
//	    int index = fl.getPath().indexOf("breeze_diagram");
//        String ext = fl.getPath().substring(0,index);
//        fl.renameTo(new File(ext+"production"));
		BreezeModel2SA instance = new BreezeModel2SA();
		try {
			//instance.convert(ProductionFile, ProductionFileString);
			ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			MessageBox msgDlg = new MessageBox(Display.getCurrent().getActiveShell(), SWT.ICON_INFORMATION | SWT.OK);
			msgDlg.setMessage("Can't initialize model file");
			msgDlg.setText("Initialization:");
			msgDlg.open();
			return;
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		if (!selection.isEmpty() && selection instanceof TreeSelection) {
			TreeSelection treeSelection = (TreeSelection) selection;
			TreePath[] paths = treeSelection.getPaths();
			for (int i = 0; i < paths.length; ++i) {
				if (paths[i] != null) {
					Object obj = paths[i].getLastSegment();
					if (obj instanceof org.eclipse.core.internal.resources.File) {
						this.selectedPath = ((org.eclipse.core.internal.resources.File) obj).getRawLocation();
					} else continue;
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
				this.selectedPath = file.getRawLocation();
			}
		}
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// TODO Auto-generated method stub
		this.targetPart = targetPart;
	}

}
