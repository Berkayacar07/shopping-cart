package com.example.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.shoppingcart.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {}

