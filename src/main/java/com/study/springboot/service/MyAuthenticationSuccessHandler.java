package com.study.springboot.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	private static final String REDIRECT_URI = "http://localhost:3000/login/callback";

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		if (authentication == null) {
			// 인증 정보가 없는 경우 처리
			response.sendRedirect(REDIRECT_URI);
	        return;
		}

		if (!(authentication.getPrincipal() instanceof OAuth2User)) {
	    	// OAuth2User가 아닌 경우 처리
	    	response.sendRedirect(REDIRECT_URI);
	        return;
	    }
		OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
		System.out.println("SuccessHandler oAuth2User: " + oAuth2User);
		String accessToken = oAuth2User.getAttribute("token");
		System.out.println("Access Token: " + accessToken);
		
		String redirectUri = UriComponentsBuilder.fromUriString(REDIRECT_URI)
				.queryParam("accessToken", accessToken)
				.build()
				.encode(StandardCharsets.UTF_8)
				.toUriString();
		
		response.sendRedirect(redirectUri);
		
//		response.sendRedirect(UriComponentsBuilder.fromUriString("http://localhost:3000/login/callback")
//				.queryParam("accessToken", accessToken)
//				.build()
//				.encode(StandardCharsets.UTF_8)
//				.toUriString());
		
		
//		ResponseEntity<String> entity = ResponseEntity.ok("Authentication successful");
//
//        // Write the response entity body to the response writer
//        response.getWriter().write(entity.getBody());
	}
}
