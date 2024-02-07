package com.study.springboot.service;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.study.springboot.api.request.CreateMemberRequest;
import com.study.springboot.api.request.EditMemberInfo;
import com.study.springboot.api.request.EditMemberPassword;
import com.study.springboot.api.request.LoginRequest;
import com.study.springboot.api.response.LoginResponse;
import com.study.springboot.api.response.MemberDetail;
import com.study.springboot.api.response.MemberList;
import com.study.springboot.config.jwt.TokenProvider;
import com.study.springboot.entity.Board;
import com.study.springboot.entity.Member;
import com.study.springboot.repository.BoardReplyRepository;
import com.study.springboot.repository.BoardRepository;
import com.study.springboot.repository.HeartRepository;
import com.study.springboot.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository;
	private final BoardRepository boardRepository;
	private final BoardReplyRepository boardReplyRepository;
	private final HeartRepository heartRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final TokenProvider tokenProvider;
	private final BoardService boardService;
	
	public Member insertMember(CreateMemberRequest request) {
		Member member = Member.builder()
							.id(request.getId())
							.email(request.getEmail())
							.password(bCryptPasswordEncoder.encode(request.getPassword()))
							.name(request.getName())
							.phoneNum(request.getPhoneNum())
							.signUpAt(ZonedDateTime.now())
							.updatedAt(ZonedDateTime.now())
							.role(request.getRole())
							.authProvider(request.getAuthProvider())
							.build();
		memberRepository.save(member);
		
		return member;
	}
	
	// id 중복확인
	public boolean checkIdDuplicate(String id) {
		return memberRepository.existsById(id);
	}
	
	public ResponseEntity<LoginResponse> login(LoginRequest request) {
		Member member = memberRepository.findById(request.getId()).orElseThrow(()->new IllegalArgumentException(request.getId()));
		
		// 사용자 존재, 비밀번호 일치 >> 로그인 성공
		if(member != null && bCryptPasswordEncoder.matches(request.getPassword(), member.getPassword())) {
			// 토큰 만료시간 설정 후 생성
			Duration expirationTime = Duration.ofMinutes(30);
			String token = tokenProvider.generateToken(member, expirationTime);
			
			// 로그인 성공 및 JWT 토큰반환
			LoginResponse response = new LoginResponse("Login successful!", token, member.getId());
			
			// 로그아웃 구분을 위한 redis 저장
			
			return ResponseEntity.ok(response);
			
		} else if (member != null && !bCryptPasswordEncoder.matches(request.getPassword(), member.getPassword())) {
			// 비밀번호 불일치
			LoginResponse response = new LoginResponse("Incorrect password.");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
			
		} else { // 로그인 실패
			LoginResponse response = new LoginResponse("Login failed.");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}
	}
	
	public List<MemberList> findAllMembers(){
		List<Member> members = memberRepository.findAll();
		return members.stream().map((member) -> MemberList.builder()
								.id(member.getId())
								.email(member.getEmail())
								.password(member.getPassword())
								.name(member.getName())
								.phoneNum(member.getPhoneNum())
								.signUpAt(member.getSignUpAt())
								.updatedAt(member.getUpdatedAt())
								.build()
								).toList();
	}
	
	public MemberDetail findById(String id) {
		Member member = memberRepository.findById(id).orElseThrow();
		
		return MemberDetail.builder()
									.id(member.getId())
									.email(member.getEmail())
									.password(member.getPassword())
									.name(member.getName())
									.phoneNum(member.getPhoneNum())
									.signUpAt(member.getSignUpAt())
									.updatedAt(member.getUpdatedAt())
									.build();
	}
	
	// 회원 삭제
	@Transactional
	public void deleteMember(String id) {
		Member member = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
		
		List<Board> boards = boardRepository.findById_Id(id);
		for (Board board : boards) {
			// 게시글에 포함된 댓글 삭제
			boardService.deleteBoard(board.getBno());
		}
		
		boardRepository.deleteAllById_Id(member.getId());
		boardReplyRepository.deleteAllById_Id(member.getId());
		heartRepository.deleteAllById_Id(member.getId());
		
		memberRepository.deleteById(id);
	}

	// 회원정보 수정
	public void editMemberInfo(String id, EditMemberInfo request) {
		Member member = memberRepository.findById(id)
				.orElseThrow(()->new RuntimeException("존재하지 않는 id입니다."));
		member.changeMemberDetail(request.getEmail(), request.getName(), request.getPhoneNum());
		memberRepository.save(member);
	}
	
	// 기존 비밀번호 확인
	public boolean checkPasswordMatch(String id, String currentPassword) {
	    Member member = memberRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("존재하지 않는 id입니다."));
	    return bCryptPasswordEncoder.matches(currentPassword, member.getPassword());
	}

	// 비밀번호 변경
	public void editMemberPw(String id, EditMemberPassword request) {
		Member member = memberRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("존재하지 않는 id입니다."));
		String newPw = bCryptPasswordEncoder.encode(request.getPassword());
		member.changeMemberPassword(newPw);
		memberRepository.save(member);
	}

	// pw 찾기 - 임시 비밀번호로 변경
	public void changeTempPw(String id, String email, String tempPw) {
		Member member = memberRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("존재하지 않는 id입니다."));
		if(!email.equals(member.getEmail())) {
			throw new RuntimeException("이메일이 일치하지 않습니다.");
		}
		// 비밀번호 생성 및 암호화
		String encodedTempPw = bCryptPasswordEncoder.encode(tempPw);
		// 임시 비밀번호로 변경
		member.changeMemberPassword(encodedTempPw);
		memberRepository.save(member);
	}
}
