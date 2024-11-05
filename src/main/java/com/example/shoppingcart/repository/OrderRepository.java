package com.example.shoppingcart.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.shoppingcart.entity.*;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findAllByCustomerId(String customerId);
}
