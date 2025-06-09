package com.example.interior.models;

import com.example.interior.enums.ProjectStatus;
import jakarta.persistence.*;

@Entity
public class ApprovedProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "project_request_id", nullable = false, unique = true)
    private ProjectRequest projectRequest;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status = ProjectStatus.INITIAL_DISCUSSION; // Default

    public ApprovedProject() {}

    public ApprovedProject(ProjectRequest projectRequest) {
        this.projectRequest = projectRequest;
        this.status = ProjectStatus.INITIAL_DISCUSSION;
    }

    public Long getId() { return id; }
    public ProjectRequest getProjectRequest() { return projectRequest; }
    public void setProjectRequest(ProjectRequest projectRequest) { this.projectRequest = projectRequest; }
    public ProjectStatus getStatus() { return status; }
    public void setStatus(ProjectStatus status) { this.status = status; }
}
