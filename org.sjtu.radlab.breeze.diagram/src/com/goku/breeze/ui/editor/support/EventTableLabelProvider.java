package com.goku.breeze.ui.editor.support;

import java.util.HashSet;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.goku.breeze.common.SafetyAttribute;
import com.goku.breeze.compiler.model.BreezeEvent;

public class EventTableLabelProvider implements ITableLabelProvider {
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
		BreezeEvent evt = (BreezeEvent) element;
		switch (columnIndex) {
		case 0:
			return evt.getId();
		case 1:
			return evt.getProbability().toString();
		case 2:
			Object type = evt.getProperty(SafetyAttribute.EVENT_TYPE);
			if (type != null) return type.toString();
			break;
		case 3:
			Object gate = evt.getProperty(SafetyAttribute.EVENT_GATE);
			if (gate != null) return gate.toString();
			break;
		case 4:
			Object parent = evt.getProperty(SafetyAttribute.EVENT_CHILDREN);

			if (parent != null) {
				HashSet<String> children = (HashSet<String>) parent;
				StringBuilder sb = new StringBuilder();
				for (String child : children) {
					sb.append(child).append(',');
				}
				if (children.size() > 0) sb.deleteCharAt(sb.length() - 1);
				System.out.println(sb.toString());
				return sb.toString();
			}
			break;
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
