package com.developer.chat;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/*
 	WebSocketHandler클래스는 4개의 메서드 오버라이드 해야한다.
 		1) 웹소켓 연결시 : afterConnectionEstablished
 		2) 데이터 통신시 : handleTextMessage
 		3) 웹소켓 연결종료시 : afterConnectionClosed
 		4) 웹소켓 통신에러시 : handleTransportError
 */
public class WebSocketHandler extends TextWebSocketHandler {

	//최초 연결시 접속 여부를 다른 사용자에게 전달하기 위해서는
	//기존 접속 사용자의 웹소켓 세션을 전부 관리하고 있어야 한다. 
	//		-> <세션ID : 세션>이라는 Map 구조 활용하자
	private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

	// 웹소켓 연결 시
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String sessionId = session.getId();
		sessions.put(sessionId, session); //(1) 세션에 저장하자.

		Message message = Message.builder()
					.sender(sessionId)
					.receiver("all")
					.build();
		message.newConnect();

		sessions.values().forEach(s -> { //(2) 본인제외, 접속 중인 모든 세션에 메세지를 보내준다. 
			try {
				if (!s.getId().equals(sessionId)) {
					s.sendMessage(new TextMessage("hellow this is " + sessionId));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	// 양방향 데이터통신
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {

		Message message = Utils.getObject(textMessage.getPayload());
		message.setSender(session.getId());

		// 메세지를 전달할 상대방을 찾는다. 
		WebSocketSession recevier = sessions.get(message.getReceiver());

		//상대방이 존재하고, 연결된 상태라면 메세지를 전송한다.
		if (recevier != null && recevier.isOpen()) {
			recevier.sendMessage(new TextMessage(Utils.getString(message)));
		}
	}

	//웹소켓 연결 종료
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

		String sessionId = session.getId();
		sessions.remove(sessionId);

		final Message message = new Message();
		message.closeConnect();
		message.setSender(sessionId);

		sessions.values().forEach(s -> {
			try {
				s.sendMessage(new TextMessage(Utils.getString(message)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	//웹소켓 통신 에러 
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
	}

}
