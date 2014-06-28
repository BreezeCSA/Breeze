package com.goku.breeze.ui.editor.support;

import java.util.ArrayList;
import java.util.List;

public class SupportUtil {
	public static String getDifferentName(String[] nameList) {
		if (nameList.length == 0)
			return "default";
		List<String> maxNameList = new ArrayList<String>();
		int max = 0;
		for (String name : nameList) {
			if (name.length() >= max) {
				if (name.length() > max) {
					maxNameList.clear();
					max = name.length();
				}
				maxNameList.add(name);
			}
		}
		String ret = "";
		for (int i = 0; i < max; ++i) {
			if (i < maxNameList.size()) {
				char ch = maxNameList.get(i).charAt(i);
				if (ch == 'a')
					ret += 'b';
				else ret += 'a';
			} else ret += maxNameList.get(0).charAt(i);
		}

		if (max < maxNameList.size()) {
			while (ret.length() <= max)
				ret += 'a';
		}
		return ret;
	}
}
