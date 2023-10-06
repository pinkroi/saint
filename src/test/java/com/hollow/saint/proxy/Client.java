package com.hollow.saint.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Client {

	public static void main(String[] args) {
		Bird bird = new Bird();
		InvocationHandler handler = new BirdProxy(bird);
		Flyable flyable = (Flyable) Proxy.newProxyInstance(handler.getClass().getClassLoader(),
				bird.getClass().getInterfaces(),
				handler);
		flyable.fly();
	}

}
