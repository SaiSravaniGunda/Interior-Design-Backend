package com.example.interior.controllers;

import com.example.interior.models.Product;
import com.example.interior.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Add a product to a shop
    @PostMapping("/add/{shopId}")
    public ResponseEntity<?> addProduct(@PathVariable Long shopId, @RequestBody Product product) {
        try {
            return ResponseEntity.ok(productService.addProduct(shopId, product));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Get all products of a shop
    @GetMapping("/shop/{shopId}")
    public ResponseEntity<List<Product>> getProductsByShop(@PathVariable Long shopId) {
        return ResponseEntity.ok(productService.getProductsByShop(shopId));
    }

    // Get a single product
    @GetMapping("/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable Long productId) {
        try {
            return ResponseEntity.ok(productService.getProductById(productId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Update a product
    @PutMapping("/update/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Long productId, @RequestBody Product product) {
        try {
            return ResponseEntity.ok(productService.updateProduct(productId, product));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Decrease stock when an order is placed
    @PutMapping("/decreaseStock/{productId}/{quantity}")
    public ResponseEntity<?> decreaseStock(@PathVariable Long productId, @PathVariable int quantity) {
        try {
            productService.decreaseStock(productId, quantity);
            return ResponseEntity.ok("Stock decreased successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/increaseStock/{productId}/{quantity}")
    public ResponseEntity<?> increaseStock(@PathVariable Long productId, @PathVariable int quantity) {
        try {
            productService.increaseStock(productId, quantity);
            return ResponseEntity.ok("Stock increased successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Delete a product
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteProduct(productId);
            return ResponseEntity.ok("Product deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
