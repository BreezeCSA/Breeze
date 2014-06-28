package com.wxw.breeze.production;

public class style {
	public NodeTemplate[] nt;
	public int nt_num;
	public style(NodeTemplate[] node_tem, int nt_num) {
		super();
		this.nt_num=nt_num;
		nt=new NodeTemplate[50];
		for(int i=0;i<nt_num;i++)
		{
			
			nt[i]=new NodeTemplate(node_tem[i]);
		}
	}
	
}
