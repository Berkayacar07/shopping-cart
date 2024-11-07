package com.example.shoppingcart.response;

import lombok.*;

@Data
@Builder
public class CustomerResponse {
    private String id;
    private String name;
    private String email;
    private String address;
    private boolean enabled;
}
