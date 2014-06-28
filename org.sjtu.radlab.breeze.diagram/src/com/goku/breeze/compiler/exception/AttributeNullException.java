package com.goku.breeze.compiler.exception;

public class AttributeNullException extends BaseException {

	public AttributeNullException(String attribute, int lineNum, int columnNum) {
		super("The attribute " + attribute + " is null", lineNum, columnNum);
		// TODO Auto-generated constructor stub
	}

}
