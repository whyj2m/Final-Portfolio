//package com.study.springboot.entity.attach;
//
//import java.time.ZonedDateTime;
//
//import com.study.springboot.entity.Board;
//import com.study.springboot.entity.BoardReply;
//import com.study.springboot.entity.Member;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Getter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "BoardAttach")
//@Entity
//public class BoardAttach {
//	
//	@Id
//	private String uuid;
//	private String origin;
//	private String filePath;
//	@ManyToOne
//	@JoinColumn(name="board_bno", referencedColumnName = "bno")
//	private Board bno;
//
//}
