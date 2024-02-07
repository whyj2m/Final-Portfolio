package com.study.springboot.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.springboot.api.request.CreateAndEditLocalFestivalRequest;
import com.study.springboot.api.response.LocalFestivalsDetail;
import com.study.springboot.api.response.LocalFestivalsList;
import com.study.springboot.entity.Location;
import com.study.springboot.service.LocalFestivalsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LocalFestivalsApi {
	
	@Autowired
	private LocalFestivalsService localFestivalsService;
	
	@GetMapping("/localFestivals")
	@CrossOrigin
	public List<LocalFestivalsList> getLocalFestivalsList(){
		return localFestivalsService.findAllFestivals();
	}
	
	@GetMapping("/localFestival/{festivalNo}")
	@CrossOrigin
	public ResponseEntity<LocalFestivalsDetail> getFestival(
	        @PathVariable(name = "festivalNo") Long festivalNo
	) {
	    LocalFestivalsDetail festival = localFestivalsService.findById(festivalNo);

	    // 조회수를 1 증가시킴
	    localFestivalsService.increaseViewCount(festivalNo);

	    // viewCnt가 null이면 0으로 설정
	    if (festival.getViewCnt() == null) {
	        festival.setViewCnt(0L);
	    }

	    return ResponseEntity.ok(festival);
	}


	@GetMapping("/localFestivals/{localNo}")
	@CrossOrigin
	public List<LocalFestivalsList> getFestivalByLocalNo(
			@PathVariable(name="localNo") Location localNo
			) {
		return localFestivalsService.findByLocalNo(localNo);
	}
	
	@PostMapping("/localFestival")
	@CrossOrigin
	public ResponseEntity<String> insertLocalFestival(
			@RequestBody CreateAndEditLocalFestivalRequest request
			){
		try {
			localFestivalsService.insertLocalFestivals(request);
			return ResponseEntity.ok("Data Input Completed");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error" + e.getMessage());
		}
	}
	
	@PutMapping("/localFestival/{festivalNo}")
	@CrossOrigin
	public void editLocalFestivals(
			@PathVariable(name="festivalNo") Long festivalNo,
			@RequestBody CreateAndEditLocalFestivalRequest request
			) {
		localFestivalsService.editLocalFestivals(festivalNo, request);
	}
	
	@DeleteMapping("/localFestival/{festivalNo}")
	@CrossOrigin
	public void deleteFestival(
			@PathVariable(name="festivalNo") Long festivalNo
			) {
		localFestivalsService.deleteLocalFestivals(festivalNo);
	}

}
