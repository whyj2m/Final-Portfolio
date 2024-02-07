package com.study.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.study.springboot.entity.category.BoardCategory;
import com.study.springboot.entity.category.LocationCategory;

import jakarta.transaction.Transactional;

import com.study.springboot.entity.Board;
import com.study.springboot.entity.Member;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{

    public List<Board> findByTitleContaining(String keyword);

    // 카테고리별 조회
    List<Board> findByCno(BoardCategory category);

    // 카테고리 숫자를 카테고리명으로 조회
     public List<Board> findByLocno(LocationCategory locationCategory);

    // bno로 단일 조회
      Optional<Board> findByBno(Long bno);

      /**
       * @author bhy98 백혜윤
       * 작성자 id로 게시글 조회
       */
      public List<Board> findByCnoAndId(BoardCategory category, Member member);
      
      /**
       * @author bhy98 백혜윤
       * 작성자 id로 게시글 삭제
       */
      @Modifying
      @Transactional
      void deleteAllById_Id(String memberId);
      
      /**
       * @author bhy98 백혜윤
       * 작성자 id로 게시글 리스트 조회
       */
      public List<Board> findById_Id(String memberId);
}