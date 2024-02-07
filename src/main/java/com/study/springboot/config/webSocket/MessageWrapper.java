package com.study.springboot.config.webSocket;

import java.util.List;

//MessageWrapper 클래스에 유저리스트 필드 추가
public class MessageWrapper {
	private String content;
	private String timestamp;
	private String userName;
	private List<String> userList;

	// 생성자, 게터, 세터 등은 생략

	// 생성자에 유저리스트 추가
	public MessageWrapper(String content, String timestamp, String userName, List<String> userList) {
		this.content = content;
		this.timestamp = timestamp;
		this.userName = userName;
		this.userList = userList;
	}

	// 기본 생성자 추가
	public MessageWrapper() {
	}

	
	// 게터 메서드 추가
	public String getContent() {
		return content;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getUserName() {
		return userName;
	}

	public List<String> getUserList() {
		return userList;
	}
}
