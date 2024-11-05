package com.example.shoppingcart.controller;

import com.example.shoppingcart.entity.Cart;
import com.example.shoppingcart.entity.Customer;
import com.example.shoppingcart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    
    @Autowired
    private CartService cartService;
    
    @PostMapping
    public Cart createCart(@RequestBody Cart cart) {
        return cartService.createCart(cart);
    }
    
    @GetMapping("/{id}")
    public Optional<Cart> getCartById(@PathVariable String id) {
        return cartService.getCartById(id);
    }
    
    @GetMapping("/customer/{customerId}")
    public Cart getCartByCustomer(@PathVariable String customerId) {
        Customer customer = Customer.builder()
                .id(customerId)
                .build();
        return cartService.getCartByCustomer(customer);
    }
    
    
    @GetMapping
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }
    
    @PutMapping("/{id}")
    public Cart updateCart(@PathVariable String id, @RequestBody Cart cart) {
        return cartService.updateCart(id, cart);
    }
    
    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable String id) {
        cartService.deleteCart(id);
    }
}
