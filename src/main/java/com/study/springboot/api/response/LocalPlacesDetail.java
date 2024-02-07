package com.study.springboot.api.response;

import com.study.springboot.entity.Location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class LocalPlacesDetail {
	
	private Long placeNo;
	private String name;
    private String location;
    private String content;
    private String longitude;
    private String latitude;
    private Long viewCnt;
    private Long heartCnt;
    private Location localNo;

}
