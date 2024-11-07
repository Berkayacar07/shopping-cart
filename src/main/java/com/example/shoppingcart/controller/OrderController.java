package com.example.shoppingcart.controller;

import com.example.shoppingcart.request.OrderRequest;
import com.example.shoppingcart.response.OrderResponse;
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
    public OrderResponse placeOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.placeOrder(orderRequest.getCustomerId());
    }
    
    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable String id) {
        return orderService.getOrderById(id);
    }
    
    @GetMapping("/customer/{customerId}")
    public List<OrderResponse> getAllOrdersForCustomer(@PathVariable String customerId) {
        return orderService.getAllOrdersForCustomer(customerId);
    }
    
    @GetMapping
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }
    
    @GetMapping("/code/{code}")
    public OrderResponse findByCode(@PathVariable String code) {
        return orderService.findByCode(code);
    }
}
