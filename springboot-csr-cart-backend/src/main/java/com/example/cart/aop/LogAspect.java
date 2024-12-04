package com.example.cart.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//針對結帳進行 log 紀錄
//OrderService.saveOrder
//隨堂練習
//加分項: log 檔案會自動更替日期名稱
//例如: 2024-12-04是: order.log <- 今日
//   2024-12-05是: 2024-12-04-order.log <- 昨日的 order.log 會自動更名變成 2024-12-04-order.log
//                 : order.log <- 今日
@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Around("execution(* com.example.cart.service.OrderService.saveOrder(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("結帳流程開始 - 方法: {}", joinPoint.getSignature().getName());
        long startTime = System.currentTimeMillis();

        Object result;
        try {
            // 執行被攔截的方法
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            logger.error("結帳流程發生異常: {}", throwable.getMessage(), throwable);
            throw throwable;
        }

        long endTime = System.currentTimeMillis();
        logger.info("結帳流程完成 - 耗時: {} ms", (endTime - startTime));

        return result;
    }
}

