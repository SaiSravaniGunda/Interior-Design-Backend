package com.example.interior.controllers;

import com.example.interior.dto.CartDTO;
import com.example.interior.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartByUser(userId));
    }

    @PostMapping("/add")
    public ResponseEntity<CartDTO> addToCart(@RequestParam Long userId, @RequestParam Long productId, @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.addToCart(userId, productId, quantity));
    }

//    @DeleteMapping("/remove")
//    public ResponseEntity<CartDTO> removeFromCart(@RequestParam Long userId, @RequestParam Long productId) {
//        return ResponseEntity.ok(cartService.removeFromCart(userId, productId));
//    }
    @DeleteMapping("/remove")
    public ResponseEntity<CartDTO> removeFromCart(@RequestParam Long userId, @RequestParam Long productId) {
        
        CartDTO updatedCart = cartService.removeFromCart(userId, productId);
        
        return ResponseEntity.ok(updatedCart);
    }


    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<String> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok("Cart cleared successfully");
    }
}
