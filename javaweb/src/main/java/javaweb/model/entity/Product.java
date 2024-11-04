package javaweb.model.entity;

import lombok.Data;

@Data
public class Product {
	private Integer productId;
	private String productName;
	private Double price;
	private Integer stockQuantity;
}
