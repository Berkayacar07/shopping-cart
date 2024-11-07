package com.example.shoppingcart.service;

import java.util.List;
import com.example.shoppingcart.entity.Order;
import com.example.shoppingcart.response.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(String customerId);
    Order getOrderById(String id);
    List<Order> getAllOrdersForCustomer(String customerId);
    List<Order> getAllOrders();
}

