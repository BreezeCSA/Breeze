package BreezeModel.diagram.navigator;

import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocument;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.navigator.ILinkHelper;

/**
 * @generated
 */
public class BreezeNavigatorLinkHelper implements ILinkHelper {

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
	public IStructuredSelection findSelection(IEditorInput anInput) {
		IDiagramDocument document = BreezeModel.diagram.part.BreezeDiagramEditorPlugin.getInstance().getDocumentProvider()
				.getDiagramDocument(anInput);
		return StructuredSelection.EMPTY;
	}

	/**
	 * @generated
	 */
	public void activateEditor(IWorkbenchPage aPage, IStructuredSelection aSelection) {
		if (aSelection == null || aSelection.isEmpty()) {
			return;
		}
		if (false == aSelection.getFirstElement() instanceof BreezeModel.diagram.navigator.BreezeAbstractNavigatorItem) {
			return;
		}

		BreezeModel.diagram.navigator.BreezeAbstractNavigatorItem abstractNavigatorItem = (BreezeModel.diagram.navigator.BreezeAbstractNavigatorItem) aSelection
				.getFirstElement();
		View navigatorView = null;
		if (abstractNavigatorItem instanceof BreezeModel.diagram.navigator.BreezeNavigatorItem) {
			navigatorView = ((BreezeModel.diagram.navigator.BreezeNavigatorItem) abstractNavigatorItem).getView();
		} else if (abstractNavigatorItem instanceof BreezeModel.diagram.navigator.BreezeNavigatorGroup) {
			BreezeModel.diagram.navigator.BreezeNavigatorGroup navigatorGroup = (BreezeModel.diagram.navigator.BreezeNavigatorGroup) abstractNavigatorItem;
			if (navigatorGroup.getParent() instanceof BreezeModel.diagram.navigator.BreezeNavigatorItem) {
				navigatorView = ((BreezeModel.diagram.navigator.BreezeNavigatorItem) navigatorGroup.getParent()).getView();
			}
		}
		if (navigatorView == null) {
			return;
		}
		IEditorInput editorInput = getEditorInput(navigatorView.getDiagram());
		IEditorPart editor = aPage.findEditor(editorInput);
		if (editor == null) {
			return;
		}
		aPage.bringToTop(editor);
		if (editor instanceof DiagramEditor) {
			DiagramEditor diagramEditor = (DiagramEditor) editor;
			ResourceSet diagramEditorResourceSet = diagramEditor.getEditingDomain().getResourceSet();
			EObject selectedView = diagramEditorResourceSet.getEObject(EcoreUtil.getURI(navigatorView), true);
			if (selectedView == null) {
				return;
			}
			GraphicalViewer graphicalViewer = (GraphicalViewer) diagramEditor.getAdapter(GraphicalViewer.class);
			EditPart selectedEditPart = (EditPart) graphicalViewer.getEditPartRegistry().get(selectedView);
			if (selectedEditPart != null) {
				graphicalViewer.select(selectedEditPart);
			}
		}
	}

}
