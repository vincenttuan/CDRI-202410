package com.example.proxy.service.impl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BMRServiceAspect {
	// 建立 Logger 實例
	private static final Logger logger = LoggerFactory.getLogger(BMRServiceAspect.class);
		
	// 定義切點
	@Pointcut("execution(* com.example.proxy.service.BMRService.*(..))")
	public void pt() {}
	
	@Around(value = "pt()")
	public Object logAround(ProceedingJoinPoint joinPoint) {
		System.out.println("BMRServiceAspect: 環繞通知");
		Object result = null;
		// 執行原方法
		try {
			result = joinPoint.proceed();
		} catch (Throwable e) {
			
		}
		
		return result;
	} 
	
}
