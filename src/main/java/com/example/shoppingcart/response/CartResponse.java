package com.example.shoppingcart.response;

import com.example.shoppingcart.entity.*;
import lombok.*;
import java.util.List;

@Getter
@Setter
public class CartResponse {
    private List<CartItemDTO> cartItems;
    private double totalPrice;
    
    public CartResponse(List<CartItemDTO> cartItems, double totalPrice) {
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }
    
    @Setter
    @Getter
    public static class CartItemDTO {
        private String productId;
        private String name;
        private String description;
        private int quantity;
        
        public CartItemDTO(CartItem cartItem) {
            Product product = cartItem.getProduct();
            this.productId = product.getId();
            this.name = product.getName();
            this.description = product.getDescription();
            this.quantity = cartItem.getQuantity();
        }
        
    }
}
