package com.website.e_commerce.portfolioproject;

import com.website.e_commerce.exception.ResourceNotFoundException;
import com.website.e_commerce.mapper.PortfolioProjectMapper;
import com.website.e_commerce.mapper.UserEntityMapper;
import com.website.e_commerce.response.ApiResponse;
import com.website.e_commerce.user.model.dto.UserEntityDto;
import com.website.e_commerce.user.model.entity.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

@RestController
@RequestMapping("portfolio-projects")
public class PortfolioProjectController {

    private final PortfolioProjectService portfolioProjectService;

    public PortfolioProjectController(PortfolioProjectService portfolioProjectService) {
        this.portfolioProjectService = portfolioProjectService;
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ApiResponse> getProjectById(@PathVariable Long projectId) {
        try {
            PortfolioProject project = portfolioProjectService.getProjectById(projectId);
            return ResponseEntity.ok(new ApiResponse("Project fetched successfully", project));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllArchitectProjects() {
        List<PortfolioProjectDto> projects = portfolioProjectService.getAllArchitectProjects();
        return ResponseEntity.ok(new ApiResponse("Projects fetched successfully", projects));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createProject(@RequestBody PortfolioProjectDto request) {
        try {
            PortfolioProjectDto createdProject = portfolioProjectService.createProject(request);
            return ResponseEntity.ok(new ApiResponse("Project created successfully", createdProject));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse("Failed to create project", e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateProject(@RequestBody PortfolioProjectDto request) {
        try {
            PortfolioProjectDto updatedProject = portfolioProjectService.updateProject(request);
            return ResponseEntity.ok(new ApiResponse("Project updated successfully", updatedProject));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse("Failed to update project", e.getMessage()));
        }
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<ApiResponse> deleteProject(@PathVariable Long projectId) {
        try {
            portfolioProjectService.deletePortfolioProjectById(projectId);
            return ResponseEntity.ok(new ApiResponse("Project deleted successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse("Failed to delete project", e.getMessage()));
        }
    }
}