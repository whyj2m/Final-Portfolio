package com.study.springboot.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAndEditLocationRequest {
	
	private String name;
    private String content;
    private String poplation;
    private String area;
    private String flower;

}
