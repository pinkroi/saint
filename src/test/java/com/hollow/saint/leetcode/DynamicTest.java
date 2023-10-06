package com.hollow.saint.leetcode;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DynamicTest {

	public int trap(int[] height) {
		int n = height.length;
		int[] leftMax = new int[n];
		int[] rightMax = new int[n];
		leftMax[0] = height[0];
		for (int i = 1; i < n; i++) {
			leftMax[i] = Math.max(leftMax[i-1], height[i]);
		}
		rightMax[n-1] = height[n-1];
		for (int i = n - 2; i >= 0; i--) {
			rightMax[i] = Math.max(rightMax[i + 1], height[i]);
		}
		int res = 0;
		for (int i = 0; i < n; i++) {
			res += Math.min(leftMax[i], rightMax[i]) - height[i];
		}
		return res;
	}

	int[][] memo;

	public int numTrees(int n) {
		memo = new int[n+1][n+1];
		return count(1, n);
	}

	public int count(int low, int high) {
		if (low > high) {
			return 1;
		}
		if (memo[low][high] != 0) {
			return memo[low][high];
		}
		int res = 0;
		for (int i = low; i <= high; i++) {
			int left = count(low, i - 1);
			int right = count(i + 1, high);
			res += left * right;
		}
		memo[low][high] = res;
		return res;
	}

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

	public List<TreeNode> generateTrees(int n) {
		return build(1, n);
	}

	public List<TreeNode> build(int low, int high) {
		List<TreeNode> res = new LinkedList<>();
		if (low > high) {
			res.add(null);
			return res;
		}
		for (int i = low; i <= high; i++) {
			List<TreeNode> left = build(low, i - 1);
			List<TreeNode> right = build(i + 1, high);
			for (TreeNode leftNode : left) {
				for (TreeNode rightNode : right) {
					TreeNode node = new TreeNode(i);
					node.left = leftNode;
					node.right = rightNode;
					res.add(node);
				}
			}
		}
		return res;
	}


	// dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> res = new LinkedList<>();
		List<Integer> dp = new LinkedList<>();
		// base case
		res.add(Arrays.asList(new Integer[]{1}));
		for (int i = 2; i <= numRows; i++) {
			List<Integer> ans = new LinkedList<>();
			ans.add(1);
			for (int j = 1; j < i - 1; j++) {
				ans.add(dp.get(j - 1) + dp.get(j));
			}
			ans.add(1);
			res.add(ans);
			dp = ans;
		}
		return res;
	}


	public List<Integer> getRow(int rowIndex) {
		List<List<Integer>> list = generate(rowIndex+1);
		return list.get(list.size()-1);
	}

}
