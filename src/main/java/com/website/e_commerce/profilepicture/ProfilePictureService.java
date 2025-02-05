package com.website.e_commerce.profilepicture;

import com.website.e_commerce.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;

@Service
public class ProfilePictureService {
    private final ProfilePictureRepository profilePictureRepository;

    public ProfilePictureService(ProfilePictureRepository profilePictureRepository) {
        this.profilePictureRepository = profilePictureRepository;
    }

    public ProfileImage getProfilePictureById(Long id) {
        return profilePictureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No profile picture found with id: " + id));
    }

    public void deleteProfilePictureById(Long id) {
        profilePictureRepository.findById(id).ifPresentOrElse(profilePictureRepository::delete, () -> {
            throw new ResourceNotFoundException("No profile picture found with id: " + id);
        });
    }

    public void updateProfilePicture(MultipartFile file, Long pictureId) {
        ProfileImage profileImage = getProfilePictureById(pictureId);
        try {
            profileImage.setFileName(file.getOriginalFilename());
            profileImage.setFileType(file.getContentType());
            profileImage.setImage(new SerialBlob(file.getBytes()));
            profilePictureRepository.save(profileImage);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ProfileImage saveProfilePicture(MultipartFile file) {
        try {
            ProfileImage profileImage = new ProfileImage();
            profileImage.setFileName(file.getOriginalFilename());
            profileImage.setFileType(file.getContentType());
            profileImage.setImage(new SerialBlob(file.getBytes()));
            profilePictureRepository.save(profileImage);

            // Construct the download URL after saving
            String downloadUrl = "/profile-pictures/download/" + profileImage.getId();
            profileImage.setDownloadUrl(downloadUrl);
            return profilePictureRepository.save(profileImage);
        } catch (IOException | SQLException e) {
            throw new RuntimeException("Error saving profile picture: " + e.getMessage(), e);
        }
    }
}

