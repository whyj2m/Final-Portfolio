package com.study.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.api.request.CreateAndEditLocationRequest;
import com.study.springboot.api.response.LocationDetail;
import com.study.springboot.api.response.LocationList;
import com.study.springboot.entity.Location;
import com.study.springboot.repository.LocationRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocationService {
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Transactional
	public Location insertLocation(CreateAndEditLocationRequest request) {
		Location location = Location.builder()
							.name(request.getName())
							.content(request.getContent())
							.poplation(request.getPoplation())
							.area(request.getArea())
							.flower(request.getFlower())
							.build();
		locationRepository.save(location);
		
		return location;
	}
	
	@Transactional
	public List<LocationList> findAllLocations(){
		List<Location> locations = locationRepository.findAll();
		return locations.stream().map((location) -> LocationList.builder()
								.localNo(location.getLocalNo())
								.name(location.getName())
								.content(location.getContent())
								.poplation(location.getPoplation())
								.area(location.getArea())
								.flower(location.getFlower())
								.build()
								).toList();
		
	}
	
	@Transactional
	public LocationDetail findById(Long localNo) {
		Location locations = locationRepository.findById(localNo).orElseThrow();
		
		return LocationDetail.builder()
							.localNo(locations.getLocalNo())
							.name(locations.getName())
							.content(locations.getContent())
							.poplation(locations.getPoplation())
							.area(locations.getArea())
							.flower(locations.getFlower())
							.build();
	}
	
	@Transactional
	public void editLocation(Long localNo, CreateAndEditLocationRequest request) {
		Location locations = locationRepository.findById(localNo)
				.orElseThrow(() -> new RuntimeException("Known LocalFood"));
		locations.changeLocationDetail(request.getName(), request.getContent(), request.getPoplation(), request.getArea(), request.getFlower());
		locationRepository.save(locations);
		
	}
	
	@Transactional
	public void deleteLocation(Long localNo) {
		Location location = locationRepository.findById(localNo).orElseThrow();
		locationRepository.delete(location);
	}

}
