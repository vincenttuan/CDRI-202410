package com.example.cart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.cart.model.entity.Product;
import com.example.cart.model.entity.ProductImage;
import com.example.cart.repository.ProductImageRepository;
import com.example.cart.repository.ProductRepository;

@SpringBootTest
class AddProductsTests {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductImageRepository productImageRepository;
    
    @Test
    void add2() {
        ProductImage productImage = new ProductImage();
        productImage.setImageBase64("12345678");
        //productImageRepository.save(productImage);
        Product product = new Product();
        product.setName("PC");
        product.setPrice(40000);
        // 設置關聯
        product.setProductImage(productImage);
        
        // 保存 Product，因為它是主要維護關聯的一方
        productRepository.save(product);
    }
    
    //@Test
    void add() {
        ProductImage productImage = new ProductImage();
        productImage.setImageBase64("abcdefg");
        productImageRepository.save(productImage);
        
        Product product = new Product();
        product.setName("PC");
        product.setPrice(40000);
        // 設置關聯
        product.setProductImage(productImage);
        
        // 保存 Product，因為它是主要維護關聯的一方
        productRepository.save(product);
    }
}
