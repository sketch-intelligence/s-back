package com.website.e_commerce.post;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p JOIN FETCH p.owner")
    List<Post> findAllWithOwners();

    @Query("SELECT p FROM Post p WHERE p.id = :postId")
    Post findPostById(@Param("postId") Long postId);
}
