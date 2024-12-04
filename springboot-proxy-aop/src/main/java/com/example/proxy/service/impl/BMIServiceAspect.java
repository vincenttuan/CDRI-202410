package com.example.proxy.service.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
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

	// BMI log 紀錄 + 檢核參數
	// 前置通知
	@Before(value = "pt() && args(h, w)")
	public void before(Double h, Double w) {
		System.out.println("BMIServiceAspect: 前置通知");
		logger.info("h={}, w={}", h, w);
		if(h == null || w == null) {
			throw new IllegalArgumentException("身高體重不合法");
		}
		
	}
	
	// 取得 BMI 計算結果 + log 記錄
	// 返回通知
	@AfterReturning(value = "pt()", returning = "result")
	public void afterReturning(Object result) {
		System.out.println("BMIServiceAspect: 返回通知");
		logger.info("bmi={}", result);
	}
	
	// 方法在執行時本身就發生例外 + log 紀錄
	// 例外通知
	@AfterThrowing(value = "pt()", throwing = "ex")
	public void afterThrowing(Exception ex) {
		logger.info("ex={}", ex);
	}
	
	
}
