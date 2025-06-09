package com.example.interior.controllers;

import com.example.interior.dto.UserOrderResponseDTO;
import com.example.interior.models.Order;



import com.example.interior.services.OrderService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    
     
    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(@RequestBody Map<String, Object> payload) {
        try {
            Order order = orderService.placeOrder(payload);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Order placement failed: " + e.getMessage());
        }
    }




    @GetMapping("/user/{userId}")
    public List<UserOrderResponseDTO> getUserOrders(@PathVariable Long userId) {
        return orderService.getUserOrders(userId);
    }
}
