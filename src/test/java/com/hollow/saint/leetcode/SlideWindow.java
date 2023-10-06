package com.hollow.saint.leetcode;

public class SlideWindow {
	public int lengthOfLongestSubstring(String s) {
		int len = s.length();
		int slow = 0, fast = 0, ans = 0;
		while (fast < len) {
			if (s.charAt(slow) != s.charAt(fast)) {
				ans++;
				fast++;
				continue;
			}
			slow++;
			fast++;
		}
		return ans;
	}
}
