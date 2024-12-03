package com.example.proxy.service.impl;

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
	@Before(value = "allMethods()")
	public void checkAmount(JoinPoint joinPoint) {
		System.out.println("Before ...");
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
