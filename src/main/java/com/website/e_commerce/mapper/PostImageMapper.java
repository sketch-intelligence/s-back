package com.website.e_commerce.mapper;

import com.website.e_commerce.postimage.PostImage;
import com.website.e_commerce.postimage.PostImageDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostImageMapper {

    PostImageMapper M = Mappers.getMapper(PostImageMapper.class);

    PostImage toEntity(PostImageDto postImageDto);

    List<PostImage> toEntity(List<PostImageDto> postImageDto);

    PostImageDto toDto(PostImage postImage);

    List<PostImageDto> toDto(List<PostImage> postImage);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)PostImage partialUpdate(PostImageDto postImageDto, @MappingTarget PostImage postImage);
}
