package com.study.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Local_Places")
@Entity
public class LocalPlaces {
	
	@Id
    @SequenceGenerator (
            name = "placeSequence",
            sequenceName = "Place_SEQ",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "placeSequence")
    private Long placeNo;

    private String name;
    private String location;
    private String content;
    private String longitude;
    private String latitude;
    private Long viewCnt;
    private Long heartCnt;
    @ManyToOne
	@JoinColumn(name="local_no", referencedColumnName = "localNo")
    private Location localNo;
	
	public void changePlacesDetail(String name, String location, String content, 
			String longitude, String latitude, Long viewCnt, Long heartCnt) {
		this.name = name;
		this.location = location;
		this.content = content;
		this.longitude = longitude;
		this.latitude = latitude;
		this.viewCnt = viewCnt;
		this.heartCnt = heartCnt;
	}
	public void increaseViewCnt() {
	    if (this.viewCnt == null) {
	        this.viewCnt = 0L;
	    }
	    this.viewCnt++;
	}
    public void increaseHeartCnt() {
        if (this.heartCnt == null) {
            this.heartCnt = 0L;
        }
        this.heartCnt++;
    }
	  // 추가된 생성자
    public LocalPlaces(Long placeNo) {
        this.placeNo = placeNo;
    }
    public void decreaseHeartCnt() {
        if (this.heartCnt == null || this.heartCnt <= 0) {
            this.heartCnt = 0L;
        } else {
            this.heartCnt--;
        }
    }

}
