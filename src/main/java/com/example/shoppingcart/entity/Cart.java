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
@Table(name = "cart")
public class Cart extends BaseEntity {
    
    @OneToOne
    private Customer customer;
    
    @OneToMany(mappedBy = "cart")
    private List<Product> products;
    
    private double totalPrice;
}
