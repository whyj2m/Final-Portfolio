package com.study.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.api.request.CreateAndEditLocalFoodsRequest;
import com.study.springboot.api.response.LocalFoodsDetail;
import com.study.springboot.api.response.LocalFoodsList;
import com.study.springboot.entity.LocalFoods;
import com.study.springboot.entity.Location;
import com.study.springboot.repository.LocalFoodsRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocalFoodsService {
	
	@Autowired
	private LocalFoodsRepository foodsRepository;
	
	@Transactional
	public LocalFoods insertLocalFoods(CreateAndEditLocalFoodsRequest request) {
		LocalFoods foods = LocalFoods.builder()
							.name(request.getName())
							.content(request.getContent())
							.viewCnt(request.getViewCnt())
							.localNo(request.getLocalNo())
							.build();
		foodsRepository.save(foods);
		
		return foods;
	}
	
	@Transactional
	public List<LocalFoodsList> findAllFoods(){
		List<LocalFoods> foods = foodsRepository.findAll();
		return foods.stream().map((food) -> LocalFoodsList.builder()
							.foodNo(food.getFoodNo())
							.name(food.getName())
							.content(food.getContent())
							.viewCnt(food.getViewCnt())
							.localNo(food.getLocalNo())
							.build()
							).toList();
		
	}
	
	@Transactional
	public LocalFoodsDetail findById(Long foodNo) {
		LocalFoods foods = foodsRepository.findById(foodNo).orElseThrow();
		
		return LocalFoodsDetail.builder()
								.foodNo(foods.getFoodNo())
								.name(foods.getName())
								.content(foods.getContent())
								.viewCnt(foods.getViewCnt())
								.build();
	}

	@Transactional
	public List<LocalFoodsList> findByLocalNo(Location localNo) {
		List<LocalFoods> foods = foodsRepository.findAllByLocalNo(localNo);
		
		return foods.stream().map((food) -> LocalFoodsList.builder()
							.foodNo(food.getFoodNo())
							.name(food.getName())
							.content(food.getContent())
							.viewCnt(food.getViewCnt())
							.localNo(food.getLocalNo())
							.build()
							).toList();
	}
	
	@Transactional
	public void editLocalFoods(Long foodNo, CreateAndEditLocalFoodsRequest request) {
		LocalFoods foods = foodsRepository.findById(foodNo)
				.orElseThrow(() -> new RuntimeException("Known LocalFood"));
		foods.changeFoodsDetail(request.getName(), request.getContent(), request.getViewCnt());
		foodsRepository.save(foods);
		
	}
	
	@Transactional
	public void deleteLocalFoods(Long foodNo) {
		LocalFoods food = foodsRepository.findById(foodNo).orElseThrow();
		foodsRepository.delete(food);
	}

}
