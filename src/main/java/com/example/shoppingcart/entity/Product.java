package com.example.shoppingcart.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {
    
    private String name;
    private String description;
    private double price;
    private int stock;
    
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
    
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
