package com.example.cart.service;

import java.util.List;

import com.example.cart.model.dto.OrderDTO;
import com.example.cart.model.dto.OrderItemDTO;

public interface OrderService {
	// 根據使用者 id 取得該使用者的訂購資料
	public List<OrderDTO> findOrdersByUserId(Long userId);
	
	// 新增訂購單
	public OrderDTO saveOrder(Long userId, List<OrderItemDTO> items);
	
}
