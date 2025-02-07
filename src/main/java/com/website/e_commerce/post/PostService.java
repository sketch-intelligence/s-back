package com.website.e_commerce.post;

import com.website.e_commerce.exception.ResourceNotFoundException;
import com.website.e_commerce.mapper.PostMapper;
import com.website.e_commerce.postimage.PostImageService;
import com.website.e_commerce.user.model.entity.UserEntity;
import com.website.e_commerce.user.repository.CustomerEntityRepository;
import com.website.e_commerce.security.service.AuthenticationService;
import com.website.e_commerce.postimage.PostImageDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService implements IPostService{
    private final PostRepository postRepository;
    private final PostImageService imageService;
    private final CustomerEntityRepository customerEntityRepository;
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
        // Validate that ownerId is provided
        if (postDto.getOwnerId() == null) {
            throw new IllegalArgumentException("Owner ID cannot be null");
        }

        // Fetch the user from the database using the instance of customerEntityRepository
        UserEntity owner = customerEntityRepository.findById(postDto.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Create the post entity
        Post post = new Post();
        post.setText(postDto.getText());
        post.setTimeStamp(LocalDateTime.now());
        post.setOwner(owner); // Assign the owner

        // Save post to the repository
        Post savedPost = postRepository.save(post);

        // Convert saved post to DTO and return
        PostDto savedPostDto = PostMapper.M.toDto(savedPost);
        savedPostDto.setOwnerId(owner.getId()); // Ensure ownerId is returned

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
                .map(post -> PostMapper.M.toDto(post));
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
