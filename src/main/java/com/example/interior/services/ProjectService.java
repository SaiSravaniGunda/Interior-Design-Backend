package com.example.interior.services;

import com.example.interior.models.Project;
import com.example.interior.models.Portfolio;
import com.example.interior.repositories.ProjectRepository;
import com.example.interior.repositories.PortfolioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final PortfolioRepository portfolioRepository;

    public ProjectService(ProjectRepository projectRepository, PortfolioRepository portfolioRepository) {
        this.projectRepository = projectRepository;
        this.portfolioRepository = portfolioRepository;
    }

    public Project addProject(Long portfolioId, String name, String specialization, String imageUrl) {
        Portfolio portfolio = portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new IllegalStateException("Portfolio not found."));
        
        Project project = new Project(name, specialization, imageUrl, portfolio);
        return projectRepository.save(project);
    }

    public List<Project> getProjectsByPortfolio(Long portfolioId) {
        return projectRepository.findByPortfolioId(portfolioId);
    }
}
