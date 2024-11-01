package javaweb.service;

import java.util.ArrayList;
import java.util.List;

import javaweb.model.entity.Order;
import javaweb.repository.OrderDao;
import javaweb.repository.OrderDaoImpl;

public class OrderService {
	
	private OrderDao orderDao = new OrderDaoImpl();
	
	// 同時新增多筆訂單
	// userId: 使用者 id
	// productIds: 每一件商品的 id    [1, 2, 3, 4, 5]
	// amounts:    每一件商品的購買數量 [5, 0, 3, 0, 2]
	// 注意: productIds 的長度必須等於 amounts 的長度
	public void batchAddOrders(Integer userId, String[] productIds, String[] amounts) {
		List<Order> orders = new ArrayList<>();
		
		
		
		orderDao.batchAddOrders(orders);
	}
	
}
