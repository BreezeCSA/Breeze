package com.goku.breeze.ui.editor;

import java.util.List;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class SMVSourceTokenScanner implements ITokenScanner {
	private static IToken DECL = new Token(new TextAttribute(new Color(Display.getCurrent(), new RGB(72, 183, 189))));
	private static IToken DEFINE = new Token(new TextAttribute(new Color(Display.getCurrent(), new RGB(52, 163, 152))));
	private static IToken DIGIT = new Token(new TextAttribute(new Color(Display.getCurrent(), new RGB(255, 0, 0))));
	private static IToken EXPR = new Token(new TextAttribute(new Color(Display.getCurrent(), new RGB(231, 154, 36))));
	private static IToken FUNC = new Token(new TextAttribute(new Color(Display.getCurrent(), new RGB(36, 164, 231))));
	private static IToken KEY = new Token(new TextAttribute(new Color(Display.getCurrent(), new RGB(197, 192, 13))));
	private static IToken MODULE_NAME = new Token(new TextAttribute(new Color(Display.getCurrent(), new RGB(136, 197, 13))));
	private static IToken MODULE_REF = new Token(new TextAttribute(new Color(Display.getCurrent(), new RGB(181, 66, 89))));
	private static IToken SP = new Token(new TextAttribute(new Color(Display.getCurrent(), new RGB(10, 250, 80))));
	private static IToken SYMBOL = new Token(new TextAttribute(new Color(Display.getCurrent(), new RGB(0, 0, 0))));
	private static IToken VARIABLE = new Token(new TextAttribute(new Color(Display.getCurrent(), new RGB(14, 39, 185))));
	private String data;
	int lastIndex = -1;
	private int offset, length;
	List<GokuToken> tokenList;

	public SMVSourceTokenScanner() {
	}

	@Override
	public int getTokenLength() {
		if (this.tokenList == null || this.lastIndex >= this.tokenList.size())
			return this.data.length();
		return this.tokenList.get(this.lastIndex).getEnd() - this.tokenList.get(this.lastIndex).getStart();
	}

	@Override
	public int getTokenOffset() {
		if (this.tokenList == null || this.lastIndex >= this.tokenList.size())
			return this.data.length();
		return this.tokenList.get(this.lastIndex).getStart();
	}

	@Override
	public IToken nextToken() {
		this.lastIndex++;
		if (this.tokenList == null || this.lastIndex >= this.tokenList.size())
			return Token.EOF;
		GokuToken smvToken = this.tokenList.get(this.lastIndex);
		switch (smvToken.getType()) {
		case SMVTokenType.TYPE_DECL:
			return DECL;
		case SMVTokenType.TYPE_DEFINE:
			return DEFINE;
		case SMVTokenType.TYPE_DIGIT:
			return DIGIT;
		case SMVTokenType.TYPE_EXPR:
			return EXPR;
		case SMVTokenType.TYPE_FUNC:
			return FUNC;
		case SMVTokenType.TYPE_KEY:
			return KEY;
		case SMVTokenType.TYPE_SYMBOL:
			return SYMBOL;
		case SMVTokenType.TYPE_VARIABLE:
			return VARIABLE;
		case SMVTokenType.TYPE_MODULE_NAME:
			return MODULE_NAME;
		case SMVTokenType.TYPE_MODULE_REF:
			return MODULE_REF;
		}
		return SP;
	}

	@Override
	public void setRange(IDocument document, int offset, int length) {
		this.offset = offset;
		this.length = length;
		this.data = document.get();
		this.lastIndex = -1;
		SMVTokenizer tokenizer = new SMVTokenizer(this.data);
		this.tokenList = tokenizer.getTokenList();
	}

}
