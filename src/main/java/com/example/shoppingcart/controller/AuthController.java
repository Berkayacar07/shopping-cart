package com.example.shoppingcart.controller;

import com.example.shoppingcart.response.ApiResponse;
import com.example.shoppingcart.entity.Customer;
import com.example.shoppingcart.service.CustomerService;
import com.example.shoppingcart.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final CustomerService customerService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public AuthController(CustomerService customerService, JwtService jwtService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.customerService = customerService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }
    
    @PostMapping("/register")
    public ApiResponse addCustomer(@Valid @RequestBody Customer customer) {
        Customer existingCustomer = customerService.findByEmail(customer.getEmail());
        if (existingCustomer != null) {
            throw new BadCredentialsException(customer.getEmail() + " zaten kayıtlı");
        }
        
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerService.addCustomer(customer);
        
        return new ApiResponse("Kullanıcı başarıyla kaydedildi");
    }
    
    @PostMapping("/login")
    public ApiResponse loginCustomer(@RequestBody Customer customer) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(customer.getEmail(), customer.getPassword()));
            
            Customer authenticatedCustomer = customerService.findByEmail(customer.getEmail());
            if (!authenticatedCustomer.isEnabled()) {
                throw new DisabledException("Kullanıcı hesabı devre dışı!");
            }
            
            String token = jwtService.generateToken(authentication.getName());
            
            return new ApiResponse("Giriş başarılı", token);
            
        } catch (DisabledException e) {
            throw new DisabledException("Kullanıcı hesabı devre dışı!");
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Kullanıcı adı veya şifre hatalı!");
        }
    }
}
