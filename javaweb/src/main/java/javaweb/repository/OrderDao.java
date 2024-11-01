package javaweb.repository;

import java.util.List;

import javaweb.model.entity.Order;

public interface OrderDao {
	
	// 查找該使用者的所有訂單，根據訂單狀態進行篩選 
	public List<Order> findAllOrders(Integer userId, String orderStatus);
	
	// 同時新增多筆訂單
	public void batchAddOrders(List<Order> orders);
	
	// 同時更新多筆訂單的指定狀態
	public void batchUpdateOrderStatus(List<Integer> orderIds, String orderStatus);
	
}
