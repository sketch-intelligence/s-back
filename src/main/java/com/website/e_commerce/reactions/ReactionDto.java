package com.website.e_commerce.reactions;

import java.util.Objects;

public class ReactionDto {
    private Long id;
    private ReactionType reactionType;
    private Long userId;
    private Long postId;

    @Override
    public String toString() {
        return "ReactionDto{" +
                "id=" + id +
                ", reactionType=" + reactionType +
                ", userId=" + userId +
                ", postId=" + postId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReactionDto that = (ReactionDto) o;

        if (!Objects.equals(id, that.id)) return false;
        if (reactionType != that.reactionType) return false;
        if (!Objects.equals(userId, that.userId)) return false;
        return Objects.equals(postId, that.postId);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (reactionType != null ? reactionType.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (postId != null ? postId.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReactionType getReactionType() {
        return reactionType;
    }

    public void setReactionType(ReactionType reactionType) {
        this.reactionType = reactionType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public ReactionDto() {
    }

    public ReactionDto(Long id, ReactionType reactionType, Long userId, Long postId) {
        this.id = id;
        this.reactionType = reactionType;
        this.userId = userId;
        this.postId = postId;
    }
}
