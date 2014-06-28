package BreezeModel.diagram.application;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

/**
 * @generated
 */
public class DiagramEditorWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	/**
	 * @generated
	 */
	public DiagramEditorWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	/**
	 * @generated
	 */
	public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
		return new BreezeModel.diagram.application.DiagramEditorActionBarAdvisor(configurer);
	}

	/**
	 * @generated
	 */
	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(1000, 700));
		configurer.setTitle(BreezeModel.diagram.part.Messages.DiagramEditorWorkbenchWindowAdvisor_Title);
	}

}
