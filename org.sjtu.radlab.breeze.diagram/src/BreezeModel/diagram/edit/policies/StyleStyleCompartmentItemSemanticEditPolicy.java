package BreezeModel.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

/**
 * @generated
 */
public class StyleStyleCompartmentItemSemanticEditPolicy extends
		BreezeModel.diagram.edit.policies.BreezeBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public StyleStyleCompartmentItemSemanticEditPolicy() {
		super(BreezeModel.diagram.providers.BreezeElementTypes.Style_2002);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (BreezeModel.diagram.providers.BreezeElementTypes.Template_3010 == req.getElementType()) {
			return getGEFWrapper(new BreezeModel.diagram.edit.commands.TemplateCreateCommand(req));
		}
		if (BreezeModel.diagram.providers.BreezeElementTypes.Production_3011 == req.getElementType()) {
			return getGEFWrapper(new BreezeModel.diagram.edit.commands.ProductionCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
