package com.developer.chatt;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import antlr.Utils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {

	private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
	
	public void afterConnectionsEstablished(WebSocketSession session) throws Exception{
		var sessionId = session.getId();
		sessions.put(sessionId, session); //(1) 세션 저장
		
		Message message = Message.builder().sender(sessionId).receiver("all").build();
		message.newConnect();
		
		sessions.values().forEach(s -> { //(2) 모든 세션에 알림 
			try {
				if(!s.getId().equals(sessionId)) {
					s.sendMessage(new TextMessage(message.toString()));
				}
			}catch (Exception e) {
				
			}
		});
	}
	
			
	
}
