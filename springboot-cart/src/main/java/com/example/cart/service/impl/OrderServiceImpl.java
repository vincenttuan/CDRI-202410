package com.example.cart.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cart.model.dto.OrderDTO;
import com.example.cart.model.dto.OrderItemDTO;
import com.example.cart.repository.OrderRepository;
import com.example.cart.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
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
		// TODO Auto-generated method stub
		return null;
	}
	
}
