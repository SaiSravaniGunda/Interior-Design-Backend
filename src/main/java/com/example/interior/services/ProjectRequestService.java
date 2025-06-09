package com.example.interior.services;

import com.example.interior.models.ProjectRequest;
import com.example.interior.repositories.ApprovedProjectRepository;
import com.example.interior.repositories.ProjectRequestRepository;
import com.example.interior.models.ApprovedProject;

import jakarta.transaction.Transactional;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectRequestService {

    @Autowired
    private ProjectRequestRepository projectRequestRepository;
    
    @Autowired
    private ApprovedProjectRepository approvedProjectRepository;


    // Create a new project request
    public ProjectRequest createProjectRequest(ProjectRequest projectRequest) {
        return projectRequestRepository.save(projectRequest);
    }

    // Approve a project request
//    public ProjectRequest approveProjectRequest(Long projectId) {
//        ProjectRequest request = projectRequestRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project request not found"));
//        request.setStatus("Approved");
//        return projectRequestRepository.save(request);
//    }
    
    public ProjectRequest approveProjectRequest(Long projectId) {
        ProjectRequest projectRequest = projectRequestRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("ProjectRequest not found"));

        projectRequest.setStatus("APPROVED");
        projectRequestRepository.save(projectRequest);

        // Create an ApprovedProject entry
        ApprovedProject approvedProject = new ApprovedProject(projectRequest);
        approvedProjectRepository.save(approvedProject);

        return projectRequest;
    }


    // Reject a project request
    public ProjectRequest rejectProjectRequest(Long projectId) {
        ProjectRequest request = projectRequestRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project request not found"));
        request.setStatus("Rejected");
        return projectRequestRepository.save(request);
    }
    
    @Transactional
    public List<ProjectRequest> getProjectRequestsForDesigner(Long designerId) {
        return projectRequestRepository.findByDesignerIdAndStatus(designerId, "Pending");
    }

    // You can add additional service methods as needed
}
