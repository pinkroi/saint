package com.hollow.saint.leetcode;

import java.util.*;

class MinStack {

	Deque<Integer> deque;
	int min;

	public MinStack() {
		deque = new LinkedList<>();
		min = Integer.MAX_VALUE;
	}

	public void push(int val) {
		deque.push(val);
		min = Math.min(min, val);
	}

	public void pop() {
		int v = deque.pop();
		if (v != min) return;
		min = Integer.MAX_VALUE;
		for (Integer n : deque) {
			min = Math.min(min, n);
		}
	}

	public int top() {
		return deque.peek();
	}

	public int getMin() {
		return min;
	}


	public static List<Integer> intersection(int[][] nums) {
		List<Integer> ans = new ArrayList<>();
		Map<Integer, Integer> freq = new HashMap<>();
		int target = nums.length;
		for (int[] num : nums) {
			for (int i : num) {
				freq.put(i, freq.getOrDefault(i, 0) + 1);
				if (freq.containsKey(i) && freq.get(i) == target)
					ans.add(i);
			}
		}
		Collections.sort(ans);
		return ans;
	}

	public String removeDuplicates(String s) {
		Deque<Character> deque = new LinkedList<>();
		for (char ch : s.toCharArray()) {
			if (!deque.isEmpty() && deque.peek().equals(ch))
				deque.pop();
			else
				deque.push(ch);
		}
		StringBuilder sb = new StringBuilder();
		while (!deque.isEmpty()) {
			sb.append(deque.pollLast());
		}
		return sb.toString();
	}

	public static void test(int num) {
		List<Integer> ans = new ArrayList<>();
		if (num == 0) return;
		if (num % 2 != 0) {
			ans.add(1);
			ans.add(num);
			System.out.println(ans);
			return;
		}
		while (num > 1) {
			if (num % 2 != 0) {
				ans.add(num);
				break;
			}
			ans.add(2);
			num = num / 2;
		}
		System.out.println(ans);
	}

	public static int romanToInt(String s) {
		int ans = 0;
		int pre = getValue(s.charAt(0));
		for (int i = 1; i < s.length(); i++) {
			int v = getValue(s.charAt(i));
			if (pre < v) {
				ans -= v;
			}
			else {
				ans += v;
			}
			pre = v;
		}
		ans += pre;
		return ans;
	}

	private static int getValue(char ch) {
		switch(ch) {
			case 'I': return 1;
			case 'V': return 5;
			case 'X': return 10;
			case 'L': return 50;
			case 'C': return 100;
			case 'D': return 500;
			case 'M': return 1000;
			default: return 0;
		}
	}

	public static int[] kWeakestRows(int[][] mat, int k) {
		int[][] tab = new int[mat.length][2];
		for (int i = 0; i < mat.length; i++) {
			int sum = 0;
			for (int n : mat[i]) {
				sum += n;
			}
			tab[i][0] = sum;
			tab[i][1] = i;
		}
		PriorityQueue<int[]> queue = new PriorityQueue<>(mat.length, (o1, o2) -> {
			if (o1[0] != o2[0]) {
				return o1[0] - o2[0];
			} else {
				return o1[1] - o2[1];
			}
		});
		for (int[] nums : tab) {
			queue.offer(nums);
		}
		int[] res = new int[k];
		for (int i = 0; i < k; i++) {
			int[] nums = queue.poll();
			res[i] = nums[0];
		}
		return res;
	}

	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int length = nums1.length + nums2.length;
		int[] res = new int[length];
		int p1 = 0, p2 = 0;
		int count = 0;
		while (p1 < nums1.length || p2 < nums2.length) {
			if (p1 == nums1.length) {
				res[count++] = nums2[p2++];
			} else if (p2 == nums2.length) {
				res[count++] = nums1[p1++];
			} else if (nums1[p1] < nums2[p2]) {
				res[count++] = nums1[p1++];
			} else {
				res[count++] = nums2[p2++];
			}
		}
		if (length % 2 == 0) {
			int sum = res[length/2-1] + res[length/2];
			return (double) sum/2;
		} else {
			return res[(length-1)/2];
		}
	}

	public static int findRepeatNumber(int[] nums) {
		Map<Integer, Integer> freq = new HashMap<>();
		for (int x : nums) {
			if (freq.containsKey(x) && freq.get(x) > 1) return x;
			freq.put(x, freq.getOrDefault(x, 0) + 1);
		}
		return -1;
	}

	public static boolean findNumberIn2DArray(int[][] matrix, int target) {
		if (matrix.length == 0 || matrix[0].length == 0) return false;
		int x = 0;
		int y = matrix[0].length - 1;
		while (x < matrix.length && y >= 0) {
			if (matrix[x][y] == target) return true;
			else if (matrix[x][y] < target) {
				x += 1;
			}
			else if (matrix[x][y] > target) {
				y -= 1;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		String s = "";

		System.out.println(findNumberIn2DArray(new int[][]{{-1}}, -1));
	}


}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
