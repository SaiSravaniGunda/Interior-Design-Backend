package com.example.interior.controllers;

import com.example.interior.enums.ProjectStatus;
import com.example.interior.models.ApprovedProject;
import com.example.interior.repositories.ApprovedProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/approved-projects")
public class ApprovedProjectController {

    @Autowired
    private ApprovedProjectRepository approvedProjectRepository;

    // Get all approved projects for a client
    @GetMapping("/client/{userId}")
    public ResponseEntity<List<ApprovedProject>> getClientProjects(@PathVariable Long userId) {
        List<ApprovedProject> projects = approvedProjectRepository.findByProjectRequest_User_Id(userId);
        return ResponseEntity.ok(projects);
    }

    // Update project status (designer only)
    @PutMapping("/{projectId}/status")
    public ResponseEntity<?> updateProjectStatus(@PathVariable Long projectId, @RequestParam ProjectStatus status) {
        Optional<ApprovedProject> optionalProject = approvedProjectRepository.findById(projectId);

        if (optionalProject.isPresent()) {
            ApprovedProject project = optionalProject.get();
            project.setStatus(status);
            approvedProjectRepository.save(project);
            return ResponseEntity.ok("Project status updated to: " + status);
        } else {
            return ResponseEntity.badRequest().body("Project not found");
        }
    }
    
 // ✅ Get all approved projects for a designer
    @GetMapping("/designer/{designerId}")
    public ResponseEntity<List<ApprovedProject>> getDesignerProjects(@PathVariable Long designerId) {
        List<ApprovedProject> projects = approvedProjectRepository.findByProjectRequest_Designer_Id(designerId);
        return ResponseEntity.ok(projects);
    }
    
    @GetMapping("/designer/{designerId}/count")
    public ResponseEntity<Long> getApprovedProjectCount(@PathVariable Long designerId) {
        long count = approvedProjectRepository.countByProjectRequest_Designer_Id(designerId);
        return ResponseEntity.ok(count);
    }
    
    @GetMapping("/designer/{designerId}/completed-count")
    public ResponseEntity<Long> getCompletedProjectCount(@PathVariable Long designerId) {
        long count = approvedProjectRepository.countByProjectRequest_Designer_IdAndStatus(designerId, ProjectStatus.COMPLETION);
        return ResponseEntity.ok(count);
    }
    
 // ✅ Get all completed projects for a designer
    @GetMapping("/designer/{designerId}/completed")
    public ResponseEntity<List<ApprovedProject>> getCompletedProjects(@PathVariable Long designerId) {
        List<ApprovedProject> completedProjects = approvedProjectRepository
            .findByProjectRequest_Designer_IdAndStatus(designerId, ProjectStatus.COMPLETION);
        return ResponseEntity.ok(completedProjects);
    }



}
