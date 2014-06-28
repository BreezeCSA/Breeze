package com.goku.breeze.ui.editor;

public class XMLTokenType {
	public final static int TYPE_ATTR_KEY = 0;
	public final static int TYPE_ATTR_VALUE = 1;
	public final static int TYPE_SP = 8;
	public final static int TYPE_TAG_EXPLAIN = 4;
	public final static int TYPE_TAG_NORMAL = 2;

	public static int judgeType(GokuToken token, String data, int attrNum, int lastType) {
		char ch = data.charAt(token.getStart());
		if (lastType == XMLTokenizer.CH_GAP)
			token.setType(TYPE_SP);
		else {
			if (ch == '?') {
				token.setType(TYPE_TAG_EXPLAIN);
				return 1;
			} else if (ch == '"') {
				token.setType(TYPE_ATTR_VALUE);
				return attrNum;
			} else if (attrNum == 0) {
				token.setType(TYPE_TAG_NORMAL);
				return 1;
			} else {
				token.setType(TYPE_ATTR_KEY);
				return ++attrNum;
			}
		}
		return attrNum;
	}
}
