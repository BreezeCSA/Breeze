package com.goku.breeze.compiler.exception;

public class BaseException extends Exception {
	private int lineNumber, columnNumber;

	public BaseException(String message, int lineNum, int columnNum) {
		super(message);
		this.lineNumber = lineNum;
		this.columnNumber = columnNum;
	}

	public int getColumnNumber() {
		return this.columnNumber;
	}

	public int getLineNumber() {
		return this.lineNumber;
	}

	public void setColumnNumber(int columnNumber) {
		this.columnNumber = columnNumber;
	}

	public void setLineNumber(int lineNum) {
		this.lineNumber = lineNum;
	}
}
