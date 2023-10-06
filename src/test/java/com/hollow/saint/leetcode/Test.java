package com.hollow.saint.leetcode;

import java.util.*;

public class Test {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {}
		TreeNode(int val) { this.val = val; }
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	public static int countZone(TreeNode node) {
		// base case
		if (null == node) {
			return 0;
		}
		int left = countZone(node.left);
		int right = countZone(node.right);
		// 左右子节点都为空
		if (null == node.left && null == node.right) {
			return 1;
		}
		// 左子节点为空
		else if(null == node.left) {
			if (node.val == node.right.val)
				return left + right;
			else
				return left + right + 1;
		}
		// 右子节点为空
		else if(null == node.right) {
			if (node.val == node.left.val)
				return left + right;
			else
				return left + right + 1;
		}
		// 左右子节点都不为空
		int leftVal = node.left.val;
		int rightVal = node.right.val;
		// 父节点与左右子节点都相同 -1
		if (node.val == leftVal && node.val == rightVal) {
			return left + right - 1;
		}
		// 父节点与一个节点相同
		else if (node.val == leftVal || node.val == rightVal) {
			return left + right;
		}
		// 都不相同
		else {
			return left + right + 1;
		}
	}

//	public static void rotate(int[][] matrix) {
//		int n = matrix.length;
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n/2; j++) {
//				swap(matrix, i, j, i, n-j-1);
//			}
//		}
//		for (int i = n-1; i > 0; i--) {
//			for (int j = 0; j < i; j++) {
//				swap(matrix, i, j, j, i);
//			}
//		}
//	}

//	public static void swap(int[][] matrix, int m, int n, int x, int y) {
//		int temp = matrix[m][n];
//		matrix[m][n] = matrix[x][y];
//		matrix[x][y] = temp;
//	}

	public static int sum(Integer... i) {
		return Arrays.stream(i).filter(Objects::nonNull).mapToInt(Integer::intValue).sum();
	}

	public static int[] merge(int[] a, int[] b) {
		int[] ans = new int[a.length + b.length];
		int p = 0, q = 0;
		int cur = 0;
		while (p < a.length || q < b.length) {
			if (p == a.length) {
				ans[cur++] = b[q++];
			}
			else if (q == b.length) {
				ans[cur++] = a[p++];
			}
			else if (a[p] < b[q]) {
				ans[cur++] = a[p++];
			}
			else {
				ans[cur++] = b[q++];
			}
		}
		return ans;
	}

	public static int findKthLargest(int[] nums, int k) {
		int n = nums.length;
		// 建堆
		// 前k个建为最小堆
		for (int i = k/2-1; i >= 0; i--) {
			sink(nums, i, k);
		}
		for (int i = k; i < n; i++) {
			if (nums[i] > nums[0]) {
				nums[0] = nums[i];
				sink(nums, 0, k);
			}
		}
		return nums[0];
	}

	public static void sink(int[] nums, int p, int n) {
		int temp = nums[p];
		int index = 2*p+1;
		while (index < n) {
			if (index + 1 < n && nums[index+1] < nums[index])
				index++;
			if (nums[index] >= temp)
				break;
			nums[p] = nums[index];
			p = index;
			index = 2*index+1;
		}
		nums[p] = temp;
	}

	public static int topK(int[] nums, int k) {
		int left = 0, right = nums.length-1;
		k = k - 1; // 减1为下标
		while (left < right) {
			int p = partition(nums, left, right);
			if (p == k) {
				break;
			}
			else if (p < k) {
				left = p + 1;
			}
			else {
				right = p - 1;
			}
		}
		return nums[k];
	}

	public static int quickSelect(int[] nums, int left, int right, int k) {
		if (left < right) {
			int p = partition(nums, left, right);
			if (p == k)
				return p;
			else if (p > k)
				quickSelect(nums, left, p-1, k);
			else if (p < k)
				quickSelect(nums, p+1, right, k);
		}
		return -1;
	}

	public static int partition(int[] nums, int left, int right) {
		int pivot = nums[right];
		int s = left;
		for (int i = left; i <= right; i++) {
			if (nums[i] > pivot) {
				swap(nums, s++, i);
			}
		}
		swap(nums, s, right);
		return s;
	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static int[] topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> freq = new HashMap<>();
		for (int n : nums) {
			freq.put(n, freq.getOrDefault(n, 0) + 1);
		}
		List<int[]> list = new ArrayList<>();
		for (int key : freq.keySet()) {
			list.add(new int[] {key, freq.get(key)});
		}
//		Collections.sort(list, new Comparator<int[]>() {
//			@Override
//			public int compare(int[] o1, int[] o2) {
//				return o2[1] - o1[1];
//			}
//		});
		for (int i = k/2 - 1; i >= 0; i--) {
			sink(list, i, k);
		}
		for (int i = k; i < list.size(); i++) {
			if (list.get(i)[1] > list.get(0)[1]) {
				list.set(0, list.get(i));
				sink(list, 0, k);
			}
		}
		int[] ans = new int[k];
		for (int i = 0; i < k; i++) {
			ans[i] = list.get(i)[0];
		}
		return ans;
	}

	public static void sink(List<int[]> list, int p, int n) {
		int index = p;
		int l = 2*index + 1;
		int r = 2*index + 2;
		if (l < n && list.get(l)[1] < list.get(index)[1]) {
			index = l;
		}
		if (r < n && list.get(r)[1] < list.get(index)[1]) {
			index = r;
		}
		if (index != p) {
			Collections.swap(list, index, p);
			sink(list, index, n);
		}
	}

	public static int searchInsert(int[] nums, int target) {
		int left = 0, right = nums.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] == target) return mid;
			else if (nums[mid] > target) {
				if (target < nums[left]) return left == 0 ? 0 : left - 1;
				else right = mid - 1;
			}
			else if (nums[mid] < target) {
				if (target > nums[right]) return right == nums.length - 1 ? right : right + 1;
				else left = mid + 1;
			}
		}
		return -1;
	}

	public static int strStr(String haystack, String needle) {
		int len1 = haystack.length(), len2 = needle.length();
		if (len1 < len2) return -1;
		int p1 = 0, p2 = 0, ans = -1;
		while (p1 < len1) {
			ans = p1;
			while (p1 < len1 && p2 < len2) {
				if (needle.charAt(p2) != haystack.charAt(p1))
					break;
				p1++;
				p2++;
			}
			if (p2 == len2) return ans;
			else {
				p1 = ans + 1;
				ans = -1;
				p2 = 0;
			}
		}
		return ans;
	}

	public boolean exist(char[][] board, String word) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (dfs(board, i, j, word.toCharArray(), 0))
					return true;
			}
		}
		return false;
	}

	public boolean dfs(char[][] board, int i, int j, char[] need, int k) {
		if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != need[k])
			return false;
		if (k == need.length - 1)
			return true;
		board[i][j] = '\0';
		boolean flag = dfs(board, i + 1, j, need, k + 1) || dfs(board, i - 1, j, need, k + 1) ||
				dfs(board, i, j - 1, need, k + 1) || dfs(board, i, j + 1, need, k + 1);
		board[i][j] = need[k];
		return flag;
	}

	// you need to treat n as an unsigned value
	public int hammingWeight(int n) {
		int ans = 0;
		for (int i = 0; i < 32; i++) {
			if ((n & 1) == n) {
				ans++;
				n >>= 1;
			}
		}
		return ans;
	}

	public static int[] printNumbers(int n) {
		List<String> ans = new ArrayList<>();
		dfs(n, "", ans);
		int[] res = new int[ans.size() - 1];
		for (int i = 0; i < res.length; i++) {
			res[i] = Integer.parseInt(ans.get(i + 1));
		}
		return res;
	}

	public static void dfs(int n, String temp, List<String> ans) {
		if (temp.length() == n) {
			ans.add(temp);
			return;
		}
		for (int i = 0; i < 10; i++) {
			dfs(n, temp + i, ans);
		}
	}

	public static double myPow(double x, int n) {
		if(x == 0) return 0;
		long b = n;
		double res = 1.0;
		if(b < 0) {
			x = 1 / x;
			b = -b;
		}
		while(b > 0){
			// 最后一位为1，需要乘上该位上的权重
			if((b & 1) == 1){
				res *= x;
			}
			x *= x;
			b >>= 1;
		}
		return res;
	}

	public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
		Map<Integer, Integer> table = new HashMap<>();
		Deque<Integer> deque = new LinkedList<>();
		for (int i = 0; i < nums2.length; i++) {
			while (!deque.isEmpty() && nums2[i] > deque.peek()) {
				table.put(deque.pop(), nums2[i]);
			}
			deque.push(nums2[i]);
		}
		// int[] ans = new int[nums1.length];
		for (int i = 0; i < nums1.length; i++) {
			nums1[i] = table.get(nums1[i]);
		}
		return nums1;
	}


	public static void main(String[] args) throws InterruptedException {
//		nextGreaterElement(new int[]{4,1,2}, new int[]{1,3,4,2});
		char[] c = new char[3];
		c[0] = '1';
		c[1] = '2';
		c[2] = '0';
		System.out.println(Integer.MAX_VALUE);
		try {

		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
	}

}
