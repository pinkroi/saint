package com.hollow.saint;

public class Heap {
	int[] data;
	int capacity;
	int size;

	public Heap(int capacity) {
		this.capacity = capacity;
		data = new int[capacity];
		size = 0;
	}

	public void add(int x) {
		int p = size;
		data[size++] = x;
		swim(p);
	}

	public int poll() {
		int t = data[0];
		data[0] = data[--size];
		data[size] = 0;
		sink(0);
		return t;
	}

	public void swim(int p) {
		while (p > 0 && less(p, (p-1)/2)) {
			swap(p, (p-1)/2);
			p = (p-1)/2;
		}
	}

	public void sink(int p) {
		int temp = data[p];
		int k = 2*p+1;
		while (k < size) {
			if (k + 1 < size && data[k+1] < data[k]) {
				k = k+1;
			}
			if (data[p] <= data[k])
				break;
			data[p] = data[k];
			p = k;
			k = 2*k+1;
		}
		data[p] = temp;
	}

	public boolean less(int a, int b) {
		return data[a] < data[b];
	}

	public void swap(int a, int b) {
		int temp = data[a];
		data[a] = data[b];
		data[b] = temp;
	}

	public static void main(String[] args) {

	}
}
