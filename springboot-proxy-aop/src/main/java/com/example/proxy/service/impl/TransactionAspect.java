package com.example.proxy.service.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TransactionAspect {
	
	@Before("execution(* com.example.proxy.service.TransactionService.pay(..))")
	public void checkAmount(JoinPoint joinPoint) {
		System.out.println("Before pay ...");
	}
	
	@AfterReturning("execution(* com.example.proxy.service.TransactionService.pay(..))")
	public void logPay(JoinPoint joinPoint) {
		System.out.println("After pay ...");
	}
	
}
