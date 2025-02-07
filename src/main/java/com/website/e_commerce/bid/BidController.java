package com.website.e_commerce.bid;

import com.website.e_commerce.user.model.entity.UserEntity;
import com.website.e_commerce.user.service.UserEntityService;
import com.website.e_commerce.role.RoleEntity;
import com.website.e_commerce.userproject.UserProject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/bids")
public class BidController {

    private final IBidService bidService;
    private final UserEntityService userEntityService; // Correct service name

    public BidController(IBidService bidService, UserEntityService userEntityService) {
        this.bidService = bidService;
        this.userEntityService = userEntityService;
    }

    @PostMapping("/place")
    public ResponseEntity<Bid> placeBid(@RequestBody BidDto bidDto) {
        // Get logged-in user's details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Extract email from token

        // Fetch user from DB
        UserEntity architect = userEntityService.findByEmail(email);

        // Extract the role from roles set
        boolean isArchitect = architect.getRoles().stream()
                .map(RoleEntity::getName) // Get the role name
                .map(Enum::name) // Convert to string
                .anyMatch(role -> role.equalsIgnoreCase("ARCHITECT")); // Check if it matches "ARCHITECT"

        // Ensure architect role
        if (!isArchitect) {
            return ResponseEntity.status(403).body(null); // Forbidden if user is not an architect
        }

        // Convert DTO to Entity and assign logged-in architect
        Bid bid = new Bid();
        bid.setUserProject(new UserProject()); // Assign project ID from DTO
        bid.getUserProject().setId(bidDto.getProjectId());
        bid.setArchitect(architect);
        bid.setPrice(bidDto.getPrice());
        bid.setExpectedDuration(bidDto.getExpectedDuration());
        bid.setDescription(bidDto.getDescription());

        // Save and return response
        return ResponseEntity.ok(bidService.placeBid(bid));
    }


    @GetMapping("/project/{projectId}") // Add this method
    public ResponseEntity<List<Bid>> getBidsByProject(@PathVariable("projectId") Long projectId) {
        List<Bid> bids = bidService.getBidsByProject(projectId);
        return ResponseEntity.ok(bids);
    }

    @DeleteMapping("/{bidId}") // ✅ Added delete method
    public ResponseEntity<String> deleteBid(@PathVariable("bidId") Long bidId) {
        bidService.deleteBid(bidId);
        return ResponseEntity.ok("Bid deleted successfully");
    }
}
