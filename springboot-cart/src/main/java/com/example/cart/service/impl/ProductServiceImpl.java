package com.example.cart.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cart.model.dto.ProductDTO;
import com.example.cart.repository.ProductRepository;
import com.example.cart.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<ProductDTO> getAllProducts() {
		return productRepository.findAll().stream() // ...Product
				.map(product -> modelMapper.map(product, ProductDTO.class)) // ...ProductDTO
				.collect(Collectors.toList()); // List<ProductDTO>
	}

	@Override
	public Optional<ProductDTO> getProductById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public ProductDTO saveProduct(ProductDTO productDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
