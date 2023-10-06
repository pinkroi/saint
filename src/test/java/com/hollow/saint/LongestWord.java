package com.hollow.saint;

import java.util.*;

public class LongestWord {

	public static String longestWord (String[] words) {
		List<String> wordsList = Arrays.asList(words);
		Arrays.sort(words, (o1, o2) -> {
			if (o1.length() == o2.length())
				return o1.compareTo(o2);
			return o2.length() - o1.length();
		});
		for (String word : words) {
			if (dfs(wordsList, 0, word))
				return word;
		}
		return "";
	}

	public static boolean dfs(List<String> wordsList, int index, String target) {
		if (index == target.length())
			return true;
		for (int i = index; i < target.length(); i++) {
			if (i - index + 1 == target.length()) {
				continue;
			}
			String temp = target.substring(index, i + 1);
			if (wordsList.contains(temp) && dfs(wordsList, i + 1, target)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		String[] words = new String[]{"cat","banana","dog","nana","walk","walker","dogwalker"};
		System.out.println(longestWord(words));
		System.out.println(Arrays.toString("dogwalkersun".split("walker")));
	}
}
