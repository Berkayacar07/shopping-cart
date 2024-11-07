package com.example.shoppingcart.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.shoppingcart.entity.*;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findAllByCustomerId(String customerId);
    Optional<Order> findByCode(String code);
}
