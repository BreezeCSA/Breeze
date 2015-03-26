package com.goku.breeze.ui.editor.support;

import java.util.Map;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.goku.breeze.compiler.model.BreezeMode;
import com.goku.breeze.ui.editor.CorrectnessEditor;

public class ModeTableLabelProvider implements ITableLabelProvider {
	private String[] propKey = null;
	private String type = null;

	public ModeTableLabelProvider(String type, String[] propKey) {
		this.type = type;
		this.propKey = propKey;
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		BreezeMode bm = (BreezeMode) element;
		Map<String, Object> prop = bm.getProperties();

		switch (columnIndex) {
		case 0:
			return bm.getId();
		case 1:
			if (CorrectnessEditor.ID.equals(this.type)) return bm.getType();
		case 2:
			if(bm.getProperty("tag")!=null)
				return bm.getProperty("tag").toString();
		default:
			Object obj = prop.get(this.propKey[columnIndex]);
			if (obj != null) return obj.toString();

		}
		return "";
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

}
