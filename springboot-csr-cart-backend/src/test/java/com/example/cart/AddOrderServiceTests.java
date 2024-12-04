package com.example.cart;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.cart.model.dto.OrderItemDTO;
import com.example.cart.model.dto.ProductDTO;
import com.example.cart.model.entity.Product;
import com.example.cart.repository.ProductRepository;
import com.example.cart.service.OrderService;
import com.example.cart.service.ProductService;

// 測試 OrderServic 是否能運作
@SpringBootTest
public class AddOrderServiceTests {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;
	
	@Test
	public void findOrder() {
		System.out.println("筆數: " + orderService.findOrdersByUserId(2L).size());
	}
	
	@Test
	public void addOrder() {
		ProductDTO product = productService.getProductById(1L).get();
		Long userId = 2L;
		
		OrderItemDTO item = new OrderItemDTO();
		item.setQuantity(1);
		item.setProduct(product);
		
		OrderItemDTO item2 = new OrderItemDTO();
		item2.setQuantity(2);
		item2.setProduct(product);
		
		List<OrderItemDTO> items = new ArrayList<>();
		items.add(item);
		items.add(item2);
		
		orderService.saveOrder(userId, items);
		System.out.println("新增訂單成功");
	}
	
}
