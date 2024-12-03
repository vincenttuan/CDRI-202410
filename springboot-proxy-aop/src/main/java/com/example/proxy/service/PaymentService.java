package com.example.proxy.service;

public interface PaymentService {
	void pay(int amount); // 付款
	void refund(int amount); // 退款
}
