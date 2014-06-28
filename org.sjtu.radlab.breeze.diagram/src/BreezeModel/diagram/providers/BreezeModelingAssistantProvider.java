package BreezeModel.diagram.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.modelingassistant.ModelingAssistantProvider;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

/**
 * @generated
 */
public class BreezeModelingAssistantProvider extends ModelingAssistantProvider {

	/**
	 * @generated
	 */
	public List getTypesForPopupBar(IAdaptable host) {
		IGraphicalEditPart editPart = (IGraphicalEditPart) host.getAdapter(IGraphicalEditPart.class);
		if (editPart instanceof BreezeModel.diagram.edit.parts.BreezeEditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(3);
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.Arch_2001);
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.Style_2002);
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.Goku_2003);
			return types;
		}
		if (editPart instanceof BreezeModel.diagram.edit.parts.ComponentEditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(1);
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.Port_3002);
			return types;
		}
		if (editPart instanceof BreezeModel.diagram.edit.parts.ConnectorEditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(1);
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.Port_3002);
			return types;
		}
		if (editPart instanceof BreezeModel.diagram.edit.parts.TemplateEditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(1);
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.NodeTemplate_3009);
			return types;
		}
		if (editPart instanceof BreezeModel.diagram.edit.parts.ArchContentCompartmentEditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(2);
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.Component_3005);
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.Connector_3003);
			return types;
		}
		if (editPart instanceof BreezeModel.diagram.edit.parts.StyleStyleCompartmentEditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(2);
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.Template_3010);
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.Production_3011);
			return types;
		}
		if (editPart instanceof BreezeModel.diagram.edit.parts.ProductionProductionCompartmentEditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(1);
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.Arch_3012);
			return types;
		}
		if (editPart instanceof BreezeModel.diagram.edit.parts.ProductionProductionRightCompartmentEditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(1);
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.Arch_3013);
			return types;
		}
		if (editPart instanceof BreezeModel.diagram.edit.parts.ArchContentCompartment2EditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(2);
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.Component_3005);
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.Connector_3003);
			return types;
		}
		if (editPart instanceof BreezeModel.diagram.edit.parts.ArchContentCompartment3EditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(2);
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.Component_3005);
			types.add(BreezeModel.diagram.providers.BreezeElementTypes.Connector_3003);
			return types;
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getRelTypesOnSource(IAdaptable source) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart instanceof BreezeModel.diagram.edit.parts.ComponentEditPart) {
			return ((BreezeModel.diagram.edit.parts.ComponentEditPart) sourceEditPart).getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof BreezeModel.diagram.edit.parts.PortEditPart) {
			return ((BreezeModel.diagram.edit.parts.PortEditPart) sourceEditPart).getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof BreezeModel.diagram.edit.parts.ConnectorEditPart) {
			return ((BreezeModel.diagram.edit.parts.ConnectorEditPart) sourceEditPart).getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof BreezeModel.diagram.edit.parts.TemplateEditPart) {
			return ((BreezeModel.diagram.edit.parts.TemplateEditPart) sourceEditPart).getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof BreezeModel.diagram.edit.parts.NodeTemplateEditPart) {
			return ((BreezeModel.diagram.edit.parts.NodeTemplateEditPart) sourceEditPart).getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof BreezeModel.diagram.edit.parts.ProductionEditPart) {
			return ((BreezeModel.diagram.edit.parts.ProductionEditPart) sourceEditPart).getMARelTypesOnSource();
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getRelTypesOnTarget(IAdaptable target) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		if (targetEditPart instanceof BreezeModel.diagram.edit.parts.ComponentEditPart) {
			return ((BreezeModel.diagram.edit.parts.ComponentEditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof BreezeModel.diagram.edit.parts.PortEditPart) {
			return ((BreezeModel.diagram.edit.parts.PortEditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof BreezeModel.diagram.edit.parts.ConnectorEditPart) {
			return ((BreezeModel.diagram.edit.parts.ConnectorEditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof BreezeModel.diagram.edit.parts.TemplateEditPart) {
			return ((BreezeModel.diagram.edit.parts.TemplateEditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof BreezeModel.diagram.edit.parts.NodeTemplateEditPart) {
			return ((BreezeModel.diagram.edit.parts.NodeTemplateEditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof BreezeModel.diagram.edit.parts.ProductionEditPart) {
			return ((BreezeModel.diagram.edit.parts.ProductionEditPart) targetEditPart).getMARelTypesOnTarget();
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getRelTypesOnSourceAndTarget(IAdaptable source, IAdaptable target) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart instanceof BreezeModel.diagram.edit.parts.ComponentEditPart) {
			return ((BreezeModel.diagram.edit.parts.ComponentEditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof BreezeModel.diagram.edit.parts.PortEditPart) {
			return ((BreezeModel.diagram.edit.parts.PortEditPart) sourceEditPart).getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof BreezeModel.diagram.edit.parts.ConnectorEditPart) {
			return ((BreezeModel.diagram.edit.parts.ConnectorEditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof BreezeModel.diagram.edit.parts.TemplateEditPart) {
			return ((BreezeModel.diagram.edit.parts.TemplateEditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof BreezeModel.diagram.edit.parts.NodeTemplateEditPart) {
			return ((BreezeModel.diagram.edit.parts.NodeTemplateEditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof BreezeModel.diagram.edit.parts.ProductionEditPart) {
			return ((BreezeModel.diagram.edit.parts.ProductionEditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getTypesForSource(IAdaptable target, IElementType relationshipType) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		if (targetEditPart instanceof BreezeModel.diagram.edit.parts.ComponentEditPart) {
			return ((BreezeModel.diagram.edit.parts.ComponentEditPart) targetEditPart).getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof BreezeModel.diagram.edit.parts.PortEditPart) {
			return ((BreezeModel.diagram.edit.parts.PortEditPart) targetEditPart).getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof BreezeModel.diagram.edit.parts.ConnectorEditPart) {
			return ((BreezeModel.diagram.edit.parts.ConnectorEditPart) targetEditPart).getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof BreezeModel.diagram.edit.parts.TemplateEditPart) {
			return ((BreezeModel.diagram.edit.parts.TemplateEditPart) targetEditPart).getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof BreezeModel.diagram.edit.parts.NodeTemplateEditPart) {
			return ((BreezeModel.diagram.edit.parts.NodeTemplateEditPart) targetEditPart).getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof BreezeModel.diagram.edit.parts.ProductionEditPart) {
			return ((BreezeModel.diagram.edit.parts.ProductionEditPart) targetEditPart).getMATypesForSource(relationshipType);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getTypesForTarget(IAdaptable source, IElementType relationshipType) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart instanceof BreezeModel.diagram.edit.parts.ComponentEditPart) {
			return ((BreezeModel.diagram.edit.parts.ComponentEditPart) sourceEditPart).getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof BreezeModel.diagram.edit.parts.PortEditPart) {
			return ((BreezeModel.diagram.edit.parts.PortEditPart) sourceEditPart).getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof BreezeModel.diagram.edit.parts.ConnectorEditPart) {
			return ((BreezeModel.diagram.edit.parts.ConnectorEditPart) sourceEditPart).getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof BreezeModel.diagram.edit.parts.TemplateEditPart) {
			return ((BreezeModel.diagram.edit.parts.TemplateEditPart) sourceEditPart).getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof BreezeModel.diagram.edit.parts.NodeTemplateEditPart) {
			return ((BreezeModel.diagram.edit.parts.NodeTemplateEditPart) sourceEditPart).getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof BreezeModel.diagram.edit.parts.ProductionEditPart) {
			return ((BreezeModel.diagram.edit.parts.ProductionEditPart) sourceEditPart).getMATypesForTarget(relationshipType);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public EObject selectExistingElementForSource(IAdaptable target, IElementType relationshipType) {
		return selectExistingElement(target, getTypesForSource(target, relationshipType));
	}

	/**
	 * @generated
	 */
	public EObject selectExistingElementForTarget(IAdaptable source, IElementType relationshipType) {
		return selectExistingElement(source, getTypesForTarget(source, relationshipType));
	}

	/**
	 * @generated
	 */
	protected EObject selectExistingElement(IAdaptable host, Collection types) {
		if (types.isEmpty()) {
			return null;
		}
		IGraphicalEditPart editPart = (IGraphicalEditPart) host.getAdapter(IGraphicalEditPart.class);
		if (editPart == null) {
			return null;
		}
		Diagram diagram = (Diagram) editPart.getRoot().getContents().getModel();
		HashSet<EObject> elements = new HashSet<EObject>();
		for (Iterator<EObject> it = diagram.getElement().eAllContents(); it.hasNext();) {
			EObject element = it.next();
			if (isApplicableElement(element, types)) {
				elements.add(element);
			}
		}
		if (elements.isEmpty()) {
			return null;
		}
		return selectElement((EObject[]) elements.toArray(new EObject[elements.size()]));
	}

	/**
	 * @generated
	 */
	protected boolean isApplicableElement(EObject element, Collection types) {
		IElementType type = ElementTypeRegistry.getInstance().getElementType(element);
		return types.contains(type);
	}

	/**
	 * @generated
	 */
	protected EObject selectElement(EObject[] elements) {
		Shell shell = Display.getCurrent().getActiveShell();
		ILabelProvider labelProvider = new AdapterFactoryLabelProvider(BreezeModel.diagram.part.BreezeDiagramEditorPlugin
				.getInstance().getItemProvidersAdapterFactory());
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(shell, labelProvider);
		dialog.setMessage(BreezeModel.diagram.part.Messages.BreezeModelingAssistantProviderMessage);
		dialog.setTitle(BreezeModel.diagram.part.Messages.BreezeModelingAssistantProviderTitle);
		dialog.setMultipleSelection(false);
		dialog.setElements(elements);
		EObject selected = null;
		if (dialog.open() == Window.OK) {
			selected = (EObject) dialog.getFirstResult();
		}
		return selected;
	}
}
