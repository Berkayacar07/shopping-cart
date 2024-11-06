package com.example.shoppingcart.response;

import com.example.shoppingcart.entity.Product;
import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseDTO {
    private List<Product> products;
    private double totalPrice;
}
