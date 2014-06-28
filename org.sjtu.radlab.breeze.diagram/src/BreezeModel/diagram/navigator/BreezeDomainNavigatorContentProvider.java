package BreezeModel.diagram.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;

/**
 * @generated
 */
public class BreezeDomainNavigatorContentProvider implements ICommonContentProvider {

	/**
	 * @generated
	 */
	private static final Object[] EMPTY_ARRAY = new Object[0];

	/**
	 * @generated
	 */
	private AdapterFactoryContentProvider myAdapterFctoryContentProvier;

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
	public BreezeDomainNavigatorContentProvider() {
		this.myAdapterFctoryContentProvier = new AdapterFactoryContentProvider(BreezeModel.diagram.part.BreezeDiagramEditorPlugin
				.getInstance().getItemProvidersAdapterFactory());
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
				if (BreezeDomainNavigatorContentProvider.this.myViewer != null) {
					BreezeDomainNavigatorContentProvider.this.myViewer.refresh();
				}
			}
		};
		this.myWorkspaceSynchronizer = new WorkspaceSynchronizer(editingDomain, new WorkspaceSynchronizer.Delegate() {
			@Override
			public void dispose() {
			}

			@Override
			public boolean handleResourceChanged(final Resource resource) {
				BreezeDomainNavigatorContentProvider.this.unloadAllResources();
				BreezeDomainNavigatorContentProvider.this.asyncRefresh();
				return true;
			}

			@Override
			public boolean handleResourceDeleted(Resource resource) {
				BreezeDomainNavigatorContentProvider.this.unloadAllResources();
				BreezeDomainNavigatorContentProvider.this.asyncRefresh();
				return true;
			}

			@Override
			public boolean handleResourceMoved(Resource resource, final URI newURI) {
				BreezeDomainNavigatorContentProvider.this.unloadAllResources();
				BreezeDomainNavigatorContentProvider.this.asyncRefresh();
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
			return this.wrapEObjects(this.myAdapterFctoryContentProvier.getChildren(resource), parentElement);
		}

		if (parentElement instanceof BreezeModel.diagram.navigator.BreezeDomainNavigatorItem) {
			return this.wrapEObjects(this.myAdapterFctoryContentProvier
					.getChildren(((BreezeModel.diagram.navigator.BreezeDomainNavigatorItem) parentElement).getEObject()),
					parentElement);
		}
		return EMPTY_ARRAY;
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
	@Override
	public void restoreState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	@Override
	public void saveState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	void unloadAllResources() {
		for (Resource nextResource : this.myEditingDomain.getResourceSet().getResources()) {
			nextResource.unload();
		}
	}

	/**
	 * @generated
	 */
	public Object[] wrapEObjects(Object[] objects, Object parentElement) {
		Collection result = new ArrayList();
		for (int i = 0; i < objects.length; i++) {
			if (objects[i] instanceof EObject) {
				result.add(new BreezeModel.diagram.navigator.BreezeDomainNavigatorItem((EObject) objects[i], parentElement,
						this.myAdapterFctoryContentProvier));
			}
		}
		return result.toArray();
	}

}
