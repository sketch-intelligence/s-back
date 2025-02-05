package com.website.e_commerce.mapper;

import com.website.e_commerce.comment.Comment;
import com.website.e_commerce.comment.CommentDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {UserEntityMapper.class})
public interface CommentMapper {

    CommentMapper M = Mappers.getMapper(CommentMapper.class);


    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "post.id" , source = "postId")
    Comment toEntity(CommentDto commentDto);


    List<Comment> toEntity(List<CommentDto> commentDto);
    @Mapping(target = "userId" , source = "user.id")
    @Mapping(target = "postId",source = "post.id")
    CommentDto toDto(Comment comment);

    List<CommentDto> toDto(List<Comment> comment);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)Comment partialUpdate(CommentDto commentDto, @MappingTarget Comment comment);
}
