package com.example.cart.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cart.model.dto.OrderDTO;
import com.example.cart.model.dto.OrderItemDTO;
import com.example.cart.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Override
	public List<OrderDTO> findOrdersByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDTO saveOrder(Long userId, List<OrderItemDTO> items) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
