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

import com.study.springboot.api.request.CreateAndEditLocalPlacesRequest;
import com.study.springboot.api.response.LocalPlacesDetail;
import com.study.springboot.api.response.LocalPlacesList;
import com.study.springboot.entity.Location;
import com.study.springboot.service.LocalPlaceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LocalPlacesApi {
	
	@Autowired
	private LocalPlaceService localPlaceService;
	
	@GetMapping("/localPlaces")
	@CrossOrigin
	public List<LocalPlacesList> getLocalPalcesList(){
		return localPlaceService.findAllPlaces();
	}
	
	@GetMapping("/localPlace/{placeNo}")
	@CrossOrigin
	public ResponseEntity<LocalPlacesDetail> getPlace(
	        @PathVariable(name="placeNo") Long placeNo
	) {
	    LocalPlacesDetail place = localPlaceService.findById(placeNo);

	    // 조회수를 1 증가시킴
	    localPlaceService.increaseViewCount(placeNo);
	    
	    // viewCnt가 null이면 0으로 설정
	    if (place.getViewCnt() == null) {
	        place.setViewCnt(0L);
	    }

	    return ResponseEntity.ok(place);
	}

	@GetMapping("/localPlaces/{localNo}")
	@CrossOrigin
	public List<LocalPlacesList> getPlaceByLocalNo(
			@PathVariable(name="localNo") Location localNo
			) {
		return localPlaceService.findByLocalNo(localNo);
	}
	
	@PostMapping("/localPlace")
	@CrossOrigin
	public ResponseEntity<String> insertLocalPlace(
			@RequestBody CreateAndEditLocalPlacesRequest request
			){
		try {
			localPlaceService.insertLocalPlaces(request);
			return ResponseEntity.ok("Data Input Completed");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error" + e.getMessage());
		}
	}
	
	@PutMapping("/localPlace/{placeNo}")
	@CrossOrigin
	public void editLocalPlaces(
			@PathVariable(name="placeNo") Long placeNo,
			@RequestBody CreateAndEditLocalPlacesRequest request
			) {
		localPlaceService.editLocalPlaces(placeNo, request);
	}
	
	@DeleteMapping("/localPlace/{placeNo}")
	@CrossOrigin
	public void deletePlace(
			@PathVariable(name="placeNo") Long placeNo
			) {
		localPlaceService.deleteLocalPlaces(placeNo);
	}

}
