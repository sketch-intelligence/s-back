package com.website.e_commerce.post;

import com.website.e_commerce.exception.ResourceNotFoundException;
import com.website.e_commerce.mapper.PostMapper;
import com.website.e_commerce.postimage.PostImageService;
import com.website.e_commerce.user.model.entity.UserEntity;
import com.website.e_commerce.user.repository.CustomerEntityRepository;
import com.website.e_commerce.user.model.dto.UserEntityDto;
import com.website.e_commerce.postimage.PostImage;
import com.website.e_commerce.postimage.PostImageRepository;
import com.website.e_commerce.security.service.AuthenticationService;
import com.website.e_commerce.postimage.PostImageDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;
import java.sql.Blob;
import javax.sql.rowset.serial.SerialBlob;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService implements IPostService{
    private final PostRepository postRepository;
    private final PostImageService imageService;
    private final CustomerEntityRepository customerEntityRepository;

    @Autowired
    private PostImageRepository postImageRepository;
    public PostService(PostRepository postRepository,@Lazy PostImageService imageService, CustomerEntityRepository customerEntityRepository) {
        this.postRepository = postRepository;
        this.imageService = imageService;
        this.customerEntityRepository = customerEntityRepository;
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("post not found"));
    }


    @Override
    public PostDto addPost(PostDto postDto) {
        if (postDto.getOwnerId() == null) {
            throw new IllegalArgumentException("Owner ID cannot be null");
        }

        // Fetch user
        UserEntity owner = customerEntityRepository.findById(postDto.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Create new Post
        Post post = new Post();
        post.setText(postDto.getText());
        post.setTimeStamp(LocalDateTime.now());
        post.setOwner(owner);

        // Save post first
        Post savedPost = postRepository.save(post);

        // Handle image uploads
        if (postDto.getImageUrls() != null && !postDto.getImageUrls().isEmpty()) {
            List<PostImage> postImages = postDto.getImageUrls().stream()
                    .map(url -> {
                        PostImage postImage = new PostImage();
                        postImage.setDownloadUrl(url);
                        postImage.setPost(savedPost);
                        return postImage;
                    })
                    .collect(Collectors.toList());

            postImageRepository.saveAll(postImages); // Save images to DB
            savedPost.setPostImages(postImages); // Associate images with post

            // Debugging logs
            System.out.println("Saved images: " + postImages.size());
        } else {
            System.out.println("No images received.");
        }

        // Convert to DTO and return
        PostDto savedPostDto = PostMapper.M.toDto(savedPost);
        savedPostDto.setOwnerId(owner.getId());

        return savedPostDto;
    }














    @Override
    public PostDto getPostDtoById(Long id) {
         Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post not found"));

         return PostMapper.M.toDto(post);
    }

    @Override
    public Page<PostDto> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable)
                .map(post -> {
                    PostDto postDto = PostMapper.M.toDto(post);
                    if (post.getOwner() != null) {
                        postDto.setOwner(new UserEntityDto(
                                post.getOwner().getId(),
                                post.getOwner().getName(),
                                post.getOwner().getImageUrl() // Assuming the owner entity has an image URL
                        ));
                    }
                    return postDto;
                });
    }

    @Override
    public List<PostDto> getPostsByUserId(Long userId) {
        List<Post> posts = postRepository.findAllByUserId(userId);
        return posts.stream().map(PostMapper.M::toDto).toList();
    }







    @Override
    public void deletePostById(Long id) {
         postRepository.findById(id).ifPresentOrElse(postRepository::delete,() -> {
            throw new ResourceNotFoundException("post not found");
        });
    }

    @Override
    public PostDto updatePost(PostDto request) {
        // Retrieve the existing post
        Post post = getPostById(request.getId());

        // Partially update the post with new details
        Post patched = PostMapper.M.partialUpdate(request, post);

        // Handle image updates

        // Save the updated post
        Post saved = postRepository.save(patched);

        // Convert to DTO and include updated image info
        PostDto updatedPostDto = PostMapper.M.toDto(saved);

        return updatedPostDto;
    }

}
