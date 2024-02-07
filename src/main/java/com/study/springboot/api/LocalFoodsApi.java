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

import com.study.springboot.api.request.CreateAndEditLocalFoodsRequest;
import com.study.springboot.api.response.LocalFoodsDetail;
import com.study.springboot.api.response.LocalFoodsList;
import com.study.springboot.entity.Location;
import com.study.springboot.service.LocalFoodsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LocalFoodsApi {
	
	@Autowired
	private LocalFoodsService localFoodsService;
	
	@GetMapping("/localFoods/all")
	@CrossOrigin
	public List<LocalFoodsList> getLocalFoodsList(){
		return localFoodsService.findAllFoods();
	}
	
	@GetMapping("/localFood/{foodNo}")
	@CrossOrigin
	public LocalFoodsDetail getFood(
			@PathVariable(name="foodNo") Long foodNo
			) {
		return localFoodsService.findById(foodNo);
	}

	@GetMapping("/localFoods/{localNo}")
	@CrossOrigin
	public List<LocalFoodsList> getFoodByLocalNo(
			@PathVariable(name="localNo") Location localNo
			) {
		return localFoodsService.findByLocalNo(localNo);
	}
	
	@PostMapping("/localFood")
	@CrossOrigin
	public ResponseEntity<String> insertLocalFood(
			@RequestBody CreateAndEditLocalFoodsRequest request
			){
		try {
			localFoodsService.insertLocalFoods(request);
			return ResponseEntity.ok("Data Input Completed");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error" + e.getMessage());
		}
	}
	
	@PutMapping("/localFood/{foodNo}")
	@CrossOrigin
	public void editLocalFoods(
			@PathVariable(name="foodNo") Long foodNo,
			@RequestBody CreateAndEditLocalFoodsRequest request
			) {
		localFoodsService.editLocalFoods(foodNo, request);
	}
	
	@DeleteMapping("/localFood/{foodNo}")
	@CrossOrigin
	public void deleteFood(
			@PathVariable(name="foodNo") Long foodNo
			) {
		localFoodsService.deleteLocalFoods(foodNo);
	}

}
