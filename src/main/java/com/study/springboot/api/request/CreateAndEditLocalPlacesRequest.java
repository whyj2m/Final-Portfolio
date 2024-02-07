package com.study.springboot.api.request;

import com.study.springboot.entity.Location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAndEditLocalPlacesRequest {
	
	private String name;
    private String location;
    private String content;
    private String longitude;
    private String latitude;
    private Long viewCnt;
    private Long heartCnt;
    private Location localNo;

}
