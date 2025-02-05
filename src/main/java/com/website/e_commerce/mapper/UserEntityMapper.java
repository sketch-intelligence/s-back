package com.website.e_commerce.mapper;

import com.website.e_commerce.user.model.dto.UserEntityDto;
import com.website.e_commerce.user.model.entity.UserEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserEntityMapper {
    UserEntityMapper M = Mappers.getMapper(UserEntityMapper.class);
    UserEntity toEntity(UserEntityDto userEntityDto);

    List<UserEntity> toEntity(List<UserEntityDto> userEntityDto);

    UserEntityDto toDto(UserEntity userEntity);

    List<UserEntityDto> toDto(List<UserEntity> userEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)UserEntity partialUpdate(UserEntityDto userEntityDto, @MappingTarget UserEntity userEntity);
}
