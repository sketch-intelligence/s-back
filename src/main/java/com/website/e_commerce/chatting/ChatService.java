package com.website.e_commerce.chatting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import com.website.e_commerce.user.repository.CustomerEntityRepository;
import com.website.e_commerce.user.model.entity.UserEntity;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import java.time.Instant;
import com.website.e_commerce.notifications.NotificationService;
@Service
public class ChatService {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public void sendMessage(ChatMessage message) {

        chatMessageRepository.save(message);

        // Notify the receiver
        String notificationMessage = "New message from " + message.getSender().getUsername();
        notificationService.sendNotification(message.getReceiver(), notificationMessage, "MESSAGE");

        // Send real-time chat message via WebSocket
        messagingTemplate.convertAndSendToUser(
                message.getReceiver().getId().toString(), "/queue/chat", message
        );
    }

    public ChatMessage saveMessage(ChatMessage message) {
        return chatMessageRepository.save(message);
    }

}


