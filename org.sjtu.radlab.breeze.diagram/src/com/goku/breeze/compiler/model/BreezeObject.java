package com.goku.breeze.compiler.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.goku.breeze.compiler.main.textFormatter.BaseSMVSourceFormatter;

public abstract class BreezeObject implements Serializable {
	public static final String ATTR_ID = "ID";
	public static final String ATTR_KEY = "key";
	public static final String ATTR_MODE = "mode";
	public static final String ATTR_NAME = "name";
	public static final String ATTR_PARAMETER = "parameter";
	public static final String ATTR_PROBABILITY = "probability";
	public static final String ATTR_REF = "IDREF";
	public static final String ATTR_SOURCE = "source";
	public static final String ATTR_SOURCE_PORT = "sourcePort";
	public static final String ATTR_TARGET = "target";
	public static final String ATTR_TARGET_PORT = "targetPort";
	public static final String ATTR_TYPE = "type";
	public static final String ATTR_VALUE = "value";
	public static final String TYPE_ARCH = "arch";
	public static final String TYPE_ATTRIBUTE = "attribute";
	public static final String TYPE_EVENT = "event";
	public static final String TYPE_METHOD = "method";
	public static final String TYPE_MODE = "mode";
	public static final String TYPE_NODE = "node";
	public static final String TYPE_PARAMETER = "parameter";
	public static final String TYPE_PORT = "port";
	public static final String TYPE_PORT_CONNECTION = "edge";
	public static final String TYPE_RETURN = "return";
	public static final String TYPE_ROOT = "breeze";
	public static final String TYPE_SAFATY_SPECIFICATION = "SafetySpecification";
	public static final String TYPE_STATE = "state";
	public static final String TYPE_TRANSITION = "transition";
	public static final String TYPE_TRIGGER = "trigger";
	public static final String ATTR_TAG = "tag";

	private final Map<String, Object> properties = new HashMap<String, Object>();

	public Map<String, Object> getProperties() {
		return this.properties;
	}

	public Object getProperty(String key) {
		return this.properties.get(key);
	}

	public void setProperty(String key, Object value) {
		this.properties.put(key, value);
	}

	public abstract String transalte(BaseSMVSourceFormatter formatter);
}
