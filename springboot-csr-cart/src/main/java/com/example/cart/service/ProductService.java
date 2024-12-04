package com.example.cart.service;

import java.util.List;
import java.util.Optional;

import com.example.cart.model.dto.ProductDTO;

public interface ProductService {
	List<ProductDTO> getAllProducts(); // 取得所有商品
	Optional<ProductDTO> getProductById(Long id); // 取得單一商品
	ProductDTO saveProduct(ProductDTO productDTO); // 新增商品
	
}
