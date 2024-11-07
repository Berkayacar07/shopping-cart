package com.example.shoppingcart.response;

import java.util.List;
import lombok.*;

@Data
@Builder
public class OrderResponse {
    private String id;
    private String createDate;
    private String updateDate;
    private CustomerResponse customer;
    private List<OrderItemResponse> orderItems;
}
