package com.example.cart.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cart.model.dto.LoginDTO;
import com.example.cart.model.dto.UserDTO;
import com.example.cart.response.ApiResponse;
import com.example.cart.service.UserService;

import jakarta.servlet.http.HttpSession;

/*
 * WEB REST API
 * ----------------------------------
 * Servlet-Path: /auth
 * ----------------------------------
 * POST /login      登入
 * GET  /logout     登出
 * GET  /isLoggedIn 判斷目前的連線是否有登入
 * */

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<LoginDTO>> login(@RequestBody LoginDTO loginDTO, HttpSession session) {
		// login 判斷比對
		Optional<UserDTO> optUserDTO = userService.login(loginDTO);
		if(optUserDTO.isEmpty()) {
			return ResponseEntity.status(404).body(ApiResponse.error(404, "登入失敗"));
		}
		// 存入 HttpSession 中
		session.setAttribute("userDTO", optUserDTO.get());
		return ResponseEntity.ok(ApiResponse.success("登入成功", null));
	}
	
	@GetMapping("/logout")
	public ResponseEntity<ApiResponse<String>> logout(HttpSession session) {
		session.invalidate(); // session 失效
		return ResponseEntity.ok(ApiResponse.success("登出結果", "登出成功"));
	}
	
	
}
