package com.study.springboot.api.request;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EditMemberInfo {
    private String email;
    private String name;
    private String phoneNum;
	private ZonedDateTime updatedAt; // 회원 정보 수정 일시
}
