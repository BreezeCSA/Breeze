package BreezeModel.diagram.part;

import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocumentProvider;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.notation.impl.ShapeImpl;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.navigator.resources.ProjectExplorer;
import org.eclipse.ui.part.IShowInTargetList;

import BreezeModel.diagram.edit.parts.ArchEditPart;
import BreezeModel.diagram.edit.parts.ComponentEditPart;
import BreezeModel.diagram.edit.parts.PortEditPart;

import com.goku.breeze.common.BreezeGk;
import com.goku.breeze.util.DiagramEditorSelectionHelper;

/**
 * @generated
 */
public class BreezeDiagramEditor extends DiagramDocumentEditor {

	/**
	 * @generated
	 */
	public static final String CONTEXT_ID = "BreezeModel.diagram.ui.diagramContext"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final String ID = "BreezeModel.diagram.part.BreezeDiagramEditorID"; //$NON-NLS-1$

	public static String selectedPath;
	public static AbstractBorderedShapeEditPart selectedEditPart=null;
	//public static ShapeImpl selectedShape = null;

	public static String getSelectedPath() {
		return selectedPath;
	}

	/**
	 * @generated
	 */
	public BreezeDiagramEditor() {
		super(true);
	}

	/**
	 * @generated
	 */
	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		BreezeModel.diagram.part.DiagramEditorContextMenuProvider provider = new BreezeModel.diagram.part.DiagramEditorContextMenuProvider(
				this, this.getDiagramGraphicalViewer());
		this.getDiagramGraphicalViewer().setContextMenu(provider);
		this.getSite().registerContextMenu(ActionIds.DIAGRAM_EDITOR_CONTEXT_MENU, provider, this.getDiagramGraphicalViewer());
	}

	/**
	 * @generated NOT
	 */
	@Override
	protected PaletteRoot createPaletteRoot(PaletteRoot existingPaletteRoot) {
		PaletteRoot root = super.createPaletteRoot(existingPaletteRoot);
		BreezePaletteFactory factory = new BreezeModel.diagram.part.BreezePaletteFactory();
		IEditorInput obj = BreezeDiagramEditor.this.getEditorInput();
		if (obj instanceof URIEditorInput) {
			URIEditorInput input = (URIEditorInput) obj;
			String[] segments = input.getURI().segments();
			String trs=input.getURI().path();
			Boolean mark_pro=false;
			if(trs.indexOf("_production")!=-1&&trs.indexOf("_diagram")!=-1)
				mark_pro=true;
			if (segments.length - 2 >= 0) {
				if (BreezeGk.SAFETY.equals(segments[segments.length - 2])) {
					root.add(factory.createArchitectureStyle4Group());
					return root;
				}
				//if (segments.length>=4&&segments[5].indexOf("production") != -1) {
				if(mark_pro){	
				factory.fillPalette_s(root);
					
					return root;
				}
			}
		}
		factory.fillPalette(root);
		return root;
	}

	/**
	 * @generated
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public Object getAdapter(Class type) {
		if (type == IShowInTargetList.class) {
			return new IShowInTargetList() {
				@Override
				public String[] getShowInTargetIds() {
					return new String[] { ProjectExplorer.VIEW_ID };
				}
			};
		}
		return super.getAdapter(type);
	}

	/**
	 * @generated
	 */
	@Override
	protected String getContextID() {
		return CONTEXT_ID;
	}

	/**
	 * @generated
	 */
	@Override
	public String getContributorId() {
		return BreezeModel.diagram.part.BreezeDiagramEditorPlugin.ID;
	}

	/**
	 * @generated
	 */
	@Override
	protected IDocumentProvider getDocumentProvider(IEditorInput input) {
		if (input instanceof URIEditorInput) {
			return BreezeModel.diagram.part.BreezeDiagramEditorPlugin.getInstance().getDocumentProvider();
		}
		return super.getDocumentProvider(input);
	}

	/**
	 * @generated
	 */
	@Override
	public TransactionalEditingDomain getEditingDomain() {
		IDocument document = this.getEditorInput() != null ? this.getDocumentProvider().getDocument(this.getEditorInput()) : null;
		if (document instanceof IDiagramDocument) {
			return ((IDiagramDocument) document).getEditingDomain();
		}
		return super.getEditingDomain();
	}

	/**
	 * @generated
	 */
	@Override
	protected PreferencesHint getPreferencesHint() {
		return BreezeModel.diagram.part.BreezeDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT;
	}

	/**
	 * @generated NOT
	 */
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		super.selectionChanged(part, selection);
		//		EObject list = this.getDiagram().getElement();
		//		RootEditPart rootEditPart = this.getDiagramEditPart().getRoot();
		//		EList<EObject> elist = list.eContents();
		//		System.out.println(rootEditPart);
		//		List<EditPart> parts = rootEditPart.getChildren();
		//		for (EditPart ep : parts) {
		//			System.out.println(ep);
		//			System.out.println(ep.getModel());
		//		}
		//		System.out.println("----------------------------------------------------------");
		if (selection instanceof StructuredSelection) {
			StructuredSelection structSelection = (StructuredSelection) selection;
			Object element = structSelection.getFirstElement();
			if (element instanceof AbstractBorderedShapeEditPart)
			{
				selectedEditPart=(AbstractBorderedShapeEditPart) element;
			}
			if (element instanceof ArchEditPart) {
				ArchEditPart aep = (ArchEditPart) element;
				ShapeImpl si = (ShapeImpl) aep.getModel();
				EObject obj = si.basicGetElement();
				URI uri = EcoreUtil.getURI(obj);
				DiagramEditorSelectionHelper.select(this.getEditorInput().getName(), uri.fragment());
			} else if (element instanceof ComponentEditPart) {
				ComponentEditPart cep = (ComponentEditPart) element;

				ShapeImpl si = (ShapeImpl) cep.getModel();
				EObject obj = si.basicGetElement();
				URI uri = EcoreUtil.getURI(obj);
				DiagramEditorSelectionHelper.select(this.getEditorInput().getName(), uri.fragment());
			} else if (element instanceof PortEditPart) {
				PortEditPart pep = (PortEditPart) element;
				ShapeImpl si = (ShapeImpl) pep.getModel();
				EObject obj = si.basicGetElement();
				URI uri = EcoreUtil.getURI(obj);
				DiagramEditorSelectionHelper.select(this.getEditorInput().getName(), uri.fragment());
			}
		}

		//		for (int i = 0; i < elist.size(); ++i) {
		//			URI uri = EcoreUtil.getURI(elist.get(i));
		//			System.out.println(uri.fragment() + ":" + elist.get(i));
		//			this.getEditorInput().getName();
		//		}
	}

	/**
	 * @generated NOT
	 */
	@Override
	protected void setDocumentProvider(IEditorInput input) {
		if (input instanceof URIEditorInput) selectedPath = ((URIEditorInput) input).getURI().toString();
		if (input instanceof URIEditorInput) {
			this.setDocumentProvider(BreezeModel.diagram.part.BreezeDiagramEditorPlugin.getInstance().getDocumentProvider());
		} else {
			super.setDocumentProvider(input);
		}
	}

	public void setSelectedPath(String selectedPath) {
		this.selectedPath = selectedPath;
	}
}
