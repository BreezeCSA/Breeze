package BreezeModel.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.structure.DiagramStructure;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented by
 * a domain model object.
 * 
 * @generated
 */
public class BreezeVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "org.sjtu.radlab.breeze.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final DiagramStructure TYPED_INSTANCE = new DiagramStructure() {
		/**
		 * @generated
		 */
		@Override
		public boolean checkNodeVisualID(View containerView, EObject domainElement, int candidate) {
			return BreezeModel.diagram.part.BreezeVisualIDRegistry.checkNodeVisualID(containerView, domainElement, candidate);
		}

		/**
		 * @generated
		 */
		@Override
		public String getModelID(View view) {
			return BreezeModel.diagram.part.BreezeVisualIDRegistry.getModelID(view);
		}

		/**
		 * @generated
		 */
		@Override
		public int getNodeVisualID(View containerView, EObject domainElement) {
			return BreezeModel.diagram.part.BreezeVisualIDRegistry.getNodeVisualID(containerView, domainElement);
		}

		/**
		 * @generated
		 */
		@Override
		public int getVisualID(View view) {
			return BreezeModel.diagram.part.BreezeVisualIDRegistry.getVisualID(view);
		}

		/**
		 * @generated
		 */
		@Override
		public boolean isCompartmentVisualID(int visualID) {
			return BreezeModel.diagram.part.BreezeVisualIDRegistry.isCompartmentVisualID(visualID);
		}

		/**
		 * @generated
		 */
		@Override
		public boolean isSemanticLeafVisualID(int visualID) {
			return BreezeModel.diagram.part.BreezeVisualIDRegistry.isSemanticLeafVisualID(visualID);
		}
	};

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = BreezeModel.diagram.part.BreezeVisualIDRegistry.getModelID(containerView);
		if (!BreezeModel.diagram.edit.parts.BreezeEditPart.MODEL_ID.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (BreezeModel.diagram.edit.parts.BreezeEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = BreezeModel.diagram.part.BreezeVisualIDRegistry.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = BreezeModel.diagram.edit.parts.BreezeEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case BreezeModel.diagram.edit.parts.BreezeEditPart.VISUAL_ID:
			if (BreezeModel.diagram.edit.parts.ArchEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BreezeModel.diagram.edit.parts.StyleEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BreezeModel.diagram.edit.parts.GokuEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BreezeModel.diagram.edit.parts.ArchEditPart.VISUAL_ID:
			if (BreezeModel.diagram.edit.parts.ArchNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BreezeModel.diagram.edit.parts.ArchContentCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BreezeModel.diagram.edit.parts.StyleEditPart.VISUAL_ID:
			if (BreezeModel.diagram.edit.parts.StyleNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BreezeModel.diagram.edit.parts.StyleStyleCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BreezeModel.diagram.edit.parts.GokuEditPart.VISUAL_ID:
			if (BreezeModel.diagram.edit.parts.GokuDocumentationPowerEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BreezeModel.diagram.edit.parts.ComponentEditPart.VISUAL_ID:
			if (BreezeModel.diagram.edit.parts.ComponentNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BreezeModel.diagram.edit.parts.PortEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BreezeModel.diagram.edit.parts.ConnectorEditPart.VISUAL_ID:
			if (BreezeModel.diagram.edit.parts.ConnectorNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BreezeModel.diagram.edit.parts.PortEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BreezeModel.diagram.edit.parts.TemplateEditPart.VISUAL_ID:
			if (BreezeModel.diagram.edit.parts.TemplateNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BreezeModel.diagram.edit.parts.TemplateTemplateCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BreezeModel.diagram.edit.parts.NodeTemplateEditPart.VISUAL_ID:
			if (BreezeModel.diagram.edit.parts.NodeTemplateNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BreezeModel.diagram.edit.parts.ProductionEditPart.VISUAL_ID:
			if (BreezeModel.diagram.edit.parts.ProductionNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BreezeModel.diagram.edit.parts.ProductionProductionCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BreezeModel.diagram.edit.parts.ProductionProductionRightCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BreezeModel.diagram.edit.parts.Arch2EditPart.VISUAL_ID:
			if (BreezeModel.diagram.edit.parts.ArchName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BreezeModel.diagram.edit.parts.ArchContentCompartment2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BreezeModel.diagram.edit.parts.Arch3EditPart.VISUAL_ID:
			if (BreezeModel.diagram.edit.parts.ArchName3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BreezeModel.diagram.edit.parts.ArchContentCompartment3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BreezeModel.diagram.edit.parts.ArchContentCompartmentEditPart.VISUAL_ID:
			if (BreezeModel.diagram.edit.parts.ComponentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BreezeModel.diagram.edit.parts.ConnectorEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BreezeModel.diagram.edit.parts.StyleStyleCompartmentEditPart.VISUAL_ID:
			if (BreezeModel.diagram.edit.parts.TemplateEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BreezeModel.diagram.edit.parts.ProductionEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BreezeModel.diagram.edit.parts.TemplateTemplateCompartmentEditPart.VISUAL_ID:
			if (BreezeModel.diagram.edit.parts.NodeTemplateEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BreezeModel.diagram.edit.parts.ProductionProductionCompartmentEditPart.VISUAL_ID:
			if (BreezeModel.diagram.edit.parts.Arch2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BreezeModel.diagram.edit.parts.ProductionProductionRightCompartmentEditPart.VISUAL_ID:
			if (BreezeModel.diagram.edit.parts.Arch3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BreezeModel.diagram.edit.parts.ArchContentCompartment2EditPart.VISUAL_ID:
			if (BreezeModel.diagram.edit.parts.ComponentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BreezeModel.diagram.edit.parts.ConnectorEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BreezeModel.diagram.edit.parts.ArchContentCompartment3EditPart.VISUAL_ID:
			if (BreezeModel.diagram.edit.parts.ComponentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BreezeModel.diagram.edit.parts.ConnectorEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static boolean checkNodeVisualID(View containerView, EObject domainElement, int candidate) {
		if (candidate == -1) {
			//unrecognized id is always bad
			return false;
		}
		int basic = getNodeVisualID(containerView, domainElement);
		return basic == candidate;
	}

	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		System.out.println(domainElement);
		if (BreezeModel.breezePackage.eINSTANCE.getBreeze().isSuperTypeOf(domainElement.eClass())
				&& isDiagram((BreezeModel.Breeze) domainElement)) {
			return BreezeModel.diagram.edit.parts.BreezeEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (BreezeModel.breezePackage.eINSTANCE.getLink().isSuperTypeOf(domainElement.eClass())) {
			return BreezeModel.diagram.edit.parts.LinkEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		String containerModelID = BreezeModel.diagram.part.BreezeVisualIDRegistry.getModelID(containerView);
		if (!BreezeModel.diagram.edit.parts.BreezeEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (BreezeModel.diagram.edit.parts.BreezeEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = BreezeModel.diagram.part.BreezeVisualIDRegistry.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = BreezeModel.diagram.edit.parts.BreezeEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case BreezeModel.diagram.edit.parts.BreezeEditPart.VISUAL_ID:
			if (BreezeModel.breezePackage.eINSTANCE.getArch().isSuperTypeOf(domainElement.eClass())) {
				return BreezeModel.diagram.edit.parts.ArchEditPart.VISUAL_ID;
			}
			if (BreezeModel.breezePackage.eINSTANCE.getStyle().isSuperTypeOf(domainElement.eClass())) {
				return BreezeModel.diagram.edit.parts.StyleEditPart.VISUAL_ID;
			}
			if (BreezeModel.breezePackage.eINSTANCE.getgoku().isSuperTypeOf(domainElement.eClass())) {
				return BreezeModel.diagram.edit.parts.GokuEditPart.VISUAL_ID;
			}
			break;
		case BreezeModel.diagram.edit.parts.ComponentEditPart.VISUAL_ID:
			if (BreezeModel.breezePackage.eINSTANCE.getPort().isSuperTypeOf(domainElement.eClass())) {
				return BreezeModel.diagram.edit.parts.PortEditPart.VISUAL_ID;
			}
			break;
		case BreezeModel.diagram.edit.parts.ConnectorEditPart.VISUAL_ID:
			if (BreezeModel.breezePackage.eINSTANCE.getPort().isSuperTypeOf(domainElement.eClass())) {
				return BreezeModel.diagram.edit.parts.PortEditPart.VISUAL_ID;
			}
			break;
		case BreezeModel.diagram.edit.parts.ArchContentCompartmentEditPart.VISUAL_ID:
			if (BreezeModel.breezePackage.eINSTANCE.getComponent().isSuperTypeOf(domainElement.eClass())) {
				return BreezeModel.diagram.edit.parts.ComponentEditPart.VISUAL_ID;
			}
			if (BreezeModel.breezePackage.eINSTANCE.getConnector().isSuperTypeOf(domainElement.eClass())) {
				return BreezeModel.diagram.edit.parts.ConnectorEditPart.VISUAL_ID;
			}
			break;
		case BreezeModel.diagram.edit.parts.StyleStyleCompartmentEditPart.VISUAL_ID:
			if (BreezeModel.breezePackage.eINSTANCE.getTemplate().isSuperTypeOf(domainElement.eClass())) {
				return BreezeModel.diagram.edit.parts.TemplateEditPart.VISUAL_ID;
			}
			if (BreezeModel.breezePackage.eINSTANCE.getProduction().isSuperTypeOf(domainElement.eClass())) {
				return BreezeModel.diagram.edit.parts.ProductionEditPart.VISUAL_ID;
			}
			break;
		case BreezeModel.diagram.edit.parts.TemplateTemplateCompartmentEditPart.VISUAL_ID:
			if (BreezeModel.breezePackage.eINSTANCE.getNodeTemplate().isSuperTypeOf(domainElement.eClass())) {
				return BreezeModel.diagram.edit.parts.NodeTemplateEditPart.VISUAL_ID;
			}
			break;
		case BreezeModel.diagram.edit.parts.ProductionProductionCompartmentEditPart.VISUAL_ID:
			if (BreezeModel.breezePackage.eINSTANCE.getArch().isSuperTypeOf(domainElement.eClass())) {
				return BreezeModel.diagram.edit.parts.Arch2EditPart.VISUAL_ID;
			}
			break;
		case BreezeModel.diagram.edit.parts.ProductionProductionRightCompartmentEditPart.VISUAL_ID:
			if (BreezeModel.breezePackage.eINSTANCE.getArch().isSuperTypeOf(domainElement.eClass())) {
				return BreezeModel.diagram.edit.parts.Arch3EditPart.VISUAL_ID;
			}
			break;
		case BreezeModel.diagram.edit.parts.ArchContentCompartment2EditPart.VISUAL_ID:
			if (BreezeModel.breezePackage.eINSTANCE.getComponent().isSuperTypeOf(domainElement.eClass())) {
				return BreezeModel.diagram.edit.parts.ComponentEditPart.VISUAL_ID;
			}
			if (BreezeModel.breezePackage.eINSTANCE.getConnector().isSuperTypeOf(domainElement.eClass())) {
				return BreezeModel.diagram.edit.parts.ConnectorEditPart.VISUAL_ID;
			}
			break;
		case BreezeModel.diagram.edit.parts.ArchContentCompartment3EditPart.VISUAL_ID:
			if (BreezeModel.breezePackage.eINSTANCE.getComponent().isSuperTypeOf(domainElement.eClass())) {
				return BreezeModel.diagram.edit.parts.ComponentEditPart.VISUAL_ID;
			}
			if (BreezeModel.breezePackage.eINSTANCE.getConnector().isSuperTypeOf(domainElement.eClass())) {
				return BreezeModel.diagram.edit.parts.ConnectorEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static String getType(int visualID) {
		return Integer.toString(visualID);
	}

	/**
	 * @generated
	 */
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(Platform.getDebugOption(DEBUG_KEY))) {
				BreezeModel.diagram.part.BreezeDiagramEditorPlugin.getInstance().logError(
						"Unable to parse view type as a visualID number: " + type);
			}
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (BreezeModel.diagram.edit.parts.BreezeEditPart.MODEL_ID.equals(view.getType())) {
				return BreezeModel.diagram.edit.parts.BreezeEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return BreezeModel.diagram.part.BreezeVisualIDRegistry.getVisualID(view.getType());
	}

	/**
	 * @generated
	 */
	public static boolean isCompartmentVisualID(int visualID) {
		switch (visualID) {
		case BreezeModel.diagram.edit.parts.ArchContentCompartmentEditPart.VISUAL_ID:
		case BreezeModel.diagram.edit.parts.StyleStyleCompartmentEditPart.VISUAL_ID:
		case BreezeModel.diagram.edit.parts.TemplateTemplateCompartmentEditPart.VISUAL_ID:
		case BreezeModel.diagram.edit.parts.ProductionProductionCompartmentEditPart.VISUAL_ID:
		case BreezeModel.diagram.edit.parts.ProductionProductionRightCompartmentEditPart.VISUAL_ID:
		case BreezeModel.diagram.edit.parts.ArchContentCompartment2EditPart.VISUAL_ID:
		case BreezeModel.diagram.edit.parts.ArchContentCompartment3EditPart.VISUAL_ID:
			return true;
		default:
			break;
		}
		return false;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(BreezeModel.Breeze element) {
		return true;
	}

	/**
	 * @generated
	 */
	public static boolean isSemanticLeafVisualID(int visualID) {
		switch (visualID) {
		case BreezeModel.diagram.edit.parts.BreezeEditPart.VISUAL_ID:
			return false;
		case BreezeModel.diagram.edit.parts.GokuEditPart.VISUAL_ID:
		case BreezeModel.diagram.edit.parts.PortEditPart.VISUAL_ID:
		case BreezeModel.diagram.edit.parts.NodeTemplateEditPart.VISUAL_ID:
			return true;
		default:
			break;
		}
		return false;
	}

}
