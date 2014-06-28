package com.wxw.breeze.production;

import java.util.HashMap;
import java.util.Map;

public class node {
	public String name;
	public String id;
	public int type;//1为构件 0为连接件
	public port[] pt = new port[10];
	public int port_num;
	public String availability;
	public Map<String,String> att=new HashMap<String, String>();;
	public String tr;
	{
		for (int i=0; i<10; i++)
		{
		       pt[i] = new port();
		} 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public port[] getPt() {
		return pt;
	}
	public void setPt(port[] pt) {
		this.pt = pt;
	}
	

	public node(String name, String id, int type, port[] pt, int port_num,
			String availability, Map<String, String> att, String tr) {
		super();
		this.name = name;
		this.id = id;
		this.type = type;
		this.pt = pt;
		this.port_num = port_num;
		this.availability = availability;
		this.att = att;
		this.tr = tr;
	}
	public node() {
		super();
	
	}
}
