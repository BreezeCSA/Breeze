package BreezeModel.diagram.edit.parts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EReferenceImpl;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.common.core.util.StringStatics;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.BorderItemSelectionEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.Properties;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.requests.ChangePropertyValueRequest;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.draw2d.CenterLayout;
import org.eclipse.gmf.tooling.runtime.edit.policies.reparent.CreationEditPolicyWithCustomReparent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.PlatformUI;

import BreezeModel.Component;
import BreezeModel.Connector;
import BreezeModel.diagram.part.BreezeDiagramEditor;
import BreezeModel.impl.NodeTemplateImpl;

/**
 * @generated NOT
 */
public class ConnectorEditPart extends AbstractBorderedShapeEditPart {
	/**
	 * @generated
	 */
	public class ConnectorFigure extends RectangleFigure {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureConnectorName;

		/**
		 * @generated
		 */
		public ConnectorFigure() {
			this.setLayoutManager(new CenterLayout());
			this.setPreferredSize(new Dimension(ConnectorEditPart.this.getMapMode().DPtoLP(100), ConnectorEditPart.this
					.getMapMode().DPtoLP(30)));
			this.createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			this.fFigureConnectorName = new WrappingLabel();

			this.fFigureConnectorName.setText("Connector");

			this.fFigureConnectorName.setBorder(new MarginBorder(ConnectorEditPart.this.getMapMode().DPtoLP(4),
					ConnectorEditPart.this.getMapMode().DPtoLP(2), ConnectorEditPart.this.getMapMode().DPtoLP(2),
					ConnectorEditPart.this.getMapMode().DPtoLP(2)));

			this.add(this.fFigureConnectorName);

		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureConnectorName() {
			return this.fFigureConnectorName;
		}

	}

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 3003;

	/**
	 * @generated
	 */
	protected IFigure contentPane;

	/**
	 * @generated
	 */
	protected IFigure primaryShape;

	
	
	
	protected void handleNotificationEvent(Notification notification) {
		super.handleNotificationEvent(notification);
		Object oob=notification.getFeature();
		super.handleNotificationEvent(notification);
		if(oob instanceof EReferenceImpl)
		{
			if(((EReferenceImpl)oob).getName().equals("TR"))
			{
				if (notification.getNotifier() instanceof Connector) {
					if (((View) this.getModel()).getElement() instanceof Connector) {
						Connector component = (Connector) (((View) this.getModel()).getElement());
						NodeTemplateImpl nti=(NodeTemplateImpl) component.getTR();
						ConnectorEditPart edp=(ConnectorEditPart) BreezeModel.diagram.part.BreezeDiagramEditor.selectedEditPart;
						 Color col=findcolor_name(nti.getName()); 
						 edp.setBackgroundColor(col);
						 
						 ChangePropertyValueRequest req = new ChangePropertyValueRequest( 
							        StringStatics.BLANK,Properties.ID_FILLCOLOR,
							    FigureUtilities.colorToInteger(col));
						 final Command cmd = edp.getCommand(req);
						cmd.execute();
						}
					}
			}
		}
	
	}
	public Color findcolor_name(String tem_id)
	{
		
		
		
		Object o=PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if(o instanceof BreezeDiagramEditor){			
		EObject list =((DiagramDocumentEditor) o).getDiagram().getElement();
		RootEditPart rootEditPart = ((DiagramEditor) o).getDiagramEditPart().getRoot();
		EList<EObject> elist = list.eContents(); 
		List<EditPart> parts = rootEditPart.getChildren();
		for (EditPart ep : parts) {
			List<StyleEditPart> d_parts=ep.getChildren();
			for(Object aep : d_parts)
			{
				if(aep instanceof StyleEditPart){
				List<StyleEditPart> d_d_parts=((StyleEditPart)aep).getChildren();
				
				for(Object anep : d_d_parts)
				{
					if(anep instanceof StyleStyleCompartmentEditPart)
					{
						List<StyleStyleCompartmentEditPart> d_d_d_parts=((StyleStyleCompartmentEditPart) anep).getChildren();
						for(Object aneps: d_d_d_parts)
						{
							if(aneps instanceof TemplateEditPart)
							{
								List<TemplateEditPart> d_d_d_d_parts=((TemplateEditPart) aneps).getChildren();
								for(Object anepss: d_d_d_d_parts)
								{
									if(anepss instanceof TemplateTemplateCompartmentEditPart)
									{
										List<NodeTemplateEditPart> d_d_d_d_d_parts=((TemplateTemplateCompartmentEditPart) anepss).getChildren();
										for(Object anepsss: d_d_d_d_d_parts)
										{
											if(anepsss instanceof NodeTemplateEditPart)
											{
												List<NodeTemplateNameEditPart> d_d_d_d_d_d_parts=((NodeTemplateEditPart) anepsss).getChildren();
												for(NodeTemplateNameEditPart anepssss: d_d_d_d_d_d_parts)
												{
													//if(((NodeTemplateEditPart)anepssss))
 													Object oobbc= anepssss.getFigure();
 													if(oobbc.toString().equals(tem_id)){
 														Object oobbjec=((NodeTemplateEditPart) anepsss).getFigure();
 														return ((NodeTemplateEditPart) anepsss).getContentPane().getBackgroundColor();
 													}
												}
												
											}
										}
									}
								}
									}
							}
							
						}
					
					}
				}
				}
			}
		
			}
		
		return null;
	
		
	}
	/**
	 * @generated
	 */
	public ConnectorEditPart(View view) {
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
		if (childEditPart instanceof BreezeModel.diagram.edit.parts.ConnectorNameEditPart) {
			((BreezeModel.diagram.edit.parts.ConnectorNameEditPart) childEditPart).setLabel(this.getPrimaryShape()
					.getFigureConnectorName());
			return true;
		}
		if (childEditPart instanceof BreezeModel.diagram.edit.parts.PortEditPart) {
			BorderItemLocator locator = new BorderItemLocator(this.getMainFigure(), PositionConstants.NONE);
			this.getBorderedFigure().getBorderItemContainer()
					.add(((BreezeModel.diagram.edit.parts.PortEditPart) childEditPart).getFigure(), locator);
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	@Override
	protected void createDefaultEditPolicies() {
		this.installEditPolicy(EditPolicyRoles.CREATION_ROLE, new CreationEditPolicyWithCustomReparent(
				BreezeModel.diagram.part.BreezeVisualIDRegistry.TYPED_INSTANCE));
		super.createDefaultEditPolicies();
		this.installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new BreezeModel.diagram.edit.policies.ConnectorItemSemanticEditPolicy());
		this.installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE, new DragDropEditPolicy());
		this.installEditPolicy(EditPolicyRoles.CANONICAL_ROLE,
				new BreezeModel.diagram.edit.policies.ConnectorCanonicalEditPolicy());
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
				View childView = (View) child.getModel();
				switch (BreezeModel.diagram.part.BreezeVisualIDRegistry.getVisualID(childView)) {
				case BreezeModel.diagram.edit.parts.PortEditPart.VISUAL_ID:
					return new BorderItemSelectionEditPolicy();
				}
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
	protected NodeFigure createMainFigure() {
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
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(100, 40);
		return result;
	}

	/**
	 * @generated
	 */
	protected IFigure createNodeShape() {
		return this.primaryShape = new ConnectorFigure();
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
		if (editPart instanceof IBorderItemEditPart) {
			return this.getBorderedFigure().getBorderItemContainer();
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
				.getType(BreezeModel.diagram.edit.parts.ConnectorNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public ConnectorFigure getPrimaryShape() {
		return (ConnectorFigure) this.primaryShape;
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
		if (childEditPart instanceof BreezeModel.diagram.edit.parts.ConnectorNameEditPart) {
			return true;
		}
		if (childEditPart instanceof BreezeModel.diagram.edit.parts.PortEditPart) {
			this.getBorderedFigure().getBorderItemContainer()
					.remove(((BreezeModel.diagram.edit.parts.PortEditPart) childEditPart).getFigure());
			return true;
		}
		return false;
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
		if (nodeShape.getLayoutManager() == null) {
			ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
			layout.setSpacing(5);
			nodeShape.setLayoutManager(layout);
		}
		return nodeShape; // use nodeShape itself as contentPane
	}

}
