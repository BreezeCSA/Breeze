package com.goku.breeze.compiler.main.textFormatter;

import com.goku.breeze.compiler.smv.SMVElement;

public class SimpleSMVSourceFormatter extends BaseSMVSourceFormatter {

	@Override
	public String formatKeywordAssign() {
		// TODO Auto-generated method stub
		return '\t' + SMVElement.KW_ASSIGN;
	}

	@Override
	public String formatKeywordCase() {
		// TODO Auto-generated method stub
		return SMVElement.KW_CASE_START;
	}

	@Override
	public String formatKeywordEsac() {
		// TODO Auto-generated method stub
		return "\t\t" + SMVElement.KW_CASE_END;
	}

	@Override
	public String formatKeywordModule() {
		// TODO Auto-generated method stub
		return SMVElement.KW_MODULE;
	}

	@Override
	public String formatKeywordVAR() {
		// TODO Auto-generated method stub
		return '\t' + SMVElement.KW_VAR;
	}

	@Override
	public String formatOther(String word, boolean isLineStart, int depth) {
		// TODO Auto-generated method stub
		if (!isLineStart)
			return word;
		else {
			switch (depth) {
			case 0:
				return word;
			case 1:
				return '\t' + word;
			case 2:
				return "\t\t" + word;
			case 3:
				return "\t\t\t" + word;
			default:
				return "\t\t\t\t" + word;
			}
		}
	}

	@Override
	public String formatSymbolAnd() {
		// TODO Auto-generated method stub
		return " " + SMVElement.OP_AND + " ";
	}

	@Override
	public String formatSymbolAssign() {
		// TODO Auto-generated method stub
		return " " + SMVElement.OP_ASSIGN + " ";
	}

	@Override
	public String formatSymbolColon() {
		// TODO Auto-generated method stub
		return " " + SMVElement.COLON + " ";
	}

	@Override
	public String formatSymbolEqual() {
		// TODO Auto-generated method stub
		return " " + SMVElement.OP_EQUAL + " ";
	}

	@Override
	public String formatSymbolGT() {
		// TODO Auto-generated method stub
		return " " + SMVElement.OP_GT + " ";
	}

	@Override
	public String formatSymbolLeftBrace() {
		// TODO Auto-generated method stub
		return SMVElement.BRACE_LEFT + " ";
	}

	@Override
	public String formatSymbolLT() {
		// TODO Auto-generated method stub
		return " " + SMVElement.OP_LT + " ";
	}

	@Override
	public String formatSymbolRightBrace() {
		// TODO Auto-generated method stub
		return " " + SMVElement.BRACE_RIGHT;
	}

	@Override
	public String formatSymbolSemiColon() {
		// TODO Auto-generated method stub
		return SMVElement.SEMICOLON + "";
	}

}
