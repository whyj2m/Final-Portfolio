package com.study.springboot.api.response;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.study.springboot.entity.FileData;
import com.study.springboot.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BoardDetail {
	
	private Long bno;
	private String title;
    private String content;
    private Long viewCnt;
    private ZonedDateTime regDate; 
	private ZonedDateTime updateDate; 
	private Member id;
	private Member userName;
	private String additionalInfo;
	private Long locationCno;
	private String location;
	private List<MultipartFile> files;
	private List<FileData> imageInfo; // 이미지 정보 
	  
	@Getter
	@Builder
	@AllArgsConstructor
	public static class BoardCategory{
		private Long cno;
		private String cname;
	}
	
	@Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class AnotherNestedClass {
        private String nestedField;
    }
	
	 public void setImageInfo(List<FileData> imageInfo) {
	        this.imageInfo = imageInfo;
	    }
	

}
