package com.website.e_commerce.mapper;

import com.website.e_commerce.post.Post;
import com.website.e_commerce.post.PostDto;
import com.website.e_commerce.postimage.PostImage;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostMapper M = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "postImages", target = "imageFiles", qualifiedByName = "mapPostImagesToUrls")
    @Mapping(target = "ownerId", expression = "java(post.getOwner() != null ? post.getOwner().getId() : null)")
    PostDto toDto(Post post);

    List<PostDto> toDto(List<Post> post);

    @Mapping(source = "imageUrls", target = "postImages", qualifiedByName = "mapUrlsToPostImages")
    @Mapping(source = "reactions", target = "reactions")
    @Mapping(source = "owner", target = "owner")
    Post toEntity(PostDto postDto);

    List<Post> toEntity(List<PostDto> postDto);

    // This is the partial update method definition you're missing
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Post partialUpdate(PostDto postDto, @MappingTarget Post post);

    @Named("mapPostImagesToUrls")
    static List<String> mapPostImagesToUrls(List<PostImage> postImages) {
        if (postImages == null) return null;
        return postImages.stream()
                .map(PostImage::getDownloadUrl)
                .collect(Collectors.toList());
    }

    @Named("mapUrlsToPostImages")
    static List<PostImage> mapUrlsToPostImages(List<String> imageUrls) {
        if (imageUrls == null) return null;
        return imageUrls.stream().map(url -> {
            PostImage postImage = new PostImage();
            postImage.setDownloadUrl(url);
            return postImage;
        }).collect(Collectors.toList());
    }
}





