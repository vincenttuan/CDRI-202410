package com.example.proxy.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.proxy.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Override
	public void pay(int amount) { // 付款方法(業務方法)
		// Before 執行業務邏輯之前:檢查金額 (公用邏輯)
		if(amount <= 0) {
			System.out.println("支付失敗: 金額必須大於零!");
			return;
		}
		
		// 業務邏輯
		System.out.println("支付 " + amount + " 元");
		
		// After 執行業務邏輯之後:添加日誌 (公用邏輯)
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("支付 " + amount + " 元 " + sdf.format(new Date()));
	}

	@Override
	public void refund(int amount) { // 退款方法(業務方法)
		// Before 執行業務邏輯之前:檢查金額 (公用邏輯)
		if(amount <= 0) {
			System.out.println("支付失敗: 金額必須大於零!");
			return;
		}
		
		// 業務邏輯
		System.out.println("退款 " + amount + " 元");
		
		// After 執行業務邏輯之後:添加日誌 (公用邏輯)
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("支付 " + amount + " 元 " + sdf.format(new Date()));
	}

}
