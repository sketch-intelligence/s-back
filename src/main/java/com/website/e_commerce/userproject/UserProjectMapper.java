package com.website.e_commerce.userproject;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import com.website.e_commerce.bid.Bid;
import com.website.e_commerce.bid.BidDto;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserProjectMapper {

    @Mapping(target = "architect.id", source = "architectId")
    @Mapping(target = "bids", source = "bidDtos")
    UserProject toEntity(UserProjectDto userProjectDto);

    List<UserProject> toEntity(List<UserProjectDto> userProjectDto);

    @Mapping(target = "architectId", source = "architect.id")
    @Mapping(target = "userName", source = "architect.name") // Explicitly map the userName
    @Mapping(target = "bidDtos", source = "bids")
    UserProjectDto toDto(UserProject userProject);

    List<UserProjectDto> toDto(List<UserProject> userProject);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserProject partialUpdate(UserProjectDto userProjectDto, @MappingTarget UserProject userProject);

    List<BidDto> mapBidsToBidDtos(List<Bid> bids);
    List<Bid> mapBidDtosToBids(List<BidDto> bidDtos);
}
