package com.example.shoppingcart.controller;

import com.example.shoppingcart.entity.Cart;
import com.example.shoppingcart.entity.Customer;
import com.example.shoppingcart.entity.Product;
import com.example.shoppingcart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    
    @Autowired
    private CartService cartService;
    
    @GetMapping("/{id}")
    public Cart getCartById(@PathVariable String id) {
        return cartService.getCartById(id).orElseThrow(() -> new IllegalArgumentException("Cart not found"));
    }
    
    @GetMapping("/customer/{customerId}")
    public Cart getCartByCustomer(@PathVariable String customerId) {
        Customer customer = new Customer();
        customer.setId(customerId);
        return cartService.getCartByCustomer(customer);
    }
    
    @PutMapping("/{id}")
    public Cart updateCart(@PathVariable String id, @RequestBody List<Product> products) {
        return cartService.updateCart(id, products);
    }
    
    @DeleteMapping("/{id}/empty")
    public void emptyCart(@PathVariable String id) {
        cartService.emptyCart(id);
    }
    
    @PostMapping("/{cartId}/addProduct")
    public Cart addProductToCart(@PathVariable String cartId, @RequestBody Product product) {
        return cartService.addProductToCart(cartId, product);
    }
    
    @DeleteMapping("/{cartId}/removeProduct")
    public Cart removeProductFromCart(@PathVariable String cartId, @RequestBody Product product) {
        return cartService.removeProductFromCart(cartId, product);
    }
}
