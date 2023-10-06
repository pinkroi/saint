package com.hollow.saint.proxy;

public class BirdTimeProxy implements Flyable{
	Flyable flyable;

	public BirdTimeProxy(Flyable flyable) {
		this.flyable = flyable;
	}

	@Override
	public void fly() {
		long a = System.currentTimeMillis();
		flyable.fly();
		long b = System.currentTimeMillis();
		System.out.println("fly time = " + (b - a));
	}

	public static void main(String[] args) {
		Bird bird = new Bird();
		BirdTimeProxy birdTimeProxy = new BirdTimeProxy(bird);
		birdTimeProxy.fly();
	}
}
