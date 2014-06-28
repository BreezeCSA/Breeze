package com.goku.breeze.common;

import java.util.Map;

import org.dom4j.Element;

import com.goku.breeze.compiler.event.ParseEvent;
import com.goku.breeze.compiler.event.ParseListener;
import com.goku.breeze.compiler.model.BreezeObject;
import com.goku.breeze.compiler.model.BreezePort;

public class ListenerExtractPortName implements ParseListener {

	@Override
	public void handleParsing(ParseEvent event) {
		// TODO Auto-generated method stub
		Element container = event.parseElement;
		BreezeObject obj = event.container;
		if (obj instanceof BreezePort) {
			BreezePort bp = (BreezePort) obj;
			Map<String, Object> prop = bp.getProperties();
			prop.put(BreezeObject.ATTR_NAME, container.attributeValue(BreezeObject.ATTR_NAME, ""));
		}
	}

}
