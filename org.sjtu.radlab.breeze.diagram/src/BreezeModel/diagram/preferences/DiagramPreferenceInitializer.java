package BreezeModel.diagram.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.goku.breeze.ui.preference.SafetyPreferencePage;

/**
 * @generated
 */
public class DiagramPreferenceInitializer extends AbstractPreferenceInitializer {

	/**
	 * @generated
	 */
	protected IPreferenceStore getPreferenceStore() {
		return BreezeModel.diagram.part.BreezeDiagramEditorPlugin.getInstance().getPreferenceStore();
	}

	/**
	 * @generated
	 */
	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = this.getPreferenceStore();
		BreezeModel.diagram.preferences.DiagramGeneralPreferencePage.initDefaults(store);
		BreezeModel.diagram.preferences.DiagramAppearancePreferencePage.initDefaults(store);
		BreezeModel.diagram.preferences.DiagramConnectionsPreferencePage.initDefaults(store);
		BreezeModel.diagram.preferences.DiagramPrintingPreferencePage.initDefaults(store);
		BreezeModel.diagram.preferences.DiagramRulersAndGridPreferencePage.initDefaults(store);
		SafetyPreferencePage.initDefaults(store);
	}
}
