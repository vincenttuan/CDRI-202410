package com.example.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cart.model.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
