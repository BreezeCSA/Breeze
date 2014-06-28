package BreezeModel.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

/**
 * @generated
 */
public class ProductionProductionCompartmentItemSemanticEditPolicy extends
		BreezeModel.diagram.edit.policies.BreezeBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public ProductionProductionCompartmentItemSemanticEditPolicy() {
		super(BreezeModel.diagram.providers.BreezeElementTypes.Production_3011);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (BreezeModel.diagram.providers.BreezeElementTypes.Arch_3012 == req.getElementType()) {
			return getGEFWrapper(new BreezeModel.diagram.edit.commands.Arch2CreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
