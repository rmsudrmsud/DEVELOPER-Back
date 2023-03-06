package com.developer.chat;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // 웹소켓 서버를 사용하도록 정의 + STOMP 사용
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

	/*
	 * 클라이언트가 웹 소켓 서버에 연결하는데 사용할 웹 소켓 엔드포인트 등록 withSockJS를 통해 웹 소켓을 지원하지 않는 브라우저에 대해
	 * 웹 소켓을 대체한다. +)메소드명에 STOMP가 들어가는 경우 통신 프로토콜인 STOMP구현에서 작동된다.
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/chat/room") // 연결될 엔드포인트
					.withSockJS(); // socket.js 연결
	}

	/* 한 클라이언트에서 다른 클라이언트로 메시지를 라우팅하는데 사용될 메시지 브로커 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/topic"); // 메세지를 구독하는 요청 URL -> 메세지 받을 때
		registry.setApplicationDestinationPrefixes("/app"); // 메세지를 발행하는 요청 URL -> 메세지 보낼 때
	}

}