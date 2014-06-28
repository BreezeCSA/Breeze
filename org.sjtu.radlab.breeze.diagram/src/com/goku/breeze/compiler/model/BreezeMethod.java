package com.goku.breeze.compiler.model;

import java.util.HashMap;
import java.util.Map;

public class BreezeMethod {
	private final Map<Integer, Integer> signalMap = new HashMap<Integer, Integer>();

	public void addSignal(Integer input, Integer response) {
		this.signalMap.put(input, response);
	}

	public Map<Integer, Integer> getSignalMap() {
		return this.signalMap;
	}
}
