package com.website.e_commerce.bid;

import com.website.e_commerce.userproject.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
    List<Bid> findByUserProject(UserProject userProject);
}
