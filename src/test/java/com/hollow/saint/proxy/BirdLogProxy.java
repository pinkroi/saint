package com.hollow.saint.proxy;

public class BirdLogProxy implements Flyable{
	Flyable flyable;

	public BirdLogProxy(Flyable flyable) {
		this.flyable = flyable;
	}

	@Override
	public void fly() {
		System.out.println("fly start");
		flyable.fly();
		System.out.println("fly ends");
	}

	public static void main(String[] args) {
		Bird bird = new Bird();
		BirdLogProxy birdLogProxy = new BirdLogProxy(bird);
	}
}
