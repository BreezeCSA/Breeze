package BreezeModel.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

/**
 * @generated
 */
public class TemplateTemplateCompartmentItemSemanticEditPolicy extends
		BreezeModel.diagram.edit.policies.BreezeBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public TemplateTemplateCompartmentItemSemanticEditPolicy() {
		super(BreezeModel.diagram.providers.BreezeElementTypes.Template_3010);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (BreezeModel.diagram.providers.BreezeElementTypes.NodeTemplate_3009 == req.getElementType()) {
			return getGEFWrapper(new BreezeModel.diagram.edit.commands.NodeTemplateCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
