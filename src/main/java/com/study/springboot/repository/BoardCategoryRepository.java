package com.study.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.springboot.entity.category.BoardCategory;

@Repository
public interface BoardCategoryRepository extends JpaRepository<BoardCategory, Long>{
	BoardCategory findByCname(String cname);
}
