package com.website.e_commerce.notifications;
import jakarta.persistence.*;
import com.website.e_commerce.user.model.entity.UserEntity;
import java.time.Instant;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private UserEntity receiver; // The user receiving the notification

    private String message;  // Notification text
    private String type; // e.g., "MESSAGE", "NEW_FRIEND", etc.

    private boolean isRead = false; // Mark if the notification has been seen

    @Column(nullable = false)
    private Long timestamp; // Time the notification was created

    public Notification() {}

    public Notification(UserEntity receiver, String message, String type) {
        this.receiver = receiver;
        this.message = message;
        this.type = type;
        this.timestamp = Instant.now().toEpochMilli();
    }

    // ✅ Add the setter if it's missing
    public void setRead(boolean read) {
        this.isRead = read;
    }

    // ✅ Add the getter if needed
    public boolean isRead() {
        return isRead;
    }
}
