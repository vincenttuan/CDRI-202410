package com.example.proxy.service;

// BMR 基礎代謝率
public interface BMRService {
	Double getBMR(Double h, Double w, Integer age, String gender);
}
