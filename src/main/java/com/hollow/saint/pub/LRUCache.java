package com.hollow.saint.pub;

import java.util.HashMap;
import java.util.Map;

class LRUCache {

	int cap, size;
	DNode dummyHead, dummyTail;

	Map<Integer, DNode> table = new HashMap<>();

	private static class DNode {
		int key, value;
		DNode prev, next;

		public DNode() {}

		public DNode(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}

	public void addToFirst(DNode node) {
		node.prev = dummyHead;
		node.next = dummyHead.next;
		dummyHead.next.prev = node;
		dummyHead.next = node;
	}

	public void moveToFirst(DNode node) {
		removeNode(node);
		addToFirst(node);
	}

	public void removeNode(DNode node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
	}

	public DNode removeLast() {
		DNode last = dummyTail.prev;
		removeNode(last);
		return last;
	}

	public LRUCache(int capacity) {
		this.cap = capacity;
		this.size = 0;
		dummyHead = new DNode();
		dummyTail = new DNode();
		dummyHead.next = dummyTail;
		dummyTail.prev = dummyHead;
	}

	public int get(int key) {
		DNode node = table.get(key);
		if (null == node) {
			return -1;
		}
		moveToFirst(node);
		return node.value;
	}

	public void put(int key, int value) {
		DNode node = table.get(key);
		if (null == node) {
			// evict least recently used
			if (size >= cap) {
				DNode last = removeLast();
				table.remove(last.key);
				size--;
			}
			// add to first
			DNode newNode = new DNode(key, value);
			addToFirst(newNode);
			table.put(key, newNode);
			size++;
		}
		else {
			node.value = value;
			moveToFirst(node);
		}
	}
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */