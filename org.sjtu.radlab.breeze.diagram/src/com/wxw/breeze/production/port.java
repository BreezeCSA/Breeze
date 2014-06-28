package com.wxw.breeze.production;

public class port {
	String id;
	int direction;//0代表in,1代表out,2代表 inout
	public int multiaccess;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
}
