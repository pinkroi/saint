package com.hollow.saint.svn.service.impl;

import com.hollow.saint.aop.LogOrder;
import com.hollow.saint.svn.service.OrderService;
import org.springframework.stereotype.Repository;

@Repository
public class OrderServiceImpl implements OrderService {
	@Override
	@LogOrder("测试")
	public void printOrder(String name, String age) {
		System.out.println("This is order...");
	}
}
