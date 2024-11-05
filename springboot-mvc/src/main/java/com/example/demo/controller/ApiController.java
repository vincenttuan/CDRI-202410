package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


// 了解各種不同 URL 與參數的傳遞接收
@RestController // 免去撰寫 @ResponseBody, 但若有要回傳 jsp 則不可使用
@RequestMapping("/api") // 統一 URL 前綴
public class ApiController {
	
	@GetMapping("/hello")
	public String addBook() {
		return "Hello !";
	}
	
	
}
