package com.hollow.saint.leetcode;

import java.util.*;

class RandomizedSet {

	List<Integer> list;
	Map<Integer, Integer> valToIndex;

	public RandomizedSet() {
		list = new ArrayList<>();
		valToIndex = new HashMap<>();
	}

	public boolean insert(int val) {
		if (valToIndex.containsKey(val)) {
			return false;
		}
		list.add(val);
		valToIndex.put(val, list.size() - 1);
		return true;
	}

	public boolean remove(int val) {
		if (!valToIndex.containsKey(val)) {
			return false;
		}
		int index = valToIndex.get(val);
		valToIndex.put(list.get(list.size() -1), index);
		Collections.swap(list, index, list.size() - 1);
		list.remove(list.size() - 1);
		valToIndex.remove(val);
		return true;
	}

	public int getRandom() {
		Random random = new Random();
		return list.get(random.nextInt(list.size()));
	}

}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */