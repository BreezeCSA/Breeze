package com.goku.breeze.compiler.model;

import com.goku.breeze.compiler.main.textFormatter.BaseSMVSourceFormatter;

public class BreezeEvent extends BreezeObject {
	private int eventNumber;

	public BreezeEvent(String id, int number, String prob) {
		this.setProperty(BreezeObject.ATTR_ID, id);
		this.eventNumber = number;
		this.setProperty(BreezeObject.ATTR_PROBABILITY, prob == null ? "100" : prob);
	}

	public String getId() {
		return (String) this.getProperty(BreezeObject.ATTR_ID);
	}

	public Integer getNumber() {
		return this.eventNumber;
	}

	public String getProbability() {
		return (String) this.getProperty(BreezeObject.ATTR_PROBABILITY);
	}

	public String getType() {
		return (String) this.getProperty(BreezeObject.ATTR_TYPE);
	}

	public void setEventNumber(Integer eventNumber) {
		this.eventNumber = eventNumber;
	}

	public void setId(String id) {
		this.setProperty(BreezeObject.ATTR_ID, id);
	}

	public void setProbability(String prob) {
		this.setProperty(BreezeObject.ATTR_PROBABILITY, prob);
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
