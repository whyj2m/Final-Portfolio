package com.study.springboot.api.request;

import com.study.springboot.entity.Location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAndEditLocalFestivalRequest {
	
    private String name;
    private String location;
    private String content;
    private String schedule;
    private Long viewCnt;
    private Location localNo;

}
