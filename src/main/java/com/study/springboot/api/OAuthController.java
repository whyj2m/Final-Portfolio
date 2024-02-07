package com.study.springboot.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.springboot.service.OAuthService;

@RestController
@RequestMapping("/oauth")
public class OAuthController {
	@Autowired
	private OAuthService oAuthService;
	
	@GetMapping("loginInfo")
	public String oauthLoginInfo(Authentication authentication) {
		if (authentication == null || authentication.getPrincipal() == null) {
            // authentication이 null이거나 principal이 null인 경우 처리
            return "Authentication information not available.";
        }
		OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
		Map<String, Object> attributes = oAuth2User.getAttributes();
		return "redirect:/";
	}

}
