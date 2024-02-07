package com.study.springboot.service;

import java.time.Duration;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.study.springboot.config.auth.MemberProfile;
import com.study.springboot.config.auth.OAuthAttributes;
import com.study.springboot.config.jwt.TokenProvider;
import com.study.springboot.entity.Member;
import com.study.springboot.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OAuthService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
	private final MemberRepository memberRepository;
	private final TokenProvider tokenProvider;
	
	public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
		OAuth2UserService delegate = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = delegate.loadUser(request);
		
		String registrationId = request.getClientRegistration().getRegistrationId();
		
		String userNameAttributeName = request.getClientRegistration()
											.getProviderDetails()
											.getUserInfoEndpoint()
											.getUserNameAttributeName();
		
		Map<String, Object> attributes = oAuth2User.getAttributes();
		String userId = attributes.get(userNameAttributeName).toString();
		
		MemberProfile memberProfile = OAuthAttributes.extract(registrationId, attributes);
		memberProfile.setAuthProvider(registrationId);
		memberProfile.setId(userId); 
		Member member = saveOrUpdate(memberProfile);
		
		Map<String, Object> customAttribute = customAttribute(attributes, userNameAttributeName, memberProfile, registrationId);
		// 멤버를 저장 또는 업데이트 한 후
		String token = tokenProvider.generateToken(member, Duration.ofMinutes(30)); // 필요에 따라 만료 시간 조정
		customAttribute.put("token", token);
		
		System.out.println("OAuth2User: " + oAuth2User);
		System.out.println("Custom Attributes: " + customAttribute);
		
		return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority("USER")), customAttribute, userNameAttributeName);
	}

	private Map customAttribute(Map attributes, String userNameAttributeName, MemberProfile memberProfile, String registrationId) {
		Map<String, Object> customAttribute = new LinkedHashMap<>();
		customAttribute.put(userNameAttributeName, attributes.get(userNameAttributeName));
		customAttribute.put("provider", registrationId);
		customAttribute.put("name", memberProfile.getName());
		customAttribute.put("email", memberProfile.getEmail());
		return customAttribute;
	}

	private Member saveOrUpdate(MemberProfile memberProfile) {
		Member member = memberRepository.findByEmailAndAuthProvider(memberProfile.getEmail(), memberProfile.getAuthProvider())
						.map(m->m.update(memberProfile.getName(), memberProfile.getEmail()))
						.orElse(memberProfile.toMember());
		return memberRepository.save(member);
	}
}
