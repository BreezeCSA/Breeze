package BreezeModel.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.tooling.runtime.providers.DiagramElementTypeImages;
import org.eclipse.gmf.tooling.runtime.providers.DiagramElementTypes;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

/**
 * @generated
 */
public class BreezeElementTypes {

	/**
	 * @generated
	 */
	public static final IElementType Arch_2001 = getElementType("org.sjtu.radlab.breeze.diagram.Arch_2001"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Arch_3012 = getElementType("org.sjtu.radlab.breeze.diagram.Arch_3012"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Arch_3013 = getElementType("org.sjtu.radlab.breeze.diagram.Arch_3013"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Breeze_1000 = getElementType("org.sjtu.radlab.breeze.diagram.Breeze_1000"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Component_3005 = getElementType("org.sjtu.radlab.breeze.diagram.Component_3005"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Connector_3003 = getElementType("org.sjtu.radlab.breeze.diagram.Connector_3003"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	private static Map<IElementType, ENamedElement> elements;
	/**
	 * @generated
	 */
	private static DiagramElementTypeImages elementTypeImages = new DiagramElementTypeImages(
			BreezeModel.diagram.part.BreezeDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory());

	/**
	 * @generated
	 */
	public static final IElementType Goku_2003 = getElementType("org.sjtu.radlab.breeze.diagram.Goku_2003"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	private static Set<IElementType> KNOWN_ELEMENT_TYPES;
	/**
	 * @generated
	 */
	public static final IElementType Link_4002 = getElementType("org.sjtu.radlab.breeze.diagram.Link_4002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType NodeTemplate_3009 = getElementType("org.sjtu.radlab.breeze.diagram.NodeTemplate_3009"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Port_3002 = getElementType("org.sjtu.radlab.breeze.diagram.Port_3002"); //$NON-NLS-1$
	public static final IElementType Production_2004 = getElementType("org.sjtu.radlab.breeze.diagram.Production_2002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Production_3011 = getElementType("org.sjtu.radlab.breeze.diagram.Production_3011"); //$NON-NLS-1$
	/**
	 * @generated NOT
	 */
	public static final IElementType Style_2002 = getElementType("org.sjtu.radlab.breeze.diagram.Style_2002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Template_3010 = getElementType("org.sjtu.radlab.breeze.diagram.Template_3010"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final DiagramElementTypes TYPED_INSTANCE = new DiagramElementTypes(elementTypeImages) {

		/**
		 * @generated
		 */
		@Override
		public ENamedElement getDefiningNamedElement(IAdaptable elementTypeAdapter) {
			return BreezeModel.diagram.providers.BreezeElementTypes.getElement(elementTypeAdapter);
		}

		/**
		 * @generated
		 */
		@Override
		public IElementType getElementTypeForVisualId(int visualID) {
			return BreezeModel.diagram.providers.BreezeElementTypes.getElementType(visualID);
		}

		/**
		 * @generated
		 */
		@Override
		public boolean isKnownElementType(IElementType elementType) {
			return BreezeModel.diagram.providers.BreezeElementTypes.isKnownElementType(elementType);
		}
	};

	/**
	 * Returns 'type' of the ecore object associated with the hint.
	 * 
	 * @generated
	 */
	public static ENamedElement getElement(IAdaptable hint) {

		Object type = hint.getAdapter(IElementType.class);
		if (elements == null) {
			elements = new IdentityHashMap<IElementType, ENamedElement>();
			elements.put(Breeze_1000, BreezeModel.breezePackage.eINSTANCE.getBreeze());

			elements.put(Arch_2001, BreezeModel.breezePackage.eINSTANCE.getArch());

			elements.put(Style_2002, BreezeModel.breezePackage.eINSTANCE.getStyle());

			elements.put(Production_2004, BreezeModel.breezePackage.eINSTANCE.getStyle());

			elements.put(Goku_2003, BreezeModel.breezePackage.eINSTANCE.getgoku());

			elements.put(Component_3005, BreezeModel.breezePackage.eINSTANCE.getComponent());

			elements.put(Port_3002, BreezeModel.breezePackage.eINSTANCE.getPort());

			elements.put(Connector_3003, BreezeModel.breezePackage.eINSTANCE.getConnector());

			elements.put(Template_3010, BreezeModel.breezePackage.eINSTANCE.getTemplate());

			elements.put(NodeTemplate_3009, BreezeModel.breezePackage.eINSTANCE.getNodeTemplate());

			elements.put(Production_3011, BreezeModel.breezePackage.eINSTANCE.getProduction());

			elements.put(Arch_3012, BreezeModel.breezePackage.eINSTANCE.getArch());

			elements.put(Arch_3013, BreezeModel.breezePackage.eINSTANCE.getArch());

			elements.put(Link_4002, BreezeModel.breezePackage.eINSTANCE.getLink());
		}
		return elements.get(type);
	}

	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID) {
		switch (visualID) {
		case BreezeModel.diagram.edit.parts.BreezeEditPart.VISUAL_ID:
			return Breeze_1000;
		case BreezeModel.diagram.edit.parts.ArchEditPart.VISUAL_ID:
			return Arch_2001;
		case BreezeModel.diagram.edit.parts.StyleEditPart.VISUAL_ID:
			return Style_2002;
		case BreezeModel.diagram.edit.parts.GokuEditPart.VISUAL_ID:
			return Goku_2003;
		case BreezeModel.diagram.edit.parts.ComponentEditPart.VISUAL_ID:
			return Component_3005;
		case BreezeModel.diagram.edit.parts.PortEditPart.VISUAL_ID:
			return Port_3002;
		case BreezeModel.diagram.edit.parts.ConnectorEditPart.VISUAL_ID:
			return Connector_3003;
		case BreezeModel.diagram.edit.parts.TemplateEditPart.VISUAL_ID:
			return Template_3010;
		case BreezeModel.diagram.edit.parts.NodeTemplateEditPart.VISUAL_ID:
			return NodeTemplate_3009;
		case BreezeModel.diagram.edit.parts.ProductionEditPart.VISUAL_ID:
			return Production_3011;
		case BreezeModel.diagram.edit.parts.Arch2EditPart.VISUAL_ID:
			return Arch_3012;
		case BreezeModel.diagram.edit.parts.Arch3EditPart.VISUAL_ID:
			return Arch_3013;
		case BreezeModel.diagram.edit.parts.LinkEditPart.VISUAL_ID:
			return Link_4002;
		}
		return null;
	}

	/**
	 * @generated
	 */
	private static IElementType getElementType(String id) {
		return ElementTypeRegistry.getInstance().getType(id);
	}

	/**
	 * @generated
	 */
	public static Image getImage(ENamedElement element) {
		return elementTypeImages.getImage(element);
	}

	/**
	 * @generated
	 */
	public static Image getImage(IAdaptable hint) {
		return getImage(getElement(hint));
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(ENamedElement element) {
		return elementTypeImages.getImageDescriptor(element);
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
		return getImageDescriptor(getElement(hint));
	}

	/**
	 * @generated
	 */
	public static boolean isKnownElementType(IElementType elementType) {
		if (KNOWN_ELEMENT_TYPES == null) {
			KNOWN_ELEMENT_TYPES = new HashSet<IElementType>();
			KNOWN_ELEMENT_TYPES.add(Breeze_1000);
			KNOWN_ELEMENT_TYPES.add(Arch_2001);
			KNOWN_ELEMENT_TYPES.add(Style_2002);
			KNOWN_ELEMENT_TYPES.add(Production_2004);
			KNOWN_ELEMENT_TYPES.add(Goku_2003);
			KNOWN_ELEMENT_TYPES.add(Component_3005);
			KNOWN_ELEMENT_TYPES.add(Port_3002);
			KNOWN_ELEMENT_TYPES.add(Connector_3003);
			KNOWN_ELEMENT_TYPES.add(Template_3010);
			KNOWN_ELEMENT_TYPES.add(NodeTemplate_3009);
			KNOWN_ELEMENT_TYPES.add(Production_3011);
			KNOWN_ELEMENT_TYPES.add(Arch_3012);
			KNOWN_ELEMENT_TYPES.add(Arch_3013);
			KNOWN_ELEMENT_TYPES.add(Link_4002);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	 * @generated
	 */
	private BreezeElementTypes() {
	}

}
