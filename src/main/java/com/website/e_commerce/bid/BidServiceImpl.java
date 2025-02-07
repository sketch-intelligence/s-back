package com.website.e_commerce.bid;

import com.website.e_commerce.userproject.UserProject;
import com.website.e_commerce.userproject.UserProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidServiceImpl implements IBidService {

    private final BidRepository bidRepository;
    private final UserProjectRepository userProjectRepository;

    public BidServiceImpl(BidRepository bidRepository, UserProjectRepository userProjectRepository) {
        this.bidRepository = bidRepository;
        this.userProjectRepository = userProjectRepository;
    }

    @Override
    public Bid placeBid(Bid bid) {
        // Ensure project exists before placing a bid
        UserProject project = userProjectRepository.findById(bid.getUserProject().getId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        // Assign project to bid
        bid.setUserProject(project);

        return bidRepository.save(bid);
    }

    @Override
    public List<Bid> getBidsByProject(Long projectId) {
        Optional<UserProject> project = userProjectRepository.findById(projectId);
        return project.map(bidRepository::findByUserProject).orElseThrow(() -> new RuntimeException("Project not found"));
    }

    @Override
    public void deleteBid(Long bidId) {
        bidRepository.deleteById(bidId);
    }

}
