package com.theo.deltaTrade.config;

import com.theo.deltaTrade.component.WebSocketServer;
import com.theo.deltaTrade.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Autowired
    public void setChatHistoryService(ChatService chatService) {
        WebSocketServer.chatService = chatService;
    }
}
