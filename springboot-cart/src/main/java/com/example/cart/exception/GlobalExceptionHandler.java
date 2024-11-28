package com.example.cart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.cart.response.ApiResponse;

@ControllerAdvice // 可以用來處理全域的例外
public class GlobalExceptionHandler {
	
	@ExceptionHandler({UnauthorizedException.class, ProductNotFoundException.class, UserNotFoundException.class})
	public ResponseEntity<ApiResponse<String>> handleUnauthorizedException(Exception ex) {
		String message;
	    HttpStatus status;

	    if (ex instanceof UnauthorizedException) {
	        message = ex.getMessage().isEmpty() ? "未登入或登入失敗" : ex.getMessage();
	        status = HttpStatus.FORBIDDEN;
	    } else if (ex instanceof ProductNotFoundException) {
	        message = ex.getMessage().isEmpty() ? "商品不存在" : ex.getMessage();
	        status = HttpStatus.NOT_FOUND;
	    } else if (ex instanceof UserNotFoundException) {
	        message = ex.getMessage().isEmpty() ? "用戶不存在" : ex.getMessage();
	        status = HttpStatus.NOT_FOUND;
	    } else {
	        message = ex.getMessage();
	        status = HttpStatus.INTERNAL_SERVER_ERROR;
	    }

	    ApiResponse<String> response = ApiResponse.error(status.value(), message);
	    return ResponseEntity.status(status).body(response);
	}
	
}
