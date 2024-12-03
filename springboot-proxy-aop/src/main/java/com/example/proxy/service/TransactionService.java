package com.example.proxy.service;

public interface TransactionService {
	void pay(int amount); // 付款
	void refund(int amount); // 退款
}
