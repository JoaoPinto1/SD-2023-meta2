package com.example.demo.WebSocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MessagingController {
    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public Message onMessage(Message message) throws InterruptedException {
        System.out.println("Message received " + message);
        Thread.sleep(1000); // simulated delay
        return new Message(HtmlUtils.htmlEscape(message.content()));
    }
}
