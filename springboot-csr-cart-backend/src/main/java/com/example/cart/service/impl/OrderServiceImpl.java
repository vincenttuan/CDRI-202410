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

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

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
		order.setUser(optUser.get()); // 設定與 user 的關聯關係
		
		// 3. 儲存 order
		//orderRepository.save(order); // 非聯集操作時要加入
		
		// 4. 建立訂單項目列表 (非聯集操作時要加入)
//		items.forEach(item -> {
//			OrderItem orderItem = modelMapper.map(item, OrderItem.class);
//			orderItem.setOrder(order); // 設定與 order 的關聯關係
//			orderItemRepository.save(orderItem); // 儲存
//		});
		
		// 3. 建立訂單項目列表 (聯集操作時要加入 @OneToMany(cascade = CascadeType.ALL))
		List<OrderItem> orderItems = items.stream()
				.map(item -> {
					OrderItem orderItem = modelMapper.map(item, OrderItem.class);
					orderItem.setOrder(order); // 設定與 order 的關聯關係
					return orderItem;
				}).collect(Collectors.toList());
		
		// 4. order 設定與 orderItems 關聯關係 + 儲存 (聯集操作時要加入 @OneToMany(cascade = CascadeType.ALL))
		order.setItems(orderItems);
		orderRepository.save(order); 
		
		return modelMapper.map(order, OrderDTO.class);
	}
	
}
