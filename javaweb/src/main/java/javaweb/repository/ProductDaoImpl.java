package javaweb.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javaweb.model.entity.Product;

public class ProductDaoImpl extends BaseDao implements ProductDao {

	@Override
	public List<Product> findAllProducts() {
		List<Product> products = new ArrayList<>();
		String sql = "select product_id, product_name, price, stock_quantity from product";
		try(Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)) {
			
			while (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setPrice(rs.getDouble("price"));
				product.setStockQuantity(rs.getInt("stock_quantity"));
				// 注入到 products 集合
				products.add(product);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return products;
	}

	@Override
	public Map<String, Double> querySalesRanking() {
		String sql = """
				SELECT p.product_name, SUM(o.subtotal) AS total_sales
				FROM orders o
				LEFT JOIN product p ON o.product_id = p.product_id
				GROUP BY p.product_name
				ORDER BY total_sales DESC
				""".trim();
		// 存放銷售排行 map
		Map<String, Double> map = new LinkedHashMap<>();
		
		try(Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)) {
			
			while (rs.next()) {
				
				String key = rs.getString("product_name");
				Double value = rs.getDouble("total_sales");
				// 將排行放到 map 集合中
				map.put(key, value);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

}
