package com.example.cart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.cart.model.entity.Order;
import com.example.cart.model.entity.User;
import com.example.cart.repository.OrderRepository;
import com.example.cart.repository.UserRepository;

@SpringBootTest
public class ChangeOrderUser {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Test
	void change() {
		User user = userRepository.findById(2L).get();
		
		Order order = orderRepository.findById(1L).get();
		order.setUser(user);
		orderRepository.save(order);
		
	}
}
