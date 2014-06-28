package BreezeModel.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

/**
 * @generated
 */
public class ProductionProductionRightCompartmentItemSemanticEditPolicy extends
		BreezeModel.diagram.edit.policies.BreezeBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public ProductionProductionRightCompartmentItemSemanticEditPolicy() {
		super(BreezeModel.diagram.providers.BreezeElementTypes.Production_3011);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (BreezeModel.diagram.providers.BreezeElementTypes.Arch_3013 == req.getElementType()) {
			return getGEFWrapper(new BreezeModel.diagram.edit.commands.Arch3CreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
