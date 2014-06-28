package com.goku.breeze.ui.editor;


public class GokuToken {
	private int end;

	private final int start;

	private int type;

	public GokuToken(int start, int end, int type) {
		this.start = start;
		this.end = end;
		this.type = type;
	}

	public int getEnd() {
		return this.end;
	}

	public int getStart() {
		return this.start;
	}

	public int getType() {
		return this.type;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public void setType(int type) {
		this.type = type;
	}
}
