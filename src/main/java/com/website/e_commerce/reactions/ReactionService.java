package com.website.e_commerce.reactions;

import com.website.e_commerce.exception.ResourceNotFoundException;
import com.website.e_commerce.mapper.ReactionMapper;
import com.website.e_commerce.post.Post;
import com.website.e_commerce.post.PostService;
import com.website.e_commerce.user.model.entity.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReactionService {
    private final ReactionRepository reactionRepository;
    private final PostService postService;
    public ReactionService(ReactionRepository reactionRepository, PostService postService) {
        this.reactionRepository = reactionRepository;
        this.postService = postService;
    }

    public ReactionDto createReaction(ReactionDto request) {
        Post post = postService.getPostById(request.getPostId());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = (UserEntity) auth.getPrincipal();
        request.setUserId(userEntity.getId());
        Reaction reaction = ReactionMapper.M.toEntity(request);
        Reaction savedReaction = reactionRepository.save(reaction);
        return ReactionMapper.M.toDto(savedReaction);
    }

    public Reaction getReactionById(Long id) {
        return reactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Reaction not found"));
    }

    public List<ReactionDto> getReactionsByPost(Long postId) {
        return reactionRepository.findByPostId(postId)
                .stream().map(reaction -> ReactionMapper.M.toDto(reaction))
                .collect(Collectors.toList());
    }

    public List<ReactionDto> getReactionsByUser(Long userId) {
        return reactionRepository.findByUserId(userId)
                .stream().map(reaction -> ReactionMapper.M.toDto(reaction))
                .collect(Collectors.toList());
    }

    public Reaction updateReaction(Long id, Reaction updatedReaction) {
        Reaction existingReaction = getReactionById(id);
        existingReaction.setReactionType(updatedReaction.getReactionType());
        return reactionRepository.save(existingReaction);
    }

    public void deleteReaction(Long id) {
        reactionRepository.findById(id).ifPresentOrElse(reactionRepository::delete, () -> {
            throw new ResourceNotFoundException("resource not found");
        });
    }
}
