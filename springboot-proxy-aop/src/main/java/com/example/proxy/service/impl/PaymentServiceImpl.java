package com.example.proxy.service.impl;

import org.springframework.stereotype.Service;

import com.example.proxy.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Override
	public void pay(int amount) {
		System.out.println("支付 " + amount + " 元");
	}

	@Override
	public void refund(int amount) {
		System.out.println("退款 " + amount + " 元");
	}

}
