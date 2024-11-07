package com.example.shoppingcart.service;

import com.example.shoppingcart.entity.CartItem;
import com.example.shoppingcart.entity.Customer;
import com.example.shoppingcart.response.CartResponseDTO;

import java.util.List;
import java.util.Optional;

public interface CartService {
    Optional<CartResponseDTO> getCartById(String id);
    CartResponseDTO getCartByCustomer(Customer customer);
    CartResponseDTO updateCart(String id, List<CartItem> cartItems); // Güncellendi
    void emptyCart(String id);
    CartResponseDTO addProductToCart(String cartId, CartItem cartItem); // Güncellendi
    CartResponseDTO removeProductFromCart(String cartId, CartItem cartItem); // Güncellendi
}
