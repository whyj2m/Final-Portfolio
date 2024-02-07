package com.study.springboot.api.response;

import java.time.ZonedDateTime;

import com.study.springboot.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardReply {
	private Long boardBno;
	private String content;
	private Member id;
	private ZonedDateTime regDate;
}
