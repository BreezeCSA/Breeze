package BreezeModel.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class BreezeParserProvider extends AbstractProvider implements IParserProvider {

	/**
	 * @generated
	 */
	private IParser archName_5002Parser;

	/**
	 * @generated
	 */
	private IParser getArchName_5002Parser() {
		if (archName_5002Parser == null) {
			EAttribute[] features = new EAttribute[] { BreezeModel.breezePackage.eINSTANCE.getNameable_Name() };
			BreezeModel.diagram.parsers.MessageFormatParser parser = new BreezeModel.diagram.parsers.MessageFormatParser(features);
			archName_5002Parser = parser;
		}
		return archName_5002Parser;
	}

	/**
	 * @generated
	 */
	private IParser styleName_5003Parser;

	/**
	 * @generated
	 */
	private IParser getStyleName_5003Parser() {
		if (styleName_5003Parser == null) {
			EAttribute[] features = new EAttribute[] { BreezeModel.breezePackage.eINSTANCE.getNameable_Name() };
			BreezeModel.diagram.parsers.MessageFormatParser parser = new BreezeModel.diagram.parsers.MessageFormatParser(features);
			styleName_5003Parser = parser;
		}
		return styleName_5003Parser;
	}

	/**
	 * @generated
	 */
	private IParser gokuDocumentation_5014Parser;

	/**
	 * @generated
	 */
	private IParser getGokuDocumentation_5014Parser() {
		if (gokuDocumentation_5014Parser == null) {
			EAttribute[] features = new EAttribute[] { BreezeModel.breezePackage.eINSTANCE.getDocumentable_Documentation() };
			BreezeModel.diagram.parsers.MessageFormatParser parser = new BreezeModel.diagram.parsers.MessageFormatParser(features);
			gokuDocumentation_5014Parser = parser;
		}
		return gokuDocumentation_5014Parser;
	}

	/**
	 * @generated
	 */
	private IParser componentName_5005Parser;

	/**
	 * @generated
	 */
	private IParser getComponentName_5005Parser() {
		if (componentName_5005Parser == null) {
			EAttribute[] features = new EAttribute[] { BreezeModel.breezePackage.eINSTANCE.getNameable_Name() };
			BreezeModel.diagram.parsers.MessageFormatParser parser = new BreezeModel.diagram.parsers.MessageFormatParser(features);
			componentName_5005Parser = parser;
		}
		return componentName_5005Parser;
	}

	/**
	 * @generated
	 */
	private IParser connectorName_5004Parser;

	/**
	 * @generated
	 */
	private IParser getConnectorName_5004Parser() {
		if (connectorName_5004Parser == null) {
			EAttribute[] features = new EAttribute[] { BreezeModel.breezePackage.eINSTANCE.getNameable_Name() };
			BreezeModel.diagram.parsers.MessageFormatParser parser = new BreezeModel.diagram.parsers.MessageFormatParser(features);
			connectorName_5004Parser = parser;
		}
		return connectorName_5004Parser;
	}

	/**
	 * @generated
	 */
	private IParser templateName_5010Parser;

	/**
	 * @generated
	 */
	private IParser getTemplateName_5010Parser() {
		if (templateName_5010Parser == null) {
			EAttribute[] features = new EAttribute[] { BreezeModel.breezePackage.eINSTANCE.getNameable_Name() };
			BreezeModel.diagram.parsers.MessageFormatParser parser = new BreezeModel.diagram.parsers.MessageFormatParser(features);
			templateName_5010Parser = parser;
		}
		return templateName_5010Parser;
	}

	/**
	 * @generated
	 */
	private IParser nodeTemplateName_5009Parser;

	/**
	 * @generated
	 */
	private IParser getNodeTemplateName_5009Parser() {
		if (nodeTemplateName_5009Parser == null) {
			EAttribute[] features = new EAttribute[] { BreezeModel.breezePackage.eINSTANCE.getNameable_Name() };
			BreezeModel.diagram.parsers.MessageFormatParser parser = new BreezeModel.diagram.parsers.MessageFormatParser(features);
			nodeTemplateName_5009Parser = parser;
		}
		return nodeTemplateName_5009Parser;
	}

	/**
	 * @generated
	 */
	private IParser productionName_5013Parser;

	/**
	 * @generated
	 */
	private IParser getProductionName_5013Parser() {
		if (productionName_5013Parser == null) {
			EAttribute[] features = new EAttribute[] { BreezeModel.breezePackage.eINSTANCE.getNameable_Name() };
			BreezeModel.diagram.parsers.MessageFormatParser parser = new BreezeModel.diagram.parsers.MessageFormatParser(features);
			productionName_5013Parser = parser;
		}
		return productionName_5013Parser;
	}

	/**
	 * @generated
	 */
	private IParser archName_5011Parser;

	/**
	 * @generated
	 */
	private IParser getArchName_5011Parser() {
		if (archName_5011Parser == null) {
			EAttribute[] features = new EAttribute[] { BreezeModel.breezePackage.eINSTANCE.getNameable_Name() };
			BreezeModel.diagram.parsers.MessageFormatParser parser = new BreezeModel.diagram.parsers.MessageFormatParser(features);
			archName_5011Parser = parser;
		}
		return archName_5011Parser;
	}

	/**
	 * @generated
	 */
	private IParser archName_5012Parser;

	/**
	 * @generated
	 */
	private IParser getArchName_5012Parser() {
		if (archName_5012Parser == null) {
			EAttribute[] features = new EAttribute[] { BreezeModel.breezePackage.eINSTANCE.getNameable_Name() };
			BreezeModel.diagram.parsers.MessageFormatParser parser = new BreezeModel.diagram.parsers.MessageFormatParser(features);
			archName_5012Parser = parser;
		}
		return archName_5012Parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case BreezeModel.diagram.edit.parts.ArchNameEditPart.VISUAL_ID:
			return getArchName_5002Parser();
		case BreezeModel.diagram.edit.parts.StyleNameEditPart.VISUAL_ID:
			return getStyleName_5003Parser();
		case BreezeModel.diagram.edit.parts.GokuDocumentationPowerEditPart.VISUAL_ID:
			return getGokuDocumentation_5014Parser();
		case BreezeModel.diagram.edit.parts.ComponentNameEditPart.VISUAL_ID:
			return getComponentName_5005Parser();
		case BreezeModel.diagram.edit.parts.ConnectorNameEditPart.VISUAL_ID:
			return getConnectorName_5004Parser();
		case BreezeModel.diagram.edit.parts.TemplateNameEditPart.VISUAL_ID:
			return getTemplateName_5010Parser();
		case BreezeModel.diagram.edit.parts.NodeTemplateNameEditPart.VISUAL_ID:
			return getNodeTemplateName_5009Parser();
		case BreezeModel.diagram.edit.parts.ProductionNameEditPart.VISUAL_ID:
			return getProductionName_5013Parser();
		case BreezeModel.diagram.edit.parts.ArchName2EditPart.VISUAL_ID:
			return getArchName_5011Parser();
		case BreezeModel.diagram.edit.parts.ArchName3EditPart.VISUAL_ID:
			return getArchName_5012Parser();
		}
		return null;
	}

	/**
	 * Utility method that consults ParserService
	 * @generated
	 */
	public static IParser getParser(IElementType type, EObject object, String parserHint) {
		return ParserService.getInstance().getParser(new HintAdapter(type, object, parserHint));
	}

	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(BreezeModel.diagram.part.BreezeVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(BreezeModel.diagram.part.BreezeVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (BreezeModel.diagram.providers.BreezeElementTypes.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	 * @generated
	 */
	private static class HintAdapter extends ParserHintAdapter {

		/**
		 * @generated
		 */
		private final IElementType elementType;

		/**
		 * @generated
		 */
		public HintAdapter(IElementType type, EObject object, String parserHint) {
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}

		/**
		 * @generated
		 */
		public Object getAdapter(Class adapter) {
			if (IElementType.class.equals(adapter)) {
				return elementType;
			}
			return super.getAdapter(adapter);
		}
	}

}
