package com.example.demo.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WebSocketClient {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WebSocketClient(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendMessage(String destination, ProgramStatus status) {
        messagingTemplate.convertAndSend(destination, status);
    }
}
