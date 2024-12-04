package com.example.cart.model.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "`order`")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// order 與 user 的關係是多對一關聯
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	// order 與 order_item 的關係是一對多
	// FetchType.EAGER 在查找 order 的同時一併找 OrderItem
	@OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	//@OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
	private List<OrderItem> items;
	
}






