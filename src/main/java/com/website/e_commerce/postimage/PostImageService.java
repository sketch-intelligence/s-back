package com.website.e_commerce.postimage;

import com.website.e_commerce.exception.ResourceNotFoundException;
import com.website.e_commerce.post.Post;
import com.website.e_commerce.post.PostRepository;
import com.website.e_commerce.post.PostService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service

public class PostImageService implements IPostImageService {
    private final PostImageRepository postImageRepository;
    private final PostRepository postRepository;
    private final PostService postService;

    public PostImageService(PostImageRepository postImageRepository, PostRepository postRepository, PostService postService) {
        this.postImageRepository = postImageRepository;
        this.postRepository = postRepository;
        this.postService = postService;
    }

    @Override
    public PostImage getImageById(Long id) {
        return postImageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No image found with id: " + id));
    }

    @Override
    public void deleteImageById(Long id) {
        postImageRepository.findById(id).ifPresentOrElse(postImageRepository::delete, () -> {
            throw new ResourceNotFoundException("No image found with id: " + id);
        });

    }



    @Override
    public void updateImage(MultipartFile file, Long imageId) {
        PostImage postImage = getImageById(imageId);
        try {
            postImage.setFileName(file.getOriginalFilename());
            postImage.setFileType(file.getContentType());
            postImage.setImage(new SerialBlob(file.getBytes()));
            postImageRepository.save(postImage);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
    @Override
    public List<PostImageDto> saveImages(List<MultipartFile> files, Long postId) {
        Post post = postService.getPostById(postId);

        List<PostImageDto> savedPostImageDto = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                PostImage postImage = new PostImage();
                postImage.setFileName(file.getOriginalFilename());
                postImage.setFileType(file.getContentType());
                postImage.setImage(new SerialBlob(file.getBytes()));
                postImage.setPost(post);

                // Save the PostImage first
                PostImage savedPostImage = postImageRepository.save(postImage);

                // Construct the download URL after saving
                String buildDownloadUrl = "/images/image/download/";
                String downloadUrl = buildDownloadUrl + savedPostImage.getId();
                savedPostImage.setDownloadUrl(downloadUrl);

                // Save the PostImage again to update the download URL
                postImageRepository.save(savedPostImage);

                // Add the image to the post (important step)
                post.getPostImages().add(savedPostImage);  // Ensure `images` field is populated
                postRepository.save(post); // Save the post again to persist the image relation

                // Prepare DTO to return
                PostImageDto postImageDto = new PostImageDto();
                postImageDto.setId(savedPostImage.getId());
                postImageDto.setFileName(savedPostImage.getFileName());
                postImageDto.setDownloadUrl(savedPostImage.getDownloadUrl());
                savedPostImageDto.add(postImageDto);

            } catch (IOException | SQLException e) {
                throw new RuntimeException("Error saving image: " + e.getMessage(), e);
            }
        }
        return savedPostImageDto;
    }


}
