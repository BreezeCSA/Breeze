package com.wxw.breeze.production;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;

public class ForProtected extends GraphicalEditPart {

	private String eg=super.elementGuid;
	public ForProtected(EObject model) {
		super(model);
		// TODO Auto-generated constructor stub
	}
	public String getEg() {
		return eg;
	}
	public void setEg(String eg) {
		this.eg = eg;
	}
	
}
