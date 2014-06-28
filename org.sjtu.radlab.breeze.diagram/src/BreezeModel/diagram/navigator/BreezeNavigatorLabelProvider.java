package BreezeModel.diagram.navigator;

import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ITreePathLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

/**
 * @generated
 */
public class BreezeNavigatorLabelProvider extends LabelProvider implements ICommonLabelProvider, ITreePathLabelProvider {

	/**
	 * @generated
	 */
	static {
		BreezeModel.diagram.part.BreezeDiagramEditorPlugin.getInstance().getImageRegistry()
				.put("Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
		BreezeModel.diagram.part.BreezeDiagramEditorPlugin.getInstance().getImageRegistry()
				.put("Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	@Override
	public String getDescription(Object anElement) {
		return null;
	}

	/**
	 * @generated
	 */
	private String getGoku_2003Text(View view) {
		IParser parser = BreezeModel.diagram.providers.BreezeParserProvider.getParser(
				BreezeModel.diagram.providers.BreezeElementTypes.Goku_2003, view.getElement() != null ? view.getElement() : view,
				BreezeModel.diagram.part.BreezeVisualIDRegistry
						.getType(BreezeModel.diagram.edit.parts.GokuDocumentationPowerEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			BreezeModel.diagram.part.BreezeDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5014); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	@Override
	public Image getImage(Object element) {
		System.out.println("getImage:" + element);
		if (element instanceof BreezeModel.diagram.navigator.BreezeNavigatorGroup) {
			BreezeModel.diagram.navigator.BreezeNavigatorGroup group = (BreezeModel.diagram.navigator.BreezeNavigatorGroup) element;
			return BreezeModel.diagram.part.BreezeDiagramEditorPlugin.getInstance().getBundledImage(group.getIcon());
		}

		if (element instanceof BreezeModel.diagram.navigator.BreezeNavigatorItem) {
			BreezeModel.diagram.navigator.BreezeNavigatorItem navigatorItem = (BreezeModel.diagram.navigator.BreezeNavigatorItem) element;
			if (!this.isOwnView(navigatorItem.getView())) {
				return super.getImage(element);
			}
			return this.getImage(navigatorItem.getView());
		}

		return super.getImage(element);
	}

	/**
	 * @generated
	 */
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = BreezeModel.diagram.part.BreezeDiagramEditorPlugin.getInstance().getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null && elementType != null
				&& BreezeModel.diagram.providers.BreezeElementTypes.isKnownElementType(elementType)) {
			image = BreezeModel.diagram.providers.BreezeElementTypes.getImage(elementType);
			imageRegistry.put(key, image);
		}

		if (image == null) {
			image = imageRegistry.get("Navigator?ImageNotFound"); //$NON-NLS-1$
			imageRegistry.put(key, image);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public Image getImage(View view) {
		System.out.println("getImage:" + view);
		switch (BreezeModel.diagram.part.BreezeVisualIDRegistry.getVisualID(view)) {
		case BreezeModel.diagram.edit.parts.GokuEditPart.VISUAL_ID:
			return this
					.getImage(
							"Navigator?TopLevelNode?http://org.sjtu.radlab.breeze/v2?goku", BreezeModel.diagram.providers.BreezeElementTypes.Goku_2003); //$NON-NLS-1$
		}
		return this.getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	@Override
	public String getText(Object element) {
		if (element instanceof BreezeModel.diagram.navigator.BreezeNavigatorGroup) {
			BreezeModel.diagram.navigator.BreezeNavigatorGroup group = (BreezeModel.diagram.navigator.BreezeNavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof BreezeModel.diagram.navigator.BreezeNavigatorItem) {
			BreezeModel.diagram.navigator.BreezeNavigatorItem navigatorItem = (BreezeModel.diagram.navigator.BreezeNavigatorItem) element;
			if (!this.isOwnView(navigatorItem.getView())) {
				return null;
			}
			return this.getText(navigatorItem.getView());
		}

		return super.getText(element);
	}

	/**
	 * @generated
	 */
	public String getText(View view) {
		if (view.getElement() != null && view.getElement().eIsProxy()) {
			return this.getUnresolvedDomainElementProxyText(view);
		}
		switch (BreezeModel.diagram.part.BreezeVisualIDRegistry.getVisualID(view)) {
		case BreezeModel.diagram.edit.parts.GokuEditPart.VISUAL_ID:
			return this.getGoku_2003Text(view);
		}
		return this.getUnknownElementText(view);
	}

	/**
	 * @generated
	 */
	private String getUnknownElementText(View view) {
		return "<UnknownElement Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	private String getUnresolvedDomainElementProxyText(View view) {
		return "<Unresolved domain element Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	@Override
	public void init(ICommonContentExtensionSite aConfig) {
	}

	/**
	 * @generated
	 */
	private boolean isOwnView(View view) {
		return BreezeModel.diagram.edit.parts.BreezeEditPart.MODEL_ID.equals(BreezeModel.diagram.part.BreezeVisualIDRegistry
				.getModelID(view));
	}

	/**
	 * @generated
	 */
	@Override
	public void restoreState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	@Override
	public void saveState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	@Override
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof BreezeModel.diagram.navigator.BreezeNavigatorItem
				&& !this.isOwnView(((BreezeModel.diagram.navigator.BreezeNavigatorItem) element).getView())) {
			return;
		}
		label.setText(this.getText(element));
		label.setImage(this.getImage(element));
	}

}
