package com.study.springboot.api.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
	private String message;
	private String token;
	private String id;
	
	public LoginResponse(String message, String token, String id) {
		this.message = message;
		this.token = token;
		this.id = id;
	}

	public LoginResponse(String message) {
		this.message = message;
	}
}
