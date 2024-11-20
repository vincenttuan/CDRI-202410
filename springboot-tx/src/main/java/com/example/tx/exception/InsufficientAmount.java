package com.example.tx.exception;

// 庫存數量不足 (checked exception)
public class InsufficientAmount extends Exception {
	public InsufficientAmount(String message) {
		super(message);
	}
}
