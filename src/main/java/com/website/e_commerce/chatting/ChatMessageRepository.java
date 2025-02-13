package com.website.e_commerce.chatting;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    // Find all messages between two users
    List<ChatMessage> findBySenderIdAndReceiverIdOrderByTimestampAsc(Long senderId, Long receiverId);

    // Find all messages for a specific user (either sent or received)
    List<ChatMessage> findBySenderIdOrReceiverIdOrderByTimestampAsc(Long senderId, Long receiverId);
}
