package com.example.interior.repositories;

import com.example.interior.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    Optional<Shop> findByVendorId(Long vendorId);
}