package com.wxw.breeze.production;

public class production {
	public String id;
	public architecture left=new architecture();
	public architecture right=new architecture();
	public production(architecture left, architecture right) {
		super();
		this.left = left;
		this.right = right;
	}
	public production(production pro)
	{
		this.id=pro.id;
		this.left=pro.left;
		this.right=pro.right;
		
	}
	
}
