package com.example.shoppingcart.service.impl;

import com.example.shoppingcart.entity.*;
import com.example.shoppingcart.repository.CustomerRepository;
import com.example.shoppingcart.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Override
    public Customer createCustomer(Customer customer) {
        Cart cart = Cart.builder()
                .customer(customer)
                .totalPrice(0.0)
                .build();
        
        customer.setCarts(cart);
        
        return customerRepository.save(customer);
    }
    
    @Override
    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.findById(id);
    }
    
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    
    @Override
    public Customer updateCustomer(String id, Customer customer) {
        customer.setId(id);
        return customerRepository.save(customer);
    }
    
    @Override
    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }
    
    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}