package BreezeModel.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

/**
 * @generated
 */
public class LinkReorientCommand extends EditElementCommand {

	/**
	 * @generated
	 */
	private final int reorientDirection;

	/**
	 * @generated
	 */
	private final EObject oldEnd;

	/**
	 * @generated
	 */
	private final EObject newEnd;

	/**
	 * @generated
	 */
	public LinkReorientCommand(ReorientRelationshipRequest request) {
		super(request.getLabel(), request.getRelationship(), request);
		reorientDirection = request.getDirection();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		if (false == getElementToEdit() instanceof BreezeModel.Link) {
			return false;
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return canReorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return canReorientTarget();
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean canReorientSource() {
		if (!(oldEnd instanceof BreezeModel.ArchElement && newEnd instanceof BreezeModel.ArchElement)) {
			return false;
		}
		BreezeModel.ArchElement target = getLink().getTarget();
		if (!(getLink().eContainer() instanceof BreezeModel.Arch)) {
			return false;
		}
		BreezeModel.Arch container = (BreezeModel.Arch) getLink().eContainer();
		return BreezeModel.diagram.edit.policies.BreezeBaseItemSemanticEditPolicy.getLinkConstraints().canExistLink_4002(
				container, getLink(), getNewSource(), target);
	}

	/**
	 * @generated
	 */
	protected boolean canReorientTarget() {
		if (!(oldEnd instanceof BreezeModel.ArchElement && newEnd instanceof BreezeModel.ArchElement)) {
			return false;
		}
		BreezeModel.ArchElement source = getLink().getSource();
		if (!(getLink().eContainer() instanceof BreezeModel.Arch)) {
			return false;
		}
		BreezeModel.Arch container = (BreezeModel.Arch) getLink().eContainer();
		return BreezeModel.diagram.edit.policies.BreezeBaseItemSemanticEditPolicy.getLinkConstraints().canExistLink_4002(
				container, getLink(), source, getNewTarget());
	}

	/**
	 * @generated
	 */
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		if (!canExecute()) {
			throw new ExecutionException("Invalid arguments in reorient link command"); //$NON-NLS-1$
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return reorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return reorientTarget();
		}
		throw new IllegalStateException();
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientSource() throws ExecutionException {
		getLink().setSource(getNewSource());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientTarget() throws ExecutionException {
		getLink().setTarget(getNewTarget());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected BreezeModel.Link getLink() {
		return (BreezeModel.Link) getElementToEdit();
	}

	/**
	 * @generated
	 */
	protected BreezeModel.ArchElement getOldSource() {
		return (BreezeModel.ArchElement) oldEnd;
	}

	/**
	 * @generated
	 */
	protected BreezeModel.ArchElement getNewSource() {
		return (BreezeModel.ArchElement) newEnd;
	}

	/**
	 * @generated
	 */
	protected BreezeModel.ArchElement getOldTarget() {
		return (BreezeModel.ArchElement) oldEnd;
	}

	/**
	 * @generated
	 */
	protected BreezeModel.ArchElement getNewTarget() {
		return (BreezeModel.ArchElement) newEnd;
	}
}
