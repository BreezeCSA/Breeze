package BreezeModel.diagram.part;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.ContributionItemService;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.providers.DiagramContextMenuProvider;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @generated
 */
public class DiagramEditorContextMenuProvider extends DiagramContextMenuProvider {

	/**
	 * @generated
	 */
	private BreezeModel.diagram.part.DeleteElementAction deleteAction;

	/**
	 * @generated
	 */
	private IWorkbenchPart part;

	/**
	 * @generated
	 */
	public DiagramEditorContextMenuProvider(IWorkbenchPart part, EditPartViewer viewer) {
		super(part, viewer);
		this.part = part;
		this.deleteAction = new BreezeModel.diagram.part.DeleteElementAction(part);
		this.deleteAction.init();
	}

	/**
	 * @generated
	 */
	@Override
	public void buildContextMenu(final IMenuManager menu) {
		this.getViewer().flush();
		try {
			TransactionUtil.getEditingDomain((EObject) this.getViewer().getContents().getModel()).runExclusive(new Runnable() {

				@Override
				public void run() {
					ContributionItemService.getInstance().contributeToPopupMenu(DiagramEditorContextMenuProvider.this,
							DiagramEditorContextMenuProvider.this.part);

					menu.remove(ActionIds.ACTION_DELETE_FROM_MODEL);

					menu.appendToGroup("editGroup", DiagramEditorContextMenuProvider.this.deleteAction);
				}
			});
		} catch (Exception e) {
			BreezeModel.diagram.part.BreezeDiagramEditorPlugin.getInstance().logError("Error building context menu", e);
		}
	}

	/**
	 * @generated
	 */
	@Override
	public void dispose() {
		if (this.deleteAction != null) {
			this.deleteAction.dispose();
			this.deleteAction = null;
		}
		super.dispose();
	}
}
