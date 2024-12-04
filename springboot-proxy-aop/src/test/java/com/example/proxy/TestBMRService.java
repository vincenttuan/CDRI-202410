package com.example.proxy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.proxy.service.BMRService;

@SpringBootTest
public class TestBMRService {
	
	@Autowired
	private BMRService bmrService;
	
	@Test
	public void test() {
		Double h = 170.0;
		Double w = 60.0;
		Integer age = 25;
		String gender = "m";
		Double bmr = bmrService.getBMR(h, w, age, gender);
		System.out.printf("bmr = %.2f%n", bmr);
		
	}
}
