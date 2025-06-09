package com.example.interior.controllers;

import com.example.interior.models.Portfolio;
import com.example.interior.models.User;
import com.example.interior.services.PortfolioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/portfolios")
public class PortfolioController {
    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPortfolio(@RequestBody Portfolio portfolio) {
        if (portfolio.getId() == null) {
            return ResponseEntity.status(400).body("User ID is required");
        }

        User designer = new User();
        designer.setId(portfolio.getId()); // Use userId directly from request

        Portfolio savedPortfolio = portfolioService.createPortfolio(designer, portfolio.getSkills(), portfolio.getExperience());
        return ResponseEntity.ok(savedPortfolio);
    }

    @GetMapping("/{designerId}")
    public ResponseEntity<?> getPortfolio(@PathVariable Long designerId) {
        Optional<Portfolio> portfolio = portfolioService.getPortfolioByDesignerId(designerId);
        return portfolio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/isPortfolio/{designerId}")
    public ResponseEntity<Boolean> isPortfolio(@PathVariable Long designerId) {
    	
        boolean exists = portfolioService.getPortfolioByDesignerId(designerId).isPresent();
        return ResponseEntity.ok(exists);
    }
    
    @GetMapping("/")
    public ResponseEntity<List<Portfolio>> getAllPortfolios() {
        List<Portfolio> portfolios = portfolioService.getAllPortfolios(); 
        return ResponseEntity.ok(portfolios);
    }
}
