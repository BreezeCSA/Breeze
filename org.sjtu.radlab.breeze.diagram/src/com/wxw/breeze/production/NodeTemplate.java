package com.wxw.breeze.production;

import java.util.HashMap;
import java.util.Map;

public class NodeTemplate {
	public String name;
	public String type;//Ϊcomponent����connector
	public String id;
	public Map<String,String> att=new HashMap<String, String>();//Ϊclient����server
	public NodeTemplate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NodeTemplate(NodeTemplate nodeTemplate) {
		// TODO Auto-generated constructor stub
		this.name=nodeTemplate.name;
		this.type=nodeTemplate.type;
		this.id=nodeTemplate.id;
		this.att=nodeTemplate.att;
	}
	
}
