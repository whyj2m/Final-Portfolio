package com.study.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.api.request.CreateAndEditHeartRequest;
import com.study.springboot.api.response.HeartDetail;
import com.study.springboot.api.response.HeartList;
import com.study.springboot.api.response.LocalPlacesDetail;
import com.study.springboot.entity.Heart;
import com.study.springboot.repository.HeartRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HeartService {
	
	@Autowired
	private HeartRepository heartRepository;
	@Autowired
	private LocalPlaceService localPlaceService;
	
	@Transactional
	public Heart insertHeart(CreateAndEditHeartRequest request) {
	    Heart heart = Heart.builder()
	            .id(request.getId())
	            .placeNo(request.getPlaceNo())  // 여기에서 LocalPlaces의 placeNo를 사용하는 부분 수정
	            .build();

	    heartRepository.save(heart);

	    // Increase heartCnt in LocalPlaces
	    localPlaceService.increaseHeartCount(request.getPlaceNo());

	    return heart;
	}
	
	@Transactional
	public List<HeartList> findAllHearts(){
		List<Heart> hearts = heartRepository.findAll();
		return hearts.stream().map((heart) -> HeartList.builder()
							.heartNo(heart.getHeartNo())
							.id(heart.getId())
							.placeNo(heart.getPlaceNo())
							.build()
							).toList();
		
	}
	@Transactional
	public List<HeartDetail> findHeartDetailsByUserId(String memberId) {
	    List<Heart> hearts = heartRepository.findById_Id(memberId);
	    List<HeartDetail> heartDetails = new ArrayList<>();

	    for (Heart heart : hearts) {
	        // 각 placeNo에 대한 상세 정보를 가져옵니다.
	        LocalPlacesDetail localPlaceDetail = localPlaceService.findLocalPlaceDetailsByPlaceNo(heart.getPlaceNo());

	        // 검색한 정보로 HeartDetail 객체를 만듭니다.
	        HeartDetail heartDetail = HeartDetail.builder()
	                .heartNo(heart.getHeartNo())
	                .id(heart.getId())
	                .placeNo(heart.getPlaceNo())
	                .localPlacesDetail(localPlaceDetail)  // HeartDetail에 LocalPlacesDetail 추가
	                .build();

	        heartDetails.add(heartDetail);
	    }

	    return heartDetails;
	}
	// 체크 하트클릭
	@Transactional
	public boolean checkIfHearted(String memberId, Long placeNo) {
        // 해당 아이디와 placeNo에 대한 로직을 구현하여 확인
        // 예시로, 임의의 로직을 구현
        return heartRepository.existsById_IdAndPlaceNo(memberId, placeNo);
    }
	@Transactional
	public List<HeartDetail> findHeartsByUserId(String memberId) {
	    List<Heart> hearts = heartRepository.findById_Id(memberId);
	    return hearts.stream()
	            .map(heart -> HeartDetail.builder()
	                    .heartNo(heart.getHeartNo())
	                    .id(heart.getId())
	                    .placeNo(heart.getPlaceNo())
	                    .build())
	            .toList();
	}
	
	@Transactional
	public HeartDetail findById(Long heartNo) {
		Heart hearts = heartRepository.findById(heartNo).orElseThrow();
		
		return HeartDetail.builder()
								.heartNo(hearts.getHeartNo())
								.id(hearts.getId())
								.placeNo(hearts.getPlaceNo())
								.build();
	}
	
//	@Transactional
//	public void editHeart(Long heartNo, CreateAndEditLocalFoodsRequest request) {
//		Heart heart = heartRepository.findById(heartNo)
//				.orElseThrow(() -> new RuntimeException("Known LocalFood"));
//		heart.changeH(request.getName(), request.getContent(), request.getViewCnt());
//		foodsRepository.save(foods);
//		
//	}
	
	@Transactional
	public void deleteHeartByMemberIdAndPlaceNo(String memberId, Long placeNo) {
	    Optional<Heart> heartOptional = heartRepository.findById_IdAndPlaceNo(memberId, placeNo);

	    if (heartOptional.isPresent()) {
	        Heart heart = heartOptional.get();
	        Long localPlaces = heart.getPlaceNo();

	        if (localPlaces != null) {
	            localPlaceService.decreaseHeartCount(localPlaces); // localPlaceService를 주입받아 사용
	        }

	        heartRepository.delete(heart);
	    }
	}
}
