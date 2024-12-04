package com.example.proxy.service.impl;

import org.springframework.stereotype.Service;

import com.example.proxy.service.BMIService;

@Service
public class BMIServiceImpl implements BMIService {
	
	// 單純計算 BMI 值
	@Override
	public Double getBMI(Double h, Double w) {
		System.out.println("BMI 開始計算");
		Double bmi = w / Math.pow(h/100, 2);
		System.out.println("BMI 計算完畢");
		return bmi;
	}

}
