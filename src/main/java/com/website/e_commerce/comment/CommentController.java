package com.website.e_commerce.comment;

import com.website.e_commerce.post.PostRepository;
import com.website.e_commerce.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("comments")
public class CommentController {
    private final CommentService commentService;
    private final PostRepository postRepository;

    public CommentController(CommentService commentService,
                             PostRepository postRepository) {
        this.commentService = commentService;
        this.postRepository = postRepository;
    }
    @PostMapping("comment/add")
    public ResponseEntity<ApiResponse> addComment(@RequestBody CommentDto request) {
        try {
            CommentDto commentDto = commentService.addComment(request);
            return ResponseEntity.status(OK).body(new ApiResponse("comment added",commentDto));
        }catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));

        }

    }
    @GetMapping("comment")
    public ResponseEntity<ApiResponse> getCommentById(@RequestParam Long commentId){
      return null;


    }
    @DeleteMapping("comment/delete")
    public ResponseEntity<ApiResponse> deleteCommentById(@RequestParam Long commentId){
        try{
            commentService.deleteCommentById(commentId);
            return ResponseEntity.status(OK).body(new ApiResponse("comment deleted",null));
        }
        catch (Exception e){

            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }

    }
    @PutMapping("comment/edit")
    public ResponseEntity<ApiResponse> editComment(@RequestBody CommentDto request){
        try{
            CommentDto commentDto = commentService.editComment(request);
            return ResponseEntity.status(OK).body(new ApiResponse("edited",commentDto));

        }
        catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));


        }

    }




}
