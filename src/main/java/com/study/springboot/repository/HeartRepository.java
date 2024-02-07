package com.study.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.study.springboot.entity.Heart;

import jakarta.transaction.Transactional;


@Repository
public interface HeartRepository extends JpaRepository<Heart, Long> {
    List<Heart> findById_Id(String id);
    boolean existsById_IdAndPlaceNo(String memberId, Long placeNo);
    
    Optional<Heart> findById_IdAndPlaceNo(String id, Long placeNo);

    /**
     * @author bhy98 백혜윤
     * 작성자 id로 좋아요 삭제
     */
    @Modifying
    @Transactional
    void deleteAllById_Id(String memberId);
}

