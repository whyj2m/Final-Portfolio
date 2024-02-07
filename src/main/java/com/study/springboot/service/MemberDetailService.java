package com.study.springboot.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.study.springboot.entity.Member;
import com.study.springboot.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberDetailService implements UserDetailsService {
	private final MemberRepository memberRepository;

	@Override
	public Member loadUserByUsername(String id) throws UsernameNotFoundException {
		return memberRepository.findById(id).orElseThrow(()->new IllegalArgumentException(id));
	}


	
}
