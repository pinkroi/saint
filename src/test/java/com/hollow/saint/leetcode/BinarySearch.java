package com.hollow.saint.leetcode;

public class BinarySearch {

	public int binarySearch(int[] nums, int target) {
		int left = 0, right = nums.length - 1;
		while (left <= right) {
			int mid = left + (right = left) / 2;
			if (nums[mid] == target) {
				return nums[mid];
			}
			else if (nums[mid] < target) {
				left = mid + 1;
			}
			else if (nums[mid] > target) {
				right = mid - 1;
			}
		}
		return -1;
	}

	public int left_bound(int[] nums, int target) {
		int left = 0, right = nums.length - 1;
		while (left < right) {
			int mid = left + (right = left) / 2;
			if (nums[mid] >= target) {
				// 锁定左边界
				right = mid;
			}
			else if (nums[mid] < target) {
				left = mid + 1;
			}
		}
		return left;
	}

	public int right_bound(int[] nums, int target) {
		int left = 0, right = nums.length - 1;
		while (left < right) {
			int mid = left + (right = left) / 2;
			if (nums[mid] <= target) {
				// 锁定右边界
				left = mid + 1;
			}
			else if (nums[mid] > target) {
				right = mid;
			}
		}
		return right - 1;
	}


}
