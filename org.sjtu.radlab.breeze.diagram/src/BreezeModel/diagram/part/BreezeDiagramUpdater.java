package BreezeModel.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.update.DiagramUpdater;

/**
 * @generated
 */
public class BreezeDiagramUpdater {

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeNodeDescriptor> getSemanticChildren(View view) {
		switch (BreezeModel.diagram.part.BreezeVisualIDRegistry.getVisualID(view)) {
		case BreezeModel.diagram.edit.parts.BreezeEditPart.VISUAL_ID:
			return getBreeze_1000SemanticChildren(view);
		case BreezeModel.diagram.edit.parts.ComponentEditPart.VISUAL_ID:
			return getComponent_3005SemanticChildren(view);
		case BreezeModel.diagram.edit.parts.ConnectorEditPart.VISUAL_ID:
			return getConnector_3003SemanticChildren(view);
		case BreezeModel.diagram.edit.parts.ArchContentCompartmentEditPart.VISUAL_ID:
			return getArchContentCompartment_7001SemanticChildren(view);
		case BreezeModel.diagram.edit.parts.StyleStyleCompartmentEditPart.VISUAL_ID:
			return getStyleStyleCompartment_7002SemanticChildren(view);
		case BreezeModel.diagram.edit.parts.TemplateTemplateCompartmentEditPart.VISUAL_ID:
			return getTemplateTemplateCompartment_7003SemanticChildren(view);
		case BreezeModel.diagram.edit.parts.ProductionProductionCompartmentEditPart.VISUAL_ID:
			return getProductionLeftProductionCompartment_7004SemanticChildren(view);
		case BreezeModel.diagram.edit.parts.ProductionProductionRightCompartmentEditPart.VISUAL_ID:
			return getProductionRightProductionCompartment_7007SemanticChildren(view);
		case BreezeModel.diagram.edit.parts.ArchContentCompartment2EditPart.VISUAL_ID:
			return getArchContentCompartment_7005SemanticChildren(view);
		case BreezeModel.diagram.edit.parts.ArchContentCompartment3EditPart.VISUAL_ID:
			return getArchContentCompartment_7006SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeNodeDescriptor> getBreeze_1000SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		BreezeModel.Breeze modelElement = (BreezeModel.Breeze) view.getElement();
		LinkedList<BreezeModel.diagram.part.BreezeNodeDescriptor> result = new LinkedList<BreezeModel.diagram.part.BreezeNodeDescriptor>();
		{
			BreezeModel.Arch childElement = modelElement.getArch();
			int visualID = BreezeModel.diagram.part.BreezeVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == BreezeModel.diagram.edit.parts.ArchEditPart.VISUAL_ID) {
				result.add(new BreezeModel.diagram.part.BreezeNodeDescriptor(childElement, visualID));
			}
		}
		{
			BreezeModel.Style childElement = modelElement.getStyle();
			int visualID = BreezeModel.diagram.part.BreezeVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == BreezeModel.diagram.edit.parts.StyleEditPart.VISUAL_ID) {
				result.add(new BreezeModel.diagram.part.BreezeNodeDescriptor(childElement, visualID));
			}
		}
		for (Iterator<?> it = modelElement.getGkeee().iterator(); it.hasNext();) {
			BreezeModel.goku childElement = (BreezeModel.goku) it.next();
			int visualID = BreezeModel.diagram.part.BreezeVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == BreezeModel.diagram.edit.parts.GokuEditPart.VISUAL_ID) {
				result.add(new BreezeModel.diagram.part.BreezeNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeNodeDescriptor> getComponent_3005SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		BreezeModel.Component modelElement = (BreezeModel.Component) view.getElement();
		LinkedList<BreezeModel.diagram.part.BreezeNodeDescriptor> result = new LinkedList<BreezeModel.diagram.part.BreezeNodeDescriptor>();
		for (Iterator<?> it = modelElement.getPort().iterator(); it.hasNext();) {
			BreezeModel.Port childElement = (BreezeModel.Port) it.next();
			int visualID = BreezeModel.diagram.part.BreezeVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == BreezeModel.diagram.edit.parts.PortEditPart.VISUAL_ID) {
				result.add(new BreezeModel.diagram.part.BreezeNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeNodeDescriptor> getConnector_3003SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		BreezeModel.Connector modelElement = (BreezeModel.Connector) view.getElement();
		LinkedList<BreezeModel.diagram.part.BreezeNodeDescriptor> result = new LinkedList<BreezeModel.diagram.part.BreezeNodeDescriptor>();
		for (Iterator<?> it = modelElement.getPort().iterator(); it.hasNext();) {
			BreezeModel.Port childElement = (BreezeModel.Port) it.next();
			int visualID = BreezeModel.diagram.part.BreezeVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == BreezeModel.diagram.edit.parts.PortEditPart.VISUAL_ID) {
				result.add(new BreezeModel.diagram.part.BreezeNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeNodeDescriptor> getArchContentCompartment_7001SemanticChildren(View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		BreezeModel.Arch modelElement = (BreezeModel.Arch) containerView.getElement();
		LinkedList<BreezeModel.diagram.part.BreezeNodeDescriptor> result = new LinkedList<BreezeModel.diagram.part.BreezeNodeDescriptor>();
		for (Iterator<?> it = modelElement.getNode().iterator(); it.hasNext();) {
			BreezeModel.Node childElement = (BreezeModel.Node) it.next();
			int visualID = BreezeModel.diagram.part.BreezeVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == BreezeModel.diagram.edit.parts.ComponentEditPart.VISUAL_ID) {
				result.add(new BreezeModel.diagram.part.BreezeNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == BreezeModel.diagram.edit.parts.ConnectorEditPart.VISUAL_ID) {
				result.add(new BreezeModel.diagram.part.BreezeNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeNodeDescriptor> getStyleStyleCompartment_7002SemanticChildren(View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		BreezeModel.Style modelElement = (BreezeModel.Style) containerView.getElement();
		LinkedList<BreezeModel.diagram.part.BreezeNodeDescriptor> result = new LinkedList<BreezeModel.diagram.part.BreezeNodeDescriptor>();
		{
			BreezeModel.Template childElement = modelElement.getArch();
			int visualID = BreezeModel.diagram.part.BreezeVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == BreezeModel.diagram.edit.parts.TemplateEditPart.VISUAL_ID) {
				result.add(new BreezeModel.diagram.part.BreezeNodeDescriptor(childElement, visualID));
			}
		}
		for (Iterator<?> it = modelElement.getProduction().iterator(); it.hasNext();) {
			BreezeModel.Production childElement = (BreezeModel.Production) it.next();
			int visualID = BreezeModel.diagram.part.BreezeVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == BreezeModel.diagram.edit.parts.ProductionEditPart.VISUAL_ID) {
				result.add(new BreezeModel.diagram.part.BreezeNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeNodeDescriptor> getTemplateTemplateCompartment_7003SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		BreezeModel.Template modelElement = (BreezeModel.Template) containerView.getElement();
		LinkedList<BreezeModel.diagram.part.BreezeNodeDescriptor> result = new LinkedList<BreezeModel.diagram.part.BreezeNodeDescriptor>();
		for (Iterator<?> it = modelElement.getNode().iterator(); it.hasNext();) {
			BreezeModel.NodeTemplate childElement = (BreezeModel.NodeTemplate) it.next();
			int visualID = BreezeModel.diagram.part.BreezeVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == BreezeModel.diagram.edit.parts.NodeTemplateEditPart.VISUAL_ID) {
				result.add(new BreezeModel.diagram.part.BreezeNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeNodeDescriptor> getProductionLeftProductionCompartment_7004SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		BreezeModel.Production modelElement = (BreezeModel.Production) containerView.getElement();
		LinkedList<BreezeModel.diagram.part.BreezeNodeDescriptor> result = new LinkedList<BreezeModel.diagram.part.BreezeNodeDescriptor>();
		{
			BreezeModel.Arch childElement = modelElement.getLeft();
			int visualID = BreezeModel.diagram.part.BreezeVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == BreezeModel.diagram.edit.parts.Arch2EditPart.VISUAL_ID) {
				result.add(new BreezeModel.diagram.part.BreezeNodeDescriptor(childElement, visualID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeNodeDescriptor> getProductionRightProductionCompartment_7007SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		BreezeModel.Production modelElement = (BreezeModel.Production) containerView.getElement();
		LinkedList<BreezeModel.diagram.part.BreezeNodeDescriptor> result = new LinkedList<BreezeModel.diagram.part.BreezeNodeDescriptor>();
		{
			BreezeModel.Arch childElement = modelElement.getRight();
			int visualID = BreezeModel.diagram.part.BreezeVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == BreezeModel.diagram.edit.parts.Arch3EditPart.VISUAL_ID) {
				result.add(new BreezeModel.diagram.part.BreezeNodeDescriptor(childElement, visualID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeNodeDescriptor> getArchContentCompartment_7005SemanticChildren(View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		BreezeModel.Arch modelElement = (BreezeModel.Arch) containerView.getElement();
		LinkedList<BreezeModel.diagram.part.BreezeNodeDescriptor> result = new LinkedList<BreezeModel.diagram.part.BreezeNodeDescriptor>();
		for (Iterator<?> it = modelElement.getNode().iterator(); it.hasNext();) {
			BreezeModel.Node childElement = (BreezeModel.Node) it.next();
			int visualID = BreezeModel.diagram.part.BreezeVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == BreezeModel.diagram.edit.parts.ComponentEditPart.VISUAL_ID) {
				result.add(new BreezeModel.diagram.part.BreezeNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == BreezeModel.diagram.edit.parts.ConnectorEditPart.VISUAL_ID) {
				result.add(new BreezeModel.diagram.part.BreezeNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeNodeDescriptor> getArchContentCompartment_7006SemanticChildren(View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		BreezeModel.Arch modelElement = (BreezeModel.Arch) containerView.getElement();
		LinkedList<BreezeModel.diagram.part.BreezeNodeDescriptor> result = new LinkedList<BreezeModel.diagram.part.BreezeNodeDescriptor>();
		for (Iterator<?> it = modelElement.getNode().iterator(); it.hasNext();) {
			BreezeModel.Node childElement = (BreezeModel.Node) it.next();
			int visualID = BreezeModel.diagram.part.BreezeVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == BreezeModel.diagram.edit.parts.ComponentEditPart.VISUAL_ID) {
				result.add(new BreezeModel.diagram.part.BreezeNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == BreezeModel.diagram.edit.parts.ConnectorEditPart.VISUAL_ID) {
				result.add(new BreezeModel.diagram.part.BreezeNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getContainedLinks(View view) {
		switch (BreezeModel.diagram.part.BreezeVisualIDRegistry.getVisualID(view)) {
		case BreezeModel.diagram.edit.parts.BreezeEditPart.VISUAL_ID:
			return getBreeze_1000ContainedLinks(view);
		case BreezeModel.diagram.edit.parts.ArchEditPart.VISUAL_ID:
			return getArch_2001ContainedLinks(view);
		case BreezeModel.diagram.edit.parts.StyleEditPart.VISUAL_ID:
			return getStyle_2002ContainedLinks(view);
		case BreezeModel.diagram.edit.parts.GokuEditPart.VISUAL_ID:
			return getGoku_2003ContainedLinks(view);
		case BreezeModel.diagram.edit.parts.ComponentEditPart.VISUAL_ID:
			return getComponent_3005ContainedLinks(view);
		case BreezeModel.diagram.edit.parts.PortEditPart.VISUAL_ID:
			return getPort_3002ContainedLinks(view);
		case BreezeModel.diagram.edit.parts.ConnectorEditPart.VISUAL_ID:
			return getConnector_3003ContainedLinks(view);
		case BreezeModel.diagram.edit.parts.TemplateEditPart.VISUAL_ID:
			return getTemplate_3010ContainedLinks(view);
		case BreezeModel.diagram.edit.parts.NodeTemplateEditPart.VISUAL_ID:
			return getNodeTemplate_3009ContainedLinks(view);
		case BreezeModel.diagram.edit.parts.ProductionEditPart.VISUAL_ID:
			return getProduction_3011ContainedLinks(view);
		case BreezeModel.diagram.edit.parts.Arch2EditPart.VISUAL_ID:
			return getArch_3012ContainedLinks(view);
		case BreezeModel.diagram.edit.parts.Arch3EditPart.VISUAL_ID:
			return getArch_3013ContainedLinks(view);
		case BreezeModel.diagram.edit.parts.LinkEditPart.VISUAL_ID:
			return getLink_4002ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getIncomingLinks(View view) {
		switch (BreezeModel.diagram.part.BreezeVisualIDRegistry.getVisualID(view)) {
		case BreezeModel.diagram.edit.parts.ArchEditPart.VISUAL_ID:
			return getArch_2001IncomingLinks(view);
		case BreezeModel.diagram.edit.parts.StyleEditPart.VISUAL_ID:
			return getStyle_2002IncomingLinks(view);
		case BreezeModel.diagram.edit.parts.GokuEditPart.VISUAL_ID:
			return getGoku_2003IncomingLinks(view);
		case BreezeModel.diagram.edit.parts.ComponentEditPart.VISUAL_ID:
			return getComponent_3005IncomingLinks(view);
		case BreezeModel.diagram.edit.parts.PortEditPart.VISUAL_ID:
			return getPort_3002IncomingLinks(view);
		case BreezeModel.diagram.edit.parts.ConnectorEditPart.VISUAL_ID:
			return getConnector_3003IncomingLinks(view);
		case BreezeModel.diagram.edit.parts.TemplateEditPart.VISUAL_ID:
			return getTemplate_3010IncomingLinks(view);
		case BreezeModel.diagram.edit.parts.NodeTemplateEditPart.VISUAL_ID:
			return getNodeTemplate_3009IncomingLinks(view);
		case BreezeModel.diagram.edit.parts.ProductionEditPart.VISUAL_ID:
			return getProduction_3011IncomingLinks(view);
		case BreezeModel.diagram.edit.parts.Arch2EditPart.VISUAL_ID:
			return getArch_3012IncomingLinks(view);
		case BreezeModel.diagram.edit.parts.Arch3EditPart.VISUAL_ID:
			return getArch_3013IncomingLinks(view);
		case BreezeModel.diagram.edit.parts.LinkEditPart.VISUAL_ID:
			return getLink_4002IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getOutgoingLinks(View view) {
		switch (BreezeModel.diagram.part.BreezeVisualIDRegistry.getVisualID(view)) {
		case BreezeModel.diagram.edit.parts.ArchEditPart.VISUAL_ID:
			return getArch_2001OutgoingLinks(view);
		case BreezeModel.diagram.edit.parts.StyleEditPart.VISUAL_ID:
			return getStyle_2002OutgoingLinks(view);
		case BreezeModel.diagram.edit.parts.GokuEditPart.VISUAL_ID:
			return getGoku_2003OutgoingLinks(view);
		case BreezeModel.diagram.edit.parts.ComponentEditPart.VISUAL_ID:
			return getComponent_3005OutgoingLinks(view);
		case BreezeModel.diagram.edit.parts.PortEditPart.VISUAL_ID:
			return getPort_3002OutgoingLinks(view);
		case BreezeModel.diagram.edit.parts.ConnectorEditPart.VISUAL_ID:
			return getConnector_3003OutgoingLinks(view);
		case BreezeModel.diagram.edit.parts.TemplateEditPart.VISUAL_ID:
			return getTemplate_3010OutgoingLinks(view);
		case BreezeModel.diagram.edit.parts.NodeTemplateEditPart.VISUAL_ID:
			return getNodeTemplate_3009OutgoingLinks(view);
		case BreezeModel.diagram.edit.parts.ProductionEditPart.VISUAL_ID:
			return getProduction_3011OutgoingLinks(view);
		case BreezeModel.diagram.edit.parts.Arch2EditPart.VISUAL_ID:
			return getArch_3012OutgoingLinks(view);
		case BreezeModel.diagram.edit.parts.Arch3EditPart.VISUAL_ID:
			return getArch_3013OutgoingLinks(view);
		case BreezeModel.diagram.edit.parts.LinkEditPart.VISUAL_ID:
			return getLink_4002OutgoingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getBreeze_1000ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getArch_2001ContainedLinks(View view) {
		BreezeModel.Arch modelElement = (BreezeModel.Arch) view.getElement();
		LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor> result = new LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Link_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getStyle_2002ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getGoku_2003ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getComponent_3005ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getPort_3002ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getConnector_3003ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getTemplate_3010ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getNodeTemplate_3009ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getProduction_3011ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getArch_3012ContainedLinks(View view) {
		BreezeModel.Arch modelElement = (BreezeModel.Arch) view.getElement();
		LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor> result = new LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Link_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getArch_3013ContainedLinks(View view) {
		BreezeModel.Arch modelElement = (BreezeModel.Arch) view.getElement();
		LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor> result = new LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Link_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getLink_4002ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getArch_2001IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getStyle_2002IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getGoku_2003IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getComponent_3005IncomingLinks(View view) {
		BreezeModel.Component modelElement = (BreezeModel.Component) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor> result = new LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Link_4002(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getPort_3002IncomingLinks(View view) {
		BreezeModel.Port modelElement = (BreezeModel.Port) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor> result = new LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Link_4002(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getConnector_3003IncomingLinks(View view) {
		BreezeModel.Connector modelElement = (BreezeModel.Connector) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor> result = new LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Link_4002(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getTemplate_3010IncomingLinks(View view) {
		BreezeModel.Template modelElement = (BreezeModel.Template) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor> result = new LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Link_4002(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getNodeTemplate_3009IncomingLinks(View view) {
		BreezeModel.NodeTemplate modelElement = (BreezeModel.NodeTemplate) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor> result = new LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Link_4002(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getProduction_3011IncomingLinks(View view) {
		BreezeModel.Production modelElement = (BreezeModel.Production) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor> result = new LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Link_4002(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getArch_3012IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getArch_3013IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getLink_4002IncomingLinks(View view) {
		BreezeModel.Link modelElement = (BreezeModel.Link) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor> result = new LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Link_4002(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getArch_2001OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getStyle_2002OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getGoku_2003OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getComponent_3005OutgoingLinks(View view) {
		BreezeModel.Component modelElement = (BreezeModel.Component) view.getElement();
		LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor> result = new LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Link_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getPort_3002OutgoingLinks(View view) {
		BreezeModel.Port modelElement = (BreezeModel.Port) view.getElement();
		LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor> result = new LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Link_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getConnector_3003OutgoingLinks(View view) {
		BreezeModel.Connector modelElement = (BreezeModel.Connector) view.getElement();
		LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor> result = new LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Link_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getTemplate_3010OutgoingLinks(View view) {
		BreezeModel.Template modelElement = (BreezeModel.Template) view.getElement();
		LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor> result = new LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Link_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getNodeTemplate_3009OutgoingLinks(View view) {
		BreezeModel.NodeTemplate modelElement = (BreezeModel.NodeTemplate) view.getElement();
		LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor> result = new LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Link_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getProduction_3011OutgoingLinks(View view) {
		BreezeModel.Production modelElement = (BreezeModel.Production) view.getElement();
		LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor> result = new LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Link_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getArch_3012OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getArch_3013OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<BreezeModel.diagram.part.BreezeLinkDescriptor> getLink_4002OutgoingLinks(View view) {
		BreezeModel.Link modelElement = (BreezeModel.Link) view.getElement();
		LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor> result = new LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Link_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<BreezeModel.diagram.part.BreezeLinkDescriptor> getContainedTypeModelFacetLinks_Link_4002(
			BreezeModel.Arch container) {
		LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor> result = new LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor>();
		for (Iterator<?> links = container.getEdge().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof BreezeModel.Link) {
				continue;
			}
			BreezeModel.Link link = (BreezeModel.Link) linkObject;
			if (BreezeModel.diagram.edit.parts.LinkEditPart.VISUAL_ID != BreezeModel.diagram.part.BreezeVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			BreezeModel.ArchElement dst = link.getTarget();
			BreezeModel.ArchElement src = link.getSource();
			result.add(new BreezeModel.diagram.part.BreezeLinkDescriptor(src, dst, link,
					BreezeModel.diagram.providers.BreezeElementTypes.Link_4002,
					BreezeModel.diagram.edit.parts.LinkEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<BreezeModel.diagram.part.BreezeLinkDescriptor> getIncomingTypeModelFacetLinks_Link_4002(
			BreezeModel.ArchElement target, Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor> result = new LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != BreezeModel.breezePackage.eINSTANCE.getRelationShip_Target()
					|| false == setting.getEObject() instanceof BreezeModel.Link) {
				continue;
			}
			BreezeModel.Link link = (BreezeModel.Link) setting.getEObject();
			if (BreezeModel.diagram.edit.parts.LinkEditPart.VISUAL_ID != BreezeModel.diagram.part.BreezeVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			BreezeModel.ArchElement src = link.getSource();
			result.add(new BreezeModel.diagram.part.BreezeLinkDescriptor(src, target, link,
					BreezeModel.diagram.providers.BreezeElementTypes.Link_4002,
					BreezeModel.diagram.edit.parts.LinkEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<BreezeModel.diagram.part.BreezeLinkDescriptor> getOutgoingTypeModelFacetLinks_Link_4002(
			BreezeModel.ArchElement source) {
		BreezeModel.Arch container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element.eContainer()) {
			if (element instanceof BreezeModel.Arch) {
				container = (BreezeModel.Arch) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor> result = new LinkedList<BreezeModel.diagram.part.BreezeLinkDescriptor>();
		for (Iterator<?> links = container.getEdge().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof BreezeModel.Link) {
				continue;
			}
			BreezeModel.Link link = (BreezeModel.Link) linkObject;
			if (BreezeModel.diagram.edit.parts.LinkEditPart.VISUAL_ID != BreezeModel.diagram.part.BreezeVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			BreezeModel.ArchElement dst = link.getTarget();
			BreezeModel.ArchElement src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new BreezeModel.diagram.part.BreezeLinkDescriptor(src, dst, link,
					BreezeModel.diagram.providers.BreezeElementTypes.Link_4002,
					BreezeModel.diagram.edit.parts.LinkEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static final DiagramUpdater TYPED_INSTANCE = new DiagramUpdater() {
		/**
		 * @generated
		 */
		@Override
		public List<BreezeModel.diagram.part.BreezeNodeDescriptor> getSemanticChildren(View view) {
			return BreezeDiagramUpdater.getSemanticChildren(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<BreezeModel.diagram.part.BreezeLinkDescriptor> getContainedLinks(View view) {
			return BreezeDiagramUpdater.getContainedLinks(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<BreezeModel.diagram.part.BreezeLinkDescriptor> getIncomingLinks(View view) {
			return BreezeDiagramUpdater.getIncomingLinks(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<BreezeModel.diagram.part.BreezeLinkDescriptor> getOutgoingLinks(View view) {
			return BreezeDiagramUpdater.getOutgoingLinks(view);
		}
	};

}
