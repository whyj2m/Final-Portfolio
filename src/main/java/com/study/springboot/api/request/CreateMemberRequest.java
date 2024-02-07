package com.study.springboot.api.request;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMemberRequest {
	private String id;
    private String email;
    private String password;
    private String name;
    private String phoneNum;
    private ZonedDateTime signUpAt; // 회원 가입 일시 
	private ZonedDateTime updatedAt; // 회원 정보 수정 일시
	private String role;
	private String authProvider;
}
