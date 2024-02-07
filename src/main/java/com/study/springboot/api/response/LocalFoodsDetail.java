package com.study.springboot.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LocalFoodsDetail {
	
	private Long foodNo;
	private String name;
	private String content;
	private String viewCnt;

}
