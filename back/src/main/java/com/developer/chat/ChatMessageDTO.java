package com.developer.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDTO {	//채팅내용 및 사용자를 담는 DTO
	
	//enum 타입은 일정 개수의 상수값을 선언하는, 관련있는 상수들의 집합
	public enum MessageType {
		CHAT, JOIN, LEAVE
	}
	
	private MessageType type;
	private String content;
	private String sender;
	
}