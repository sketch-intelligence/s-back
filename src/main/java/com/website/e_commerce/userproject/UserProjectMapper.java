package com.website.e_commerce.userproject;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserProjectMapper {
    UserProjectMapper M = Mappers.getMapper(UserProjectMapper.class);

    @Mapping(target = "architect.id",source = "architectId")
    UserProject toEntity(UserProjectDto userProjectDto);

    List<UserProject> toEntity(List<UserProjectDto> userProjectDto);
    @Mapping(target = "architectId",source = "architect.id")
    UserProjectDto toDto(UserProject userProject);

    List<UserProjectDto> toDto(List<UserProject> userProject);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)UserProject partialUpdate(UserProjectDto userProjectDto, @MappingTarget UserProject userProject);
}
