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
						where user_id = ? and order_status = ?
					 """.trim(); 
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setInt(1, userId);
			pstmt.setString(2, orderStatus);
			
			try(ResultSet rs = pstmt.executeQuery()) {
				
				while (rs.next()) {
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
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orders;
	}

	@Override
	public void batchAddOrders(List<Order> orders) {
		String sql = """
						insert into 
						orders(user_id, order_date, product_id, quantity, unit_price, subtotal, order_status) 
						values(?, ?, ?, ?, ?, ?, ?)
					 """.trim();
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.clearBatch(); // 清除批次(重要 !!!)
			
			for(Order order : orders) {
				pstmt.setInt(1, order.getUserId());
				pstmt.setString(2, order.getOrderDate());
				pstmt.setInt(3, order.getProductId());
				pstmt.setInt(4, order.getQuantity());
				pstmt.setDouble(5, order.getUnitPrice());
				pstmt.setInt(6, order.getSubtotal());
				pstmt.setString(7, order.getOrderStatus());
				
				pstmt.addBatch(); // 加入批次
			}
			
			pstmt.executeBatch(); // 執行批次(一次整批傳送給 MySQL)
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void batchUpdateOrderStatus(List<Integer> orderIds, String orderStatus) {
		String sql = """
						update orders
						set order_status = ?
						where order_id = ?
				""".trim();
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.clearBatch(); // 清除批次(重要 !!!)
			
			for(Integer orderId : orderIds) {
				pstmt.setString(1, orderStatus);
				pstmt.setInt(2, orderId);
				
				pstmt.addBatch(); // 加入批次
			}
			
			pstmt.executeBatch(); // 執行批次(一次整批傳送給 MySQL)
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
