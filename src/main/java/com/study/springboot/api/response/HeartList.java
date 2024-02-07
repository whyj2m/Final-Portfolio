package com.study.springboot.api.response;

import com.study.springboot.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class HeartList {
	
	private Long heartNo;
	private Member id;
	private Long placeNo;

}
