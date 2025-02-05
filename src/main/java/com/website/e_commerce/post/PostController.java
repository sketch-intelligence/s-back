package com.website.e_commerce.post;

import com.website.e_commerce.response.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    //todo reaction post.id and user.id are null --> fix em bitch
    @GetMapping("post")
    public ResponseEntity<ApiResponse> getPostById(@RequestParam Long postId){
        try{
            PostDto post = postService.getPostDtoById(postId);
            if (post != null) {
                return ResponseEntity.status(OK).body(new ApiResponse("post found",post));

            }
            else {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("post not found", null));
            }
        }catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }

    }
    @GetMapping("all")
    public ResponseEntity<ApiResponse> getAllPosts(@RequestParam int page) {
        try {
            int defaultPageSize = 10;
            Pageable pageable = PageRequest.of(page, defaultPageSize);

            Page<PostDto> postDtos = postService.getAllPosts(pageable);

            if (postDtos.isEmpty()) {
                return ResponseEntity.status(NO_CONTENT).body(new ApiResponse("No posts available", postDtos));
            }

            return ResponseEntity.status(OK).body(new ApiResponse("Fetched successfully", postDtos));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }






    @PostMapping(value = "/add")
    public ResponseEntity<ApiResponse> addPost(
            @RequestBody
            PostDto postDto) {
        try {

            PostDto createdPost = postService.addPost(postDto);
            return ResponseEntity.status(OK).body(new ApiResponse("post created", createdPost));


        } catch (Exception e) {

            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), INTERNAL_SERVER_ERROR));

        }


    }

    @DeleteMapping("post/delete")
    public ResponseEntity<ApiResponse> deletePostById(Long postId){
        postService.deletePostById(postId);
        try {

            return ResponseEntity.status(OK).body(new ApiResponse("post deleted",null));


        }
        catch (Exception e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));

        }
    }

    @PutMapping("post/update")
    public ResponseEntity<ApiResponse> updatePost(@RequestBody PostDto request){

                try {
                    PostDto post = postService.updatePost(request);
                    return ResponseEntity.status(OK).body(new ApiResponse("post created",post));

                }
                catch (Exception e){
                    return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
                }

    }

    }







