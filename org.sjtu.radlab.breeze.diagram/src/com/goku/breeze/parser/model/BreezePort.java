package com.goku.breeze.parser.model;

public class BreezePort extends BreezeObject {
	private BreezeNode container;
	private String direction;

	public BreezePort(String id, String name, String direction) {
		super(id, name);
		this.direction = direction;
	}

	public BreezeNode getContainer() {
		return this.container;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setContainer(BreezeNode cnt) {
		this.container = cnt;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
}
