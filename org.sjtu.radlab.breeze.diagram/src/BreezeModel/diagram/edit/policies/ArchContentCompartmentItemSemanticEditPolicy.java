package BreezeModel.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

/**
 * @generated
 */
public class ArchContentCompartmentItemSemanticEditPolicy extends
		BreezeModel.diagram.edit.policies.BreezeBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public ArchContentCompartmentItemSemanticEditPolicy() {
		super(BreezeModel.diagram.providers.BreezeElementTypes.Arch_2001);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (BreezeModel.diagram.providers.BreezeElementTypes.Component_3005 == req.getElementType()) {
			return getGEFWrapper(new BreezeModel.diagram.edit.commands.ComponentCreateCommand(req));
		}
		if (BreezeModel.diagram.providers.BreezeElementTypes.Connector_3003 == req.getElementType()) {
			return getGEFWrapper(new BreezeModel.diagram.edit.commands.ConnectorCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
