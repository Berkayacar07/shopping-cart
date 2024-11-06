package com.example.shoppingcart.service.impl;

import com.example.shoppingcart.entity.Cart;
import com.example.shoppingcart.entity.Customer;
import com.example.shoppingcart.entity.Product;
import com.example.shoppingcart.repository.CartRepository;
import com.example.shoppingcart.response.CartResponseDTO;
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
    public Optional<CartResponseDTO> getCartById(String id) {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            return Optional.of(new CartResponseDTO(cart.getProducts(), cart.getTotalPrice()));
        } else {
            throw new IllegalArgumentException("Cart not found with ID: " + id);
        }
    }
    
    @Override
    public CartResponseDTO getCartByCustomer(Customer customer) {
        Cart cart = cartRepository.findByCustomer(customer);
        if (cart == null) {
            throw new IllegalArgumentException("Cart not found for the customer: " + customer.getId());
        }
        return new CartResponseDTO(cart.getProducts(), cart.getTotalPrice());
    }
    
    @Override
    public CartResponseDTO updateCart(String id, List<Product> products) {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.setProducts(products);
            cartRepository.save(cart);
            return new CartResponseDTO(cart.getProducts(), cart.getTotalPrice());
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
            cart.setTotalPrice(0);
            cartRepository.save(cart);
        } else {
            throw new IllegalArgumentException("Cart not found with ID: " + id);
        }
    }
    
    @Override
    public CartResponseDTO addProductToCart(String cartId, Product product) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            
            cart.getProducts().add(product);
            
            double updatedTotalPrice = cart.getTotalPrice() + product.getPrice();
            cart.setTotalPrice(updatedTotalPrice);
            
            cartRepository.save(cart);
            
            return new CartResponseDTO(cart.getProducts(), cart.getTotalPrice());
        } else {
            throw new IllegalArgumentException("Cart not found with ID: " + cartId);
        }
    }
    
    @Override
    public CartResponseDTO removeProductFromCart(String cartId, Product product) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            
            if (cart.getProducts().contains(product)) {
                cart.getProducts().remove(product);
                
                double updatedTotalPrice = cart.getTotalPrice() - product.getPrice();
                cart.setTotalPrice(updatedTotalPrice);
                
                cartRepository.save(cart);
                
                return new CartResponseDTO(cart.getProducts(), cart.getTotalPrice());
            } else {
                throw new IllegalArgumentException("Product not found in cart with ID: " + cartId);
            }
        } else {
            throw new IllegalArgumentException("Cart not found with ID: " + cartId);
        }
    }
}

