package com.study.springboot.entity;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Member")
@Entity
public class Member implements UserDetails{

	@Id
    private String id;
    private String email;
    private String password;
    private String name;
    private String phoneNum;
    private ZonedDateTime signUpAt; // 회원가입 일시 
	private ZonedDateTime updatedAt; // 회원정보 수정 일시
	
	private String role;
	private String authProvider;
	
	public void changeMemberDetail(String email, String name, String phoneNum) {
		this.email = email;
		this.name = name;
		this.phoneNum = phoneNum;
		this.updatedAt = ZonedDateTime.now();
	}
	
	public Member update(String name, String email) {
        this.name = name;
        this.email = email;
        return this;
    }
	
	public void changeMemberPassword(String password) {
		this.password = password;
		this.updatedAt = ZonedDateTime.now();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("user"));
	}

	@Override
	public String getUsername() {
		return id;
	}
	
	@Override
    public String getPassword() {
        return password;
    }

	@Override // 계정 만료 여부 반환 (true : 만료X)
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override // 계정 잠금 여부 반환 (true : 잠금X)
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override // 패스워드 만료 여부 반환 (true : 만료X)
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override // 계정 사용 가능 여부 반환 (true : 사용가능)
	public boolean isEnabled() {
		return true;
	}
	
	/**
     * @author 윤주민
     * Heart Post 시에 id 조회
     */
    public Member(String id) {
        this.id = id;
    }
}
