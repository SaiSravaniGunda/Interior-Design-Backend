package com.example.interior.repositories;

import com.example.interior.models.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    Optional<Portfolio> findByDesignerId(Long designerId);
    
    List<Portfolio> findAll();
}
