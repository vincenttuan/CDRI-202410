package com.example.proxy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.example.proxy.service.TransactionService;

@SpringBootTest
@EnableAspectJAutoProxy // 啟用 Spring AOP 自動代理功能 !! (重要 !!)
public class TestTransactionService {
	
	@Autowired
	private TransactionService transactionService;
	
	@Test
	public void test() {
		transactionService.pay(100);
		transactionService.pay(-50);
		
		transactionService.refund(200);
		transactionService.refund(-30);
		
	}
	
}
