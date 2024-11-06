package com.example.shoppingcart.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.shoppingcart.entity.Customer;
import com.example.shoppingcart.repository.CustomerRepository;
import jakarta.transaction.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    private final CustomerRepository customerRepository;
    
    public CustomUserDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(username);
        if (customer == null) {
            throw new UsernameNotFoundException("Customer not found");
        }
        return customer;
    }
}

