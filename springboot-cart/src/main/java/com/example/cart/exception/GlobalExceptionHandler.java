package com.example.cart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.cart.response.ApiResponse;

@ControllerAdvice // 可以用來處理全域的例外
public class GlobalExceptionHandler {
	
	@ExceptionHandler({UnauthorizedException.class, ProductNotFoundException.class})
	public ResponseEntity<ApiResponse<String>> handleUnauthorizedException(Exception ex) {
		String message;
	    HttpStatus status;

	    if (ex instanceof UnauthorizedException) {
	        message = "未登入或登入失敗";
	        status = HttpStatus.FORBIDDEN;
	    } else if (ex instanceof ProductNotFoundException) {
	        message = "商品不存在";
	        status = HttpStatus.NOT_FOUND;
	    } else {
	        message = ex.getMessage();
	        status = HttpStatus.INTERNAL_SERVER_ERROR;
	    }

	    ApiResponse<String> response = ApiResponse.error(status.value(), message);
	    return ResponseEntity.status(status).body(response);
	}
	
}
