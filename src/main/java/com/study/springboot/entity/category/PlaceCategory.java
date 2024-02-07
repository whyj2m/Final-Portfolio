package com.study.springboot.entity.category;

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
@Table(name = "PlaceCategory")
@Entity
public class PlaceCategory {
	
	@Id
    @SequenceGenerator (
            name = "placeSequence",
            sequenceName = "PlaceCategory_SEQ",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "placeSequence")
    private Long pcno;
	private String placeName; // 관광지 카테고리명

}
