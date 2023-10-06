package com.hollow.saint.leetcode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

class CQueue {
	private volatile static CQueue singleton;

	public CQueue() {
		if (null == singleton) {
			synchronized (CQueue.class) {
				if (null == singleton) {
					singleton = new CQueue();
				}
			}
		}
	}

	public CQueue getSingleton() {
		return singleton;
	}

	public static void main(String[] args) throws IOException {
		try (InputStream inputStream = new FileInputStream("/Users/jesse/Desktop/iplist")) {
			int r;
			while ((r = inputStream.read()) != -1) {
				System.out.println(r);
			}
		}
	}
}
