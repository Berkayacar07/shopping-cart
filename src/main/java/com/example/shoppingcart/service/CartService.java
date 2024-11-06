package com.example.shoppingcart.service;

import com.example.shoppingcart.entity.Cart;
import com.example.shoppingcart.entity.Customer;
import com.example.shoppingcart.entity.Product;

import java.util.List;
import java.util.Optional;

public interface CartService {
    Optional<Cart> getCartById(String id);
    Cart getCartByCustomer(Customer customer);
    Cart updateCart(String id, List<Product> products);
    void emptyCart(String id);
    Cart addProductToCart(String cartId, Product product);
    Cart removeProductFromCart(String cartId, Product product);
}
