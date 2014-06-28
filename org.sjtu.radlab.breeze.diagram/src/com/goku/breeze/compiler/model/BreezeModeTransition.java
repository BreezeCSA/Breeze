package com.goku.breeze.compiler.model;

import com.goku.breeze.compiler.main.textFormatter.BaseSMVSourceFormatter;

public class BreezeModeTransition extends BreezeObject {
	public static final String TRANSITION_SRC = "source";
	public static final String TRANSITION_TGT = "target";
	public static final String TRIGGER_TYPE_RECEIVE = "receive";
	public static final String TRIGGER_TYPE_SELF = "self";
	public static final String TRIGGER_TYPE_SEND = "send";
	private BreezePort port;

	// how many port could a port connect to
	// if more than two, is transition triggered by specified event?
	public BreezeModeTransition(BreezePort port, BreezeMode srcState, BreezeMode tgtState, BreezeEvent tgr, String tgrType) {
		this.setSourceState(srcState);
		this.setTargetState(tgtState);
		this.setTrigger(tgr);
		this.setTriggerType(tgrType);
		this.port = port;
	}

	public BreezePort getPort() {
		return this.port;
	}

	public BreezeMode getSourceState() {
		return (BreezeMode) this.getProperty(BreezeObject.ATTR_SOURCE);
	}

	public BreezeMode getTargetState() {
		return (BreezeMode) this.getProperty(BreezeObject.ATTR_TARGET);
	}

	public BreezeEvent getTrigger() {
		return (BreezeEvent) this.getProperty(BreezeObject.TYPE_TRIGGER);
	}

	public String getTriggerType() {
		return (String) this.getProperty(BreezeObject.ATTR_TYPE);
	}

	public void setPort(BreezePort port) {
		this.port = port;
	}

	public void setSourceState(BreezeMode src) {
		this.setProperty(BreezeObject.ATTR_SOURCE, src);
	}

	public void setTargetState(BreezeMode tgt) {
		this.setProperty(BreezeObject.ATTR_TARGET, tgt);
	}

	public void setTrigger(BreezeEvent evt) {
		this.setProperty(BreezeObject.TYPE_TRIGGER, evt);
	}

	public void setTriggerType(String type) {
		this.setProperty(BreezeObject.ATTR_TYPE, type);
	}

	@Override
	public String transalte(BaseSMVSourceFormatter formatter) {
		// TODO Auto-generated method stub
		return "";
	}
}
