package com.goku.breeze.parser.model;

public class BreezeEdge extends BreezeObject {
	private String direction = null;
	private BreezePort source, target;

	public BreezeEdge(String id, String name) {
		super(id, name);
	}

	public String getDirection() {
		return this.direction;
	}

	public BreezePort getSource() {
		return this.source;
	}

	public BreezePort getTarget() {
		return this.target;
	}

	public void setDirection(String dir) {
		this.direction = dir;
	}

	public void setSource(BreezePort source) {
		this.source = source;
	}

	public void setTarget(BreezePort target) {
		this.target = target;
	}
}
