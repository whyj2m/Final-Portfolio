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
@Table(name = "Local_Foods")
@Entity
public class LocalFoods {
	
	@Id
    @SequenceGenerator (
            name = "foodSequence",
            sequenceName = "Food_SEQ",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "foodSequence")
    private Long foodNo;

    private String name;
    private String content;
    private String viewCnt;
    @ManyToOne
	@JoinColumn(name="local_no", referencedColumnName = "localNo")
    private Location localNo;
	
	public void changeFoodsDetail(String name, String content, String viewCnt) {
		this.name = name;
		this.content = content;
		this.viewCnt = viewCnt;
	}

}
