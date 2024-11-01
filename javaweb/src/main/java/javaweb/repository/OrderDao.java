package javaweb.repository;

import java.util.List;

import javaweb.model.entity.Order;

public interface OrderDao {
	
	public List<Order> findAllOrdersByUserId(String userId);
	
	public void batchAddOrders(List<Order> orders);
	
	public void batchUpdateOrderStatus(List<Integer> orderIds, String orderStatus);
	
}
