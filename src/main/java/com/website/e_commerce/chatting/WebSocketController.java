package com.website.e_commerce.chatting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import com.website.e_commerce.user.repository.CustomerEntityRepository;
import com.website.e_commerce.user.model.entity.UserEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;

import org.springframework.messaging.handler.annotation.SendTo;

import com.website.e_commerce.notifications.Notification;
import org.springframework.web.bind.annotation.RequestBody;
import java.time.Instant;

@Controller
public class WebSocketController {

    @MessageMapping("/notifications")
    @SendTo("/topic/notifications")
    public Notification send(Notification notification) {
        return notification;
    }
}
