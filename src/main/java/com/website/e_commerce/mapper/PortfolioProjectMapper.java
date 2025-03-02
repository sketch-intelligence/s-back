package com.website.e_commerce.mapper;

import com.website.e_commerce.portfolioproject.PortfolioProject;
import com.website.e_commerce.portfolioproject.PortfolioProjectDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PortfolioProjectMapper {
    PortfolioProjectMapper M = Mappers.getMapper(PortfolioProjectMapper.class);

    PortfolioProject toEntity(PortfolioProjectDto portfolioProjectDto);

    @AfterMapping
    default void linkProjectImage(@MappingTarget PortfolioProject portfolioProject) {
        portfolioProject.getProjectImage().forEach(projectImage -> projectImage.setPortfolioProject(portfolioProject));
    }

    List<PortfolioProject> toEntity(List<PortfolioProjectDto> portfolioProjectDto);

    PortfolioProjectDto toDto(PortfolioProject portfolioProject);

    List<PortfolioProjectDto> toDto(List<PortfolioProject> portfolioProject);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)PortfolioProject partialUpdate(PortfolioProjectDto portfolioProjectDto, @MappingTarget PortfolioProject portfolioProject);
}
