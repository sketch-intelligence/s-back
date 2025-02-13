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

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public UserProjectDto createUserProject(UserProjectDto userProjectDto) {
        // Check if architectId is provided
        if (userProjectDto.getArchitectId() == null) {
            throw new IllegalArgumentException("The architectId must not be null");
        }

        // Fetch architect entity using the architectId from the DTO
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = (UserEntity) auth.getPrincipal();

        log.info("auth:{}", auth);

        // Fetch the UserEntity by architectId to get the user name
        Optional<UserEntity> architect = userEntityRepository.findById(userProjectDto.getArchitectId());
        if (architect.isPresent()) {
            userProjectDto.setUserName(architect.get().getName());  // Set the name of the user (architect)
        } else {
            throw new RuntimeException("Architect not found with ID: " + userProjectDto.getArchitectId());
        }

        // Use the injected mapper instead of UserProjectMapper.M
        UserProject userProject = userProjectMapper.toEntity(userProjectDto);
        userProject.setArchitect(userEntity);

        // Save and return the DTO
        userProject = userProjectRepository.save(userProject);
        return userProjectMapper.toDto(userProject);
    }



    @Override
    public UserProjectDto getUserProjectById(Long id) {
        Optional<UserProject> userProject = userProjectRepository.findById(id);

        return userProject.map(project -> {
            UserProjectDto dto = userProjectMapper.toDto(project);
            if (project.getArchitect() != null) {
                dto.setUserName(project.getArchitect().getName()); // Set name
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
                dto.setUserName(project.getArchitect().getName()); // Set name
            }
            return dto;
        }).collect(Collectors.toList());
    }


    @Override
    public UserProjectDto updateUserProject(Long id, UserProjectDto userProjectDto) {
        UserProject userProject = userProjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserProject not found"));

        userProjectMapper.partialUpdate(userProjectDto, userProject);
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


