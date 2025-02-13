package com.website.e_commerce.notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import com.website.e_commerce.user.repository.CustomerEntityRepository;
import com.website.e_commerce.user.model.entity.UserEntity;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import java.time.Instant;


@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate; // For WebSocket notifications

    public void sendNotification(UserEntity receiver, String message, String type) {
        // Save notification to database
        Notification notification = new Notification(receiver, message, type);
        notificationRepository.save(notification);

        // Send real-time notification via WebSocket
        messagingTemplate.convertAndSendToUser(
                receiver.getId().toString(), "/queue/notifications", notification
        );
    }

    public List<Notification> getUnreadNotifications(Long userId) {
        return notificationRepository.findByReceiverIdAndIsReadFalse(userId);
    }

    public void markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setRead(true);
        notificationRepository.save(notification);
    }
}
