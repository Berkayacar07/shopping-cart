package com.example.shoppingcart.service;

import com.example.shoppingcart.entity.Customer;
import com.example.shoppingcart.response.CustomerResponse;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    CustomerResponse addCustomer(Customer customer);
    Optional<CustomerResponse> getCustomerById(String id);
    List<CustomerResponse> getAllCustomers();
    CustomerResponse updateCustomer(String id, Customer customer);
    void deleteCustomer(String id);
    Customer findByEmail(String email);
}
