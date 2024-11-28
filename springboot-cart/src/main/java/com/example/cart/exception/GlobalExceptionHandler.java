package com.example.cart.exception;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.cart.response.ApiResponse;

@ControllerAdvice // 可以用來處理全域的例外
public class GlobalExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	
	@ExceptionHandler(Exception.class)
	// Locale 在 Spring 中或預設會參考瀏覽器的 Accept-Language Header 來判斷
	// 範例: accept-language header: zh-CN, zh-TW; q = ...
	public ResponseEntity<ApiResponse<String>> handleUnauthorizedException(Exception ex, Locale locale) {
		//ex.printStackTrace();
		
		Throwable actualException = ex;

	    // 解包 UndeclaredThrowableException
	    if (ex instanceof UndeclaredThrowableException) {
	        actualException = ((UndeclaredThrowableException) ex).getUndeclaredThrowable();
	    }
	    
		String message;
	    HttpStatus status;
	    if (ex instanceof UnauthorizedException) {
	        message = ex.getMessage().isEmpty() ? messageSource.getMessage("exception.unauthorized", null, locale) : ex.getMessage();
	        status = HttpStatus.FORBIDDEN;
	    } else if (ex instanceof ProductNotFoundException) {
	        message = ex.getMessage().isEmpty() ? messageSource.getMessage("exception.product_not_found", null, locale) : ex.getMessage();
	        status = HttpStatus.NOT_FOUND;
	    } else if (ex instanceof UserNotFoundException) {
	        message = ex.getMessage().isEmpty() ? messageSource.getMessage("exception.user_not_found", null, locale) : ex.getMessage();
	        status = HttpStatus.NOT_FOUND;
	    } else {
	    	message = actualException.getMessage();
	        status = HttpStatus.INTERNAL_SERVER_ERROR;
	    }

	    ApiResponse<String> response = ApiResponse.error(status.value(), message);
	    return ResponseEntity.status(status).body(response);
	}
	
}
