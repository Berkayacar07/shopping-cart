package com.example.shoppingcart.service.impl;

import com.example.shoppingcart.entity.*;
import com.example.shoppingcart.exception.CustomerNotFoundException;
import com.example.shoppingcart.repository.CustomerRepository;
import com.example.shoppingcart.response.CustomerResponse;
import com.example.shoppingcart.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Override
    public CustomerResponse addCustomer(Customer customer) {
        Cart cart = Cart.builder()
                .customer(customer)
                .totalPrice(0.0)
                .build();
        
        customer.setCart(cart);
        customer.setEnabled(true);
        
        return convertToCustomerResponse(customerRepository.save(customer));
    }
    
    private CustomerResponse convertToCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .enabled(customer.isEnabled())
                .build();
    }
    
    @Override
    public Optional<CustomerResponse> getCustomerById(String id) {
        Optional<Customer> customer = customerRepository.findById(id);
        
        return customer.map(this::convertToCustomerResponse);
    }
    
    
    @Override
    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        
        return customers.stream()
                .map(this::convertToCustomerResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public CustomerResponse updateCustomer(String id, Customer customer) {
        customer.setId(id);
        return convertToCustomerResponse(customerRepository.save(customer));
    }
    
    @Override
    public void deleteCustomer(String id) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            customer.setEnabled(false);
            customerRepository.save(customer);
        } else {
            throw new CustomerNotFoundException("Bu id ile kullanıcı bulunamadı: " + id);
        }
    }
    
    
    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}