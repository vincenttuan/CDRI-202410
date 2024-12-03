package com.example.proxy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.proxy.service.PaymentService;
import com.example.proxy.service.impl.PaymentServiceProxy;

@SpringBootTest
public class TestPaymentService {
	
	@Autowired
	private PaymentService paymentService;
	
	@Test
	public void test() {
		
		PaymentServiceProxy proxy = new PaymentServiceProxy(paymentService);
		proxy.pay(100);
		proxy.pay(-50);
		
		proxy.refund(200);
		proxy.refund(-30);
		
	}
	
}
