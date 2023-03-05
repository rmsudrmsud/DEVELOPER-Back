package com.developer.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message { //클라이언트 소켓 통신에서 사용하는 메세지 스펙 정의
	private String type;
	private String sender;
	private String receiver;
	private Object data;
	
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public void newConnect() {
		this.type = "new";
	}
	
	public void closeConnect() {
		this.type = "close";
	}

}
