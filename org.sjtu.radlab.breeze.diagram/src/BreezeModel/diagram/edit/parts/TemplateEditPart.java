package BreezeModel.diagram.edit.parts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.edit.policies.reparent.CreationEditPolicyWithCustomReparent;
import org.eclipse.swt.graphics.Color;

/**
 * @generated
 */
public class TemplateEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 3010;

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
	public TemplateEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		installEditPolicy(EditPolicyRoles.CREATION_ROLE, new CreationEditPolicyWithCustomReparent(
				BreezeModel.diagram.part.BreezeVisualIDRegistry.TYPED_INSTANCE));
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new BreezeModel.diagram.edit.policies.TemplateItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {
		org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy lep = new org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy() {

			protected EditPolicy createChildEditPolicy(EditPart child) {
				EditPolicy result = child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
				if (result == null) {
					result = new NonResizableEditPolicy();
				}
				return result;
			}

			protected Command getMoveChildrenCommand(Request request) {
				return null;
			}

			protected Command getCreateCommand(CreateRequest request) {
				return null;
			}
		};
		return lep;
	}

	/**
	 * @generated
	 */
	protected IFigure createNodeShape() {
		return primaryShape = new DefalutCompartmentWithName();
	}

	/**
	 * @generated
	 */
	public DefalutCompartmentWithName getPrimaryShape() {
		return (DefalutCompartmentWithName) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof BreezeModel.diagram.edit.parts.TemplateNameEditPart) {
			((BreezeModel.diagram.edit.parts.TemplateNameEditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigureDefaultCompartmentName());
			return true;
		}
		if (childEditPart instanceof BreezeModel.diagram.edit.parts.TemplateTemplateCompartmentEditPart) {
			IFigure pane = getPrimaryShape().getFigureDefaultCompartmentFigure();
			setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way 
			pane.add(((BreezeModel.diagram.edit.parts.TemplateTemplateCompartmentEditPart) childEditPart).getFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof BreezeModel.diagram.edit.parts.TemplateNameEditPart) {
			return true;
		}
		if (childEditPart instanceof BreezeModel.diagram.edit.parts.TemplateTemplateCompartmentEditPart) {
			IFigure pane = getPrimaryShape().getFigureDefaultCompartmentFigure();
			pane.remove(((BreezeModel.diagram.edit.parts.TemplateTemplateCompartmentEditPart) childEditPart).getFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected void addChildVisual(EditPart childEditPart, int index) {
		if (addFixedChild(childEditPart)) {
			return;
		}
		super.addChildVisual(childEditPart, -1);
	}

	/**
	 * @generated
	 */
	protected void removeChildVisual(EditPart childEditPart) {
		if (removeFixedChild(childEditPart)) {
			return;
		}
		super.removeChildVisual(childEditPart);
	}

	/**
	 * @generated
	 */
	protected IFigure getContentPaneFor(IGraphicalEditPart editPart) {
		if (editPart instanceof BreezeModel.diagram.edit.parts.TemplateTemplateCompartmentEditPart) {
			return getPrimaryShape().getFigureDefaultCompartmentFigure();
		}
		return getContentPane();
	}

	/**
	 * @generated
	 */
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(100, 100);
		return result;
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model
	 * so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */
	protected NodeFigure createNodeFigure() {
		NodeFigure figure = createNodePlate();
		figure.setLayoutManager(new StackLayout());
		IFigure shape = createNodeShape();
		figure.add(shape);
		contentPane = setupContentPane(shape);
		return figure;
	}

	/**
	 * Default implementation treats passed figure as content pane.
	 * Respects layout one may have set for generated figure.
	 * @param nodeShape instance of generated figure class
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

	/**
	 * @generated
	 */
	public IFigure getContentPane() {
		if (contentPane != null) {
			return contentPane;
		}
		return super.getContentPane();
	}

	/**
	 * @generated
	 */
	protected void setForegroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setForegroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	protected void setBackgroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setBackgroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	protected void setLineWidth(int width) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineWidth(width);
		}
	}

	/**
	 * @generated
	 */
	protected void setLineType(int style) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineStyle(style);
		}
	}

	/**
	 * @generated
	 */
	public EditPart getPrimaryChildEditPart() {
		return getChildBySemanticHint(BreezeModel.diagram.part.BreezeVisualIDRegistry
				.getType(BreezeModel.diagram.edit.parts.TemplateNameEditPart.VISUAL_ID));
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
	public EditPart getTargetEditPart(Request request) {
		if (request instanceof CreateViewAndElementRequest) {
			CreateElementRequestAdapter adapter = ((CreateViewAndElementRequest) request).getViewAndElementDescriptor()
					.getCreateElementRequestAdapter();
			IElementType type = (IElementType) adapter.getAdapter(IElementType.class);
			if (type == BreezeModel.diagram.providers.BreezeElementTypes.NodeTemplate_3009) {
				return getChildBySemanticHint(BreezeModel.diagram.part.BreezeVisualIDRegistry
						.getType(BreezeModel.diagram.edit.parts.TemplateTemplateCompartmentEditPart.VISUAL_ID));
			}
		}
		return super.getTargetEditPart(request);
	}

	/**
	 * @generated
	 */
	public class DefalutCompartmentWithName extends RectangleFigure {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureDefaultCompartmentName;
		/**
		 * @generated
		 */
		private RectangleFigure fFigureDefaultCompartmentFigure;

		/**
		 * @generated
		 */
		public DefalutCompartmentWithName() {

			BorderLayout layoutThis = new BorderLayout();
			this.setLayoutManager(layoutThis);

			this.setPreferredSize(new Dimension(getMapMode().DPtoLP(100), getMapMode().DPtoLP(100)));
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			fFigureDefaultCompartmentName = new WrappingLabel();

			fFigureDefaultCompartmentName.setText("New");

			this.add(fFigureDefaultCompartmentName, BorderLayout.TOP);

			fFigureDefaultCompartmentFigure = new RectangleFigure();

			this.add(fFigureDefaultCompartmentFigure, BorderLayout.CENTER);

		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureDefaultCompartmentName() {
			return fFigureDefaultCompartmentName;
		}

		/**
		 * @generated
		 */
		public RectangleFigure getFigureDefaultCompartmentFigure() {
			return fFigureDefaultCompartmentFigure;
		}

	}

}
