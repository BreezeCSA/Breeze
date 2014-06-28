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

public class XMLSourceTokenScanner implements ITokenScanner {
	private static final IToken ATTR_KEY = new Token(new TextAttribute(new Color(Display.getCurrent(), new RGB(22, 219, 23))));
	private static final IToken ATTR_VAL = new Token(new TextAttribute(new Color(Display.getCurrent(), new RGB(219, 192, 22))));
	private static final IToken DEFAULT = new Token(new TextAttribute(new Color(Display.getCurrent(), new RGB(0, 0, 0))));
	private static final IToken EXPLAIN_TAG = new Token(new TextAttribute(new Color(Display.getCurrent(), new RGB(208, 41, 61))));
	private static final IToken NORMAL_TAG = new Token(new TextAttribute(new Color(Display.getCurrent(), new RGB(30, 22, 219))));
	private String data;
	private int lastIndex = -1;
	private int offset, length;
	private List<GokuToken> tokenList;

	@Override
	public int getTokenLength() {
		// TODO AutSystem.out.println("get token");
		if (this.tokenList == null || this.lastIndex >= this.tokenList.size())
			return this.data.length();
		return this.tokenList.get(this.lastIndex).getEnd() - this.tokenList.get(this.lastIndex).getStart();
	}

	@Override
	public int getTokenOffset() {
		// TODO Auto-generated method stub
		if (this.tokenList == null || this.lastIndex >= this.tokenList.size())
			return this.data.length();
		return this.tokenList.get(this.lastIndex).getStart();
	}

	@Override
	public IToken nextToken() {
		// TODO Auto-generated method stub
		this.lastIndex++;
		if (this.tokenList == null || this.lastIndex >= this.tokenList.size())
			return Token.EOF;
		GokuToken xmlToken = this.tokenList.get(this.lastIndex);
		switch (xmlToken.getType()) {
		case XMLTokenType.TYPE_ATTR_KEY:
			return ATTR_KEY;
		case XMLTokenType.TYPE_ATTR_VALUE:
			return ATTR_VAL;
		case XMLTokenType.TYPE_TAG_EXPLAIN:
			return EXPLAIN_TAG;
		case XMLTokenType.TYPE_TAG_NORMAL:
			return NORMAL_TAG;
		}
		return DEFAULT;

	}

	@Override
	public void setRange(IDocument document, int offset, int length) {
		// TODO Auto-generated method stub
		this.offset = offset;
		this.length = length;
		this.data = document.get();
		this.lastIndex = -1;
		XMLTokenizer tokenizer = new XMLTokenizer(this.data);
		this.tokenList = tokenizer.getTokenList();
	}

}
