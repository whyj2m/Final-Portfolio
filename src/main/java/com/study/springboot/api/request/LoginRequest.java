package com.study.springboot.api.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {
	private String id;
	private String password;
}
