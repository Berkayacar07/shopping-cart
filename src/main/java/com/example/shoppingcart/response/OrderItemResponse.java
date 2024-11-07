package com.example.shoppingcart.response;

import lombok.*;

@Data
@Builder
public class OrderItemResponse {
    private String id;
    private String createDate;
    private String updateDate;
    private String productId;
    private int quantity;
    private double price;
    private String code;
    
}
