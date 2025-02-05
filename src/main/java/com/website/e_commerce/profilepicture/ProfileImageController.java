package com.website.e_commerce.profilepicture;

import com.website.e_commerce.exception.ResourceNotFoundException;
import com.website.e_commerce.response.ApiResponse;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("profile-pictures")
public class ProfileImageController {
    private final ProfilePictureService profilePictureService;

    public ProfileImageController(ProfilePictureService profilePictureService) {
        this.profilePictureService = profilePictureService;
    }

    @GetMapping("/download/{pictureId}")
    public ResponseEntity<Resource> downloadProfilePicture(@PathVariable Long pictureId) throws SQLException {
        ProfileImage profileImage = profilePictureService.getProfilePictureById(pictureId);
        ByteArrayResource resource = new ByteArrayResource(profileImage.getImage().getBytes(1, (int) profileImage.getImage().length()));
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(profileImage.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + profileImage.getFileName() + "\"")
                .body(resource);
    }

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> uploadProfilePicture(@RequestParam MultipartFile file) {
        try {
            ProfileImage profileImage = profilePictureService.saveProfilePicture(file);
            return ResponseEntity.ok(new ApiResponse("Upload success!", profileImage));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Upload failed!", e.getMessage()));
        }
    }

    @PutMapping("/{pictureId}/update")
    public ResponseEntity<ApiResponse> updateProfilePicture(@PathVariable Long pictureId, @RequestBody MultipartFile file) {
        try {
            profilePictureService.updateProfilePicture(file, pictureId);
            return ResponseEntity.ok(new ApiResponse("Update success!", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Update failed!", e.getMessage()));
        }
    }

    @DeleteMapping("/{pictureId}/delete")
    public ResponseEntity<ApiResponse> deleteProfilePicture(@PathVariable Long pictureId) {
        try {
            profilePictureService.deleteProfilePictureById(pictureId);
            return ResponseEntity.ok(new ApiResponse("Delete success!", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Delete failed!", e.getMessage()));
        }
    }
}

