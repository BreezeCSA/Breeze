package com.goku.breeze.model;

public class BreezeSafetyEvent {
	private String description;
	private String id;

	public BreezeSafetyEvent(String id, String desc) {
		this.id = id;
		this.description = desc;
	}

	public String getDescription() {
		return this.description;
	}

	public String getId() {
		return this.id;
	}

	public void setDescription(String desc) {
		this.description = desc;
	}

	public void setId(String st) {
		this.id = st;
	}

	@Override
	public String toString() {
		return this.id + ":" + this.description;
	}
}
