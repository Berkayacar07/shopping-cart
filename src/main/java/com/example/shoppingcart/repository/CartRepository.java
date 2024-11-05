package com.example.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.shoppingcart.entity.*;

public interface CartRepository extends JpaRepository<Cart, String> {
    Cart findByCustomer(Customer customer);
}

