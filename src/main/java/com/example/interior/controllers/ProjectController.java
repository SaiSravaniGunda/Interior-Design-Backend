package com.example.interior.controllers;

import com.example.interior.models.Project;
import com.example.interior.services.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProject(@RequestParam Long portfolioId, @RequestBody Project project) {
        Project savedProject = projectService.addProject(portfolioId, project.getName(), project.getSpecialization(), project.getImageUrl());
        return ResponseEntity.ok(savedProject);
    }

    @GetMapping("/{portfolioId}")
    public ResponseEntity<List<Project>> getProjects(@PathVariable Long portfolioId) {
        return ResponseEntity.ok(projectService.getProjectsByPortfolio(portfolioId));
    }
}
