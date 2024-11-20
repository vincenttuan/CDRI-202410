package com.example.cart.model.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private Integer price;
	
	@OneToMany(mappedBy = "product")
	private List<OrderItem> orderItems;
	
	// 與 ProductImage 的一對一關聯
    @OneToOne
    @JoinColumn(name = "product_image_id")
    private ProductImage productImage;
	
}






