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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.springboot.api.request.CreateAndEditHeartRequest;
import com.study.springboot.api.response.HeartDetail;
import com.study.springboot.api.response.HeartList;
import com.study.springboot.service.HeartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HeartApi {

	@Autowired
	private HeartService heartService;

	@GetMapping("/hearts")
	@CrossOrigin
	public List<HeartList> getHeartList() {
		return heartService.findAllHearts();
	}

	@GetMapping("/hearts/{memberid}")
	@CrossOrigin
	public List<HeartDetail> getHeartsByUserId(@PathVariable(name = "memberid") String memberId) {
		return heartService.findHeartsByUserId(memberId);
	}

	@GetMapping("/heart/check/{memberid}/{placeno}")
	@CrossOrigin
	public ResponseEntity<Boolean> checkIfHearted(@PathVariable(name = "memberid") String memberId,
			@PathVariable(name = "placeno") Long placeNo) {
		try {
			boolean isHearted = heartService.checkIfHearted(memberId, placeNo);
			return ResponseEntity.ok(isHearted);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}
	}

	@GetMapping("/heart/{heartNo}")
	@CrossOrigin
	public HeartDetail getHeart(@PathVariable(name = "heartNo") Long heartNo) {
		return heartService.findById(heartNo);
	}

	@GetMapping("/hearts/details/{memberid}")
	@CrossOrigin
	public List<HeartDetail> getHeartDetailsByUserId(@PathVariable(name = "memberid") String memberId) {
		return heartService.findHeartDetailsByUserId(memberId);
	}

	@PostMapping("/heart")
	@CrossOrigin
	public ResponseEntity<String> inserHeart(@RequestBody CreateAndEditHeartRequest request) {
		try {
			heartService.insertHeart(request);
			return ResponseEntity.ok("Data Input Completed");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error" + e.getMessage());
		}
	}

//	@PutMapping("/heart/{heartNo}")
//	public void editHeart(
//			@PathVariable(name = "heartNo") Long heartNo,
//			@RequestBody CreateAndEditHeartRequest request
//			) {
//		heartService.editHeart(heartNo, request);
//	}

	@DeleteMapping("/hearts/{memberid}/{placeno}")
	@CrossOrigin
	public ResponseEntity<String> deleteHeart(@PathVariable(name = "memberid") String memberId,
			@PathVariable(name = "placeno") Long placeNo) {
		try {
			heartService.deleteHeartByMemberIdAndPlaceNo(memberId, placeNo);
			return ResponseEntity.ok("Heart deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error: " + e.getMessage());
		}
	}
}
