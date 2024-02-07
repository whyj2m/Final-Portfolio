package com.study.springboot.api;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.springboot.api.response.BoardList;
import com.study.springboot.service.BoardService;
import com.study.springboot.service.MemberService;

import lombok.RequiredArgsConstructor;

/**
 * @author bhy98 백혜윤
 * 마이페이지 활동 내역 조회 api
 */
@RestController
@RequiredArgsConstructor
public class MypageApi {
	private final MemberService memberService;
	private final BoardService boardService;
	
	// 나의 활동 - 게시글(관광지 추천)
	@CrossOrigin
	@GetMapping("/mypage/boardlist/tourspot/{userId}")
	public List<BoardList> myTourspotList(@PathVariable String userId) {
		List<BoardList> tourspotList = boardService.tourSpotFindById(userId);
		
		return tourspotList;
	}
	
	// 나의 활동 - 게시글(여행 메이트)
	@CrossOrigin
	@GetMapping("/mypage/boardlist/travelmate/{userId}")
	public List<BoardList> myTravelmateList(@PathVariable String userId) {
		List<BoardList> travelmateList = boardService.travelmateFindById(userId);
		
		return travelmateList;
	}
	
	
	// 나의 활동 - 댓글
	@CrossOrigin
	@GetMapping("/mypage/replylist/{userId}")
	public List<Map<String, Object>> myReplyList(@PathVariable String userId) {
		List<Map<String, Object>> replyList = boardService.replyFindById(userId);
		return replyList;
	}
}
