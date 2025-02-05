package com.website.e_commerce.user.repository;

import com.website.e_commerce.user.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface CustomerEntityRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    // Method to check if an email already exists
    boolean existsByEmail(String email);

    // If you have a username field, you can add this as well:
    boolean existsByName(String name);

    boolean existsByname(String name);
}
