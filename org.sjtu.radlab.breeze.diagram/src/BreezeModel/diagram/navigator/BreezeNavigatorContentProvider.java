package BreezeModel.diagram.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;

/**
 * @generated
 */
public class BreezeNavigatorContentProvider implements ICommonContentProvider {

	/**
	 * @generated
	 */
	private static final Object[] EMPTY_ARRAY = new Object[0];

	/**
	 * @generated
	 */
	private AdapterFactoryEditingDomain myEditingDomain;

	/**
	 * @generated
	 */
	private Viewer myViewer;

	/**
	 * @generated
	 */
	private Runnable myViewerRefreshRunnable;

	/**
	 * @generated
	 */
	private WorkspaceSynchronizer myWorkspaceSynchronizer;

	/**
	 * @generated
	 */
	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	public BreezeNavigatorContentProvider() {
		TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain();
		this.myEditingDomain = (AdapterFactoryEditingDomain) editingDomain;
		this.myEditingDomain.setResourceToReadOnlyMap(new HashMap() {
			@Override
			public Object get(Object key) {
				if (!this.containsKey(key)) {
					this.put(key, Boolean.TRUE);
				}
				return super.get(key);
			}
		});
		this.myViewerRefreshRunnable = new Runnable() {
			@Override
			public void run() {
				if (BreezeNavigatorContentProvider.this.myViewer != null) {
					BreezeNavigatorContentProvider.this.myViewer.refresh();
				}
			}
		};
		this.myWorkspaceSynchronizer = new WorkspaceSynchronizer(editingDomain, new WorkspaceSynchronizer.Delegate() {
			@Override
			public void dispose() {
			}

			@Override
			public boolean handleResourceChanged(final Resource resource) {
				BreezeNavigatorContentProvider.this.unloadAllResources();
				BreezeNavigatorContentProvider.this.asyncRefresh();
				return true;
			}

			@Override
			public boolean handleResourceDeleted(Resource resource) {
				BreezeNavigatorContentProvider.this.unloadAllResources();
				BreezeNavigatorContentProvider.this.asyncRefresh();
				return true;
			}

			@Override
			public boolean handleResourceMoved(Resource resource, final URI newURI) {
				BreezeNavigatorContentProvider.this.unloadAllResources();
				BreezeNavigatorContentProvider.this.asyncRefresh();
				return true;
			}
		});
	}

	/**
	 * @generated
	 */
	void asyncRefresh() {
		if (this.myViewer != null && !this.myViewer.getControl().isDisposed()) {
			this.myViewer.getControl().getDisplay().asyncExec(this.myViewerRefreshRunnable);
		}
	}

	/**
	 * @generated
	 */
	private Collection<BreezeModel.diagram.navigator.BreezeNavigatorItem> createNavigatorItems(Collection<View> views,
			Object parent, boolean isLeafs) {
		ArrayList<BreezeModel.diagram.navigator.BreezeNavigatorItem> result = new ArrayList<BreezeModel.diagram.navigator.BreezeNavigatorItem>(
				views.size());
		for (View nextView : views) {
			result.add(new BreezeModel.diagram.navigator.BreezeNavigatorItem(nextView, parent, isLeafs));
		}
		return result;
	}

	/**
	 * @generated
	 */
	@Override
	public void dispose() {
		this.myWorkspaceSynchronizer.dispose();
		this.myWorkspaceSynchronizer = null;
		this.myViewerRefreshRunnable = null;
		this.myViewer = null;
		this.unloadAllResources();
		((TransactionalEditingDomain) this.myEditingDomain).dispose();
		this.myEditingDomain = null;
	}

	/**
	 * @generated
	 */
	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof IFile) {
			IFile file = (IFile) parentElement;
			URI fileURI = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
			Resource resource = this.myEditingDomain.getResourceSet().getResource(fileURI, true);
			ArrayList<BreezeModel.diagram.navigator.BreezeNavigatorItem> result = new ArrayList<BreezeModel.diagram.navigator.BreezeNavigatorItem>();
			ArrayList<View> topViews = new ArrayList<View>(resource.getContents().size());
			for (EObject o : resource.getContents()) {
				if (o instanceof View) {
					topViews.add((View) o);
				}
			}
			return result.toArray();
		}

		if (parentElement instanceof BreezeModel.diagram.navigator.BreezeNavigatorGroup) {
			BreezeModel.diagram.navigator.BreezeNavigatorGroup group = (BreezeModel.diagram.navigator.BreezeNavigatorGroup) parentElement;
			return group.getChildren();
		}

		if (parentElement instanceof BreezeModel.diagram.navigator.BreezeNavigatorItem) {
			BreezeModel.diagram.navigator.BreezeNavigatorItem navigatorItem = (BreezeModel.diagram.navigator.BreezeNavigatorItem) parentElement;
			if (navigatorItem.isLeaf() || !this.isOwnView(navigatorItem.getView())) {
				return EMPTY_ARRAY;
			}
			return this.getViewChildren(navigatorItem.getView(), parentElement);
		}

		return EMPTY_ARRAY;
	}

	/**
	 * @generated
	 */
	private Collection<View> getChildrenByType(Collection<? extends View> nodes, String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (View nextNode : nodes) {
			result.addAll(this.selectViewsByType(nextNode.getChildren(), type));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getDiagramLinksByType(Collection<Diagram> diagrams, String type) {
		ArrayList<View> result = new ArrayList<View>();
		for (Diagram nextDiagram : diagrams) {
			result.addAll(this.selectViewsByType(nextDiagram.getEdges(), type));
		}
		return result;
	}

	/**
	 * @generated
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		return this.getChildren(inputElement);
	}

	/**
	 * @generated
	 */
	private Collection<View> getIncomingLinksByType(Collection<? extends View> nodes, String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (View nextNode : nodes) {
			result.addAll(this.selectViewsByType(nextNode.getTargetEdges(), type));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getLinksSourceByType(Collection<Edge> edges, String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (Edge nextEdge : edges) {
			View nextEdgeSource = nextEdge.getSource();
			if (type.equals(nextEdgeSource.getType()) && this.isOwnView(nextEdgeSource)) {
				result.add(nextEdgeSource);
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getLinksTargetByType(Collection<Edge> edges, String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (Edge nextEdge : edges) {
			View nextEdgeTarget = nextEdge.getTarget();
			if (type.equals(nextEdgeTarget.getType()) && this.isOwnView(nextEdgeTarget)) {
				result.add(nextEdgeTarget);
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getOutgoingLinksByType(Collection<? extends View> nodes, String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (View nextNode : nodes) {
			result.addAll(this.selectViewsByType(nextNode.getSourceEdges(), type));
		}
		return result;
	}

	/**
	 * @generated
	 */
	@Override
	public Object getParent(Object element) {
		if (element instanceof BreezeModel.diagram.navigator.BreezeAbstractNavigatorItem) {
			BreezeModel.diagram.navigator.BreezeAbstractNavigatorItem abstractNavigatorItem = (BreezeModel.diagram.navigator.BreezeAbstractNavigatorItem) element;
			return abstractNavigatorItem.getParent();
		}
		return null;
	}

	/**
	 * @generated
	 */
	private Object[] getViewChildren(View view, Object parentElement) {
		switch (BreezeModel.diagram.part.BreezeVisualIDRegistry.getVisualID(view)) {

		case BreezeModel.diagram.edit.parts.BreezeEditPart.VISUAL_ID: {
			LinkedList<BreezeModel.diagram.navigator.BreezeAbstractNavigatorItem> result = new LinkedList<BreezeModel.diagram.navigator.BreezeAbstractNavigatorItem>();
			Diagram sv = (Diagram) view;
			Collection<View> connectedViews;
			connectedViews = this.getChildrenByType(Collections.singleton(sv), BreezeModel.diagram.part.BreezeVisualIDRegistry
					.getType(BreezeModel.diagram.edit.parts.GokuEditPart.VISUAL_ID));
			result.addAll(this.createNavigatorItems(connectedViews, parentElement, false));
			return result.toArray();
		}
		}
		return EMPTY_ARRAY;
	}

	/**
	 * @generated
	 */
	@Override
	public boolean hasChildren(Object element) {
		return element instanceof IFile || this.getChildren(element).length > 0;
	}

	/**
	 * @generated
	 */
	@Override
	public void init(ICommonContentExtensionSite aConfig) {
	}

	/**
	 * @generated
	 */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.myViewer = viewer;
	}

	/**
	 * @generated
	 */
	private boolean isOwnView(View view) {
		return BreezeModel.diagram.edit.parts.BreezeEditPart.MODEL_ID.equals(BreezeModel.diagram.part.BreezeVisualIDRegistry
				.getModelID(view));
	}

	/**
	 * @generated
	 */
	@Override
	public void restoreState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	@Override
	public void saveState(IMemento aMemento) {
	}

	// TODO refactor as static method
	/**
	 * @generated
	 */
	private Collection<View> selectViewsByType(Collection<View> views, String type) {
		ArrayList<View> result = new ArrayList<View>();
		for (View nextView : views) {
			if (type.equals(nextView.getType()) && this.isOwnView(nextView)) {
				result.add(nextView);
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	void unloadAllResources() {
		for (Resource nextResource : this.myEditingDomain.getResourceSet().getResources()) {
			nextResource.unload();
		}
	}

}
