package com.example.cart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.cart.response.ApiResponse;

@ControllerAdvice // 可以用來處理全域的例外
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UnauthorizedException.class) // 捕獲指定例外
	public ResponseEntity<ApiResponse<String>> handleUnauthorizedException(UnauthorizedException ex) {
		ApiResponse<String> response = ApiResponse.error(HttpStatus.FORBIDDEN.value(), "未登入或登入失敗");
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
	}
	
	
}
