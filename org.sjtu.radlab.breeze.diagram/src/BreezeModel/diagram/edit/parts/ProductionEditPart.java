package BreezeModel.diagram.edit.parts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RoundedRectangle;
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
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

/**
 * @generated
 */
public class ProductionEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public class ProductionFigure extends RoundedRectangle {

		/**
		 * @generated
		 */
		private RoundedRectangle fFigureLeftProductionCompartment;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureProductionName;
		/**
		 * @generated
		 */
		private RoundedRectangle fFigureRightProductionCompartment;

		/**
		 * @generated
		 */
		public ProductionFigure() {

			GridLayout layoutThis = new GridLayout();
			layoutThis.numColumns = 1;
			layoutThis.makeColumnsEqualWidth = true;
			this.setLayoutManager(layoutThis);

			this.setCornerDimensions(new Dimension(ProductionEditPart.this.getMapMode().DPtoLP(12), ProductionEditPart.this
					.getMapMode().DPtoLP(12)));
			this.setLineWidth(2);
			this.setLineStyle(Graphics.LINE_DASH);
			this.createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			this.fFigureProductionName = new WrappingLabel();

			this.fFigureProductionName.setText("New Production");

			GridData constraintFFigureProductionName = new GridData();
			constraintFFigureProductionName.verticalAlignment = GridData.CENTER;
			constraintFFigureProductionName.horizontalAlignment = GridData.CENTER;
			constraintFFigureProductionName.horizontalIndent = 0;
			constraintFFigureProductionName.horizontalSpan = 2;
			constraintFFigureProductionName.verticalSpan = 2;
			constraintFFigureProductionName.grabExcessHorizontalSpace = true;
			constraintFFigureProductionName.grabExcessVerticalSpace = false;
			this.add(this.fFigureProductionName, constraintFFigureProductionName);

			this.fFigureLeftProductionCompartment = new RoundedRectangle();

			this.fFigureLeftProductionCompartment.setCornerDimensions(new Dimension(ProductionEditPart.this.getMapMode().DPtoLP(
					12), ProductionEditPart.this.getMapMode().DPtoLP(12)));

			this.add(this.fFigureLeftProductionCompartment);

			this.fFigureRightProductionCompartment = new RoundedRectangle();

			this.fFigureRightProductionCompartment.setCornerDimensions(new Dimension(ProductionEditPart.this.getMapMode().DPtoLP(
					12), ProductionEditPart.this.getMapMode().DPtoLP(12)));

			this.add(this.fFigureRightProductionCompartment);

		}

		/**
		 * @generated
		 */
		public RoundedRectangle getFigureLeftProductionCompartment() {
			return this.fFigureLeftProductionCompartment;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureProductionName() {
			return this.fFigureProductionName;
		}

		/**
		 * @generated
		 */
		public RoundedRectangle getFigureRightProductionCompartment() {
			return this.fFigureRightProductionCompartment;
		}

	}

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 3011;

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
	public ProductionEditPart(View view) {
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
		if (childEditPart instanceof BreezeModel.diagram.edit.parts.ProductionNameEditPart) {
			((BreezeModel.diagram.edit.parts.ProductionNameEditPart) childEditPart).setLabel(this.getPrimaryShape()
					.getFigureProductionName());
			return true;
		}
		if (childEditPart instanceof BreezeModel.diagram.edit.parts.ProductionProductionCompartmentEditPart) {
			IFigure pane = this.getPrimaryShape().getFigureLeftProductionCompartment();
			this.setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way 
			pane.add(((BreezeModel.diagram.edit.parts.ProductionProductionCompartmentEditPart) childEditPart).getFigure());
			return true;
		}
		if (childEditPart instanceof BreezeModel.diagram.edit.parts.ProductionProductionRightCompartmentEditPart) {
			IFigure pane = this.getPrimaryShape().getFigureRightProductionCompartment();
			this.setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way 
			pane.add(((BreezeModel.diagram.edit.parts.ProductionProductionRightCompartmentEditPart) childEditPart).getFigure());
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
		this.installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new BreezeModel.diagram.edit.policies.ProductionItemSemanticEditPolicy());
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
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(40, 40);
		return result;
	}

	/**
	 * @generated
	 */
	protected IFigure createNodeShape() {
		return this.primaryShape = new ProductionFigure();
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
		if (editPart instanceof BreezeModel.diagram.edit.parts.ProductionProductionCompartmentEditPart) {
			return this.getPrimaryShape().getFigureLeftProductionCompartment();
		}
		if (editPart instanceof BreezeModel.diagram.edit.parts.ProductionProductionRightCompartmentEditPart) {
			return this.getPrimaryShape().getFigureRightProductionCompartment();
		}
		return this.getContentPane();
	}

	public String getElementGuid() {
		return super.elementGuid;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnSource() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(1);
		types.add(BreezeModel.diagram.providers.BreezeElementTypes.Link_4002);
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnSourceAndTarget(IGraphicalEditPart targetEditPart) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (targetEditPart instanceof BreezeModel.diagram.edit.parts.ComponentEditPart) {
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.Link_4002);
		}
		if (targetEditPart instanceof BreezeModel.diagram.edit.parts.PortEditPart) {
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.Link_4002);
		}
		if (targetEditPart instanceof BreezeModel.diagram.edit.parts.ConnectorEditPart) {
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.Link_4002);
		}
		if (targetEditPart instanceof BreezeModel.diagram.edit.parts.TemplateEditPart) {
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.Link_4002);
		}
		if (targetEditPart instanceof BreezeModel.diagram.edit.parts.NodeTemplateEditPart) {
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.Link_4002);
		}
		if (targetEditPart instanceof BreezeModel.diagram.edit.parts.ProductionEditPart) {
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.Link_4002);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnTarget() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(1);
		types.add(BreezeModel.diagram.providers.BreezeElementTypes.Link_4002);
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMATypesForSource(IElementType relationshipType) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (relationshipType == BreezeModel.diagram.providers.BreezeElementTypes.Link_4002) {
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.Component_3005);
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.Port_3002);
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.Connector_3003);
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.Template_3010);
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.NodeTemplate_3009);
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.Production_3011);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMATypesForTarget(IElementType relationshipType) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (relationshipType == BreezeModel.diagram.providers.BreezeElementTypes.Link_4002) {
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.Component_3005);
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.Port_3002);
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.Connector_3003);
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.Template_3010);
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.NodeTemplate_3009);
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.Production_3011);
		}
		return types;
	}

	/**
	 * @generated
	 */
	@Override
	public EditPart getPrimaryChildEditPart() {
		return this.getChildBySemanticHint(BreezeModel.diagram.part.BreezeVisualIDRegistry
				.getType(BreezeModel.diagram.edit.parts.ProductionNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public ProductionFigure getPrimaryShape() {
		return (ProductionFigure) this.primaryShape;
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
		if (childEditPart instanceof BreezeModel.diagram.edit.parts.ProductionNameEditPart) {
			return true;
		}
		if (childEditPart instanceof BreezeModel.diagram.edit.parts.ProductionProductionCompartmentEditPart) {
			IFigure pane = this.getPrimaryShape().getFigureLeftProductionCompartment();
			pane.remove(((BreezeModel.diagram.edit.parts.ProductionProductionCompartmentEditPart) childEditPart).getFigure());
			return true;
		}
		if (childEditPart instanceof BreezeModel.diagram.edit.parts.ProductionProductionRightCompartmentEditPart) {
			IFigure pane = this.getPrimaryShape().getFigureRightProductionCompartment();
			pane.remove(((BreezeModel.diagram.edit.parts.ProductionProductionRightCompartmentEditPart) childEditPart).getFigure());
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
