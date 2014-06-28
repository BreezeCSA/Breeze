package com.goku.breeze.compiler.model;

import java.util.HashMap;
import java.util.Map;

import com.goku.breeze.compiler.main.textFormatter.BaseSMVSourceFormatter;

public class BreezePort extends BreezeObject {
	public static final String PORT_IN = "in";
	public static final String PORT_IN_OUT = "in/out";
	public static final String PORT_OUT = "out";

	private boolean isInPort = false;
	private boolean isOutPort = false;
	private BreezeNode owner = null;
	private final Map<String, BreezePort> sources = new HashMap<String, BreezePort>();

	private final Map<String, BreezePort> targets = new HashMap<String, BreezePort>();

	public BreezePort(String id, String name, String type, BreezeNode owner) {
		this.setId(id);
		this.setType(type);
		this.setName(name);
		this.owner = owner;
	}

	public void addSource(BreezePort src) {
		this.sources.put(src.getId(), src);
	}

	public void addTarget(BreezePort tgt) {
		this.sources.put(tgt.getId(), tgt);
	}

	public String getId() {
		return (String) this.getProperty(BreezeObject.ATTR_ID);
	}

	public String getName() {
		return (String) this.getProperty(ATTR_NAME);
	}

	public BreezeNode getOwner() {
		return this.owner;
	}

	public String getSmvInName(String target) {
		return this.getId() + target;
	}

	public String getSmvOutName() {
		return this.getId();
	}

	public Map<String, BreezePort> getSource() {
		return this.sources;
	}

	public Map<String, BreezePort> getTarget() {
		return this.targets;
	}

	public String getType() {
		return (String) this.getProperty(BreezeObject.ATTR_TYPE);
	}

	public boolean isInPort() {
		return this.isInPort;
	}

	public boolean isOutPort() {
		return this.isOutPort;
	}

	public void setId(String id) {
		this.setProperty(BreezeObject.ATTR_ID, id);
	}

	public void setName(String name) {
		this.setProperty(ATTR_NAME, name);
	}

	public void setType(String type) {
		this.setProperty(BreezeObject.ATTR_TYPE, type);
		if (PORT_IN_OUT.equals(type)) {
			this.isInPort = true;
			this.isOutPort = true;
		} else if (PORT_IN.equals(type))
			this.isInPort = true;
		else if (PORT_OUT.equals(type)) this.isOutPort = true;
	}

	@Override
	public String transalte(BaseSMVSourceFormatter formatter) {
		// TODO Auto-generated method stub
		return null;
	}

}
