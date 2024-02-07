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
@Table(name = "Heart")
@Entity
public class Heart {
	
	@Id
    @SequenceGenerator (
            name = "heartSequence",
            sequenceName = "Heart_SEQ",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "heartSequence")
    private Long heartNo;
	@ManyToOne
	@JoinColumn(name="member_id", referencedColumnName = "id")
	private Member id;
//	@ManyToOne
//	@JoinColumn(name="place_no", referencedColumnName = "placeNo")
	private Long placeNo;
	

}
