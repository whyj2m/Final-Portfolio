package com.study.springboot.api;

import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.study.springboot.api.request.CreateAndEditBoardRequest;
import com.study.springboot.api.request.CreateReplyRequest;
import com.study.springboot.api.response.BoardDetail;
import com.study.springboot.api.response.BoardList;
import com.study.springboot.entity.Board;
import com.study.springboot.entity.FileData;
import com.study.springboot.entity.Member;
import com.study.springboot.repository.MemberRepository;
import com.study.springboot.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardApi {

	private final BoardService boardService;
	private final MemberRepository memberRepository;
	 
	// 관광지 추천 게시글 목록 조회
	@GetMapping("/board/touristSpot")
	@CrossOrigin
	public List<BoardList> getBoardList(){
		return boardService.findByTouristSpot();
	}
	
	// 관광지 추천 bno별 상세 조회
	@GetMapping("/board/touristSpot/{bno}")
	@CrossOrigin
	public BoardDetail getBoardDetail(@PathVariable Long bno) {
	    BoardDetail board = boardService.findByBno(bno);

	    boardService.viewCount(bno);

	    if (board.getViewCnt() == null) {
	        board.setViewCnt(0L);
	    }

	    // 이미지 정보를 가져와서 BoardDetail에 추가
	    List<FileData> imageInfo = boardService.findByBoardBno(bno);
	    board.setImageInfo(imageInfo);

	    return board;
	}
	
	// 여행메이트 bno별 상세 조회
	@GetMapping("/board/companyView/{bno}")
	@CrossOrigin
	public BoardDetail getCompanyDetail(@PathVariable Long bno) {
		
		BoardDetail board = boardService.findByBno(bno);
		
		boardService.viewCount(bno); // 조회수 1씩 증가
		
		if(board.getViewCnt() == null) {  // viewCnt가 null이면 0으로 설정
			board.setViewCnt(0L);
		}
		
		return boardService.findByBno(bno);
	}
	
	// 여행메이트 게시글 조회
	@GetMapping("/board/company")
	@CrossOrigin
	public List<BoardList> getCompanyList(){
		return boardService.findByCompany();
	}

	// 게시글작성
	@PostMapping("/board/boardWrite")
	@CrossOrigin
	public ResponseEntity<String> insertBoardWithFile(
	        @RequestParam(value = "title") String title,
	        @RequestParam(value = "content") String content,
	        @RequestParam(value = "boardCno") Long boardCno,
	        @RequestParam(value = "locationCno") Long locationCno,
	        @RequestParam(value = "location") String location,
	        @RequestParam(value = "id")String id,
	        @RequestParam(required = false) List<MultipartFile> files
	) {
	    try {
	        CreateAndEditBoardRequest request = new CreateAndEditBoardRequest();
	        request.setTitle(title);
	        request.setContent(content);
	        request.setBoardCno(boardCno);
	        request.setLocationCno(locationCno);
	        request.setLocation(location);

	        Board board = boardService.insertBoard(request, id, files); // 게시글과 파일들을 함께 처리

	        return ResponseEntity.ok("BackEnd: 파일 업로드 성공");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("BackEnd: 게시글 작성 오류" + e.getMessage());
	    }
	  
	}
	
	// 게시글 수정
	@PutMapping("/board/edit/{bno}")
	@CrossOrigin
	public void editBoard(
			@PathVariable(name = "bno") Long bno,
			@RequestBody CreateAndEditBoardRequest request
			) {
		boardService.editBoard(bno, request);
	}
	
	// 게시글 삭제
	@DeleteMapping("/board/delete/{bno}")
	@CrossOrigin
	public void deleteBoard(
			@PathVariable(name = "bno") Long bno
			) {
		boardService.deleteBoard(bno);
	}	
	
	// 댓글 작성
	@PostMapping("/board/companyView/reply")
	@CrossOrigin
	public ResponseEntity<String> insertReply(@RequestBody CreateReplyRequest request){
	    Optional<Member> optionalMember = memberRepository.findById(request.getId().getId());

	    if (optionalMember.isPresent()) {
	        Member member = optionalMember.get();
	        try {
	            boardService.BoardReply(request.getBno(), request.getContent(), member);
	            return ResponseEntity.status(HttpStatus.CREATED).body("댓글 작성 성공");
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 작성 실패");
	        }
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 id의 멤버를 찾을 수 없습니다: " + request.getId().getId());
	    }  
	}
	
	// 댓글 조회
	@GetMapping("/board/companyView/reply/{bno}")
	@CrossOrigin
    public List<Map<String, Object>> getReply(@PathVariable Long bno) {
        return boardService.findReply(bno);
    }
	
	// 댓글 삭제
	@DeleteMapping("/board/companyView/reply/delete/{rno}")
	@CrossOrigin
	public void deleteReply(
			@PathVariable(name = "rno") Long rno
			) {
		boardService.deleteReply(rno);
	}	

	// 이미지 조회
	@GetMapping("/api/images/{bno}")
	@CrossOrigin
	public ResponseEntity<?> getImage(@PathVariable("bno") Long bno) throws IOException{
		byte[] downloadImage = boardService.downloadImageSystem(bno);
		if(downloadImage != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.contentType(MediaType.valueOf("image/png"))
					.body(downloadImage);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	// 이미지 여러개 (실패)
//	@GetMapping("/api/images/{bno}")
//	@CrossOrigin
//	public ResponseEntity<List<FileData>> getFileListDataByBno(@PathVariable("bno") Long bno) {
//	    List<FileData> fileListData = boardService.findByBoardBno(bno);
//
//	    if (fileListData != null && !fileListData.isEmpty()) {
//	        return ResponseEntity.status(HttpStatus.OK).body(fileListData);
//	    } else {
//	        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//	    }
//	}
}