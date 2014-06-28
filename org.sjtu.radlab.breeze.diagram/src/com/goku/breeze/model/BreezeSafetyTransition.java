package com.goku.breeze.model;

public class BreezeSafetyTransition {
	private String id = null;
	private String source, target;
	private String trigger;

	public BreezeSafetyTransition(String id, String source, String target, String trigger) {
		this.source = source;
		this.target = target;
		this.trigger = trigger;
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public String getSource() {
		return this.source;
	}

	public String getTarget() {
		return this.target;
	}

	public String getTrigger() {
		return this.trigger;
	}

	public void setSource(String src) {
		this.source = src;
	}

	public void setTarget(String tgt) {
		this.target = tgt;
	}

	public void setTrigger(String tgr) {
		this.trigger = tgr;
	}

	@Override
	public String toString() {
		return this.source.toString() + "-" + this.trigger.toString() + "->" + this.target.toString();
	}
}
