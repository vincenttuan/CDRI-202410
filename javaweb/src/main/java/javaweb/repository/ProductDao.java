package javaweb.repository;

import java.util.List;

import javaweb.model.entity.Product;

public interface ProductDao {
	List<Product> findAllProducts();
}
