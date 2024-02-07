package com.study.springboot.entity.attach;

import com.study.springboot.entity.LocalFoods;

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
@Table(name = "FoodAttach")
@Entity
public class FoodAttach {
	
	@Id
	private String uuid;
	private String origin;
	private String filePath;
	@ManyToOne
	@JoinColumn(name="food_no", referencedColumnName = "foodNo")
	private LocalFoods foodNo;

}
