package com.example.interior.controllers;

import com.example.interior.dto.OrderItemResponse;

import com.example.interior.services.OrderItemService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendor/order-items")
public class VendorController {

    @Autowired
    private OrderItemService orderItemService;
    
//    @GetMapping("/{vendorId}")
//    public List<OrderItem> getVendorOrders(@PathVariable Long vendorId) {
//        return orderItemService.getVendorOrders(vendorId);
//    }
    
    @GetMapping("/{vendorId}")
    public ResponseEntity<?> getVendorOrders(@PathVariable Long vendorId) {
        List<OrderItemResponse> orders = orderItemService.getVendorOrders(vendorId);

        if (orders.isEmpty()) {
            return ResponseEntity.status(404).body("No orders found for this vendor.");
        }

        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{orderItemId}/update-status")
    public String updateOrderItemStatus(@PathVariable Long orderItemId, @RequestParam String status) {
        return orderItemService.updateOrderItemStatus(orderItemId, status);
    }
}
