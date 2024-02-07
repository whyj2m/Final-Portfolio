package com.study.springboot.entity.attach;

import com.study.springboot.entity.LocalFestivals;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FestivalAttach")
@Entity
public class FestivalAttach {
	
	@Id
	private String uuid;
	private String origin;
	private String filePath;
	@ManyToOne
	@JoinColumn(name="festival_no", referencedColumnName = "festivalNo")
	private LocalFestivals festivalNo;

}
