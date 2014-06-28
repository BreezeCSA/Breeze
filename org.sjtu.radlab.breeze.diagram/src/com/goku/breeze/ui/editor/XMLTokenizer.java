package com.goku.breeze.ui.editor;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class XMLTokenizer {
	public final static int CH_GAP = 0;
	public final static int CH_OTHER = 1;

	public static void main(String[] args) {
		File file = new File("/home/goku/ww.breeze");
		try {
			FileInputStream fis = new FileInputStream(file);
			byte[] buf = new byte[102400];
			int len = fis.read(buf);
			String data = new String(buf, 0, len);
			XMLTokenizer tk = new XMLTokenizer(data);
			List<GokuToken> list = tk.getTokenList();
			for (GokuToken token : list) {
				System.out.println(data.substring(token.getStart(), token.getEnd()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private final String data;

	private final List<GokuToken> list = new ArrayList<GokuToken>();

	public XMLTokenizer(String data) {
		this.data = data;
		this.split(data);
	}

	public List<GokuToken> getTokenList() {
		return this.list;
	}

	private int getType(char ch) {
		if (ch == '<' || ch == '>' || ch == '/' || Character.isWhitespace(ch) || ch == '=')
			return CH_GAP;
		return CH_OTHER;
	}

	private void split(String data) {
		int lastType = this.getType(data.charAt(0));
		GokuToken lastToken = new GokuToken(0, 1, lastType);
		boolean openQuote = data.charAt(0) == '"';
		int attrNum = 0;

		for (int i = 1; i < data.length(); ++i) {
			char ch = data.charAt(i);
			int type = this.getType(ch);

			if (type == lastType || openQuote && ch != '<' && ch != '>') {
			} else {
				lastToken.setEnd(i);
				attrNum = XMLTokenType.judgeType(lastToken, data, attrNum, lastType);
				this.list.add(lastToken);
				lastType = type;
				lastToken = new GokuToken(i, i + 1, XMLTokenType.TYPE_SP);
			}

			if (ch == '<') {
				attrNum = 0;
			} else if (ch == '"')
				openQuote = !openQuote;
		}
		lastToken.setEnd(data.length());
		XMLTokenType.judgeType(lastToken, data, attrNum, lastType);
		this.list.add(lastToken);
	}
}
