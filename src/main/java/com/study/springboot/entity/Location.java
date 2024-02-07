package com.study.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "Location")
@Entity
public class Location {
	
	@Id
    @SequenceGenerator (
            name = "locationSequence",
            sequenceName = "Location_SEQ",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "locationSequence")
    private Long localNo;

    private String name;
    private String content;
    private String poplation;
    private String area;
    private String flower;
	
	public void changeLocationDetail(String name, String content, String poplation, String area, String flower) {
		this.name = name;
		this.content = content;
		this.poplation = poplation;
		this.area = area;
		this.flower = flower;
	}

}
