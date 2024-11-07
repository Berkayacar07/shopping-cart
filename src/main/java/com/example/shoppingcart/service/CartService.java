package com.example.shoppingcart.service;

import com.example.shoppingcart.entity.CartItem;
import com.example.shoppingcart.entity.Customer;
import com.example.shoppingcart.response.CartResponse;

import java.util.List;
import java.util.Optional;

public interface CartService {
    Optional<CartResponse> getCartById(String id);
    CartResponse getCartByCustomer(Customer customer);
    CartResponse updateCart(String id, List<CartItem> cartItems); // Güncellendi
    void emptyCart(String id);
    CartResponse addProductToCart(String cartId, CartItem cartItem); // Güncellendi
    CartResponse removeProductFromCart(String cartId, CartItem cartItem); // Güncellendi
}
