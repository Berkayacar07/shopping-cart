package com.example.shoppingcart.service.impl;

import com.example.shoppingcart.entity.Product;
import com.example.shoppingcart.repository.ProductRepository;
import com.example.shoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    
    @Override
    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }
    
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    @Override
    public Product updateProduct(String id, Product product) {
        product.setId(id);
        return productRepository.save(product);
    }
    
    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
