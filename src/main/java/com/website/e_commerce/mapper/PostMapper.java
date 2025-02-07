package com.website.e_commerce.mapper;

import com.website.e_commerce.post.Post;
import com.website.e_commerce.post.PostDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {CommentMapper.class})
public interface PostMapper {
    PostMapper M = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "images", target = "postImages")
    @Mapping(source = "reactions", target = "reactions")
    Post toEntity(PostDto postDto);

    List<Post> toEntity(List<PostDto> postDto);

    @Mapping(source = "postImages", target = "images")
    @Mapping(source = "reactions", target = "reactions")
    @Mapping(target = "ownerId", expression = "java(post.getOwner() != null ? post.getOwner().getId() : null)") // Fix here
    PostDto toDto(Post post);

    List<PostDto> toDto(List<Post> post);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Post partialUpdate(PostDto postDto, @MappingTarget Post post);
}


