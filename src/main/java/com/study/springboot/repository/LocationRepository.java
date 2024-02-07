package com.study.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.springboot.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>{


}
