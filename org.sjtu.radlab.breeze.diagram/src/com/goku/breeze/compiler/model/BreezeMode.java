package com.goku.breeze.compiler.model;

import com.goku.breeze.compiler.main.textFormatter.BaseSMVSourceFormatter;

public class BreezeMode extends BreezeObject {
	public static final String BREEZE_MODE_NAME = "mode";
	public static final String TYPE_FAILURE = "failure";
	public static final String TYPE_INITIAL = "initial";
	public static final String TYPE_NORMAL = "normal";
	public static final String TYPE_STOP = "stopped";
	private int counter = 0;
	private final boolean isBreezeMode;
	private final boolean isInitState;
	private final String smvName;

	public BreezeMode(String id, String type, boolean isBM) {
		this.setId(id);
		this.setType(type);
		this.isInitState = TYPE_INITIAL.equals(type);
		this.isBreezeMode = isBM;
		this.smvName = type + "_" + id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BreezeMode) {
			BreezeMode ins = (BreezeMode) obj;
			String id = this.getId();
			String type = this.getType();
			return id != null && id.equals(ins.getId()) && type != null && type.equals(ins.getType());
		}
		return false;
	}

	public String generateInternalStateName(String srcName, String tgtName) {
		return srcName == null ? "" : srcName + "_" + tgtName + this.counter++;
	}

	public String getId() {
		Object value = this.getProperty(BreezeObject.ATTR_ID);
		return (String) value;
	}

	public String getSmvName() {
		return this.smvName;
	}

	public String getType() {
		return (String) this.getProperty(BreezeObject.ATTR_TYPE);
	}

	public boolean isBreezeMode() {
		return this.isBreezeMode;
	}

	public boolean isInitState() {
		return this.isInitState;
	}

	public void setId(String id) {
		this.setProperty(BreezeObject.ATTR_ID, id);
	}

	public void setType(String type) {
		this.setProperty(BreezeObject.ATTR_TYPE, type);
	}

	@Override
	public String transalte(BaseSMVSourceFormatter formatter) {
		// TODO Auto-generated method stub
		return "";
	}
}
