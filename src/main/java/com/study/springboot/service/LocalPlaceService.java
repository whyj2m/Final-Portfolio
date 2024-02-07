package com.study.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.api.request.CreateAndEditLocalPlacesRequest;
import com.study.springboot.api.response.LocalPlacesDetail;
import com.study.springboot.api.response.LocalPlacesList;
import com.study.springboot.entity.LocalPlaces;
import com.study.springboot.entity.Location;
import com.study.springboot.repository.LocalPlacesRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocalPlaceService {
	
	@Autowired
	private LocalPlacesRepository placesRepository;
	
	@Transactional
	public LocalPlaces insertLocalPlaces(CreateAndEditLocalPlacesRequest request) {
		LocalPlaces places = LocalPlaces.builder()
							.name(request.getName())
							.location(request.getLocation())
							.content(request.getContent())
							.longitude(request.getLongitude())
							.latitude(request.getLatitude())
							.viewCnt(request.getViewCnt())
							.heartCnt(request.getHeartCnt())
							.localNo(request.getLocalNo())
							.build();
		placesRepository.save(places);
		
		return places;
	}
	
	@Transactional
	public List<LocalPlacesList> findAllPlaces(){
		List<LocalPlaces> places = placesRepository.findAll();
		return places.stream().map((place) -> LocalPlacesList.builder()
							.placeNo(place.getPlaceNo())
							.name(place.getName())
							.location(place.getLocation())
							.content(place.getContent())
							.longitude(place.getLongitude())
							.latitude(place.getLatitude())
							.viewCnt(place.getViewCnt())
							.heartCnt(place.getHeartCnt())
							.localNo(place.getLocalNo())
							.build()
							).toList();
		
	}
	
	 @Transactional
	    public LocalPlacesDetail findLocalPlaceDetailsByPlaceNo(Long placeNo) {
	        LocalPlaces localPlace = placesRepository.findById(placeNo).orElseThrow(EntityNotFoundException::new);

	        // 검색한 정보로 LocalPlacesDetail 객체를 만들어 반환합니다.
	        return LocalPlacesDetail.builder()
	                .placeNo(localPlace.getPlaceNo())
	                .name(localPlace.getName())
	                .location(localPlace.getLocation())
	                .content(localPlace.getContent())
	                .longitude(localPlace.getLongitude())
	                .latitude(localPlace.getLatitude())
	                .viewCnt(localPlace.getViewCnt())
	                .heartCnt(localPlace.getHeartCnt())
	                .localNo(localPlace.getLocalNo())
	                .build();
	    }
	
	@Transactional
	public LocalPlacesDetail findById(Long placeNo) {
		LocalPlaces places = placesRepository.findById(placeNo).orElseThrow();
		
		return LocalPlacesDetail.builder()
								.placeNo(places.getPlaceNo())
								.name(places.getName())
								.location(places.getLocation())
								.content(places.getContent())
								.longitude(places.getLongitude())
								.latitude(places.getLatitude())
								.viewCnt(places.getViewCnt())
								.heartCnt(places.getHeartCnt())
								.localNo(places.getLocalNo())
								.build();
	}

	@Transactional
	public List<LocalPlacesList> findByLocalNo(Location localNo) {
		List<LocalPlaces> places = placesRepository.findAllByLocalNo(localNo);
		
		return places.stream().map((place) -> LocalPlacesList.builder()
							.placeNo(place.getPlaceNo())
							.name(place.getName())
							.location(place.getLocation())
							.content(place.getContent())
							.longitude(place.getLongitude())
							.latitude(place.getLatitude())
							.viewCnt(place.getViewCnt())
							.heartCnt(place.getHeartCnt())
							.localNo(place.getLocalNo())
							.build()
							).toList();
	}
	
	@Transactional
	public void editLocalPlaces(Long placeNo, CreateAndEditLocalPlacesRequest request) {
		LocalPlaces places = placesRepository.findById(placeNo)
				.orElseThrow(() -> new RuntimeException("Known LocalFood"));
		places.changePlacesDetail(request.getName(), request.getLocation(), request.getContent(), 
				request.getLongitude(), request.getLatitude(), request.getViewCnt(), request.getHeartCnt());
		placesRepository.save(places);
		
	}
	
	@Transactional
	public void deleteLocalPlaces(Long placeNo) {
		LocalPlaces places = placesRepository.findById(placeNo).orElseThrow();
		placesRepository.delete(places);
	}
	
	@Transactional
	public void increaseViewCount(Long placeNo) {
	    LocalPlaces place = placesRepository.findById(placeNo)
	            .orElseThrow(() -> new RuntimeException("장소를 찾을 수 없습니다."));

	    // 조회수 증가
	    place.increaseViewCnt();
	    // 변경 사항을 저장
	     placesRepository.save(place);
	}
	
	@Transactional
    public void increaseHeartCount(Long placeNo) {
        LocalPlaces place = placesRepository.findById(placeNo)
                .orElseThrow(() -> new RuntimeException("장소를 찾을 수 없습니다."));

        // 좋아요 수 증가
        place.increaseHeartCnt();
        // 변경 사항을 저장
        placesRepository.save(place);
    }
	
	@Transactional
	public void decreaseHeartCount(Long placeNo) {
	    LocalPlaces place = placesRepository.findById(placeNo)
	            .orElseThrow(() -> new RuntimeException("장소를 찾을 수 없습니다."));

	    // 하트 수 감소
	    place.decreaseHeartCnt();
	    // 변경 사항을 저장
	    placesRepository.save(place);
	}

}
