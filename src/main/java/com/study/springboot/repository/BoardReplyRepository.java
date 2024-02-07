package com.study.springboot.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.study.springboot.entity.Board;
import com.study.springboot.entity.BoardReply;
import com.study.springboot.entity.Member;

import jakarta.transaction.Transactional;

@Repository
public interface BoardReplyRepository extends JpaRepository<BoardReply, Long> {

    List<BoardReply> findByBoardBno(Long bno); // 메소드 이름 변경

	Optional<Board> findByRno(Long rno);
	 
    /**
     * @author bhy98 백혜윤
     * 작성자 id로 댓글 조회
     */
    List<BoardReply> findById(Member member);
    
    /**
     * @author bhy98 백혜윤
     * 작성자 id로 댓글 삭제
     */
    @Modifying
    @Transactional
    void deleteAllById_Id(String memberId);

	void deleteAllByBoardBno(Long bno);
}