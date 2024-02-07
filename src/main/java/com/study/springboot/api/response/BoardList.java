package com.study.springboot.api.response;

import java.time.ZonedDateTime;

import com.study.springboot.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardList {
	
	private Long bno; 
	private String title;
    private String content;
    private Long viewCnt; 
    private ZonedDateTime regDate; 
	private ZonedDateTime updateDate; 
	private Member id;
	private Long boardCno;
	private Long locationCno;
	private String location;
	
	@Getter
	@Builder
	@AllArgsConstructor
	public static class BoardCategory{
		private Long cno;
		private String cname;
	}
}
