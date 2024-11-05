package com.example.shoppingcart.service;

import java.util.List;
import com.example.shoppingcart.entity.Order;
import java.util.Optional;

public interface OrderService {
    Order createOrder(Order order);
    Optional<Order> getOrderById(String id);
    List<Order> getOrdersByCustomerId(String customerId);
    List<Order> getAllOrders();
    Order updateOrder(String id, Order order);
    void deleteOrder(String id);
}

