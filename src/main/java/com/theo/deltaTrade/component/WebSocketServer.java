package com.theo.deltaTrade.component;


import com.theo.deltaTrade.common.JacksonObjectMapper;
import com.theo.deltaTrade.entity.Chat;
import com.theo.deltaTrade.service.ChatService;
import com.theo.deltaTrade.vo.Message;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat/{userId}")
@Component
@Slf4j
public class WebSocketServer {

    public static ChatService chatService;

    private static final Map<Long, Session> sessionMap = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(@PathParam("userId") Long userId, Session session) {
        sessionMap.put(userId, session);
    }

    @OnMessage
    public void onMessage(@PathParam("userId") Long userId, String message, Session session) throws IOException {
        if (message.equals("ping")) {
            sendMessage("pong", session);
            return;
        }
        JacksonObjectMapper jacksonObjectMapper = new JacksonObjectMapper();
        Message obj = jacksonObjectMapper.readValue(message, Message.class);
        Chat chat = new Chat();
        chat.setCommodityId(obj.getCommodityId());
        chat.setFromId(userId);
        chat.setToId(obj.getToId());
        chat.setContent(obj.getContent());
        if (chatService.save(chat)) {
            Session toSession = sessionMap.get(obj.getToId());
            sendMessage(obj.getContent(), toSession);
        }
    }

    @OnError
    public void onError(Throwable throwable) {
        log.error(throwable.getMessage());
    }

    @OnClose
    public void onClose(@PathParam("userId") Long userId) {
        sessionMap.remove(userId);
    }

    private void sendMessage(String text, Session toSession) throws IOException {
        toSession.getBasicRemote().sendText(text);
    }
}
