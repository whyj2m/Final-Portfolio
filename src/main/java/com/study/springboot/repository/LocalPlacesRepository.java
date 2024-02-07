package com.study.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.study.springboot.entity.LocalPlaces;
import com.study.springboot.entity.Location;

@Repository
public interface LocalPlacesRepository extends JpaRepository<LocalPlaces, Long>{


    @Query("SELECT fds FROM LocalPlaces fds WHERE fds.localNo = :localNo")
	List<LocalPlaces> findAllByLocalNo(@Param("localNo") Location localNo);

}
