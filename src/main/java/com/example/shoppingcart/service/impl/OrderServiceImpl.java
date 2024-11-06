package com.example.shoppingcart.service.impl;

import com.example.shoppingcart.entity.Order;
import com.example.shoppingcart.entity.OrderItem;
import com.example.shoppingcart.entity.Product;
import com.example.shoppingcart.repository.OrderRepository;
import com.example.shoppingcart.repository.ProductRepository;
import com.example.shoppingcart.service.CartService;
import com.example.shoppingcart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CartService cartService;
    
    @Override
    public Order placeOrder(Order order) {
        for (OrderItem item : order.getOrderItems()) {
            Product product = item.getProduct();
            
            // Stok kontrolü
            if (product.getStock() < item.getQuantity()) {
                throw new IllegalArgumentException("Stokta yeterli miktar yok: " + product.getName());
            }
            
            // Stok güncellemesi
            product.setStock(product.getStock() - item.getQuantity());
            item.setPrice(product.getPrice());  // Sipariş anındaki fiyatı sakla
            productRepository.save(product);
        }
        
        // Siparişin toplam fiyatını hesapla
        order.calculateTotalPrice();
        
        // Siparişi kaydet
        Order savedOrder = orderRepository.save(order);
        
        // Müşterinin sepetini boşalt
        cartService.emptyCart(order.getCustomer().getId());
        
        return savedOrder;
    }
    
    @Override
    public Order getOrderById(String id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElse(null);
    }
    
    @Override
    public List<Order> getAllOrdersForCustomer(String customerId) {
        return orderRepository.findAllByCustomerId(customerId);
    }
    
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
