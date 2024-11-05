package com.example.shoppingcart.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity {
    
    @ManyToOne
    private Customer customer;
    
    @OneToMany(mappedBy = "order")
    private List<Product> products;
    
    private double totalPrice;
}
