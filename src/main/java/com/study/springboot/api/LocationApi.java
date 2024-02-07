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

import com.study.springboot.api.request.CreateAndEditLocationRequest;
import com.study.springboot.api.response.LocationDetail;
import com.study.springboot.api.response.LocationList;
import com.study.springboot.service.LocationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LocationApi {
	
	@Autowired
	private LocationService locationService;
	
	@GetMapping("/locations")
	@CrossOrigin
	public List<LocationList> getLocationList(){
		return locationService.findAllLocations();
	}
	
	@GetMapping("/location/{localNo}")
	@CrossOrigin
	public LocationDetail getLocation(
			@PathVariable(name = "localNo") Long localNo
			) {
		return locationService.findById(localNo);
	}
	
	@PostMapping("/location")
	@CrossOrigin
	public ResponseEntity<String> inserLocation(
			@RequestBody CreateAndEditLocationRequest request
			){
		try {
			locationService.insertLocation(request);
			return ResponseEntity.ok("Data Input Completed");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error" + e.getMessage());
		}
	}
	
	@PutMapping("/location/{localNo}")
	@CrossOrigin
	public void editLocation(
			@PathVariable(name = "localNo") Long localNo,
			@RequestBody CreateAndEditLocationRequest request
			) {
		locationService.editLocation(localNo, request);
	}
	
	@DeleteMapping("/location/{localNo}")
	@CrossOrigin
	public void deleteLocation(
			@PathVariable(name = "localNo") Long localNo
			) {
		locationService.deleteLocation(localNo);
	}
}
