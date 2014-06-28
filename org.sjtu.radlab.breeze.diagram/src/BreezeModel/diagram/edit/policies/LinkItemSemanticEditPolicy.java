package BreezeModel.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

/**
 * @generated
 */
public class LinkItemSemanticEditPolicy extends BreezeModel.diagram.edit.policies.BreezeBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public LinkItemSemanticEditPolicy() {
		super(BreezeModel.diagram.providers.BreezeElementTypes.Link_4002);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getGEFWrapper(new DestroyElementCommand(req));
	}

	/**
	 * @generated
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		Command command = req.getTarget() == null ? getStartCreateRelationshipCommand(req)
				: getCompleteCreateRelationshipCommand(req);
		return command != null ? command : super.getCreateRelationshipCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getStartCreateRelationshipCommand(CreateRelationshipRequest req) {
		if (BreezeModel.diagram.providers.BreezeElementTypes.Link_4002 == req.getElementType()) {
			return getGEFWrapper(new BreezeModel.diagram.edit.commands.LinkCreateCommand(req, req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(CreateRelationshipRequest req) {
		if (BreezeModel.diagram.providers.BreezeElementTypes.Link_4002 == req.getElementType()) {
			return getGEFWrapper(new BreezeModel.diagram.edit.commands.LinkCreateCommand(req, req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * Returns command to reorient EClass based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientRelationshipCommand(ReorientRelationshipRequest req) {
		switch (getVisualID(req)) {
		case BreezeModel.diagram.edit.parts.LinkEditPart.VISUAL_ID:
			return getGEFWrapper(new BreezeModel.diagram.edit.commands.LinkReorientCommand(req));
		}
		return super.getReorientRelationshipCommand(req);
	}

}
