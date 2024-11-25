package com.example.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cart.model.dto.OrderDTO;
import com.example.cart.model.dto.OrderItemDTO;
import com.example.cart.model.dto.UserDTO;
import com.example.cart.response.ApiResponse;
import com.example.cart.service.OrderService;

import jakarta.servlet.http.HttpSession;

/*
 * WEB REST API
 * ----------------------------------
 * Servlet-Path: /orders
 * ----------------------------------
 * GET   /         查詢該使用者所有商品(多筆)
 * POST  /checkout 該使用者進行結帳
 * */

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<OrderDTO>>> getAllOrders(HttpSession session) {
		try {
			UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");
			List<OrderDTO> orderDTOs = orderService.findOrdersByUserId(userDTO.getId());
			return ResponseEntity.ok(ApiResponse.success("查詢成功", orderDTOs));
		} catch (Exception e) {
			return ResponseEntity.status(404).body(ApiResponse.error(404, "用戶尚未登入, 請先登入:" + e.getMessage()));
		}
	}
	
	@PostMapping("/checkout")
	public ResponseEntity<ApiResponse<OrderDTO>> createOrder(@RequestBody List<OrderItemDTO> items, HttpSession session) {
		try {
			UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");
			OrderDTO orderDTO = orderService.saveOrder(userDTO.getId(), items);
			return ResponseEntity.ok(ApiResponse.success("新增成功", orderDTO));
		} catch (Exception e) {
			return ResponseEntity.status(404).body(ApiResponse.error(404, "用戶尚未登入, 請先登入:" + e.getMessage()));
		}
	}
}
