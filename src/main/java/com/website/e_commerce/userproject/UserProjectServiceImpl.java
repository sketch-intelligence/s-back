package com.website.e_commerce.userproject;

import com.website.e_commerce.exception.UnauthorizedRoleException;
import com.website.e_commerce.role.RoleEntity;
import com.website.e_commerce.user.RoleEnum;
import com.website.e_commerce.user.model.dto.UserEntityDto;
import com.website.e_commerce.user.model.entity.UserEntity;
import com.website.e_commerce.user.model.entity.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.website.e_commerce.bid.Bid;
import com.website.e_commerce.bid.BidRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.website.e_commerce.security.controller.AuthenticationController.log;
import static com.website.e_commerce.user.RoleEnum.USER;

@Service
public class UserProjectServiceImpl implements IUserProjectService {

    @Autowired
    private UserProjectRepository userProjectRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private UserProjectMapper userProjectMapper;

    @Autowired
    private  BidRepository bidRepository;

    public UserProject acceptBid(Long projectId, Long bidId, Long userId) {
        // Fetch project
        UserProject project = userProjectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        // Ensure the user is the owner of the project
        if (!project.getArchitect().getId().equals(userId)) {
            throw new RuntimeException("You are not allowed to accept a bid for this project.");
        }

        // Fetch bid
        Bid acceptedBid = bidRepository.findById(bidId)
                .orElseThrow(() -> new RuntimeException("Bid not found"));

        // Ensure the bid belongs to the project
        if (!project.getBids().contains(acceptedBid)) {
            throw new RuntimeException("This bid does not belong to the given project.");
        }

        // Accept the bid
        project.setStatus("closed");
        userProjectRepository.save(project);

        return project;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public UserProjectDto createUserProject(UserProjectDto userProjectDto) {
        if (userProjectDto.getArchitectId() == null) {
            throw new IllegalArgumentException("The architectId must not be null");
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = (UserEntity) auth.getPrincipal();

        log.info("auth:{}", auth);

        Optional<UserEntity> architect = userEntityRepository.findById(userProjectDto.getArchitectId());
        if (architect.isPresent()) {
            userProjectDto.setUserName(architect.get().getName());
        } else {
            throw new RuntimeException("Architect not found with ID: " + userProjectDto.getArchitectId());
        }

        // Use the injected mapper
        UserProject userProject = userProjectMapper.toEntity(userProjectDto);
        userProject.setArchitect(userEntity);

        // Set default values for new fields
        if (userProject.getStatus() == null) {
            userProject.setStatus("Pending");
        }
// Default status when creating
        userProject.setPublishedSince(0); // Newly created projects start at 0

        userProject = userProjectRepository.save(userProject);
        return userProjectMapper.toDto(userProject);
    }

    @Override
    public UserProjectDto getUserProjectById(Long id) {
        Optional<UserProject> userProject = userProjectRepository.findById(id);

        return userProject.map(project -> {
            UserProjectDto dto = userProjectMapper.toDto(project);
            if (project.getArchitect() != null) {
                dto.setUserName(project.getArchitect().getName());
            }
            return dto;
        }).orElseThrow(() -> new RuntimeException("UserProject not found"));
    }

    @Override
    public List<UserProjectDto> getAllUserProjects() {
        List<UserProject> projects = userProjectRepository.findAll();
        return projects.stream().map(project -> {
            UserProjectDto dto = userProjectMapper.toDto(project);
            if (project.getArchitect() != null) {
                dto.setUserName(project.getArchitect().getName());
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public UserProjectDto updateUserProject(Long id, UserProjectDto userProjectDto) {
        UserProject userProject = userProjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserProject not found"));

        userProjectMapper.partialUpdate(userProjectDto, userProject);

        // Ensure status & publishedSince are updated if present in the DTO
        if (userProjectDto.getStatus() != null) {
            userProject.setStatus(userProjectDto.getStatus());
        }
        if (userProjectDto.getPublishedSince() != 0) {
            userProject.setPublishedSince(userProjectDto.getPublishedSince());
        }

        userProject = userProjectRepository.save(userProject);
        return userProjectMapper.toDto(userProject);
    }

    @Override
    public void deleteUserProject(Long id) {
        if (!userProjectRepository.existsById(id)) {
            throw new RuntimeException("UserProject not found");
        }
        userProjectRepository.deleteById(id);
    }
}



