package BreezeModel.diagram.edit.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.directedit.locator.CellEditorLocatorAccess;

/**
 * @generated
 */
public class BreezeEditPartFactory implements EditPartFactory {

	/**
	 * @generated
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (BreezeModel.diagram.part.BreezeVisualIDRegistry.getVisualID(view)) {

			case BreezeModel.diagram.edit.parts.BreezeEditPart.VISUAL_ID:
				return new BreezeModel.diagram.edit.parts.BreezeEditPart(view);

			case BreezeModel.diagram.edit.parts.ArchEditPart.VISUAL_ID:
				return new BreezeModel.diagram.edit.parts.ArchEditPart(view);

			case BreezeModel.diagram.edit.parts.ArchNameEditPart.VISUAL_ID:
				return new BreezeModel.diagram.edit.parts.ArchNameEditPart(view);

			case BreezeModel.diagram.edit.parts.StyleEditPart.VISUAL_ID:
				return new BreezeModel.diagram.edit.parts.StyleEditPart(view);

			case BreezeModel.diagram.edit.parts.StyleNameEditPart.VISUAL_ID:
				return new BreezeModel.diagram.edit.parts.StyleNameEditPart(view);

			case BreezeModel.diagram.edit.parts.GokuEditPart.VISUAL_ID:
				return new BreezeModel.diagram.edit.parts.GokuEditPart(view);

			case BreezeModel.diagram.edit.parts.GokuDocumentationPowerEditPart.VISUAL_ID:
				return new BreezeModel.diagram.edit.parts.GokuDocumentationPowerEditPart(view);

			case BreezeModel.diagram.edit.parts.ComponentEditPart.VISUAL_ID:
				return new BreezeModel.diagram.edit.parts.ComponentEditPart(view);

			case BreezeModel.diagram.edit.parts.ComponentNameEditPart.VISUAL_ID:
				return new BreezeModel.diagram.edit.parts.ComponentNameEditPart(view);

			case BreezeModel.diagram.edit.parts.PortEditPart.VISUAL_ID:
				return new BreezeModel.diagram.edit.parts.PortEditPart(view);

			case BreezeModel.diagram.edit.parts.ConnectorEditPart.VISUAL_ID:
				return new BreezeModel.diagram.edit.parts.ConnectorEditPart(view);

			case BreezeModel.diagram.edit.parts.ConnectorNameEditPart.VISUAL_ID:
				return new BreezeModel.diagram.edit.parts.ConnectorNameEditPart(view);

			case BreezeModel.diagram.edit.parts.TemplateEditPart.VISUAL_ID:
				return new BreezeModel.diagram.edit.parts.TemplateEditPart(view);

			case BreezeModel.diagram.edit.parts.TemplateNameEditPart.VISUAL_ID:
				return new BreezeModel.diagram.edit.parts.TemplateNameEditPart(view);

			case BreezeModel.diagram.edit.parts.NodeTemplateEditPart.VISUAL_ID:
				return new BreezeModel.diagram.edit.parts.NodeTemplateEditPart(view);

			case BreezeModel.diagram.edit.parts.NodeTemplateNameEditPart.VISUAL_ID:
				return new BreezeModel.diagram.edit.parts.NodeTemplateNameEditPart(view);

			case BreezeModel.diagram.edit.parts.ProductionEditPart.VISUAL_ID:
				return new BreezeModel.diagram.edit.parts.ProductionEditPart(view);

			case BreezeModel.diagram.edit.parts.ProductionNameEditPart.VISUAL_ID:
				return new BreezeModel.diagram.edit.parts.ProductionNameEditPart(view);

			case BreezeModel.diagram.edit.parts.Arch2EditPart.VISUAL_ID:
				return new BreezeModel.diagram.edit.parts.Arch2EditPart(view);

			case BreezeModel.diagram.edit.parts.ArchName2EditPart.VISUAL_ID:
				return new BreezeModel.diagram.edit.parts.ArchName2EditPart(view);

			case BreezeModel.diagram.edit.parts.Arch3EditPart.VISUAL_ID:
				return new BreezeModel.diagram.edit.parts.Arch3EditPart(view);

			case BreezeModel.diagram.edit.parts.ArchName3EditPart.VISUAL_ID:
				return new BreezeModel.diagram.edit.parts.ArchName3EditPart(view);

			case BreezeModel.diagram.edit.parts.ArchContentCompartmentEditPart.VISUAL_ID:
				return new BreezeModel.diagram.edit.parts.ArchContentCompartmentEditPart(view);

			case BreezeModel.diagram.edit.parts.StyleStyleCompartmentEditPart.VISUAL_ID:
				return new BreezeModel.diagram.edit.parts.StyleStyleCompartmentEditPart(view);

			case BreezeModel.diagram.edit.parts.TemplateTemplateCompartmentEditPart.VISUAL_ID:
				return new BreezeModel.diagram.edit.parts.TemplateTemplateCompartmentEditPart(view);

			case BreezeModel.diagram.edit.parts.ProductionProductionCompartmentEditPart.VISUAL_ID:
				return new BreezeModel.diagram.edit.parts.ProductionProductionCompartmentEditPart(view);

			case BreezeModel.diagram.edit.parts.ProductionProductionRightCompartmentEditPart.VISUAL_ID:
				return new BreezeModel.diagram.edit.parts.ProductionProductionRightCompartmentEditPart(view);

			case BreezeModel.diagram.edit.parts.ArchContentCompartment2EditPart.VISUAL_ID:
				return new BreezeModel.diagram.edit.parts.ArchContentCompartment2EditPart(view);

			case BreezeModel.diagram.edit.parts.ArchContentCompartment3EditPart.VISUAL_ID:
				return new BreezeModel.diagram.edit.parts.ArchContentCompartment3EditPart(view);

			case BreezeModel.diagram.edit.parts.LinkEditPart.VISUAL_ID:
				return new BreezeModel.diagram.edit.parts.LinkEditPart(view);

			}
		}
		return createUnrecognizedEditPart(context, model);
	}

	/**
	 * @generated
	 */
	private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
		// Handle creation of unrecognized child node EditParts here
		return null;
	}

	/**
	 * @generated
	 */
	public static CellEditorLocator getTextCellEditorLocator(ITextAwareEditPart source) {
		return CellEditorLocatorAccess.INSTANCE.getTextCellEditorLocator(source);
	}

}
