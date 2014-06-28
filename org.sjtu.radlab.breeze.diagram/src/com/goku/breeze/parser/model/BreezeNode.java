package com.goku.breeze.parser.model;

import java.util.Map;

public class BreezeNode extends BreezeObject {
	private Map<String, BreezePort> ports;
	private final String type;

	public BreezeNode(String id, String name, String type) {
		super(id, name);
		this.type = type;
	}

	public Map<String, BreezePort> getPorts() {
		return this.ports;
	}

	public String getType() {
		return this.type;
	}

	public void setPorts(Map<String, BreezePort> ports) {
		this.ports = ports;
	}
}
