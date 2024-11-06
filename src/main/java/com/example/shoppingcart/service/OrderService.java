package com.example.shoppingcart.service;

import java.util.List;
import com.example.shoppingcart.entity.Order;
import java.util.Optional;

public interface OrderService {
    Order placeOrder(Order order);
    Order getOrderById(String id);
    List<Order> getAllOrdersForCustomer(String customerId);
    List<Order> getAllOrders();
}

