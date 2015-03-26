package com.wxw.breeze.production;

public class edge {
	public String id;
	public String source_port;
	public String target_port;
	public String source_node;
	public String target_node;
	
	
	
	public edge() {
		this.id=new String();
		this.source_node=new String();
		this.target_node=new String();
		this.source_port=new String();
		this.target_port=new String();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSource_port() {
		return source_port;
	}
	public void setSource_port(String source_port) {
		this.source_port = source_port;
	}
	public String getTarget_port() {
		return target_port;
	}
	public void setTarget_port(String target_port) {
		this.target_port = target_port;
	}
	public String getSource_node() {
		return source_node;
	}
	public void setSource_node(String source_node) {
		this.source_node = source_node;
	}
	public String getTarget_node() {
		return target_node;
	}
	public void setTarget_node(String target_node) {
		this.target_node = target_node;
	}
	
}
