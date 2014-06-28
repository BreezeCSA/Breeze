package BreezeModel.diagram.edit.parts;

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

/**
 * @generated
 */
public class ArchEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public class ArchFigure extends RectangleFigure {

		/**
		 * @generated
		 */
		private RectangleFigure fFigureArchCompartmentFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureArchNameFigure;

		/**
		 * @generated
		 */
		public ArchFigure() {

			BorderLayout layoutThis = new BorderLayout();
			this.setLayoutManager(layoutThis);

			this.setPreferredSize(new Dimension(ArchEditPart.this.getMapMode().DPtoLP(400), ArchEditPart.this.getMapMode()
					.DPtoLP(400)));
			this.createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			this.fFigureArchNameFigure = new WrappingLabel();

			this.fFigureArchNameFigure.setText("Architecture");

			this.add(this.fFigureArchNameFigure, BorderLayout.TOP);

			this.fFigureArchCompartmentFigure = new RectangleFigure();

			this.add(this.fFigureArchCompartmentFigure, BorderLayout.CENTER);

		}

		/**
		 * @generated
		 */
		public RectangleFigure getFigureArchCompartmentFigure() {
			return this.fFigureArchCompartmentFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureArchNameFigure() {
			return this.fFigureArchNameFigure;
		}

	}

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 2001;

	/**
	 * @generated
	 */
	protected IFigure contentPane;

	/**
	 * @generated
	 */
	protected IFigure primaryShape;

	/**
	 * @generated
	 */
	public ArchEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	@Override
	protected void addChildVisual(EditPart childEditPart, int index) {
		if (this.addFixedChild(childEditPart)) {
			return;
		}
		super.addChildVisual(childEditPart, -1);
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof BreezeModel.diagram.edit.parts.ArchNameEditPart) {
			((BreezeModel.diagram.edit.parts.ArchNameEditPart) childEditPart).setLabel(this.getPrimaryShape()
					.getFigureArchNameFigure());
			return true;
		}
		if (childEditPart instanceof BreezeModel.diagram.edit.parts.ArchContentCompartmentEditPart) {
			IFigure pane = this.getPrimaryShape().getFigureArchCompartmentFigure();
			this.setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way 
			pane.add(((BreezeModel.diagram.edit.parts.ArchContentCompartmentEditPart) childEditPart).getFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	@Override
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		this.installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new BreezeModel.diagram.edit.policies.ArchItemSemanticEditPolicy());
		this.installEditPolicy(EditPolicy.LAYOUT_ROLE, this.createLayoutEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {
		org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy lep = new org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy() {

			@Override
			protected EditPolicy createChildEditPolicy(EditPart child) {
				EditPolicy result = child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
				if (result == null) {
					result = new NonResizableEditPolicy();
				}
				return result;
			}

			@Override
			protected Command getCreateCommand(CreateRequest request) {
				return null;
			}

			@Override
			protected Command getMoveChildrenCommand(Request request) {
				return null;
			}
		};
		return lep;
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model so
	 * you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */
	@Override
	protected NodeFigure createNodeFigure() {
		NodeFigure figure = this.createNodePlate();
		figure.setLayoutManager(new StackLayout());
		IFigure shape = this.createNodeShape();
		figure.add(shape);
		this.contentPane = this.setupContentPane(shape);
		return figure;
	}

	/**
	 * @generated
	 */
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(400, 400);
		return result;
	}

	/**
	 * @generated
	 */
	protected IFigure createNodeShape() {
		return this.primaryShape = new ArchFigure();
	}

	/**
	 * @generated
	 */
	@Override
	public IFigure getContentPane() {
		if (this.contentPane != null) {
			return this.contentPane;
		}
		return super.getContentPane();
	}

	/**
	 * @generated
	 */
	@Override
	protected IFigure getContentPaneFor(IGraphicalEditPart editPart) {
		if (editPart instanceof BreezeModel.diagram.edit.parts.ArchContentCompartmentEditPart) {
			return this.getPrimaryShape().getFigureArchCompartmentFigure();
		}
		return this.getContentPane();
	}

	/**
	 * @generated
	 */
	@Override
	public EditPart getPrimaryChildEditPart() {
		return this.getChildBySemanticHint(BreezeModel.diagram.part.BreezeVisualIDRegistry
				.getType(BreezeModel.diagram.edit.parts.ArchNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public ArchFigure getPrimaryShape() {
		return (ArchFigure) this.primaryShape;
	}

	/**
	 * @generated
	 */
	@Override
	protected void removeChildVisual(EditPart childEditPart) {
		if (this.removeFixedChild(childEditPart)) {
			return;
		}
		super.removeChildVisual(childEditPart);
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof BreezeModel.diagram.edit.parts.ArchNameEditPart) {
			return true;
		}
		if (childEditPart instanceof BreezeModel.diagram.edit.parts.ArchContentCompartmentEditPart) {
			IFigure pane = this.getPrimaryShape().getFigureArchCompartmentFigure();
			pane.remove(((BreezeModel.diagram.edit.parts.ArchContentCompartmentEditPart) childEditPart).getFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	@Override
	protected void setBackgroundColor(Color color) {
		if (this.primaryShape != null) {
			this.primaryShape.setBackgroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	@Override
	protected void setForegroundColor(Color color) {
		if (this.primaryShape != null) {
			this.primaryShape.setForegroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	@Override
	protected void setLineType(int style) {
		if (this.primaryShape instanceof Shape) {
			((Shape) this.primaryShape).setLineStyle(style);
		}
	}

	/**
	 * @generated
	 */
	@Override
	protected void setLineWidth(int width) {
		if (this.primaryShape instanceof Shape) {
			((Shape) this.primaryShape).setLineWidth(width);
		}
	}

	/**
	 * Default implementation treats passed figure as content pane. Respects
	 * layout one may have set for generated figure.
	 * 
	 * @param nodeShape
	 *            instance of generated figure class
	 * @generated
	 */
	protected IFigure setupContentPane(IFigure nodeShape) {
		if (nodeShape.getLayoutManager() == null) {
			ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
			layout.setSpacing(5);
			nodeShape.setLayoutManager(layout);
		}
		return nodeShape; // use nodeShape itself as contentPane
	}

}
