package com.example.demo.WebSocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Configuration
@EnableWebSocketMessageBroker
@EnableScheduling
public class WebSocketServer implements WebSocketMessageBrokerConfigurer {

    private final SimpMessagingTemplate messagingTemplate;
    private final ProgramStatusUpdater serverStatusUpdater;

    public WebSocketServer(SimpMessagingTemplate messagingTemplate, ProgramStatusUpdater serverStatusUpdater) {
        this.messagingTemplate = messagingTemplate;
        this.serverStatusUpdater = serverStatusUpdater;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket").withSockJS();
    }

    @Scheduled(fixedRate = 5000)
    public void updateServerStatusAndBroadcast() {
        ProgramStatus serverStatus = serverStatusUpdater.getProgramStatus();
        messagingTemplate.convertAndSend("/topic/serverStatus", serverStatus);
    }
}
