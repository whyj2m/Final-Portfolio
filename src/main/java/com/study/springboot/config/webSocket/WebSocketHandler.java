package com.study.springboot.config.webSocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

	private static final List<WebSocketSession> sessionList = new ArrayList<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// 연결이 성공하면 세션을 리스트에 추가
		sessionList.add(session);
		System.out.println("WebSocket 연결이 열렸습니다: " + session.getId());

	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// 세션에서 사용자 이름 가져오기
		String leavingUserName = getUserName(session);

		// 연결이 종료되면 세션을 리스트에서 제거
		sessionList.remove(session);
		System.out.println("WebSocket 연결이 닫혔습니다: " + session.getId());

		// 나간 유저에게 알리는 메시지를 보내기
		String leaveMessage = leavingUserName + "님이 나가셨습니다.";
		broadcastToAllSessions(leaveMessage, String.valueOf(System.currentTimeMillis()), leavingUserName);
	}

	// 세션에서 유저 이름 가져오기
	private String getUserName(WebSocketSession session) {
		return session.getAttributes().get("name").toString();
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 클라이언트로부터 메시지 수신
		String payload = message.getPayload();
		System.out.println(session.getId() + "에서 메시지 수신: " + payload);

		// 받은 JSON 메시지 파싱
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(payload);

		// type 필드로 메시지 종류 구분
		String messageType = jsonNode.get("type").asText();
		switch (messageType) {
		case "login":
			// 로그인 이벤트 처리
			handleLoginEvent(session, jsonNode);
			break;
		case "message":
			// 메시지 이벤트 처리
			handleMessageEvent(session, jsonNode);
			break;
		default:
			System.out.println("Unknown message type: " + messageType);
		}
	}

	// 로그인 이벤트 처리
	private void handleLoginEvent(WebSocketSession session, JsonNode jsonNode) throws IOException {
		String name = jsonNode.get("name").asText();
		String loginMessage = name + "님이 로그인하셨습니다.";

		// 세션에 사용자 이름 저장
		session.getAttributes().put("name", name);

		// 여기에 빈값줘서 사용자 Name 제거
		String name2 = "";

		String timestamp = String.valueOf(System.currentTimeMillis());
		// 받은 메시지를 다른 세션들에게 전송
		broadcastToAllSessions(loginMessage, timestamp, name2);
	}

	// 메시지 이벤트 처리
	private void handleMessageEvent(WebSocketSession session, JsonNode jsonNode) throws IOException {
		String content = jsonNode.get("content").asText();
		// 이름이 없을시에는 UnknownUser로 뜹니다
		String name = jsonNode.has("name") ? jsonNode.get("name").asText() : "UnknownUser";
		String timestamp = String.valueOf(System.currentTimeMillis());

		// 받은 메시지를 다른 세션들에게 전송
		broadcastToAllSessions(content, timestamp, name);
	}

	// 브로드캐스트를 위한 메서드 수정
	private void broadcastToAllSessions(String content, String timestamp, String userName) {
		ObjectMapper objectMapper = new ObjectMapper();

		// 클라이언트에게 전송할 유저리스트 생성
		List<String> userList = sessionList.stream().filter(WebSocketSession::isOpen).map(WebSocketSession::getId)
				.peek(id -> System.out.println("User ID: " + id)) // 여기에 로그 추가
				.collect(Collectors.toList());

		// 메시지, 타임스탬프, userName, 유저리스트을 JSON 형식으로 변환하여 전송
		for (WebSocketSession webSocketSession : sessionList) {
			if (webSocketSession.isOpen()) {
				try {
					webSocketSession.sendMessage(new TextMessage(objectMapper
							.writeValueAsString(new MessageWrapper(content, timestamp, userName, userList))));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
