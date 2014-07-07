package com.goku.breeze.ui.menu;

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
import com.goku.breeze.util.BreezeModel2SARBD;
import com.goku.breeze.util.InitModelUtil;

public class InitRBDModel implements IObjectActionDelegate {
	private IPath selectedPath = null;
	private IWorkbenchPart targetPart = null;

	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		//		if (this.absPath != null && this.selectedFileNames != null) {
		//			for (int i = 0; i < this.selectedFileNames.length; ++i) {
		//				if (this.selectedFileNames[i] != null) {
		//					String fileName = this.absPath + this.selectedFileNames[i];
		//					if (fileName.endsWith(".breeze")) {
		//						String saFileName = fileName.substring(0, fileName.length() - "breeze".length())
		//								+ SafetyEditor.FILE_EXTENSION;
		//						BreezeModel2SA instance = new BreezeModel2SA();
		//						try {
		//							instance.convert(fileName, saFileName);
		//							ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);
		//						} catch (UnsupportedEncodingException e) {
		//							// TODO Auto-generated catch block
		//							e.printStackTrace();
		//						} catch (DocumentException e) {
		//							// TODO Auto-generated catch block
		//							e.printStackTrace();
		//						} catch (IOException e) {
		//							// TODO Auto-generated catch block
		//							e.printStackTrace();
		//						} catch (CoreException e) {
		//							// TODO Auto-generated catch block
		//							e.printStackTrace();
		//						}
		//					}
		//				}
		//			}
		//		}
//		System.out.println("rbdActive");
		String safetyModelFile = InitModelUtil.copy(this.selectedPath, BreezeGk.RELIABILITY+"\\RBD");
		if (safetyModelFile == null)
			return;
		String safetyFileString = safetyModelFile.substring(0, safetyModelFile.length() - "breeze".length()-"_production.".length())+"."
				+ "rbd_model";
		BreezeModel2SARBD instance = new BreezeModel2SARBD();
		try {
			instance.convert(safetyModelFile, safetyFileString);
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
