package com.example.cart.exception;

// 自定義無此用戶例外
public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException() {
		super();
	}
	
	public UserNotFoundException(String message) {
		super(message);
	}
	
}
