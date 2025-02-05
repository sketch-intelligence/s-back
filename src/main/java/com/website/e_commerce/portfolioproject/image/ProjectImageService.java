package com.website.e_commerce.portfolioproject.image;

import com.website.e_commerce.exception.ResourceNotFoundException;
import com.website.e_commerce.portfolioproject.PortfolioProject;
import com.website.e_commerce.portfolioproject.PortfolioProjectRepository;
import com.website.e_commerce.portfolioproject.PortfolioProjectService;
import com.website.e_commerce.post.Post;
import com.website.e_commerce.postimage.PostImage;
import com.website.e_commerce.postimage.PostImageDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectImageService {
    private final ProjectImageRepository projectImageRepository;
    private final PortfolioProjectService portfolioProjectService;
    private final PortfolioProjectRepository portfolioProjectRepository;

    public ProjectImageService(ProjectImageRepository projectImageRepository, PortfolioProjectService portfolioProjectService,
                               PortfolioProjectRepository portfolioProjectRepository) {
        this.projectImageRepository = projectImageRepository;
        this.portfolioProjectService = portfolioProjectService;
        this.portfolioProjectRepository = portfolioProjectRepository;
    }

    public ProjectImage getImageById(Long imageId) {
        return projectImageRepository.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException("image not found"));

    }


    public ProjectImageDto  saveImage(List<MultipartFile> files, Long projectId){

        return null ;

    }

    public void updateImage(MultipartFile file, Long imageId) {
        ProjectImage projctImage = getImageById(imageId);
        try {
            projctImage.setFileName(file.getOriginalFilename());
            projctImage.setFileType(file.getContentType());
            projctImage.setImage(new SerialBlob(file.getBytes()));
            projectImageRepository.save(projctImage);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
    public void deleteImageById(Long imageId){
        projectImageRepository.findById(imageId).ifPresentOrElse(projectImageRepository::delete,() -> {
            throw new ResourceNotFoundException("image not found");
        });

    }
    public List<ProjectImageDto> saveImages(List<MultipartFile> files, Long projectId) {
        // جلب المشروع بواسطة المعرف
        PortfolioProject project = portfolioProjectService.getProjectById(projectId);

        List<ProjectImageDto> savedProjectImageDto = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                // إنشاء كائن ProjectImage جديد
                ProjectImage image = new ProjectImage();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setPortfolioProject(project);

                // حفظ الصورة في المستودع
                ProjectImage savedPostImage = projectImageRepository.save(image);

                // إنشاء رابط التنزيل بعد الحفظ
                String downloadUrl = "/images/image/download/" + savedPostImage.getId();
                savedPostImage.setDownloadUrl(downloadUrl);

                // تحديث الصورة مع رابط التنزيل
                projectImageRepository.save(savedPostImage);

                // إضافة الصورة إلى المشروع
                project.getProjectImage().add(savedPostImage);
                portfolioProjectRepository.save(project);

                // إنشاء وإضافة DTO للنتيجة
                ProjectImageDto imageDto = new ProjectImageDto();
                imageDto.setId(savedPostImage.getId());
                imageDto.setFileName(savedPostImage.getFileName());
                imageDto.setDownloadUrl(savedPostImage.getDownloadUrl());
                savedProjectImageDto.add(imageDto);

            } catch (IOException | SQLException e) {
                throw new RuntimeException("Error saving image: " + e.getMessage(), e);
            }
        }

        return savedProjectImageDto;
    }




}
