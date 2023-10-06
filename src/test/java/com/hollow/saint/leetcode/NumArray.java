package com.hollow.saint.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class NumArray {

	int[] sum;

	public NumArray(int[] nums) {
		int n = nums.length;
		sum = new int[n+1];
		for (int i = 0; i < nums.length; i++) {
			sum[i+1] = sum[i] + nums[i];
		}
	}

	public int sumRange(int left, int right) {
		return sum[right+1] - sum[left];
	}

	public int subarraySum(int[] nums, int k) {
		int n = nums.length;
		Map<Integer, Integer> pre = new HashMap<>();
		int sum_i = 0, res = 0;
		for (int i = 0; i < n; i++) {
			sum_i += nums[i];
			int sum_j = sum_i - k;
			if (pre.containsKey(sum_j)) {
				res += pre.get(sum_j);
			}
			pre.put(sum_i, pre.getOrDefault(sum_i, 0) + 1);
		}
		return res;
	}

	public String minWindow(String s, String t) {
		Map<Character, Integer> need = new HashMap<>();
		Map<Character, Integer> window = new HashMap<>();
		for (char c : t.toCharArray()) {
			need.put(c, need.getOrDefault(c, 0) + 1);
		}
		int left = 0, right = 0, valid = 0;
		int start = 0, end = Integer.MAX_VALUE;
		while (right < s.length()) {
			char c = s.charAt(right);
			right++;
			if (need.containsKey(c)) {
				window.put(c, window.getOrDefault(c, 0) + 1);
				if (window.get(c).equals(need.get(c))) {
					valid++;
				}
			}
			while (valid == need.size()) {
				if (right - left < end - start) {
					start = left;
					end = right;
				}
				char d = s.charAt(left);
				left++;
				if (need.containsKey(d)) {
					if (need.get(d).equals(window.get(d))) {
						valid--;
					}
					window.put(d, window.getOrDefault(d, 0) - 1);
				}
			}
		}
		return end == Integer.MAX_VALUE ? "" : s.substring(start, end);
	}

	public boolean checkInclusion(String s1, String s2) {
		Map<Character, Integer> need = new HashMap<>();
		Map<Character, Integer> window = new HashMap<>();
		for (char c : s1.toCharArray()) {
			need.put(c, need.getOrDefault(c, 0) + 1);
		}
		int left = 0, right = 0, valid = 0;
		while (right < s2.length()) {
			char c = s2.charAt(right);
			right++;
			if (need.containsKey(c)) {
				window.put(c, window.getOrDefault(c, 0) + 1);
				if (window.get(c).equals(need.get(c))) {
					valid++;
				}
			}
			while (valid == need.size()) {
				if (right - left == s1.length()) {
					return true;
				}
				char d = s2.charAt(left);
				left++;
				if (need.containsKey(d)) {
					if (need.get(d).equals(window.get(d))) {
						valid--;
					}
					window.put(d, window.getOrDefault(d, 0) - 1);
				}
			}
		}
		return false;
	}

	public List<Integer> findAnagrams(String s, String p) {
		List<Integer> res = new ArrayList<>();
		Map<Character, Integer> need = new HashMap<>();
		Map<Character, Integer> window = new HashMap<>();
		for (char c : p.toCharArray()) {
			need.put(c, need.getOrDefault(c, 0) + 1);
		}
		int left = 0, right = 0, valid = 0;
		while (right < s.length()) {
			char c = s.charAt(right);
			right++;
			if (need.containsKey(c)) {
				window.put(c, window.getOrDefault(c, 0) + 1);
				if (window.get(c).equals(need.get(c))) {
					valid++;
				}
			}
			while (valid == need.size()) {
				if (right - left == p.length()) {
					res.add(left);
				}
				char d = s.charAt(left);
				left++;
				if (need.containsKey(d)) {
					if (need.get(d).equals(window.get(d))) {
						valid--;
					}
					window.put(d, window.getOrDefault(d, 0) - 1);
				}
			}
		}
		return res;
	}

	public int lengthOfLongestSubstring(String s) {
		Map<Character, Integer> window = new HashMap<>();
		int left = 0, right = 0, res = 0;
		while (right < s.length()) {
			char c = s.charAt(right);
			right++;
			window.put(c, window.getOrDefault(c, 0) + 1);
			while (window.get(c) > 1) {
				char d = s.charAt(left);
				left++;
				window.put(d, window.getOrDefault(d, 0) - 1);
			}
			res = Math.max(res, right - left);
		}
		return res;
	}

	public int removeDuplicates(int[] nums) {
		int slow = 0, fast = 1;
		while (fast < nums.length) {
			if (nums[fast] != nums[slow]) {
				slow++;
				nums[slow] = nums[fast];
			}
			fast++;
		}
		return slow+1;
	}

}
