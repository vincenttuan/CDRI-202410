package com.example.proxy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.proxy.service.PaymentService;

@SpringBootTest
public class TestPaymentService {
	
	@Autowired
	private PaymentService paymentService;
	
	@Test
	public void test() {
		paymentService.pay(100);
		paymentService.pay(-60);
		
		paymentService.refund(200);
		paymentService.refund(-30);
	}
	
}
