package com.website.e_commerce.userproject;

import com.website.e_commerce.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-projects")
public class UserProjectController {

    @Autowired
    private IUserProjectService userProjectService;

    @PostMapping
    public ResponseEntity<ApiResponse> createUserProject(@RequestBody UserProjectDto userProjectDto) {
        try {
            UserProjectDto createdProject = userProjectService.createUserProject(userProjectDto);
                return ResponseEntity.ok(new ApiResponse("Project created successfully", createdProject));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllUserProjects() {
        try {
            List<UserProjectDto> userProjects = userProjectService.getAllUserProjects();
            if (userProjects != null && !userProjects.isEmpty()) {
                return ResponseEntity.ok(new ApiResponse("Projects retrieved successfully", userProjects));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ApiResponse("No projects found", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("An unexpected error occurred", null));
        }
    }

    @GetMapping("/get")
    public ResponseEntity<ApiResponse> getUserProject(@RequestParam Long id) {
        try {
            UserProjectDto userProject = userProjectService.getUserProjectById(id);
            if (userProject != null) {
                return ResponseEntity.ok(new ApiResponse("Project retrieved successfully", userProject));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ApiResponse("Project not found", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("An unexpected error occurred", null));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateUserProject(@RequestParam Long id, @RequestBody UserProjectDto userProjectDto) {
        try {
            UserProjectDto updatedProject = userProjectService.updateUserProject(id, userProjectDto);
            if (updatedProject != null) {
                return ResponseEntity.ok(new ApiResponse("Project updated successfully", updatedProject));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ApiResponse("Failed to update project", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("An unexpected error occurred", null));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> deleteUserProject(@RequestParam Long id) {
        try {
            userProjectService.deleteUserProject(id);
            return ResponseEntity.ok(new ApiResponse("Project deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("An unexpected error occurred", null));
        }
    }
}
