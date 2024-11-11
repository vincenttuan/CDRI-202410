package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private UserService userService;
	
	@GetMapping
	public String loginPage() {
		return "login";
	}
	
	@PostMapping
	public String checkLogin(@RequestParam String username, @RequestParam String password) {
		// 透過 userService 來確認登入
		
		return "redirect:/rooms"; // 登入成功後到首頁
	}
	
}
