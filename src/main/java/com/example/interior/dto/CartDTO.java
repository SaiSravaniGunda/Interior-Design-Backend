package com.example.interior.dto;

import java.util.List;

public class CartDTO {
    private Long cartId;
    private Long userId;
    private List<CartItemDTO> cartItems;

    public CartDTO(Long cartId, Long userId, List<CartItemDTO> cartItems) {
        this.cartId = cartId;
        this.userId = userId;
        this.cartItems = cartItems;
    }

    public Long getCartId() { return cartId; }
    public Long getUserId() { return userId; }
    public List<CartItemDTO> getCartItems() { return cartItems; }
}
