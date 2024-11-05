package com.example.shoppingcart.service.impl;

import com.example.shoppingcart.entity.Cart;
import com.example.shoppingcart.entity.Customer;
import com.example.shoppingcart.repository.CartRepository;
import com.example.shoppingcart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    
    @Autowired
    private CartRepository cartRepository;
    
    @Override
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }
    
    @Override
    public Optional<Cart> getCartById(String id) {
        return cartRepository.findById(id);
    }
    
    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }
    
    @Override
    public Cart updateCart(String id, Cart cart) {
        cart.setId(id);
        return cartRepository.save(cart);
    }
    
    @Override
    public void deleteCart(String id) {
        cartRepository.deleteById(id);
    }
    
    @Override
    public Cart getCartByCustomer(Customer customer) {
        return cartRepository.findByCustomer(customer);
    }
}
