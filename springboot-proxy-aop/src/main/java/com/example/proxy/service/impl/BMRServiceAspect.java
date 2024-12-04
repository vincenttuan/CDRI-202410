package com.example.proxy.service.impl;

import java.util.Arrays;

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
		// -- 相當於 @Begin 前置通知 ------------------------------
		System.out.println("BMRServiceAspect: 環繞通知");
		Object[] args = joinPoint.getArgs();
		System.out.println(Arrays.toString(args));
		// 若年齡沒資料返回 0.0
		if (args[2] == null) {
			return Double.valueOf("0.0");
		}
		//-----------------------------------------------------
		Object result = null;
		try {
			// 執行原方法
			result = joinPoint.proceed();
			// 相當於 @AfterReturning 後置通知 
			// code ...
		} catch (Throwable ex) {
			// 相當於 @AfterThrowing 後置通知
			// code ...
		} finally {
			// 相當於 @After 後置通知
			// code ...
		}
		
		return result;
	} 
	
}
