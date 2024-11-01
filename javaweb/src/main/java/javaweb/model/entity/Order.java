package javaweb.model.entity;

import lombok.Data;

@Data
public class Order {
	private Integer orderId;
	private Integer userId;
	private String orderDate;
	private Integer productId;
	private Integer quantity;
	private Double unitPrice;
	private Integer subtotal;
	private String orderStatus;
	
}
