package com.hollow.saint.proxy;

public class Bird2 extends Bird{
	@Override
	public void fly() {
		long a = System.currentTimeMillis();
		super.fly();
		long b = System.currentTimeMillis();
		System.out.println("fly time = " + (b - a));
	}

	public static void main(String[] args) {
		Bird2 b = new Bird2();
		b.fly();
	}
}
