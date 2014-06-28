package com.goku.breeze.compiler.main.textFormatter;

import com.goku.breeze.compiler.smv.SMVElement;

public abstract class BaseSMVSourceFormatter {
	public String format(char ch, boolean isLineStart, int depth) {
		switch (ch) {
		case SMVElement.BRACE_LEFT:
			return this.formatSymbolLeftBrace();
		case SMVElement.BRACE_RIGHT:
			return this.formatSymbolRightBrace();
		case SMVElement.SEMICOLON:
			return this.formatSymbolSemiColon();
		case SMVElement.COLON:
			return this.formatSymbolColon();
		case SMVElement.OP_GT:
			return this.formatSymbolGT();
		case SMVElement.OP_LT:
			return this.formatSymbolLT();
		case SMVElement.OP_AND:
			return this.formatSymbolAnd();
		case SMVElement.OP_EQUAL:
			return this.formatSymbolEqual();
		default:
			return "" + ch;
		}
	}

	public String format(String word, boolean isLineStart, int depth) {
		if (SMVElement.KW_ASSIGN.equals(word))
			return this.formatKeywordAssign();
		else if (SMVElement.KW_CASE_END.equals(word))
			return this.formatKeywordEsac();
		else if (SMVElement.KW_CASE_START.equals(word))
			return this.formatKeywordCase();
		else if (SMVElement.KW_MODULE.equals(word))
			return this.formatKeywordModule();
		else if (SMVElement.KW_VAR.equals(word))
			return this.formatKeywordVAR();
		else if (SMVElement.OP_ASSIGN.equals(word))
			return this.formatSymbolAssign();
		else return this.formatOther(word, isLineStart, depth);
	}

	public abstract String formatKeywordAssign();

	public abstract String formatKeywordCase();

	public abstract String formatKeywordEsac();

	public abstract String formatKeywordModule();

	public abstract String formatKeywordVAR();

	public abstract String formatOther(String word, boolean isLineStart, int depth);

	public abstract String formatSymbolAnd();

	public abstract String formatSymbolAssign();

	public abstract String formatSymbolColon();

	public abstract String formatSymbolEqual();

	public abstract String formatSymbolGT();

	public abstract String formatSymbolLeftBrace();

	public abstract String formatSymbolLT();

	public abstract String formatSymbolRightBrace();

	public abstract String formatSymbolSemiColon();
}
