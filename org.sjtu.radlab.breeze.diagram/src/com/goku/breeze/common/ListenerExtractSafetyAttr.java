package com.goku.breeze.common;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.dom4j.Element;

import com.goku.breeze.common.SafetyAttribute;
import com.goku.breeze.compiler.event.ParseEvent;
import com.goku.breeze.compiler.event.ParseListener;
import com.goku.breeze.compiler.model.BreezeEvent;
import com.goku.breeze.compiler.model.BreezeMode;
import com.goku.breeze.compiler.model.BreezeObject;

public class ListenerExtractSafetyAttr implements ParseListener {

	@Override
	public void handleParsing(ParseEvent event) {
		// TODO Auto-generated method stub
		Element container = event.parseElement;
		BreezeObject obj = event.container;
		if (obj instanceof BreezeMode) {
			BreezeMode bm = (BreezeMode) obj;
			Map<String, Object> prop = bm.getProperties();
			this.setAttributeValue(prop, SafetyAttribute.MODE_DETECTION, container, "1");
			this.setAttributeValue(prop, SafetyAttribute.MODE_OCCURRENCE, container, "1");
			this.setAttributeValue(prop, SafetyAttribute.MODE_SEVERITY, container, "1");
			this.setAttributeValue(prop, SafetyAttribute.MODE_HUMAN_FACTOR, container, "");
			this.setAttributeValue(prop, SafetyAttribute.MODE_ECONOMY, container, "");
			this.setAttributeValue(prop, SafetyAttribute.MODE_ADVICE, container, "");
			this.setAttributeValue(prop, SafetyAttribute.MODE_CAUSE, container, "");
			this.setAttributeValue(prop, SafetyAttribute.MODE_DEADLINE, container, "");
			this.setAttributeValue(prop, SafetyAttribute.MODE_EFFECT, container, "");
			this.setAttributeValue(prop, SafetyAttribute.MODE_FUNCTION, container, "");
			this.setAttributeValue(prop, SafetyAttribute.MODE_MEASURE, container, "");
			this.setAttributeValue(prop, SafetyAttribute.MODE_RPN, container, "");
		} else if (obj instanceof BreezeEvent) {
			BreezeEvent eventObj = (BreezeEvent) obj;
			Map<String, Object> prop = eventObj.getProperties();

			this.setAttributeValue(prop, SafetyAttribute.EVENT_GATE, container, SafetyAttribute.EVENT_GATE_AND);
			this.setAttributeValue(prop, SafetyAttribute.EVENT_TYPE, container, SafetyAttribute.EVENT_TYPE_ATMOIC);
			if (prop.get(SafetyAttribute.EVENT_CHILDREN) == null) {
				Set<String> children = new HashSet<String>();
				String childrenStr = container.attributeValue(SafetyAttribute.EVENT_CHILDREN);
				if (childrenStr != null) {
					String[] list = childrenStr.split(",");
					for (String child : list)
						if (child.trim().length() > 0) children.add(child.trim());
				}
				prop.put(SafetyAttribute.EVENT_CHILDREN, children);
			}
		}
	}

	private void setAttributeValue(Map<String, Object> prop, String key, Element container, String defaultValue) {
		if (prop.get(key) == null) prop.put(key, container.attributeValue(key, defaultValue));
	}

}
