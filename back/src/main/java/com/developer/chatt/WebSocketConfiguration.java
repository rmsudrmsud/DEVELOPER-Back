package com.developer.chatt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket //웹소켓서버를 사용하도록 정의한다. 
public class WebSocketConfiguration implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry
			.addHandler(signalingSocketHandler(), "/room") //웹소켓서버의 엔드포인트는 url:port/room으로 정의한다. 
			.setAllowedOrigins("*"); //우선 모든 요청 수락 , CORS
	}
	
	@Bean
	public WebSocketHandler signalingSocketHandler() { //웹소켓 핸들러로 정의한다.
		return new WebSocketHandler();
	}

}
