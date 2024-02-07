package com.study.springboot.api.request;

import com.study.springboot.entity.Location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAndEditLocalFoodsRequest {
	
	private String name;
	private String content;
	private String viewCnt;
	private Location localNo;
	
}
