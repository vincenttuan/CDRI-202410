package javaweb.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javaweb.model.entity.Order;

public class OrderDaoImpl extends BaseDao implements OrderDao {

	@Override
	public List<Order> findAllOrders(Integer userId, String orderStatus) {
		List<Order> orders = new ArrayList<>();
		String sql = """
						select order_id, user_id, order_date, product_id, 
						   	   quantity, unit_price, subtotal, order_status
						from orders
						where user_id = ? and orderStatus = ?
					 """.trim(); 
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setInt(1, userId);
			pstmt.setString(2, orderStatus);
			
			try(ResultSet rs = pstmt.executeQuery()) {
				Order order = new Order();
				order.setOrderId(rs.getInt("order_id"));
				order.setUserId(rs.getInt("user_id"));
				order.setOrderDate(rs.getString("order_date"));
				order.setProductId(rs.getInt("product_id"));
				order.setQuantity(rs.getInt("quantity"));
				order.setUnitPrice(rs.getDouble("unit_price"));
				order.setSubtotal(rs.getInt("subtotal"));
				order.setOrderStatus(rs.getString("order_status"));
				// 加入到 orders 集合
				orders.add(order);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orders;
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
