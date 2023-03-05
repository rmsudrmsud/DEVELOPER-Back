package com.developer.chat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/*
 	@EnableWebSocket
 	웹 소켓 관련 설정을 자동으로 해주고, 
 	 WebSocketConfigurer을 implement + override 하여 메서드를 customize
 */

@Configuration
@EnableWebSocket //웹소켓 서버를 사용하도록 정의
public class WebSocketConfiguration implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
                .addHandler(signalingSocketHandler(), "/room") //웹소켓 서버의 엔드포인트 정의
                .setAllowedOrigins("*"); //우선 서버 요청시 모든 요청 수용 CORS 
    }

    @Bean
    public WebSocketHandler signalingSocketHandler() { //WebSocketHandler클래스를 웹소켓 핸들러로 정의
        return new WebSocketHandler(); 
    }
}