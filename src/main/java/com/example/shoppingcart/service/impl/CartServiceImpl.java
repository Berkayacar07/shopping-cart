package com.example.shoppingcart.service.impl;

import com.example.shoppingcart.entity.*;
import com.example.shoppingcart.repository.*;
import com.example.shoppingcart.response.CartResponse;
import com.example.shoppingcart.service.CartService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CartServiceImpl implements CartService {
    
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    
    // Sepet ID'sine göre sepeti getir
    @Override
    public Optional<CartResponse> getCartById(String id) {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            List<CartResponse.CartItemDTO> cartItems = cart.getCartItems().stream()
                    .map(cartItem -> new CartResponse.CartItemDTO(cartItem))
                    .collect(Collectors.toList());
            return Optional.of(new CartResponse(cartItems, cart.getTotalPrice()));
        } else {
            throw new IllegalArgumentException("Bu ID ile sepet bulunamadı: " + id);
        }
    }
    
    // Sepeti boşalt
    @Override
    public void emptyCart(String cartId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.getCartItems().clear();
            cart.setTotalPrice(0.0);
            cartRepository.save(cart);
        } else {
            throw new IllegalArgumentException("Bu ID ile sepet bulunamadı: " + cartId);
        }
    }
    
    // Müşteriye ait sepeti getir
    @Override
    public CartResponse getCartByCustomer(Customer customer) {
        Cart cart = cartRepository.findByCustomer(customer);
        if (cart != null) {
            List<CartResponse.CartItemDTO> cartItems = cart.getCartItems().stream()
                    .map(cartItem -> new CartResponse.CartItemDTO(cartItem))
                    .collect(Collectors.toList());
            return new CartResponse(cartItems, cart.getTotalPrice());
        } else {
            throw new IllegalArgumentException("Bu müşteri için sepet bulunamadı: " + customer.getId());
        }
    }
    
    // Sepeti güncelle
    @Override
    @Transactional
    public CartResponse updateCart(String id, List<CartItem> cartItems) {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.getCartItems().clear();
            
            for (CartItem cartItem : cartItems) {
                Product product = productRepository.findById(cartItem.getProduct().getId())
                        .orElseThrow(() -> new IllegalArgumentException("Bu ID ile ürün bulunamadı: " + cartItem.getProduct().getId()));
                
                if (product.getStock() < cartItem.getQuantity()) {
                    throw new IllegalArgumentException("Ürün: " + product.getName() + " için yeterli stok yok. Mevcut stok: " + product.getStock());
                }
                
                cartItem.setCart(cart);
                cart.getCartItems().add(cartItem);
            }
            
            updateTotalPrice(cart);
            cartRepository.save(cart);
            
            List<CartResponse.CartItemDTO> updatedCartItems = cart.getCartItems().stream()
                    .map(cartItem -> new CartResponse.CartItemDTO(cartItem))
                    .collect(Collectors.toList());
            
            return new CartResponse(updatedCartItems, cart.getTotalPrice());
        } else {
            throw new IllegalArgumentException("Bu ID ile sepet bulunamadı: " + id);
        }
    }
    
    // Sepete ürün ekle
    @Override
    public CartResponse addProductToCart(String cartId, CartItem cartItem) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            Product product = productRepository.findById(cartItem.getProduct().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Bu ID ile ürün bulunamadı: " + cartItem.getProduct().getId()));
            log.info("Sepete ürün ekleniyor: " + product.getName());
            
            if (cartItem.getQuantity() > product.getStock()) {
                throw new IllegalArgumentException("Sepetteki miktar stok miktarını aşıyor. Stok miktarı: " + product.getStock());
            }
            
            CartItem existingCartItem = cart.getCartItems().stream()
                    .filter(item -> item.getProduct().getId().equals(product.getId()))
                    .findFirst()
                    .orElse(null);
            
            if (existingCartItem != null) {
                int newQuantity = existingCartItem.getQuantity() + cartItem.getQuantity();
                
                if (newQuantity > product.getStock()) {
                    throw new IllegalArgumentException("Sepetteki toplam miktar stok miktarını aşıyor. Stok miktarı: " + product.getStock());
                }
                
                existingCartItem.setQuantity(newQuantity);
            } else {
                cartItem.setCart(cart);
                cart.getCartItems().add(cartItem);
            }
            
            updateTotalPrice(cart);
            cartRepository.save(cart);
            
            List<CartResponse.CartItemDTO> updatedCartItems = cart.getCartItems().stream()
                    .map(cartItem1 -> new CartResponse.CartItemDTO(cartItem1))
                    .collect(Collectors.toList());
            
            return new CartResponse(updatedCartItems, cart.getTotalPrice());
        } else {
            throw new IllegalArgumentException("Bu ID ile sepet bulunamadı: " + cartId);
        }
    }
    
    // Sepetten ürün çıkar
    @Override
    public CartResponse removeProductFromCart(String cartId, CartItem cartItem) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            Product product = productRepository.findById(cartItem.getProduct().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Bu ID ile ürün bulunamadı: " + cartItem.getProduct().getId()));
            
            CartItem existingCartItem = cart.getCartItems().stream()
                    .filter(item -> item.getProduct().equals(product))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Sepette bu ID ile ürün bulunamadı: " + cartId));
            
            cart.getCartItems().remove(existingCartItem);
            updateTotalPrice(cart);
            cartRepository.save(cart);
            
            List<CartResponse.CartItemDTO> updatedCartItems = cart.getCartItems().stream()
                    .map(cartItem1 -> new CartResponse.CartItemDTO(cartItem1))
                    .collect(Collectors.toList());
            
            return new CartResponse(updatedCartItems, cart.getTotalPrice());
        } else {
            throw new IllegalArgumentException("Bu ID ile sepet bulunamadı: " + cartId);
        }
    }
    
    // Toplam fiyatı güncelle
    private void updateTotalPrice(Cart cart) {
        double total = 0.0;
        
        for (CartItem cartItem : cart.getCartItems()) {
            Product product = productRepository.findById(cartItem.getProduct().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Bu ID ile ürün bulunamadı: " + cartItem.getProduct().getId()));
            
            total += cartItem.getQuantity() * product.getPrice();
        }
        
        cart.setTotalPrice(total);
    }
}
