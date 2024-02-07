package com.study.springboot.api.response;

import com.study.springboot.entity.Location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LocalFestivalsList {
	
	private Long festivalNo;
	private String name;
    private String location;
    private String content;
    private String schedule;
    private Long viewCnt;
    private Location localNo;

}
