package com.example.interior.services;

import com.example.interior.models.Portfolio;
import com.example.interior.models.User;
import com.example.interior.repositories.PortfolioRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class PortfolioService {
    private final PortfolioRepository portfolioRepository;

    public PortfolioService(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    public Portfolio createPortfolio(User designer, String skills, int experience) {
        if (portfolioRepository.findByDesignerId(designer.getId()).isPresent()) {
            throw new IllegalStateException("Portfolio already exists for this designer.");
        }
        Portfolio portfolio = new Portfolio(designer, skills, experience);
        return portfolioRepository.save(portfolio);
    }

    public Optional<Portfolio> getPortfolioByDesignerId(Long designerId) {
        return portfolioRepository.findByDesignerId(designerId);
    }
    
    public List<Portfolio> getAllPortfolios() {
        return portfolioRepository.findAll(); // Fetch all portfolios from the database
    }
}
