package javaweb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
	private Integer productId;
	private String productName;
	private Double price;
	private Integer stockQuantity;
}
