package com.example.cart.exception;

// 自定義非授權例外
public class ProductNotFoundException extends RuntimeException {
	public ProductNotFoundException(String message) {
		super(message);
	}
}
