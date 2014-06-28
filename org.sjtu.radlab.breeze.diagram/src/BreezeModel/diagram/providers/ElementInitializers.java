package BreezeModel.diagram.providers;

/**
 * @generated
 */
public class ElementInitializers {

	protected ElementInitializers() {
		// use #getInstance to access cached instance
	}

	/**
	 * @generated
	 */
	public static ElementInitializers getInstance() {
		ElementInitializers cached = BreezeModel.diagram.part.BreezeDiagramEditorPlugin.getInstance().getElementInitializers();
		if (cached == null) {
			BreezeModel.diagram.part.BreezeDiagramEditorPlugin.getInstance().setElementInitializers(
					cached = new ElementInitializers());
		}
		return cached;
	}
}
