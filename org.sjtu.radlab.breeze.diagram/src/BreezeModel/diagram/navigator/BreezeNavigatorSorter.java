package BreezeModel.diagram.navigator;

import org.eclipse.jface.viewers.ViewerSorter;

/**
 * @generated
 */
public class BreezeNavigatorSorter extends ViewerSorter {

	/**
	 * @generated
	 */
	private static final int GROUP_CATEGORY = 7009;

	/**
	 * @generated
	 */
	public int category(Object element) {
		if (element instanceof BreezeModel.diagram.navigator.BreezeNavigatorItem) {
			BreezeModel.diagram.navigator.BreezeNavigatorItem item = (BreezeModel.diagram.navigator.BreezeNavigatorItem) element;
			return BreezeModel.diagram.part.BreezeVisualIDRegistry.getVisualID(item.getView());
		}
		return GROUP_CATEGORY;
	}

}
