package com.example.shoppingcart.service;

import java.util.*;
import com.example.shoppingcart.entity.Order;
import com.example.shoppingcart.response.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(String customerId);
    OrderResponse getOrderById(String id);
    List<OrderResponse> getAllOrdersForCustomer(String customerId);
    List<OrderResponse> getAllOrders();
    OrderResponse findByCode(String code);
    
}

