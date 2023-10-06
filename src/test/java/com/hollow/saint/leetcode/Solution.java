package com.hollow.saint.leetcode;


import java.util.*;

public class Solution {

	Map<Integer, Integer> mapping = new HashMap<>();
	int wLen;

	public Solution(int n, int[] blacklist) {
		// 黑名单长度
		int bLen = blacklist.length;
		// 白名单长度
		wLen = n - bLen;
		Set<Integer> set = new HashSet<>();
		for (int i = wLen; i < n; i++) {
			set.add(i);
		}
		for (int x : blacklist) {
			set.remove(x);
		}
		// 建立映射
		Iterator<Integer> iterator = set.iterator();
		for (int x : blacklist) {
			if (x < wLen) {
				mapping.put(x, iterator.next());
			}
		}
	}

	public int pick() {
		Random random = new Random();
		int k = random.nextInt(wLen);
		return mapping.getOrDefault(k, k);
	}

	public static void main(String[] args) {
//		Solution s = new Solution(3, new int[]{1});
		method1();
		method2();
	}

	public static void method1() {
		System.out.println("方法1");
	}

	public static void method2() {
		System.out.println("方法1");
	}

}
