package com.developer.chat;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatController {
	
	private final SimpMessagingTemplate simpMessagingTemplate;
	
    @MessageMapping("/ws/chat/{room}") //@MessageMapping에서 추출할 때는 @DestinationVariable을 사용
    public void chat(@DestinationVariable("room") String room, Chat chat) throws Exception {
    	simpMessagingTemplate.convertAndSend("/sub/" + room , chat);	//convertAndSend()같은 경우, 첫번째가 destination 두번째는 payload
    }

}