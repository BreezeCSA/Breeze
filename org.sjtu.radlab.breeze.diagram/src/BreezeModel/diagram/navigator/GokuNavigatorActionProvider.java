package BreezeModel.diagram.navigator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerSite;

public class GokuNavigatorActionProvider extends CommonActionProvider {
	private Action propertyAction;
	private ICommonViewerSite viewSite;

	@Override
	public void fillContextMenu(IMenuManager menu) {
		menu.add(this.propertyAction);
	}

	@Override
	public void init(ICommonActionExtensionSite site) {
		super.init(site);
		this.viewSite = site.getViewSite();
		this.propertyAction = new Action("Show Property") {
			@Override
			public void run() {
				//				IStructuredSelection selection = (IStructuredSelection) GokuNavigatorActionProvider.this.viewSite
				//						.getSelectionProvider().getSelection();
				//				Object firstElement = selection.getFirstElement();
				//
				//				MessageDialog.openInformation(GokuNavigatorActionProvider.this.viewSite.getShell(), "Property", "hello,Goku");
				IProgressMonitor progressMonitor = new NullProgressMonitor();
				IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

				IProject project = root.getProject("MyProject");

				try {
					project.create(progressMonitor);
					project.open(progressMonitor);
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		};
	}

}
