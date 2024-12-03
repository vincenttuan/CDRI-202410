package com.example.proxy.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.proxy.service.PaymentService;

// PaymentService 代理程式
@Service
@Primary
public class PaymentServiceProxy implements PaymentService {
	
	private PaymentService paymentService;
	
	public PaymentServiceProxy(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	@Override
	public void pay(int amount) {
		// Before 執行業務邏輯之前:檢查金額 (公用邏輯)
		if(amount <= 0) {
			System.out.println("支付失敗: 金額必須大於零!");
			return;
		}
		
		// 代理執行實際支付業務
		paymentService.pay(amount); 
		
		// After 執行業務邏輯之後:添加日誌 (公用邏輯)
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("支付 " + amount + " 元 " + sdf.format(new Date()));
	}

	@Override
	public void refund(int amount) {
		// Before 執行業務邏輯之前:檢查金額 (公用邏輯)
		if(amount <= 0) {
			System.out.println("退款失敗: 金額必須大於零!");
			return;
		}
		
		// 代理執行實際退款業務
		paymentService.refund(amount);
		
		// After 執行業務邏輯之後:添加日誌 (公用邏輯)
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("退款 " + amount + " 元 " + sdf.format(new Date()));
	}

}
