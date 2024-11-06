package com.example.shoppingcart.service.impl;

import com.example.shoppingcart.entity.Cart;
import com.example.shoppingcart.entity.Customer;
import com.example.shoppingcart.entity.Product;
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
    public Optional<Cart> getCartById(String id) {
        return cartRepository.findById(id);
    }
    
    @Override
    public Cart getCartByCustomer(Customer customer) {
        return cartRepository.findByCustomer(customer);
    }
    
    @Override
    public Cart updateCart(String id, List<Product> products) {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.setProducts(products);
            return cartRepository.save(cart);
        } else {
            throw new IllegalArgumentException("Cart not found with ID: " + id);
        }
    }
    
    @Override
    public void emptyCart(String id) {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.getProducts().clear();
            cartRepository.save(cart);
        } else {
            throw new IllegalArgumentException("Cart not found with ID: " + id);
        }
    }
    
    @Override
    public Cart addProductToCart(String cartId, Product product) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.getProducts().add(product); // Sepette ürünler listesine eklenir
            return cartRepository.save(cart);
        } else {
            throw new IllegalArgumentException("Cart not found with ID: " + cartId);
        }
    }
    
    @Override
    public Cart removeProductFromCart(String cartId, Product product) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.getProducts().remove(product); // Sepetteki ürün listesi güncellenir
            return cartRepository.save(cart);
        } else {
            throw new IllegalArgumentException("Cart not found with ID: " + cartId);
        }
    }
}
