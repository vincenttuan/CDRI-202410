package com.example.cart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.cart.model.entity.Order;
import com.example.cart.model.entity.OrderItem;
import com.example.cart.model.entity.Product;
import com.example.cart.model.entity.User;
import com.example.cart.repository.OrderItemRepository;
import com.example.cart.repository.OrderRepository;
import com.example.cart.repository.ProductRepository;
import com.example.cart.repository.UserRepository;

@SpringBootTest
class AddOrdersTests {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Test
	void add() {
		// user
		User user = userRepository.findById(1L).get();
		// product
		Product product = productRepository.findById(1L).get();
		
		// order
		Order order = new Order();
		order.setUser(user);
		orderRepository.save(order);
		
		// order_item 1
		OrderItem item1 = new OrderItem();
		item1.setProduct(product);
		item1.setQuantity(10);
		item1.setOrder(order); // 建立 order_item 與 order 之間的關聯
		orderItemRepository.save(item1);
		
		// order_item 2
		OrderItem item2 = new OrderItem();
		item2.setProduct(product);
		item2.setQuantity(20);
		item2.setOrder(order); // 建立 order_item 與 order 之間的關聯
		orderItemRepository.save(item2);
	}

}
