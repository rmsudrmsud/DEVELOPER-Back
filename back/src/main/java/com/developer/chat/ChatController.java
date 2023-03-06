package com.developer.chat;

import java.util.ArrayList;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.developer.chat.ChatMessageDTO.MessageType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ChatController { //채팅을 보냈을 때 or 채팅인원이 변경되면 처리해줄 Controller
	
	private final SimpMessageSendingOperations messagingTemplate;
	
	ArrayList<String> users = new ArrayList<String>();
	
	// 새로운 사용자가 웹 소켓을 연결할 때 실행됨
	@EventListener //한 개의 매개변수만 가질 수 있다.
	public void handleWebSocketConnecListener(SessionConnectEvent event) {
		log.info("Received a new web socket connection");
	}
    
	// 사용자가 웹 소켓 연결을 끊으면 실행됨
	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		
		StompHeaderAccessor headerAccesor = StompHeaderAccessor.wrap(event.getMessage());
		String username = (String) headerAccesor.getSessionAttributes().get("username");
	
		if(username != null) {
			log.info("User Disconnected : " + username);
			
			users.remove(username);
			System.out.println(users);
			
			ChatMessageDTO chat = new ChatMessageDTO(MessageType.LEAVE, null, username);
			messagingTemplate.convertAndSend("/topic/public", chat);
		}
	}
	
	// 요청이 들어오면 해당 메소드로 처리된다.
	@MessageMapping("/sendMessage")
	@SendTo("/topic/public")
	public ChatMessageDTO sendMessage(@Payload ChatMessageDTO chat) {
		return chat;
	}
	
	// 요청이 들어오면 해당 메소드로 처리된다.
	@MessageMapping("/addUser")
	@SendTo("/topic/public")
	public ChatMessageDTO addUser(@Payload ChatMessageDTO chat, SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put("username", chat.getSender());
		users.add(chat.getSender());
		return chat;
	}
}