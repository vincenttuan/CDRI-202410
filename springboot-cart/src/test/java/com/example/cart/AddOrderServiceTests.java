package com.example.cart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.cart.service.OrderService;

// 測試 OrderServic 是否能運作
@SpringBootTest
public class AddOrderServiceTests {
	
	@Autowired
	private OrderService orderService;
	
	@Test
	public void addOrder() {
		System.out.println("筆數: " + orderService.findOrdersByUserId(2L).size());
		
	}
	
}
