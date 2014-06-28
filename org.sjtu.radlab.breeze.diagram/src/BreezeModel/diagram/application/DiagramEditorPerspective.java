package BreezeModel.diagram.application;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;

import com.goku.breeze.ui.console.ConsoleFactory;

/**
 * @generated
 */
public class DiagramEditorPerspective implements IPerspectiveFactory {
	/**
	 * @generated NOT
	 */
	@Override
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(true);
		layout.addPerspectiveShortcut(BreezeModel.diagram.application.DiagramEditorWorkbenchAdvisor.PERSPECTIVE_ID);
		IFolderLayout left = layout.createFolder("left", IPageLayout.LEFT, 0.2f, layout.getEditorArea());
		left.addView("com.goku.breeze.ui.navigator.view");
		IFolderLayout bottom = layout.createFolder("bottom", IPageLayout.BOTTOM, 0.7f, layout.getEditorArea());
		bottom.addView(IConsoleConstants.ID_CONSOLE_VIEW);
		ConsoleFactory factory = new ConsoleFactory();
		factory.openConsole();
		factory.getConsole().activate();
		IFolderLayout right = layout.createFolder("right", IPageLayout.RIGHT, 0.6f, layout.getEditorArea()); //$NON-NLS-1$
		right.addView(IPageLayout.ID_OUTLINE);
		IFolderLayout bottomRight = layout.createFolder("bottomRight", IPageLayout.BOTTOM, 0.6f, "right"); //$NON-NLS-1$	 //$NON-NLS-2$
		bottomRight.addView(IPageLayout.ID_PROP_SHEET);
	}
}
