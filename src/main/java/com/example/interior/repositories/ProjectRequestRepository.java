package com.example.interior.repositories;

import com.example.interior.models.ProjectRequest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRequestRepository extends JpaRepository<ProjectRequest, Long> {
    // You can add custom queries here if needed, e.g. to find all project requests for a designer
	List<ProjectRequest> findByDesignerId(Long designerId);
	 List<ProjectRequest> findByDesignerIdAndStatus(Long designerId, String status);
	 
	 List<ProjectRequest> findByUserIdAndStatus(Long userId, String status);
	 
	 long countByDesignerId(Long designerId);

	 
}
