package com.example.interior.services;

import com.example.interior.enums.Role;
import com.example.interior.models.Shop;
import com.example.interior.models.User;
import com.example.interior.repositories.ShopRepository;
import com.example.interior.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private UserRepository userRepository;

    // Create Shop for Vendor
    public Shop createShop(Long vendorId, Shop shop) throws Exception {
        Optional<User> vendor = userRepository.findById(vendorId);

        if (vendor.isEmpty() || vendor.get().getRole() != Role.VENDOR) {
            throw new Exception("User is not a vendor or does not exist");
        }

        // Check if vendor already has a shop
        if (shopRepository.findByVendorId(vendorId).isPresent()) {
            throw new Exception("Vendor already has a shop");
        }

        shop.setVendor(vendor.get());
        return shopRepository.save(shop);
    }

    // Get Vendor's Shop
    public Shop getVendorShop(Long vendorId) throws Exception {
        return shopRepository.findByVendorId(vendorId)
                .orElseThrow(() -> new Exception("Shop not found for this vendor"));
    }
    
    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }
}
