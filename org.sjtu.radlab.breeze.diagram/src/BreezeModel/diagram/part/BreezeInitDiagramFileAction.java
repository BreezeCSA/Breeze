package BreezeModel.diagram.part;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

/**
 * @generated
 */
public class BreezeInitDiagramFileAction implements IWorkbenchWindowActionDelegate {

	/**
	 * @generated
	 */
	private IWorkbenchWindow window;

	/**
	 * @generated
	 */
	@Override
	public void dispose() {
		this.window = null;
	}

	/**
	 * @generated
	 */
	private Shell getShell() {
		return this.window.getShell();
	}

	/**
	 * @generated
	 */
	@Override
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	/**
	 * @generated
	 */
	@Override
	public void run(IAction action) {
		TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain();
		Resource resource = BreezeModel.diagram.part.BreezeDiagramEditorUtil.openModel(this.getShell(),
				BreezeModel.diagram.part.Messages.InitDiagramFile_OpenModelFileDialogTitle, editingDomain);
		if (resource == null || resource.getContents().isEmpty()) {
			return;
		}
		EObject diagramRoot = resource.getContents().get(0);
		Wizard wizard = new BreezeModel.diagram.part.BreezeNewDiagramFileWizard(resource.getURI(), diagramRoot, editingDomain);
		wizard.setWindowTitle(NLS.bind(BreezeModel.diagram.part.Messages.InitDiagramFile_WizardTitle,
				BreezeModel.diagram.edit.parts.BreezeEditPart.MODEL_ID));
		BreezeModel.diagram.part.BreezeDiagramEditorUtil.runWizard(this.getShell(), wizard, "InitDiagramFile"); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
	}
}
