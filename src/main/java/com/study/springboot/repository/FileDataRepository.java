package com.study.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.springboot.entity.FileData;

@Repository
public interface FileDataRepository extends JpaRepository<FileData, String>{

	List<FileData> findByBoardBno(Long boardBno);
	List<FileData> findByUuid(String uuid);
	
}
