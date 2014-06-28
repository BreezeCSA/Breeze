package com.goku.breeze.ui.editor;

import java.util.HashSet;
import java.util.Set;

public class SMVTokenType {
	static String[] declAry = new String[] { "mod", "union", "in", "xor", "xnor", "self", "TRUE", "FALSE", "boolean", "integer",
			"real", "word", "word1", "bool", "signed", "unsigned" };

	private static Set<String> declSet = new HashSet<String>();
	private static String[] defineAry = new String[] { "MODULE", "DEFINE", "MDEFINE", "CONSTANTS", "VAR", "IVAR", "FROZENVAR",
			"INIT", "TRANS", "INVAR", "SPEC", "CTLSPEC", "LTLSPEC", "PSLSPEC", "COMPUTE", "NAME", "INVARSPEC", "FAIRNESS",
			"JUSTICE", "COMPASSION", "ISA" };

	private static Set<String> defineSet = new HashSet<String>();
	private static String[] exprAry = new String[] { "ASSIGN", "CONSTRAINT", "SIMPWFF", "CTLWFF", "LTLWFF", "PSLWFF", "COMPWFF",
			"IN", "MIN", "MAX", "MIRROR", "PRED", "PREDICATES" };

	private static Set<String> exprSet = new HashSet<String>();
	private static String[] funcAry = new String[] { "EX", "AX", "EF", "AF", "EG", "AG", "E", "F", "O", "G", "H", "X", "Y", "Z",
			"A", "U", "S", "V", "T", "BU", "EBF", "ABF", "EBG", "ABG" };

	private static Set<String> funcSet = new HashSet<String>();
	private static String[] keyAry = new String[] { "process", "array", "of", "extend", "resize", "sizeof", "uwconst", "count",
			"swconst", "case", "esac", "next", "init" };

	private static Set<String> keySet = new HashSet<String>();
	public static final int TYPE_DECL = 16;
	public static final int TYPE_DEFINE = 4;
	public static final int TYPE_DIGIT = 1;
	public static final int TYPE_EXPR = 8;
	public static final int TYPE_FUNC = 32;
	public static final int TYPE_KEY = 128;
	public static final int TYPE_MODULE_NAME = 256;
	public static final int TYPE_MODULE_REF = 512;
	public static final int TYPE_SP = 64;
	public static final int TYPE_SYMBOL = 0;
	public static final int TYPE_VARIABLE = 2;

	static {
		for (String work : SMVTokenType.declAry)
			declSet.add(work);
		for (String work : defineAry)
			defineSet.add(work);
		for (String work : exprAry)
			exprSet.add(work);
		for (String work : funcAry)
			funcSet.add(work);
		for (String work : keyAry)
			keySet.add(work);
	}

	public static int judgeType(String str) {
		if (declSet.contains(str))
			return TYPE_DECL;
		else if (defineSet.contains(str))
			return TYPE_DEFINE;
		else if (exprSet.contains(str))
			return TYPE_EXPR;
		else if (funcSet.contains(str))
			return TYPE_FUNC;
		else if (keySet.contains(str))
			return TYPE_KEY;
		return TYPE_VARIABLE;
	}

	public SMVTokenType() {
	}
}