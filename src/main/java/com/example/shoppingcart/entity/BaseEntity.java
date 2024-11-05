package com.example.shoppingcart.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime createDate;
    
    @Column(nullable = false)
    private LocalDateTime updateDate;
    
    @PrePersist
    public void onCreate() {
        createDate = LocalDateTime.now();
    }
    
    @PreUpdate
    public void onUpdate() {
        updateDate = LocalDateTime.now();
    }
}
