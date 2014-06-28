package BreezeModel.diagram.application;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.internal.ide.model.WorkbenchAdapterBuilder;

import BreezeModel.diagram.part.BreezeDiagramEditorPlugin;

/**
 * @generated
 */
public class DiagramEditorWorkbenchAdvisor extends WorkbenchAdvisor {
	/**
	 * @generated
	 */
	public static final String PERSPECTIVE_ID = "BreezeModel.diagram.BreezePerspective"; //$NON-NLS-1$

	/**
	 * @generated NOT
	 */
	@Override
	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		WorkbenchAdapterBuilder.registerAdapters();
		return new BreezeModel.diagram.application.DiagramEditorWorkbenchWindowAdvisor(configurer);
	}

	@Override
	public IAdaptable getDefaultPageInput() {
		return ResourcesPlugin.getWorkspace().getRoot();
	}

	/**
	 * @generated
	 */
	@Override
	public String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}

	/**
	 * @generated NOT
	 */
	@Override
	public void initialize(IWorkbenchConfigurer configurer) {
		configurer.declareImage(IDE.SharedImages.IMG_OBJ_PROJECT,
				BreezeDiagramEditorPlugin.getBundledImageDescriptor("icons/obj16/projectOpenIcon.png"), true);
		configurer.declareImage(IDE.SharedImages.IMG_OBJ_PROJECT_CLOSED,
				BreezeDiagramEditorPlugin.getBundledImageDescriptor("icons/obj16/projectCloseIcon.png"), true);
		super.initialize(configurer);
		configurer.setSaveAndRestore(true);
		IDE.registerAdapters();
	}

}
