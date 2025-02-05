package com.website.e_commerce.reactions;

import com.website.e_commerce.mapper.ReactionMapper;
import com.website.e_commerce.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("reactions")
public class ReactionController {
    private final ReactionService reactionService;

    public ReactionController(ReactionService reactionService) {
        this.reactionService = reactionService;
    }

    @PostMapping("add")
    public ResponseEntity<ApiResponse> createReaction(@RequestBody ReactionDto reactionRequest) {
       try{
           ReactionDto reaction = reactionService.createReaction(reactionRequest);
           return ResponseEntity.status(OK).body(new ApiResponse("Reaction Added",reaction));
       }
       catch (Exception e)
       {
           return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
       }
    }
    @GetMapping("reaction")
    public ResponseEntity<ApiResponse> getReactionById(@RequestParam Long id) {
        try {
            Reaction reaction = reactionService.getReactionById(id);
            return ResponseEntity.ok(new ApiResponse("Reaction retrieved successfully", reaction));
        } catch (RuntimeException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("post/reaction")
    public ResponseEntity<ApiResponse> getReactionsByPostId(@RequestParam Long postId) {
        try {
            return ResponseEntity.ok(new ApiResponse("Reactions retrieved successfully", reactionService.getReactionsByPost(postId)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }


    @PutMapping("reaction/update")
    public ResponseEntity<ApiResponse> updateReaction(@PathVariable Long id, @RequestBody ReactionDto reaction) {
        try {
            Reaction reaction1 = ReactionMapper.M.toEntity(reaction);
            Reaction updatedReaction = reactionService.updateReaction(id, reaction1);
            return ResponseEntity.ok(new ApiResponse("Reaction updated successfully", updatedReaction));
        } catch (RuntimeException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteReaction(@PathVariable Long id) {
        try {
            reactionService.deleteReaction(id);
            return ResponseEntity.ok(new ApiResponse("Reaction deleted successfully", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
}