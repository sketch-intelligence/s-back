package com.website.e_commerce.portfolioproject;

import com.website.e_commerce.exception.ResourceNotFoundException;
import com.website.e_commerce.mapper.PortfolioProjectMapper;
import com.website.e_commerce.mapper.UserEntityMapper;
import com.website.e_commerce.user.model.dto.UserEntityDto;
import com.website.e_commerce.user.model.entity.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PortfolioProjectService {

    private final PortfolioProjectRepository portfolioProjectRepository;

    public PortfolioProjectService(PortfolioProjectRepository portfolioProjectRepository) {
        this.portfolioProjectRepository = portfolioProjectRepository;
    }

    public PortfolioProject getProjectById(Long projectId) {
        return portfolioProjectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

    }

    public List<PortfolioProjectDto> getAllArchitectProjects() {
        List<PortfolioProject> projects = portfolioProjectRepository.findAll();
        return projects.stream().map(PortfolioProjectMapper.M::toDto).toList();
    }

    public PortfolioProjectDto updateProject(PortfolioProjectDto request) {
        PortfolioProject portfolioProject = portfolioProjectRepository.findById(request.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();
        UserEntityDto userEntityDto = UserEntityMapper.M.toDto(user);
        request.setArchitect(userEntityDto);
        PortfolioProject project = PortfolioProjectMapper.M.toEntity(request);
        PortfolioProject savedProject = portfolioProjectRepository.save(project);
        return PortfolioProjectMapper.M.toDto(savedProject);
    }

    public PortfolioProjectDto createProject(PortfolioProjectDto request) {
        PortfolioProject project = PortfolioProjectMapper.M.toEntity(request);

        // Ensure project images are not null before processing
        if (project.getProjectImage() == null) {
            project.setProjectImage(List.of()); // Set an empty list instead of null
        }

        PortfolioProject savedProject = portfolioProjectRepository.save(project);
        return PortfolioProjectMapper.M.toDto(savedProject);
    }



    public void deletePortfolioProjectById(Long projectId) {
        portfolioProjectRepository.findById(projectId)
                .ifPresentOrElse(portfolioProjectRepository::delete, () -> {
                    throw new ResourceNotFoundException("Project not found");
                });
    }
}
