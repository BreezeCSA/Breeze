package com.goku.breeze.parser.model;

import java.util.List;
import java.util.Map;

public class BreezeArch extends BreezeObject {
	private List<BreezeEdge> edges;
	private Map<String, BreezeNode> nodes;

	public BreezeArch(String id, String name) {
		super(id, name);
	}

	public List<BreezeEdge> getEdges() {
		return this.edges;
	}

	public Map<String, BreezeNode> getNodes() {
		return this.nodes;
	}

	public void setEdges(List<BreezeEdge> edges) {
		this.edges = edges;
	}

	public void setNodes(Map<String, BreezeNode> nodes) {
		this.nodes = nodes;
	}
}
