package com.example.shoppingcart.controller;

import com.example.shoppingcart.entity.Order;
import com.example.shoppingcart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    @PostMapping
    public Order placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }
    
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable String id) {
        return orderService.getOrderById(id);
    }
    
    @GetMapping("/customer/{customerId}")
    public List<Order> getAllOrdersForCustomer(@PathVariable String customerId) {
        return orderService.getAllOrdersForCustomer(customerId);
    }
    
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
}
