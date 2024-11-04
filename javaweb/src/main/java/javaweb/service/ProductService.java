package javaweb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javaweb.model.dto.ProductDto;
import javaweb.model.entity.Product;
import javaweb.repository.ProductDao;
import javaweb.repository.ProductDaoImpl;

public class ProductService {
	private ProductDao productDao = new ProductDaoImpl();
	
	public List<ProductDto> findAllProducts() {
		List<Product> products = productDao.findAllProducts();
		List<ProductDto> productDtos = new ArrayList<>();
		
		products.stream().forEach((p) -> {
			productDtos.add(new ProductDto(p.getProductId(), p.getProductName(), p.getPrice(), p.getStockQuantity()));
		});
		
		return productDtos;
	}
	
	public Map<String, Double> querySalesRanking() {
		return productDao.querySalesRanking();
	}
}
