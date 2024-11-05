package com.example.shoppingcart.controller;

import com.example.shoppingcart.entity.Product;
import com.example.shoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }
    
    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }
    
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }
    
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }
}