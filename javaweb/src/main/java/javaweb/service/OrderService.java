package javaweb.service;

import java.util.List;

import javaweb.model.entity.Order;
import javaweb.repository.OrderDao;
import javaweb.repository.OrderDaoImpl;

public class OrderService {
	
	private OrderDao orderDao = new OrderDaoImpl();
	
	// 同時新增多筆訂單
	// userId: 使用者 id
	// productIds: 每一件商品的 id
	// quantities: 每一件商品的購買數量
	public void batchAddOrders(Integer userId, String[] productIds, String[] quantities) {
		
	}
	
}
