package com.example.shoppingcart.service;

import com.example.shoppingcart.entity.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    Optional<Customer> getCustomerById(String id);
    List<Customer> getAllCustomers();
    Customer updateCustomer(String id, Customer customer);
    void deleteCustomer(String id);
    Customer findByEmail(String email);
}
