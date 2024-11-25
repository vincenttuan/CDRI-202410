package com.example.cart.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cart.model.dto.OrderDTO;
import com.example.cart.model.dto.OrderItemDTO;
import com.example.cart.model.entity.Order;
import com.example.cart.model.entity.OrderItem;
import com.example.cart.model.entity.User;
import com.example.cart.repository.OrderItemRepository;
import com.example.cart.repository.OrderRepository;
import com.example.cart.repository.ProductRepository;
import com.example.cart.repository.UserRepository;
import com.example.cart.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<OrderDTO> findOrdersByUserId(Long userId) {
		return orderRepository.findByUserId(userId).stream() // ... Order
				.map(order -> modelMapper.map(order, OrderDTO.class)) // ... OrderDTO
				.collect(Collectors.toList());
	}

	@Override
	public OrderDTO saveOrder(Long userId, List<OrderItemDTO> items) {
		// 1. 得到 user
		Optional<User> optUser = userRepository.findById(userId);
		if(optUser.isEmpty()) return null;
		
		// 2. 建立訂單 + 設定關聯關係
		Order order = new Order();
		order.setUser(optUser.get());
		orderRepository.save(order);
		
		// 3. 建立訂單項目列表
		items.forEach(item -> {
			OrderItem orderItem = modelMapper.map(item, OrderItem.class);
			orderItem.setOrder(order);
			orderItemRepository.save(orderItem);
		});
		
		return modelMapper.map(order, OrderDTO.class);
	}
	
}
