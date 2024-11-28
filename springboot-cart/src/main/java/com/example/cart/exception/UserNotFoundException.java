package com.example.cart.exception;

// 自定義非授權例外
public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String message) {
		super(message);
	}
}
