package BreezeModel.diagram.edit.parts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
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
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.workspace.AbstractEMFOperation;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.gmf.runtime.common.core.util.StringStatics;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.BorderItemSelectionEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;
import org.eclipse.gmf.runtime.diagram.ui.figures.DiagramColorConstants;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.Properties;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.render.editparts.RenderedDiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.ChangePropertyValueRequest;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.CreateGraphicEditPartOperation;
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
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import com.wxw.breeze.production.HighLight;
import com.wxw.breeze.production.Properties_Copy;
import com.wxw.breeze.production.architecture;

import BreezeModel.Component;
import BreezeModel.Port;
import BreezeModel.diagram.part.BreezeDiagramEditor;
import BreezeModel.impl.BreezeImpl;
import BreezeModel.impl.ComponentImpl;
import BreezeModel.impl.NodeInstanceImpl;
import BreezeModel.impl.NodeTemplateImpl;

/**
 * @generated
 */
public class ComponentEditPart extends AbstractBorderedShapeEditPart {

	
	
	public String getElementGuid() {
		return super.elementGuid;
	}

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 3005;
//	public static View view=null;
	/**
	 * @generated
	 */
	protected IFigure contentPane;

	/**
	 * @generated NOT
	 */
	protected IFigure primaryShape;

	public void removeChild1(EditPart child) {
		Assert.isNotNull(child);
		int index = getChildren().indexOf(child);
		if (index < 0)
			return;
		fireRemovingChild(child, index);
		if (isActive())
			child.deactivate();
		child.removeNotify();
		removeChildVisual(child);
		child.setParent(null);
		getChildren().remove(child);
	}
	
	
	/**
	 * @generated
	 */
	public ComponentEditPart(View view) {
		
		super(view);
		
	}
	protected void handleNotificationEvent(Notification notification) {
		Object oob=notification.getFeature();
		super.handleNotificationEvent(notification);
		if(oob instanceof EReferenceImpl)
		{
			if(((EReferenceImpl)oob).getName().equals("TR"))
			{
				if (notification.getNotifier() instanceof Component) {
					if (((View) this.getModel()).getElement() instanceof Component) {
						Component component = (Component) (((View) this.getModel()).getElement());
						NodeTemplateImpl nti=(NodeTemplateImpl) component.getTR();
						 ComponentEditPart edp=(ComponentEditPart) BreezeModel.diagram.part.BreezeDiagramEditor.selectedEditPart;
						 Color col=findcolor_name(nti.getName()); 
						 edp.setBackgroundColor(col);
//						 List<Port> list1 =  nti.getPort();
//						 Iterator<Port> it1 = list1.iterator();
//						 Iterator<Object> it2=edp.getChildren().iterator();
//						 int index=0;
//						 Object o=PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();		
//						 BreezeDiagramEditor diagramEditor = (BreezeDiagramEditor) o;
//						 IDiagramGraphicalViewer viewer = diagramEditor.getDiagramGraphicalViewer();
//					 	 List<PortEditPart> ooo=viewer.findEditPartsForElement("_a3OesCT-EeS_0Z86yJizKw", PortEditPart.class);
//					 	 PortEditPart ppep=new PortEditPart(null);
//
//					    System.out.println("ttest");
//						 while(it2.hasNext())
//						 {
//							 index++;
//							 Object pep=it2.next();
//							 if(pep instanceof PortEditPart)
//							 {
//								     it2.remove();
//								 index--;
//								 
//							 }
//							
//							 
//						 }
//						 
// 						while(it1.hasNext())
// 					 	 edp.addChild((PortEditPart)it1.next(), index);
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
	protected void createDefaultEditPolicies() {
		installEditPolicy(EditPolicyRoles.CREATION_ROLE, new CreationEditPolicyWithCustomReparent(
				BreezeModel.diagram.part.BreezeVisualIDRegistry.TYPED_INSTANCE));
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new BreezeModel.diagram.edit.policies.ComponentItemSemanticEditPolicy());
		installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE, new DragDropEditPolicy());
		installEditPolicy(EditPolicyRoles.CANONICAL_ROLE, new BreezeModel.diagram.edit.policies.ComponentCanonicalEditPolicy());
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
		return primaryShape = new ComponentFigure();
	}

	/**
	 * @generated
	 */
	public ComponentFigure getPrimaryShape() {
		return (ComponentFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof BreezeModel.diagram.edit.parts.ComponentNameEditPart) {
			((BreezeModel.diagram.edit.parts.ComponentNameEditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigureComponentNameFigure());
			return true;
		}
		if (childEditPart instanceof BreezeModel.diagram.edit.parts.PortEditPart) {
			BorderItemLocator locator = new BorderItemLocator(getMainFigure(), PositionConstants.NONE);
			getBorderedFigure().getBorderItemContainer().add(
					((BreezeModel.diagram.edit.parts.PortEditPart) childEditPart).getFigure(), locator);
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof BreezeModel.diagram.edit.parts.ComponentNameEditPart) {
			return true;
		}
		if (childEditPart instanceof BreezeModel.diagram.edit.parts.PortEditPart) {
			getBorderedFigure().getBorderItemContainer().remove(
					((BreezeModel.diagram.edit.parts.PortEditPart) childEditPart).getFigure());
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
		if (editPart instanceof IBorderItemEditPart) {
			return getBorderedFigure().getBorderItemContainer();
		}
		return getContentPane();
	}

	/**
	 * @generated
	 */
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(80, 40);
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
	protected NodeFigure createMainFigure() {
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
	public void setBackgroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setBackgroundColor(color);
		 	// BreezeModel.diagram.edit.parts.NodeTemplateEditPart.ComponentFigure cs=new ComponentShape();
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
				.getType(BreezeModel.diagram.edit.parts.ComponentNameEditPart.VISUAL_ID));
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
	public class ComponentFigure extends RectangleFigure {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureComponentNameFigure;

		/**
		 * @generated
		 */
		public ComponentFigure() {
			this.setLayoutManager(new CenterLayout());
			this.setPreferredSize(new Dimension(getMapMode().DPtoLP(80), getMapMode().DPtoLP(40)));
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			fFigureComponentNameFigure = new WrappingLabel();

			fFigureComponentNameFigure.setText("Component");

			fFigureComponentNameFigure.setBorder(new MarginBorder(getMapMode().DPtoLP(2), getMapMode().DPtoLP(2), getMapMode()
					.DPtoLP(2), getMapMode().DPtoLP(4)));

			this.add(fFigureComponentNameFigure);

		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureComponentNameFigure() {
			return fFigureComponentNameFigure;
		}

	}

}
