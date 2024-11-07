package com.example.shoppingcart.controller;

import com.example.shoppingcart.entity.CartItem;
import com.example.shoppingcart.entity.Customer;
import com.example.shoppingcart.response.CartResponse;
import com.example.shoppingcart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    
    @Autowired
    private CartService cartService;
    
    @GetMapping("/{id}")
    public CartResponse getCartById(@PathVariable String id) {
        return cartService.getCartById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
    }
    
    @GetMapping("/customer/{customerId}")
    public CartResponse getCartByCustomer(@PathVariable String customerId) {
        Customer customer = new Customer();
        customer.setId(customerId);
        return cartService.getCartByCustomer(customer);
    }
    
    @PutMapping("/{id}")
    public CartResponse updateCart(@PathVariable String id, @RequestBody List<CartItem> cartItems) {
        return cartService.updateCart(id, cartItems);
    }
    
    @DeleteMapping("/{id}/empty")
    public void emptyCart(@PathVariable String id) {
        cartService.emptyCart(id);
    }
    
    @PostMapping("/{cartId}/addProduct")
    public CartResponse addProductToCart(@PathVariable String cartId, @RequestBody CartItem cartItem) {
        return cartService.addProductToCart(cartId, cartItem);
    }
    
    @DeleteMapping("/{cartId}/removeProduct")
    public CartResponse removeProductFromCart(@PathVariable String cartId, @RequestBody CartItem cartItem) {
        return cartService.removeProductFromCart(cartId, cartItem);
    }
}
