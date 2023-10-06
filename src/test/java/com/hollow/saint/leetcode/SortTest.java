package com.hollow.saint.leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SortTest {

	public void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	// bubble sort
	public void bubbleSort(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n-1; i++) {
			for (int j = 0; j < n-i-1; j++) {
				if (arr[j] > arr[j+1]) {
					swap(arr, j, j+1);
				}
			}
		}
	}

	@Test
	public void testBubbleSort() {
		int[] arr = {5,4,3,2,1};
		bubbleSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	// 快速排序
	public void quickSort(int[] nums, int left, int right) {
		if (left < right) {
			int p = partition(nums, left, right);
			quickSort(nums, left, p-1);
			quickSort(nums, p+1, right);
		}
	}

	// 分割数组 返回cur
	// 左边均比nums[cur]小
	// 右边均比nums[cur]大
	public int partition(int[] nums, int left, int right) {
		int pivot = nums[right];
		int cur = left;
		for (int i = left; i <= right; i++) {
			if (nums[i] < pivot) {
				swap(nums, cur++, i);
			}
		}
		swap(nums, cur, right);
		return cur;
	}

	@Test
	public void testQuickSort() {
		int[] arr = {5,4,3,12,10};
		quickSort(arr, 0, arr.length-1);
		System.out.println(Arrays.toString(arr));
	}

	// 归并排序
	public void mergeSort(int[] nums, int left, int right) {
		if (left < right) {
			int mid = (left+right)/2;
			mergeSort(nums, left, mid);
			mergeSort(nums, mid+1, right);
			merge(nums, left, mid, right);
		}
	}

	// 合并两个升序数组
	public void merge(int[] nums, int left, int mid, int right) {
		int[] temp = new int[right-left+1];
		int p1 = left, p2 = mid+1, cur = 0;
		while (p1 <= mid || p2 <= right) {
			if (p1 > mid) {
				temp[cur++] = nums[p2++];
				continue;
			}
			if (p2 > right) {
				temp[cur++] = nums[p1++];
				continue;
			}
			if (nums[p1] < nums[p2]) {
				temp[cur++] = nums[p1++];
			}
			else {
				temp[cur++] = nums[p2++];
			}
		}
		for (int i = 0; i < temp.length; i++) {
			nums[left+i] = temp[i];
		}
	}

	@Test
	public void testMergeSort() {
		int[] arr = {2,23,12,1,4,3,87,66};
		mergeSort(arr, 0, arr.length-1);
		System.out.println(Arrays.toString(arr));
	}

	// 堆排序
	// O(nlogn)
	public void heapSort(int[] nums) {
		// 建堆
		int n = nums.length;
		// 从最后一个非叶子节点开始
		// 数组长度为n，则最后一个叶子节点下标为n-1，其父节点为(n-1-1)/2 -> n/2-1
		for (int i = n/2-1; i >= 0; i--) {
			heapify(nums, i, n);
		}
		// 交换元素，调整堆
		for (int i = n - 1; i > 0; i--) {
			// 交换堆顶和最后一个元素
			swap(nums, 0, i);
			// 调整堆
			heapify(nums, 0, i);
		}
	}

	// 堆化（最大堆）
	// 非递归
	public void heapify(int[] nums, int p, int n) {
		int temp = nums[p];
		int index = 2*p+1;
		while (index < n) {
			// 取左右子节点中较大一个
			if (index + 1 < n && nums[index] < nums[index+1]) {
				index++;
			}
			if (nums[index] <= temp)
				break;
			nums[p] = nums[index];
			// 更新p
			p = index;
			index = 2*index+1;
		}
		// 开始时堆顶的值
		nums[p] = temp;
	}

	// 堆化
	// 递归
	public void heapify2(int[] nums, int p, int n) {
		int maxIndex = p;
		int l = 2*p + 1;
		int r = 2*p + 2;
		if (l < n && nums[l] > nums[maxIndex]) {
			maxIndex = l;
		}
		if (r < n && nums[r] > nums[maxIndex]) {
			maxIndex = r;
		}
		if (maxIndex != p) {
			swap(nums, maxIndex, p);
			heapify2(nums, maxIndex, n);
		}
	}

	@Test
	public void heapSortTest() {
		int[] arr = new int[] {3,23,18,32,27,2,3,1};
		heapSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	// 选择排序
	public void selectSort(int[] nums) {
		int n = nums.length;
		int minIndex;
		for (int i = 0; i < n-1; i++) {
			minIndex = i;
			for (int j = i+1; j < n; j++) {
				if (nums[j] < nums[minIndex]) {
					minIndex = j;
				}
			}
			swap(nums, i, minIndex);
		}
	}

	@Test
	public void testSelectSort() {
		int[] arr = {5,45,3,34,1};
		selectSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public void insertSort(int[] nums) {
		int n = nums.length;
		for (int i = 0; i < nums.length; i++) {
			int k = i;
			while (k > 0 && nums[k] < nums[k-1]) {
				swap(nums, k, k - 1);
				k--;
			}
		}
	}

	@Test
	public void testInsertSort() {
		int[] arr = {5,45,3,34,1};
		insertSort(arr);
		System.out.println(Arrays.toString(arr));
	}
}
