package com.website.e_commerce.post;

import com.website.e_commerce.exception.ResourceNotFoundException;
import com.website.e_commerce.mapper.PostMapper;
import com.website.e_commerce.postimage.PostImageService;
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

    public PostService(PostRepository postRepository,@Lazy PostImageService imageService) {
        this.postRepository = postRepository;
        this.imageService = imageService;
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("post not found"));
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
    public PostDto addPost(PostDto postDto) {
        // Create the post entity
        Post post = new Post();
        post.setText(postDto.getText());
        post.setTimeStamp(LocalDateTime.now());

        // Save post to the repository
        Post savedPost = postRepository.save(post);

        // Map saved data back to PostDto

        return PostMapper.M.toDto(savedPost);
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
