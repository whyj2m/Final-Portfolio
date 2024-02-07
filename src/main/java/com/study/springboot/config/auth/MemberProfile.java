package com.study.springboot.config.auth;

import com.study.springboot.entity.Member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberProfile {
	private String id;
	private String name;
	private String email;
	private String authProvider;
	
	public Member toMember() {
		return Member.builder()
				.id(id)
				.name(name)
				.email(email)
				.authProvider(authProvider)
				.build();
	}
}
