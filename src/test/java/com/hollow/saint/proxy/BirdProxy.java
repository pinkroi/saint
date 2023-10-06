package com.hollow.saint.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class BirdProxy implements InvocationHandler {

	private Object subject;

	public BirdProxy(Object subject) {
		this.subject = subject;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("start");
		method.invoke(subject, args);
		System.out.println("end");
		return null;
	}

}
