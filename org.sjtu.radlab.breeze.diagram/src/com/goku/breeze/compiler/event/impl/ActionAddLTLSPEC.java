package com.goku.breeze.compiler.event.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.goku.breeze.compiler.event.TranslationEvent;
import com.goku.breeze.compiler.event.TranslationListener;
import com.goku.breeze.compiler.main.textFormatter.BaseSMVSourceFormatter;
import com.goku.breeze.compiler.model.BreezeMode;
import com.goku.breeze.compiler.model.BreezeNode;
import com.goku.breeze.compiler.model.BreezeObject;
import com.goku.breeze.compiler.smv.SMVElement;

public class ActionAddLTLSPEC implements TranslationListener {
	private static final String ltlspecTemplate = "LTLSPEC G ( ${modeName} = ${modeValue} -> ( F ${modeName} != ${modeValue}) );\n";

	private Map<String, Set<String>> listToAddSpec = null;

	public ActionAddLTLSPEC(Map<String, Set<String>> listToAddSpec) {
		this.listToAddSpec = listToAddSpec;
	}

	@Override
	public void handleTranslation(TranslationEvent event) {
		// TODO Auto-generated method stub
		BreezeObject obj = event.targetObject;
		if (!(obj instanceof BreezeNode)) return;
		BreezeNode node = (BreezeNode) obj;
		StringBuilder sb = event.translationBuffer;
		BaseSMVSourceFormatter formatter = event.formatter;
		if (node == null || sb == null || formatter == null) return;
		Set<String> specList = null;
		if (this.listToAddSpec != null) {
			specList = this.listToAddSpec.get(node.getId());
			if (specList == null) return;
		}
		List<BreezeMode> modeList = node.getModeList();
		for (BreezeMode bm : modeList) {
			if (!BreezeMode.TYPE_NORMAL.equals(bm.getType())) continue;

			if (specList != null && !specList.contains(bm.getId())) continue;
			String row = ltlspecTemplate.replaceAll("\\$\\{modeName\\}", SMVElement.DECL_MODE_NAME).replaceAll(
					"\\$\\{modeValue\\}", bm.getSmvName());
			sb.append(formatter.format(row, true, 2));
		}
		sb.append(SMVElement.NEWLINE);
	}

}
