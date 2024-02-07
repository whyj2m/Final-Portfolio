package com.study.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.springboot.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>{
	Member findByEmail(String email);
	boolean existsById(String id);
	Optional<Member> findById(String id); // id로 사용자 정보 가져옴
	Optional<Member> findByEmailAndAuthProvider(String email, String authProvider);
	Member findByEmailAndName(String email, String name);
	void deleteById(String id);
}
