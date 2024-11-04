package javaweb.model.dto;

import lombok.Data;

@Data
public class OrderDto {
	private Integer orderId;
	private Integer userId;
	private String orderDate;
	private Integer productId;
	private Integer quantity;
	private Double unitPrice;
	private Integer subtotal;
	private String orderStatus;
	// 外加欄位
	private String productName;
}
