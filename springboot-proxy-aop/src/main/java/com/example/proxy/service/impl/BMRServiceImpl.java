package com.example.proxy.service.impl;

import org.springframework.stereotype.Service;

import com.example.proxy.service.BMRService;

/*
 * 男性 BMR=88.362+(13.397×體重(公斤))+(4.799×身高(公分))−(5.677×年齡)
 * 女性 BMR=447.593+(9.247×體重(公斤))+(3.098×身高(公分))−(4.330×年齡)
 * */
@Service
public class BMRServiceImpl implements BMRService {

	@Override
	public Double getBMR(Double h, Double w, Integer age, String gender) {
		// Java 12 新型 switch
		return switch (gender.toLowerCase()) {
			case "male", "m" -> 88.362+(13.397*w)+(4.799*h)-(5.677*age);
			case "female", "f" -> 447.593+(9.247*w)+(3.098*h)-(4.330*age);
			default -> throw new IllegalArgumentException("資料錯誤");
		};
	}

}
