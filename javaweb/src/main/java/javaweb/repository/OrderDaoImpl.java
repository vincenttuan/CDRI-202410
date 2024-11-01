package javaweb.repository;

import java.util.List;

import javaweb.model.entity.Order;

public class OrderDaoImpl extends BaseDao implements OrderDao {

	@Override
	public List<Order> findAllOrders(String userId, String orderStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batchAddOrders(List<Order> orders) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchUpdateOrderStatus(List<Integer> orderIds, String orderStatus) {
		// TODO Auto-generated method stub
		
	}
	
}
