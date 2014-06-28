package com.goku.breeze.parser.model;

public class BreezeObject {
	public static final String ATTR_DIRECTION = "direction";
	public static final String ATTR_ID = "id";
	public static final String ATTR_NAME = "name";
	public static final String ATTR_SOURCE = "source";
	public static final String ATTR_TARGET = "target";
	public static final String ATTR_TYPE = "type";
	public static final String BREEZE_ARCH = "arch";
	public static final String BREEZE_LINK = "edge";
	public static final String BREEZE_NODE = "node";
	public static final String BREEZE_PORT = "port";
	private final String id;
	private final String name;

	public BreezeObject(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}
}
