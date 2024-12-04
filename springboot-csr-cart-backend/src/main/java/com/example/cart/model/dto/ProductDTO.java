package com.example.cart.model.dto;

import lombok.Data;

@Data
public class ProductDTO {
	private Long id;
	private String name;
	private Integer price;
	
	private String imageBase64;
}
