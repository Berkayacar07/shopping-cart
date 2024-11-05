package com.example.shoppingcart.service;

import java.util.*;
import com.example.shoppingcart.entity.*;

public interface CartService {
    Cart createCart(Cart cart);
    Optional<Cart> getCartById(String id);
    List<Cart> getAllCarts();
    Cart updateCart(String id, Cart cart);
    void deleteCart(String id);
    Cart getCartByCustomer(Customer customer);
}

