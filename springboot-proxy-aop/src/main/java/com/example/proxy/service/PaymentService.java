package com.example.proxy.service;

public interface PaymentService {
	Boolean pay(int amount); // 付款
	Boolean refund(int amount); // 退款
}
