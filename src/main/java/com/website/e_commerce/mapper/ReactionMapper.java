package com.website.e_commerce.mapper;

import com.website.e_commerce.reactions.Reaction;
import com.website.e_commerce.reactions.ReactionDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ReactionMapper {
    ReactionMapper M = Mappers.getMapper(ReactionMapper.class);
    @Mapping(source = "userId" , target = "user.id")
    @Mapping(source = "postId",target = "post.id")

    Reaction toEntity(ReactionDto reactionDto);

    List<Reaction> toEntity(List<ReactionDto> reactionDto);
    @Mapping(source = "post.id",target = "postId")
    @Mapping(source = "user.id", target = "userId")
    ReactionDto toDto(Reaction reaction);

    List<ReactionDto> toDto(List<Reaction> reaction);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)Reaction partialUpdate(ReactionDto reactionDto, @MappingTarget Reaction reaction);
}
