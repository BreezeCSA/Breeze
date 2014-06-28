package com.goku.breeze.model;

import java.util.List;

public class TraceNode {
	private List<TraceNode> children;
	private String id, value, name;
	private TraceNode parent;

	public TraceNode(String id, String value, String name) {
		this.setId(id);
		this.setName(name);
		this.setValue(value);
	}

	public List<TraceNode> getChildren() {
		return this.children;
	}

	public String getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public TraceNode getParent() {
		return this.parent;
	}

	public String getValue() {
		return this.value;
	}

	public void setChildren(List<TraceNode> children) {
		this.children = children;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParent(TraceNode node) {
		this.parent = node;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
