package com.website.e_commerce.postimage;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPostImageService
{
    PostImage getImageById(Long id);
    void deleteImageById(Long id);
    List<PostImageDto> saveImages(List<MultipartFile> files, Long postId);
    void updateImage(MultipartFile file,  Long imageId);
}
