package com.website.e_commerce.userproject;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProjectRepository extends JpaRepository<UserProject,Long> {
    List<UserProject> findByArchitectId(Long userId);
}
