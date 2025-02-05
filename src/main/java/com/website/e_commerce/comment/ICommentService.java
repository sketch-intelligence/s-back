package com.website.e_commerce.comment;

public interface ICommentService {

    CommentDto addComment(CommentDto request);
    Comment getCommentById(Long commentId);

    void deleteCommentById(Long commentId);

    CommentDto editComment(CommentDto request);

}
