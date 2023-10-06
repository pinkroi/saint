package com.hollow.saint;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Print {

	private static Thread t1;
	private static Thread t2;

	private static Lock lock = new ReentrantLock();
	private static Condition condition = lock.newCondition();
	private static Object object = new Object();
	private static int count = 1;

	public static void main(String[] args) {
		t1 = new Thread(() -> {
				try {
					while (true) {
						System.out.println(Thread.currentThread().getName() + ":" + count++);
						object.notify();
						object.wait();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

		});
		t2 = new Thread(() -> {
			try {
				while (true) {
					object.wait();
					System.out.println(Thread.currentThread().getName() + ":" + count++);
					object.notify();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		t1.start();
		t2.start();

	}

}
