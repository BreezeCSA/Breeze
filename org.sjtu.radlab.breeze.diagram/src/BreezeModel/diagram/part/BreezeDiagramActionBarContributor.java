package BreezeModel.diagram.part;

import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramActionBarContributor;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;

/**
 * @generated
 */
public class BreezeDiagramActionBarContributor extends DiagramActionBarContributor {

	/**
	 * @generated
	 */
	protected Class getEditorClass() {
		return BreezeModel.diagram.part.BreezeDiagramEditor.class;
	}

	/**
	 * @generated
	 */
	protected String getEditorId() {
		return BreezeModel.diagram.part.BreezeDiagramEditor.ID;
	}

	/**
	 * @generated
	 */
	public void init(IActionBars bars, IWorkbenchPage page) {
		super.init(bars, page);
		// print preview
		IMenuManager fileMenu = bars.getMenuManager().findMenuUsingPath(IWorkbenchActionConstants.M_FILE);
		assert fileMenu != null;
		fileMenu.remove("pageSetupAction"); //$NON-NLS-1$
		IMenuManager editMenu = bars.getMenuManager().findMenuUsingPath(IWorkbenchActionConstants.M_EDIT);
		assert editMenu != null;
		if (editMenu.find("validationGroup") == null) { //$NON-NLS-1$
			editMenu.add(new GroupMarker("validationGroup")); //$NON-NLS-1$
		}
		IAction validateAction = new BreezeModel.diagram.part.ValidateAction(page);
		editMenu.appendToGroup("validationGroup", validateAction); //$NON-NLS-1$
	}
}
