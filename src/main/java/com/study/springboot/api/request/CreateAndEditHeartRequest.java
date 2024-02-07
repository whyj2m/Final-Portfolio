package com.study.springboot.api.request;

import com.study.springboot.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAndEditHeartRequest {
	private Member id;
	private Long placeNo;
	private String memberId;

}
