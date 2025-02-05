package com.website.e_commerce.mapper;

import com.website.e_commerce.portfolioproject.image.ProjectImage;
import com.website.e_commerce.portfolioproject.image.ProjectImageDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProfileImageMapper {
    ProfileImageMapper MAPPER = Mappers.getMapper(ProfileImageMapper.class);

    ProjectImage toEntity(ProjectImageDto projectImageDto);

    List<ProjectImage> toEntity(List<ProjectImageDto> projectImageDto);

    ProjectImageDto toDto(ProjectImage projectImage);

    List<ProjectImageDto> toDto(List<ProjectImage> projectImage);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)ProjectImage partialUpdate(ProjectImageDto projectImageDto, @MappingTarget ProjectImage projectImage);
}
