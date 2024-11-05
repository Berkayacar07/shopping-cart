package com.example.shoppingcart.service.impl;


import com.example.shoppingcart.entity.Order;
import com.example.shoppingcart.repository.OrderRepository;
import com.example.shoppingcart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
    
    @Override
    public Optional<Order> getOrderById(String id) {
        return orderRepository.findById(id);
    }
    
    @Override
    public List<Order> getOrdersByCustomerId(String customerId) {
        return orderRepository.findAllByCustomerId(customerId);
    }
    
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    
    @Override
    public Order updateOrder(String id, Order order) {
        order.setId(id);
        return orderRepository.save(order);
    }
    
    @Override
    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }
}