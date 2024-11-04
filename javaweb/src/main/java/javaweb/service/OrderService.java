package javaweb.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javaweb.model.dto.OrderDto;
import javaweb.model.entity.Order;
import javaweb.model.entity.Product;
import javaweb.repository.OrderDao;
import javaweb.repository.OrderDaoImpl;
import javaweb.repository.ProductDao;
import javaweb.repository.ProductDaoImpl;

public class OrderService {
	
	private OrderDao orderDao = new OrderDaoImpl();
	private ProductDao productDao = new ProductDaoImpl();
	
	// 同時新增多筆訂單
	// userId: 使用者 id
	// productIds: 每一件商品的 id   [1, 2, 3, 4, 5]
	// unitPrices: 每一件商品的單價    [10, 20, 30, 40, 50]
	// amounts:    每一件商品的購買數量 [5, 0, 3, 0, 2]
    //                        i:  0  1  2  3  4
	// 注意: productIds 的長度必須等於 amounts 的長度
	public void batchAddOrders(Integer userId, String[] productIds, String[] unitPrices, String[] amounts) {
		List<Order> orders = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<productIds.length;i++) {
			Integer productId = Integer.parseInt(productIds[i]);
			Double unitPrice = Double.parseDouble(unitPrices[i]);
			Integer amount = Integer.parseInt(amounts[i]);
			
			// 過濾有效的訂單
			if(amount <= 0) {
				continue;
			}
			
			Order order = new Order();
			order.setUserId(userId);
			order.setOrderDate(sdf.format(new Date()));
			order.setProductId(productId);
			order.setQuantity(amount);
			order.setUnitPrice(unitPrice); // ?
			order.setSubtotal((int)(order.getQuantity() * order.getUnitPrice()));
			order.setOrderStatus("Pending");
			
			orders.add(order);
		}
		
		orderDao.batchAddOrders(orders);
	}
	
	// 查找該使用者的所有訂單，根據訂單狀態進行篩選
	public List<OrderDto> findAllOrders(Integer userId, String orderStatus) {
		// 取得訂單資料
		List<Order> orders = orderDao.findAllOrders(userId, orderStatus);
		// 取得所有商品
		List<Product> products = productDao.findAllProducts();
		
		// 將 List<Order> 轉 List<OrderDto>
		List<OrderDto> orderDtos = new ArrayList<>();
		for(Order order : orders) {
			OrderDto orderDto = new OrderDto();
			orderDto.setOrderId(order.getOrderId());
			orderDto.setUserId(order.getUserId());
			orderDto.setOrderDate(order.getOrderDate());
			orderDto.setProductId(order.getProductId());
			orderDto.setQuantity(order.getQuantity());
			orderDto.setUnitPrice(order.getUnitPrice());
			orderDto.setSubtotal(order.getSubtotal());
			orderDto.setOrderStatus(order.getOrderStatus());
			// 透過 productId 找到對應的 productName
			orderDto.setProductName(
					products.stream()
							.filter(p -> p.getProductId().equals(orderDto.getProductId()))
							.findFirst()
							.orElseGet(null)
							.getProductName()
			);
			// 注入到 orderDtos 集合
			orderDtos.add(orderDto);
		}
		
		return orderDtos;
	}
	
}
