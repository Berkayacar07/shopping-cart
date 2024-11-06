package com.example.shoppingcart.service;

import com.example.shoppingcart.entity.Cart;
import com.example.shoppingcart.entity.Customer;
import com.example.shoppingcart.entity.Product;
import com.example.shoppingcart.response.CartResponseDTO;

import java.util.List;
import java.util.Optional;

public interface CartService {
    Optional<CartResponseDTO> getCartById(String id);
    CartResponseDTO getCartByCustomer(Customer customer);
    CartResponseDTO updateCart(String id, List<Product> products);
    void emptyCart(String id);
    CartResponseDTO addProductToCart(String cartId, Product product);
    CartResponseDTO removeProductFromCart(String cartId, Product product);
}
