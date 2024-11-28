package com.example.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cart.aop.CheckUserSession;
import com.example.cart.model.dto.FavoriteProductDTO;
import com.example.cart.model.dto.UserDTO;
import com.example.cart.response.ApiResponse;
import com.example.cart.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/favorites")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class FavoriteController {
	
	@Autowired
	private UserService userService;
	
	// 獲取用戶關注清單
	@GetMapping
	@CheckUserSession
	public ResponseEntity<ApiResponse<List<FavoriteProductDTO>>> getFavorites(HttpSession session) {
		Long userId = ((UserDTO)session.getAttribute("userDTO")).getId();
		List<FavoriteProductDTO> favorites = userService.getFavoriteProducts(userId);
		return ResponseEntity.ok(ApiResponse.success("獲取關注成功清單", favorites));
	}
	
	
}













