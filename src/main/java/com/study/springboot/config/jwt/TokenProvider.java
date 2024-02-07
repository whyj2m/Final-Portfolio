package com.study.springboot.config.jwt;

import java.time.Duration;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.study.springboot.entity.Member;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TokenProvider {
    private final JwtProperties jwtProperties;
    
    public String generateToken(Member member, Duration expiredAt) {
        Date now = new Date();
        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), member);
    }

    //토큰 생성
    private String makeToken(Date expiry, Member member) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .setSubject(member.getEmail())
                .claim("email", member.getEmail())
                .claim("id", member.getUsername())
                .claim("role", member.getRole())
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    //토큰 유효성검사
    public String validateAndGetUserId(String token) throws JwtException {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey()) // 비밀값으로 복호
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject();
        } catch (JwtException e) {
            // 토큰이 유효하지 않은 경우 예외 처리
            throw e;
        }
    }


    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }
    
    public String createRefreshJwt(String id, String jwtSecret, Long refreshExpired) {
    	Claims claims = Jwts.claims();
    	claims.put("id", id);
    	
    	return Jwts.builder()
    			.setClaims(claims)
    			.setIssuedAt(new Date(System.currentTimeMillis()))
    			.setExpiration(new Date(System.currentTimeMillis()+refreshExpired))
    			.signWith(SignatureAlgorithm.HS256, jwtSecret)
    			.compact();
    }
    
//    public Date getExpired(String token, String jwtSecret) {
//    	
//    }
}
