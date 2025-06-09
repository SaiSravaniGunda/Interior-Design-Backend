package com.example.interior.controllers;

import com.example.interior.models.ProjectRequest;
import com.example.interior.models.User;
import com.example.interior.repositories.ProjectRequestRepository;
import com.example.interior.repositories.UserRepository;
import com.example.interior.services.ProjectRequestService;
import com.example.interior.dto.ProjectRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/project-requests")
public class ProjectRequestController {

    @Autowired
    private ProjectRequestService projectRequestService;
    
    @Autowired
    private ProjectRequestRepository projectRequestRepository;

    @Autowired
    private UserRepository userRepository;  // Inject UserRepository

    // Create a new project request
    @PostMapping("/create")
    public ResponseEntity<ProjectRequest> createProjectRequest(@RequestBody ProjectRequestDTO projectRequestDTO) {
        Optional<User> userOptional = userRepository.findById(projectRequestDTO.getUserId());
        Optional<User> designerOptional = userRepository.findById(projectRequestDTO.getDesignerId());

        if (!userOptional.isPresent() || !designerOptional.isPresent()) {
            return ResponseEntity.badRequest().build(); // Return bad request if user or designer not found
        }

        User user = userOptional.get();
        User designer = designerOptional.get();

        // Create the ProjectRequest and set user and designer
        ProjectRequest projectRequest = new ProjectRequest();
        projectRequest.setUser(user);
        projectRequest.setDesigner(designer);
        projectRequest.setName(projectRequestDTO.getName());
        projectRequest.setSpecialization(projectRequestDTO.getSpecialization());
        projectRequest.setBudget(projectRequestDTO.getBudget());
        projectRequest.setTheme(projectRequestDTO.getTheme());

        // Save the project request
        projectRequestRepository.save(projectRequest);

        return ResponseEntity.ok(projectRequest);
    }





    // Approve a project request
    @PutMapping("/approve/{projectId}")
    public ResponseEntity<ProjectRequest> approveProjectRequest(@PathVariable Long projectId) {
        ProjectRequest approvedRequest = projectRequestService.approveProjectRequest(projectId);
        return new ResponseEntity<>(approvedRequest, HttpStatus.OK);
    }

    // Reject a project request
    @PutMapping("/reject/{projectId}")
    public ResponseEntity<ProjectRequest> rejectProjectRequest(@PathVariable Long projectId) {
        ProjectRequest rejectedRequest = projectRequestService.rejectProjectRequest(projectId);
        return new ResponseEntity<>(rejectedRequest, HttpStatus.OK);
    }

    // Get all project requests for a designer
//    @GetMapping("/")
//    public ResponseEntity<List<ProjectRequest>> getProjectRequestsForDesigner(@RequestParam Long designerId) {
//        // Fetch project requests for a specific designer
//        List<ProjectRequest> projectRequests = projectRequestRepository.findByDesignerId(designerId);
//        return ResponseEntity.ok(projectRequests);
//    }
    @GetMapping
    public ResponseEntity<?> getPendingProjectRequests(@RequestParam Long designerId) {
        if (designerId == null) {
            return ResponseEntity.badRequest().body("Designer ID is required.");
        }

        List<ProjectRequest> projectRequests = projectRequestService.getProjectRequestsForDesigner(designerId);

//        if (projectRequests.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No pending project requests found for this designer.");
//        }

        return ResponseEntity.ok(projectRequests);
    }
    
    @GetMapping("/my-designers/{clientId}")
    public ResponseEntity<List<ProjectRequest>> getMyDesigners(@PathVariable Long clientId) {
        List<ProjectRequest> approvedProjects = projectRequestRepository.findByUserIdAndStatus(clientId, "APPROVED");
        return ResponseEntity.ok(approvedProjects);
    }
    
    @GetMapping("/my-clients/{designerId}")
    public ResponseEntity<List<ProjectRequest>> getMyClients(@PathVariable Long designerId) {
        List<ProjectRequest> approvedProjects = projectRequestRepository.findByDesignerIdAndStatus(designerId, "APPROVED");
        return ResponseEntity.ok(approvedProjects);
    }
    
    @GetMapping("/count")
    public ResponseEntity<Long> getProjectRequestCount(@RequestParam Long designerId) {
        if (designerId == null) {
            return ResponseEntity.badRequest().build();
        }

        Long requestCount = projectRequestRepository.countByDesignerId(designerId);
        return ResponseEntity.ok(requestCount);
    }
    
    @GetMapping("/rejected/client/{clientId}")
    public ResponseEntity<List<ProjectRequest>> getRejectedProjectRequestsForClient(@PathVariable Long clientId) {
        List<ProjectRequest> rejectedProjects = projectRequestRepository.findByUserIdAndStatus(clientId, "Rejected");
        return ResponseEntity.ok(rejectedProjects);
    }



}
