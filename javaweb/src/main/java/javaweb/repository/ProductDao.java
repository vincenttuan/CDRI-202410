package javaweb.repository;

import java.util.List;
import java.util.Map;

import javaweb.model.entity.Product;

public interface ProductDao {
	List<Product> findAllProducts();
	// Map<商品名稱(product_name), 銷售金額 (total_sales)>
	Map<String, Double> querySalesRanking(); // 銷售排行
}
