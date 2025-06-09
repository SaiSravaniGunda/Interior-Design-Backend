package com.example.interior.controllers;

import com.example.interior.models.Shop;
import com.example.interior.services.ShopService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shops")
public class ShopController {

    @Autowired
    private ShopService shopService;

    // Create Shop
    @PostMapping("/create/{vendorId}")
    public ResponseEntity<?> createShop(@PathVariable Long vendorId, @RequestBody Shop shop) {
        try {
            return ResponseEntity.ok(shopService.createShop(vendorId, shop));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Get Vendor's Shop
    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<?> getShop(@PathVariable Long vendorId) {
        try {
            return ResponseEntity.ok(shopService.getVendorShop(vendorId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Shop>> getAllShops() {
        return ResponseEntity.ok(shopService.getAllShops());
    }
}
