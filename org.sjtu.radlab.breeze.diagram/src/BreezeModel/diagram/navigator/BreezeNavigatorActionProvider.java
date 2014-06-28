package BreezeModel.diagram.navigator;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

/**
 * @generated
 */
public class BreezeNavigatorActionProvider extends CommonActionProvider {

	/**
	 * @generated
	 */
	private static class OpenDiagramAction extends Action {

		/**
		 * @generated
		 */
		private static IEditorInput getEditorInput(Diagram diagram) {
			Resource diagramResource = diagram.eResource();
			URI uri = EcoreUtil.getURI(diagram);
			String editorName = uri.lastSegment() + '#' + diagram.eResource().getContents().indexOf(diagram);
			IEditorInput editorInput = new URIEditorInput(uri, editorName);
			return editorInput;
		}

		/**
		 * @generated
		 */
		private Diagram myDiagram;

		/**
		 * @generated
		 */
		private final ICommonViewerWorkbenchSite myViewerSite;

		/**
		 * @generated
		 */
		public OpenDiagramAction(ICommonViewerWorkbenchSite viewerSite) {
			super(BreezeModel.diagram.part.Messages.NavigatorActionProvider_OpenDiagramActionName);
			this.myViewerSite = viewerSite;
		}

		/**
		 * @generated
		 */
		@Override
		public void run() {
			if (this.myDiagram == null || this.myDiagram.eResource() == null) {
				return;
			}

			IEditorInput editorInput = getEditorInput(this.myDiagram);
			IWorkbenchPage page = this.myViewerSite.getPage();
			try {
				page.openEditor(editorInput, BreezeModel.diagram.part.BreezeDiagramEditor.ID);
			} catch (PartInitException e) {
				BreezeModel.diagram.part.BreezeDiagramEditorPlugin.getInstance().logError("Exception while openning diagram", e); //$NON-NLS-1$
			}
		}

		/**
		 * @generated
		 */
		public final void selectionChanged(IStructuredSelection selection) {
			this.myDiagram = null;
			if (selection.size() == 1) {
				Object selectedElement = selection.getFirstElement();
				if (selectedElement instanceof BreezeModel.diagram.navigator.BreezeNavigatorItem) {
					selectedElement = ((BreezeModel.diagram.navigator.BreezeNavigatorItem) selectedElement).getView();
				} else if (selectedElement instanceof IAdaptable) {
					selectedElement = ((IAdaptable) selectedElement).getAdapter(View.class);
				}
				if (selectedElement instanceof Diagram) {
					Diagram diagram = (Diagram) selectedElement;
					if (BreezeModel.diagram.edit.parts.BreezeEditPart.MODEL_ID
							.equals(BreezeModel.diagram.part.BreezeVisualIDRegistry.getModelID(diagram))) {
						this.myDiagram = diagram;
					}
				}
			}
			this.setEnabled(this.myDiagram != null);
		}

	}

	/**
	 * @generated
	 */
	private boolean myContribute;

	/**
	 * @generated
	 */
	private OpenDiagramAction myOpenDiagramAction;

	/**
	 * @generated
	 */
	@Override
	public void fillActionBars(IActionBars actionBars) {
		if (!this.myContribute) {
			return;
		}
		IStructuredSelection selection = (IStructuredSelection) this.getContext().getSelection();
		this.myOpenDiagramAction.selectionChanged(selection);
		if (this.myOpenDiagramAction.isEnabled()) {
			actionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, this.myOpenDiagramAction);
		}
	}

	/**
	 * @generated
	 */
	@Override
	public void fillContextMenu(IMenuManager menu) {
		menu.appendToGroup(ICommonMenuConstants.GROUP_OPEN, this.myOpenDiagramAction);
	}

	/**
	 * @generated
	 */
	@Override
	public void init(ICommonActionExtensionSite aSite) {
		super.init(aSite);
		if (aSite.getViewSite() instanceof ICommonViewerWorkbenchSite) {
			this.myContribute = true;
			this.makeActions((ICommonViewerWorkbenchSite) aSite.getViewSite());
		} else {
			this.myContribute = false;
		}
	}

	/**
	 * @generated
	 */
	private void makeActions(ICommonViewerWorkbenchSite viewerSite) {
		this.myOpenDiagramAction = new OpenDiagramAction(viewerSite);
	}

}
