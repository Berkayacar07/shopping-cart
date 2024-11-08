package com.example.shoppingcart.service.impl;

import com.example.shoppingcart.entity.*;
import com.example.shoppingcart.repository.*;
import com.example.shoppingcart.response.*;
import com.example.shoppingcart.service.CartService;
import com.example.shoppingcart.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private CartService cartService;
    
    private OrderResponse convertToOrderResponseDTO(Order order) {
        OrderResponse response = OrderResponse.builder().
                id(order.getId()).
                createDate(order.getCreateDate().toString()).
                updateDate(order.getUpdateDate().toString()).
                build();
        
        CustomerResponse customerDTO = CustomerResponse.builder().
                id(order.getCustomer().getId()).
                name(order.getCustomer().getName()).
                email(order.getCustomer().getEmail()).
                address(order.getCustomer().getAddress()).
                enabled(order.getCustomer().isEnabled()).
                build();

        response.setCustomer(customerDTO);
        
        List<OrderItemResponse> orderItems = order.getOrderItems().stream().map(item -> {
            OrderItemResponse itemDTO = OrderItemResponse.builder().
                    id(item.getId()).
                    createDate(item.getCreateDate().toString()).
                    updateDate(item.getUpdateDate().toString()).
                    productId(item.getProduct().getId()).
                    quantity(item.getQuantity()).
                    price(item.getPrice()).
                    code(order.getCode()).
                    build();

            return itemDTO;
        }).collect(Collectors.toList());
        
        response.setOrderItems(orderItems);
        return response;
    }
    @Override
    @Transactional
    public OrderResponse placeOrder(String customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() ->
                new IllegalArgumentException("Müşteri bulunamadı: " + customerId));
        Cart cart = customer.getCart();
        
        String orderCode = System.currentTimeMillis() + "-" + (int) (Math.random() * 10000);
        
        Order order = Order.builder().
                customer(customer).
                totalPrice(cart.getTotalPrice()).
                code(orderCode).
                        build();
        
        for (CartItem cartItem : cart.getCartItems()) {
            Product product = productRepository.findById(cartItem.getProduct().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Ürün bulunamadı: " + cartItem.getProduct().getId()));
            
            if (product.getStock() < cartItem.getQuantity()) {
                throw new IllegalArgumentException("Stokta yeterli miktar yok: " + product.getName());
            }
            
            product.setStock(product.getStock() - cartItem.getQuantity());
            
            OrderItem orderItem = OrderItem.builder().
                    product(product).
                    quantity(cartItem.getQuantity()).
                    price(product.getPrice()).
                    build();
            
            order.addOrderItem(orderItem);
            
            productRepository.save(product);
        }
        
        Order savedOrder = orderRepository.save(order);
        
        cartService.emptyCart(customer.getCart().getId());
        
        return convertToOrderResponseDTO(savedOrder);
    }
    
    
    
    
    @Override
    public OrderResponse getOrderById(String id) {
        Optional<Order> order = orderRepository.findById(id);
        return convertToOrderResponseDTO(order.orElseThrow(() -> new IllegalArgumentException("Sipariş bulunamadı: " + id)));
    }
    
    @Override
    public List<OrderResponse> getAllOrdersForCustomer(String customerId) {
        List<Order> orders = orderRepository.findAllByCustomerId(customerId);
        
        return orders.stream()
                .map(this::convertToOrderResponseDTO)
                .collect(Collectors.toList());
    }
    
    
    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        
        return orders.stream()
                .map(this::convertToOrderResponseDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public OrderResponse findByCode(String code) {
        Optional<Order> order =  orderRepository.findByCode(code);
        return convertToOrderResponseDTO(order.orElseThrow(() -> new IllegalArgumentException("Sipariş bulunamadı: " + code)));
    }
    
}

