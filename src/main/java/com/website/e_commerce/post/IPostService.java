package com.website.e_commerce.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPostService {

    Post getPostById(Long id);
    PostDto getPostDtoById(Long id);
    Page<PostDto> getAllPosts(Pageable pageable);
    PostDto addPost(PostDto post);
    void deletePostById(Long id);
    PostDto updatePost(PostDto request);
}
