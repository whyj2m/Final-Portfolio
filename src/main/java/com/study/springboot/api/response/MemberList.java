package com.study.springboot.api.response;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberList {
	
	private String id;
    private String email;
    private String password;
    private String name;
    private String phoneNum;
    private ZonedDateTime signUpAt; // 회원 가입 일시 
	private ZonedDateTime updatedAt; // 비밀 번호 수정 일시
	private boolean admin;
	

}
