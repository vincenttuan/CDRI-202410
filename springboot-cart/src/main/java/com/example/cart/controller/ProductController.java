package com.example.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cart.model.dto.ProductDTO;
import com.example.cart.response.ApiResponse;
import com.example.cart.service.ProductService;

/*
 * WEB REST API
 * ----------------------------------
 * Servlet-Path: /products
 * ----------------------------------
 * GET   /      查詢所有商品(多筆)
 * GET   /{id}  查詢指定商品(單筆)
 * POST  /      新增商品
 * */
@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<ProductDTO>>> getAllProducts() {
		return ResponseEntity.ok(ApiResponse.success("查詢成功", productService.getAllProducts()));
	}
	
	
}




