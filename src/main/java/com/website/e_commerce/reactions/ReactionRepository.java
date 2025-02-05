package com.website.e_commerce.reactions;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    List<Reaction> findByPostId(Long postId);
    List<Reaction> findByUserId(Long userId);
}
