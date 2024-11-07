package com.example.shoppingcart.controller;

import com.example.shoppingcart.entity.Customer;
import com.example.shoppingcart.response.CustomerResponse;
import com.example.shoppingcart.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;
    
    @PostMapping
    public CustomerResponse createCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }
    
    @GetMapping("/{id}")
    public Optional<CustomerResponse> getCustomerById(@PathVariable String id) {
        return customerService.getCustomerById(id);
    }
    
    @GetMapping
    public List<CustomerResponse> getAllCustomers() {
        return customerService.getAllCustomers();
    }
    
    @PutMapping("/{id}")
    public CustomerResponse updateCustomer(@PathVariable String id, @RequestBody Customer customer) {
        return customerService.updateCustomer(id, customer);
    }
    
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
    }
}
