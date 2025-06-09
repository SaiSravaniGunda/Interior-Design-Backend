package com.example.interior.dto;

import java.util.List;

public class OrderRequestDTO {
    private Long userId;
    private List<CartItemDTO> cartItems;

    public OrderRequestDTO() {}

    public OrderRequestDTO(Long userId, List<CartItemDTO> cartItems) {
        this.userId = userId;
        this.cartItems = cartItems;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<CartItemDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDTO> cartItems) {
        this.cartItems = cartItems;
    }
}
