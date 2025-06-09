package com.example.interior.repositories;

import com.example.interior.enums.ProjectStatus;
import com.example.interior.models.ApprovedProject;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApprovedProjectRepository extends JpaRepository<ApprovedProject, Long> {
    List<ApprovedProject> findByProjectRequest_User_Id(Long userId);
    
    List<ApprovedProject> findByProjectRequest_Designer_Id(Long designerId);
    
    long countByProjectRequest_Designer_Id(Long designerId);

    long countByProjectRequest_Designer_IdAndStatus(Long designerId, ProjectStatus status);

    List<ApprovedProject> findByProjectRequest_Designer_IdAndStatus(Long designerId, ProjectStatus status);


}
