package BreezeModel.diagram.part;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

import com.goku.breeze.common.BreezeGk;

/**
 * @generated
 */
public class BreezeCreationWizard extends Wizard implements INewWizard {

	/**
	 * @generated
	 */
	protected Resource diagram;

	//	/**
	//	 * @generated
	//	 */
	//	protected BreezeModel.diagram.part.BreezeCreationWizardPage diagramModelFilePage;
	//
	//	/**
	//	 * @generated
	//	 */
	//	protected BreezeModel.diagram.part.BreezeCreationWizardPage domainModelFilePage;

	/**
	 * @generated NOT
	 */
	private BreezeCreationDiagramWizardPage fileNamePage;

	/**
	 * @generated
	 */
	private boolean openNewlyCreatedDiagramEditor = true;

	/**
	 * @generated NOT
	 */
	private WizardNewProjectCreationPage projectNamePage;

	/**
	 * @generated
	 */
	protected IStructuredSelection selection;

	/**
	 * @generated
	 */
	private IWorkbench workbench;

	/**
	 * @generated NOT
	 */
	@Override
	public void addPages() {
		this.projectNamePage = new WizardNewProjectCreationPage("basicNewProjectPage");
		this.projectNamePage.setDescription("Project Name");
		this.projectNamePage.setTitle("New Breeze Project");
		this.addPage(this.projectNamePage);

		this.fileNamePage = new BreezeCreationDiagramWizardPage(this.projectNamePage.getImage());
		this.addPage(this.fileNamePage);

		//		this.diagramModelFilePage = new BreezeModel.diagram.part.BreezeCreationWizardPage(
		//				"DiagramModelFile", this.getSelection(), "breeze_diagram"); //$NON-NLS-1$ //$NON-NLS-2$
		//		this.diagramModelFilePage.setTitle(BreezeModel.diagram.part.Messages.BreezeCreationWizard_DiagramModelFilePageTitle);
		//		this.diagramModelFilePage
		//				.setDescription(BreezeModel.diagram.part.Messages.BreezeCreationWizard_DiagramModelFilePageDescription);
		//		this.addPage(this.diagramModelFilePage);
		//
		//		this.domainModelFilePage = new BreezeModel.diagram.part.BreezeCreationWizardPage(
		//				"DomainModelFile", this.getSelection(), "breeze") { //$NON-NLS-1$ //$NON-NLS-2$
		//
		//			@Override
		//			public void setVisible(boolean visible) {
		//				if (visible) {
		//					String fileName = BreezeCreationWizard.this.diagramModelFilePage.getFileName();
		//					fileName = fileName.substring(0, fileName.length() - ".breeze_diagram".length()); //$NON-NLS-1$
		//					this.setFileName(BreezeModel.diagram.part.BreezeDiagramEditorUtil.getUniqueFileName(
		//							this.getContainerFullPath(), fileName, "breeze")); //$NON-NLS-1$
		//				}
		//				super.setVisible(visible);
		//			}
		//		};
		//		this.domainModelFilePage.setTitle(BreezeModel.diagram.part.Messages.BreezeCreationWizard_DomainModelFilePageTitle);
		//		this.domainModelFilePage
		//				.setDescription(BreezeModel.diagram.part.Messages.BreezeCreationWizard_DomainModelFilePageDescription);
		//		this.addPage(this.domainModelFilePage);
	}

	/**
	 * @generated
	 */
	public final Resource getDiagram() {
		return this.diagram;
	}

	/**
	 * @generated
	 */
	public IStructuredSelection getSelection() {
		return this.selection;
	}

	/**
	 * @generated
	 */
	public IWorkbench getWorkbench() {
		return this.workbench;
	}

	/**
	 * @generated
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		this.selection = selection;
		this.setWindowTitle(BreezeModel.diagram.part.Messages.BreezeCreationWizardTitle);
		this.setDefaultPageImageDescriptor(BreezeModel.diagram.part.BreezeDiagramEditorPlugin
				.getBundledImageDescriptor("icons/wizban/NewbreezeWizard.gif")); //$NON-NLS-1$
		this.setNeedsProgressMonitor(true);
	}

	/**
	 * @generated
	 */
	public final boolean isOpenNewlyCreatedDiagramEditor() {
		return this.openNewlyCreatedDiagramEditor;
	}

	/**
	 * @generated NOT
	 */
	@Override
	public boolean performFinish() {
		IRunnableWithProgress op = new IRunnableWithProgress() {

			@Override
			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				IProject prj = BreezeCreationWizard.this.projectNamePage.getProjectHandle();

				try {
					BreezeCreationWizard.this.diagram = BreezeModel.diagram.part.BreezeDiagramEditorUtil.createDiagram(prj,
							BreezeCreationWizard.this.fileNamePage.getFileName(), ".breeze", ".breeze_diagram", monitor);
					prj.getFolder(BreezeGk.SAFETY).create(true, true, monitor);
					prj.getFolder(BreezeGk.CORRECTNESS).create(true, true, monitor);
					prj.getFolder(BreezeGk.PERFORMANCE).create(true, true, monitor);
					prj.getFolder(BreezeGk.RELIABILITY).create(true, true, monitor);
					prj.getFolder(BreezeGk.PRODUCTION).create(true, true, monitor);
					//prj.getFolder("production\\test").create(true, true, monitor);
					prj.getFolder(BreezeGk.RELIABILITY+"\\RBD").create(true, true, monitor);
					prj.getFolder(BreezeGk.RELIABILITY+"\\Original DTMC").create(true, true, monitor);
					prj.getFolder(BreezeGk.RELIABILITY+"\\Extended DTMC").create(true, true, monitor);
				
				} catch (CoreException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (BreezeCreationWizard.this.isOpenNewlyCreatedDiagramEditor() && BreezeCreationWizard.this.diagram != null) {
					try {
						BreezeModel.diagram.part.BreezeDiagramEditorUtil.openDiagram(BreezeCreationWizard.this.diagram);
					} catch (PartInitException e) {
						ErrorDialog.openError(BreezeCreationWizard.this.getContainer().getShell(),
								BreezeModel.diagram.part.Messages.BreezeCreationWizardOpenEditorError, null, e.getStatus());
					}
				}
			}
		};
		try {
			this.getContainer().run(false, true, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			if (e.getTargetException() instanceof CoreException) {
				ErrorDialog.openError(this.getContainer().getShell(),
						BreezeModel.diagram.part.Messages.BreezeCreationWizardCreationError, null,
						((CoreException) e.getTargetException()).getStatus());
			} else {
				BreezeModel.diagram.part.BreezeDiagramEditorPlugin.getInstance().logError(
						"Error creating diagram", e.getTargetException()); //$NON-NLS-1$
			}
			return false;
		}
		return this.diagram != null;
	}

	/**
	 * @generated
	 */
	public void setOpenNewlyCreatedDiagramEditor(boolean openNewlyCreatedDiagramEditor) {
		this.openNewlyCreatedDiagramEditor = openNewlyCreatedDiagramEditor;
	}
}
