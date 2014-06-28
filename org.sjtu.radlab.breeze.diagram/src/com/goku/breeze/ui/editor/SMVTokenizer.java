package com.goku.breeze.ui.editor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SMVTokenizer {
	public static final int CH_DIGIT = 8;
	public static final int CH_LETTER = 1;
	public static final int CH_MINUS = 4;
	public static final int CH_SP = 0;
	public static final int CH_SYMBOL = 2;

	public static void main(String[] argv) {
		File file = new File("/home/goku/test.smv");
		try {
			FileInputStream fis = new FileInputStream(file);
			byte[] buf = new byte[102400];
			int len = fis.read(buf);
			String data = new String(buf, 0, len);
			SMVTokenizer tokenizer = new SMVTokenizer(data);
			List<GokuToken> list = tokenizer.getTokenList();
			for (GokuToken token : list) {
				System.out.println(data.substring(token.getStart(), token.getEnd()));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	Set<String> moduleNameSet = new HashSet<String>();
	List<GokuToken> tokenList = new ArrayList<GokuToken>();

	public SMVTokenizer(String str) {
		if (str.length() > 0) {
			this.split(str);
			this.split2(str);
		}
	}

	public List<GokuToken> getTokenList() {
		return this.tokenList;
	}

	private int getType(char ch) {
		if (Character.isWhitespace(ch))
			return CH_SP;
		else if (ch == '-')
			return CH_MINUS;
		else if (Character.isDigit(ch))
			return CH_DIGIT;
		else if (Character.isLetter(ch) || ch == '_' || ch == '$' || ch == '#')
			return CH_LETTER;
		else return CH_SYMBOL;
	}

	private int judgeType(int lastType, GokuToken lastToken, int currentPos, String data, int isModule) {
		switch (lastType) {
		case SMVTokenizer.CH_DIGIT:
			lastToken.setType(SMVTokenType.TYPE_DIGIT);
			break;
		case SMVTokenizer.CH_LETTER:
			lastToken.setType(SMVTokenType.judgeType(data.substring(lastToken.getStart(), currentPos)));
			break;
		case SMVTokenizer.CH_MINUS:
			lastToken.setType(SMVTokenType.TYPE_SYMBOL);
			break;
		case SMVTokenizer.CH_SP:
			lastToken.setType(SMVTokenType.TYPE_SP);
			break;
		case SMVTokenizer.CH_SYMBOL:
			lastToken.setType(SMVTokenType.TYPE_SYMBOL);
			break;
		}

		if (lastToken.getType() == SMVTokenType.TYPE_DEFINE
				&& "MODULE".equals(data.substring(lastToken.getStart(), lastToken.getEnd()))) {
			isModule = 2;
		}
		if (lastToken.getType() == SMVTokenType.TYPE_VARIABLE) {
			if (isModule == 0) {
				isModule = data.length() + 1;
				lastToken.setType(SMVTokenType.TYPE_MODULE_NAME);
				this.moduleNameSet.add(data.substring(lastToken.getStart(), lastToken.getEnd()));
			} else if (this.moduleNameSet.contains(data.substring(lastToken.getStart(), lastToken.getEnd()))) {
				lastToken.setType(SMVTokenType.TYPE_MODULE_REF);
			}
		}
		return isModule;
	}

	private void split(String data) {
		int lastType = this.getType(data.charAt(0));
		GokuToken lastToken = new GokuToken(0, 1, SMVTokenType.TYPE_SYMBOL);
		int isModule = data.length() + 1;
		for (int i = 1; i < data.length(); ++i) {
			char ch = data.charAt(i);
			int type = this.getType(ch);
			if (type == lastType || lastType == CH_LETTER && (type == CH_DIGIT || type == CH_MINUS) || lastType == CH_MINUS
					&& type == CH_SYMBOL || lastType == CH_SYMBOL && type == CH_MINUS) {
				lastToken.setEnd(i + 1);
			} else {
				isModule--;
				isModule = this.judgeType(lastType, lastToken, i, data, isModule);
				this.tokenList.add(lastToken);
				lastToken = new GokuToken(i, i + 1, SMVTokenType.TYPE_SYMBOL);
				lastType = type;
			}
		}

		this.judgeType(lastType, lastToken, data.length(), data, isModule);
		this.tokenList.add(lastToken);
	}

	private void split2(String data) {
		for (GokuToken token : this.tokenList) {
			if (token.getType() == SMVTokenType.TYPE_VARIABLE) {
				String var = data.substring(token.getStart(), token.getEnd());
				if (this.moduleNameSet.contains(var))
					token.setType(SMVTokenType.TYPE_MODULE_REF);
			}
		}
	}

}
