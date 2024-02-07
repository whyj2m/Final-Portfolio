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
@Table(name = "LocationCategory")
@Entity
public class LocationCategory {
	
	@Id
    @SequenceGenerator (
            name = "locationSequence",
            sequenceName = "LocationCategory_SEQ",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "locationSequence")
    private Long locno;
	private String localName; // 관광지 카테고리명
	
}
