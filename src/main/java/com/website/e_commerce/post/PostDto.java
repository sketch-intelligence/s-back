package com.website.e_commerce.post;

import com.website.e_commerce.comment.CommentDto;
import com.website.e_commerce.postimage.PostImageDto;
import com.website.e_commerce.reactions.ReactionDto;
import com.website.e_commerce.user.model.dto.UserEntityDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


public class PostDto {
    Long id;
    String text;
    LocalDateTime timeStamp;




    private List<PostImageDto> images;

    private List<CommentDto> comments;

    private List<ReactionDto> reactions;

    public PostDto(Long id, String text, LocalDateTime timeStamp, List<PostImageDto> images, List<CommentDto> comments, List<ReactionDto> reactions) {
        this.id = id;
        this.text = text;
        this.timeStamp = timeStamp;
        this.images = images;
        this.comments = comments;
        this.reactions = reactions;
    }

    public PostDto() {
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", timeStamp=" + timeStamp +
                ", images=" + images +
                ", comments=" + comments +
                ", reactions=" + reactions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostDto postDto = (PostDto) o;

        if (!Objects.equals(id, postDto.id)) return false;
        if (!Objects.equals(text, postDto.text)) return false;
        if (!Objects.equals(timeStamp, postDto.timeStamp)) return false;
        if (!Objects.equals(images, postDto.images)) return false;
        if (!Objects.equals(comments, postDto.comments)) return false;
        return Objects.equals(reactions, postDto.reactions);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (timeStamp != null ? timeStamp.hashCode() : 0);
        result = 31 * result + (images != null ? images.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + (reactions != null ? reactions.hashCode() : 0);
        return result;
    }

    private Long ownerId;

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
    private UserEntityDto owner; // Change from Long ownerId to UserEntityDto

    // Getter & Setter
    public UserEntityDto getOwner() {
        return owner;
    }

    public void setOwner(UserEntityDto owner) {
        this.owner = owner;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<PostImageDto> getImages() {
        return images;
    }

    public void setImages(List<PostImageDto> images) {
        this.images = images;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public List<ReactionDto> getReactions() {
        return reactions;
    }

    public void setReactions(List<ReactionDto> reactions) {
        this.reactions = reactions;
    }



}
