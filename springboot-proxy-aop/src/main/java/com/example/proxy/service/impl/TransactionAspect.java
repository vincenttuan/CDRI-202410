package com.example.proxy.service.impl;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TransactionAspect {
	// 切點方法
	@Pointcut(value = "execution(* com.example.proxy.service.TransactionService.pay(..))")
	public void pay() {};
	
	@Pointcut(value = "execution(* com.example.proxy.service.TransactionService.refund(..))")
	public void refund() {};
	
	@Pointcut(value = "execution(* com.example.proxy.service.TransactionService.*(..))")
	public void allMethods() {};
	
	@Pointcut(value = "execution(* com.example.proxy.service.*.*(..))")
	public void allClasses() {};
	
	@Pointcut(value = "execution(* *.*(..))")
	public void all() {};
	
	//@Before(value = "pay() || refund()")
	/*
	@Before(value = "allMethods()")
	public void checkAmount(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName(); // 方法名稱
		Object[] args = joinPoint.getArgs(); // 方法參數
		System.out.printf("Before %s %s%n", methodName, Arrays.toString(args));
	}
	*/
	@Before(value = "allMethods() && args(amount)")
	public void checkAmount(int amount) {
		System.out.printf("Before amount = %d%n", amount);
	}
	
	
	
	@AfterReturning(value = "pay()")
	public void logPay(JoinPoint joinPoint) {
		System.out.println("After pay ...");
	}
	
	@AfterReturning(value = "refund()")
	public void logRefund(JoinPoint joinPoint) {
		System.out.println("After refund ...");
	}
	
}
