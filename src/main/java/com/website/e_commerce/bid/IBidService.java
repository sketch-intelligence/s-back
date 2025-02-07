package com.website.e_commerce.bid;

import java.util.List;

public interface IBidService {
    Bid placeBid(Bid bid);
    List<Bid> getBidsByProject(Long projectId);
    void deleteBid(Long bidId);
}
