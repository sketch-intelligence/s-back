package com.website.e_commerce.comment;

import com.website.e_commerce.exception.ResourceNotFoundException;
import com.website.e_commerce.mapper.CommentMapper;
import com.website.e_commerce.post.Post;
import com.website.e_commerce.post.PostService;
import com.website.e_commerce.user.model.entity.UserEntity;
import com.website.e_commerce.user.service.UserEntityService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements ICommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;

    public CommentService(CommentRepository commentRepository, PostService postService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
    }


    public CommentDto addComment(CommentDto request) {
        Post post = postService.getPostById(request.getPostId());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        request.setUserId(userEntity.getId());
        Comment comment = CommentMapper.M.toEntity(request);
        Comment savedComment = commentRepository.save(comment);
        return CommentMapper.M.toDto(savedComment);

    }

    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment not found"));
    }

    public void deleteCommentById(Long commentId) {
            commentRepository.findById(commentId)
                    .ifPresentOrElse(commentRepository::delete, () -> {
                        throw new ResourceNotFoundException("comment not found");
                    });
    }

    public CommentDto editComment(CommentDto request) {
        Post post = postService.getPostById(request.getPostId());
        Comment comment = getCommentById(request.getId());
        Comment patched = CommentMapper.M.partialUpdate(request,comment);
        Comment savedComment = commentRepository.save(patched);
        return CommentMapper.M.toDto(savedComment);
    }
}
