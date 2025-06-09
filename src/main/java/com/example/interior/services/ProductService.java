package com.example.interior.services;

import com.example.interior.models.Product;
import com.example.interior.models.Shop;
import com.example.interior.repositories.ProductRepository;
import com.example.interior.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShopRepository shopRepository;

    // Add a product to a shop
    public Product addProduct(Long shopId, Product product) throws Exception {
        Optional<Shop> shop = shopRepository.findById(shopId);

        if (shop.isEmpty()) {
            throw new Exception("Shop not found");
        }

        if (product.getQuantity() == null || product.getQuantity() < 0) {
            throw new Exception("Quantity must be at least 0");
        }

        product.setShop(shop.get());
        return productRepository.save(product);
    }

    // Get all products of a shop
    public List<Product> getProductsByShop(Long shopId) {
        return productRepository.findByShopId(shopId);
    }

    // Get a single product by ID
    public Product getProductById(Long productId) throws Exception {
        return productRepository.findById(productId)
                .orElseThrow(() -> new Exception("Product not found"));
    }

    // Update a product
    public Product updateProduct(Long productId, Product updatedProduct) throws Exception {
        Product existingProduct = getProductById(productId);

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setImageUrl(updatedProduct.getImageUrl());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setQuantity(updatedProduct.getQuantity()); // ✅ Update quantity

        return productRepository.save(existingProduct);
    }

    // Decrease stock when a product is purchased
    public void decreaseStock(Long productId, int quantity) throws Exception {
        Product product = getProductById(productId);

        if (product.getQuantity() < quantity) {
            throw new Exception("Not enough stock available");
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
    }

    // Increase stock (if vendor restocks)
    public void increaseStock(Long productId, int quantity) throws Exception {
        Product product = getProductById(productId);
        product.setQuantity(product.getQuantity() + quantity);
        productRepository.save(product);
    }

    // Delete a product
    public void deleteProduct(Long productId) throws Exception {
        Product product = getProductById(productId);
        productRepository.delete(product);
    }
    
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
