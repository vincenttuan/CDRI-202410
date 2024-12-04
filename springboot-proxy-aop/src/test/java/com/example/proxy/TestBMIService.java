package com.example.proxy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.proxy.service.BMIService;

@SpringBootTest
public class TestBMIService {
	
	@Autowired
	private BMIService bmiService;
	
	@Test
	public void test() {
		Double h = 170.0;
		Double w = 60.0;
		Double bmi = bmiService.getBMI(h, w);
		System.out.printf("bmi = %.2f%n", bmi);
	}
}
