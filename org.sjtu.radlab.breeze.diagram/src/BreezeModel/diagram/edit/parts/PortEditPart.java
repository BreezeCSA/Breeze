package BreezeModel.diagram.edit.parts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutListener;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;
import org.eclipse.gmf.runtime.diagram.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

import BreezeModel.Port;

/**
 * @generated NOT
 */
public class PortEditPart extends AbstractBorderItemEditPart {
	/**
	 * @generated
	 */
	public class PortFigure extends RectangleFigure {

		private int direction = 1;

		private final int[][] DRPoints = new int[4][];
		private int rotation = 0;

		/**
		 * @generated
		 */
		public PortFigure() {
			this.setPreferredSize(new Dimension(PortEditPart.this.getMapMode().DPtoLP(10), PortEditPart.this.getMapMode().DPtoLP(
					10)));
		}

		public PortFigure(int direction) {
			this.setPreferredSize(new Dimension(PortEditPart.this.getMapMode().DPtoLP(10), PortEditPart.this.getMapMode().DPtoLP(
					10)));
			this.direction = direction;
		}

		@Override
		public void paintFigure(Graphics graphics) {
			super.paintFigure(graphics);
			Rectangle b = this.getClientArea();
			// 0 -> west, 1 -> north, 2 -> east, 3 -> south
			this.DRPoints[0] = new int[] { b.x + b.width / 2, b.y, b.x + b.width, b.y + b.height / 2, b.x + b.width / 2,
					b.y + b.height };

			this.DRPoints[1] = new int[] { b.x, b.y + b.height / 2, b.x + b.width, b.y + b.height / 2, b.x + b.width / 2,
					b.y + b.height };

			this.DRPoints[2] = new int[] { b.x + b.width / 2, b.y, b.x, b.y + b.height / 2, b.x + b.width / 2, b.y + b.height };

			this.DRPoints[3] = new int[] { b.x + b.width / 2, b.y, b.x + b.width, b.y + b.height / 2, b.x, b.y + b.height / 2 };
			graphics.setBackgroundColor(ColorConstants.black);
			if ((this.direction & 1) != 0) {
				graphics.fillPolygon(this.DRPoints[this.rotation]);
			}
			if ((this.direction & 2) != 0) {
				graphics.fillPolygon(this.DRPoints[(this.rotation + 2) % 4]);
			}
		}

		public void setPortDirection(int direction) {
			this.direction = direction;
			this.invalidate();
			return;
		}

		public void setRotationInDegrees(int rotation) {
			this.rotation = rotation;
			this.invalidate();
			return;
		}
	}

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 3002;

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
	public PortEditPart(View view) {
		super(view);
	}

	@Override
	public void activate() {

		IBorderItemLocator locator = this.getBorderItemLocator();
		if (locator instanceof BorderItemLocator) {
			((BorderItemLocator) locator).setBorderItemOffset(new Dimension(5, 5));
		}
		IFigure figure = this.getFigure();
		if (figure instanceof DefaultSizeNodeFigure) {
			DefaultSizeNodeFigure bnf = (DefaultSizeNodeFigure) figure;
			IFigure bicf = bnf.getParent();
			bicf.addLayoutListener(new LayoutListener.Stub() {
				@Override
				public void postLayout(IFigure container) {
					PortEditPart.this.getBorderItemLocator().relocate(PortEditPart.this.getFigure());
					int position = PortEditPart.this.getBorderItemLocator().getCurrentSideOfParent();
					int rotation = 0;
					switch (position) {
					case PositionConstants.WEST:
						break;
					case PositionConstants.NORTH:
						rotation = 1;
						break;
					case PositionConstants.EAST:
						rotation = 2;
						break;
					case PositionConstants.SOUTH:
						rotation = 3;
						break;
					default:
						break;
					}
					PortEditPart.this.getPrimaryShape().setRotationInDegrees(rotation);
				}
			});
		}

		super.activate();
	}

	/**
	 * @generated
	 */
	@Override
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		this.installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, this.getPrimaryDragEditPolicy());
		this.installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new BreezeModel.diagram.edit.policies.PortItemSemanticEditPolicy());
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
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(10, 10);

		//FIXME: workaround for #154536
		result.getBounds().setSize(result.getPreferredSize());
		return result;
	}

	/**
	 * @generated NOT
	 */
	protected IFigure createNodeShape() {
		return this.primaryShape = new PortFigure(((Port) (((View) this.getModel()).getElement())).getDirection().getValue() + 1);
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
	public PortFigure getPrimaryShape() {
		return (PortFigure) this.primaryShape;
	}

	@Override
	protected void handleNotificationEvent(Notification notification) {
		if (notification.getNotifier() instanceof Port) {
			if (((View) this.getModel()).getElement() instanceof Port) {
				Port port = (Port) (((View) this.getModel()).getElement());
				this.getPrimaryShape().setPortDirection(port.getDirection().getValue() + 1);
			}
		}
		super.handleNotificationEvent(notification);
	}

	/**
	 * @generated
	 */
	@Override
	public void setBackgroundColor(Color color) {
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
		return nodeShape; // use nodeShape itself as contentPane
	}
}
