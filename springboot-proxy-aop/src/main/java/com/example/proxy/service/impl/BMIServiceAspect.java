package com.example.proxy.service.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BMIServiceAspect {
	
	// 建立 Logger 實例
	private static final Logger logger = LoggerFactory.getLogger(BMIServiceAspect.class);
		
	// 定義切點
	@Pointcut("execution(* com.example.proxy.service.BMIService.*(..))")
	public void pt() {}

	// BMI 調用紀錄
	// 前置通知
	@Before(value = "pt() && args(h, w)")
	public void before(Double h, Double w) {
		System.out.println("BMIServiceAspect: 前置通知");
		logger.info("h={}, w={}", h, w);
	}
	
	// 取得 BMI 計算結果並記錄
	// 返回通知
	@AfterReturning(value = "pt()", returning = "result")
	public void afterReturning(Object result) {
		System.out.println("BMIServiceAspect: 返回通知");
		logger.info("bmi={}", result);
	}
	
	
}
