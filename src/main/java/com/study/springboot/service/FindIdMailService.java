package com.study.springboot.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.study.springboot.entity.Member;
import com.study.springboot.repository.MemberRepository;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindIdMailService {
	private final JavaMailSender javaMailSender;
	private final MemberRepository memberRepository;
	private static final String sender = "localkorea20s@gmail.com";
	private String id;
	
	public String findId(String email, String name) {
		Member member = memberRepository.findByEmailAndName(email, name);
		if (member != null) {
            id = member.getId();
        } else {
            // 해당 이메일과 이름으로 회원을 찾을 수 없는 경우 예외 처리
        	throw new RuntimeException("해당 이메일과 이름으로 회원을 찾을 수 없습니다.");
        }
		return id;
	}
	
	public MimeMessage createIdMail(String name, String email, String id) {
		MimeMessage message = javaMailSender.createMimeMessage();
		
		try {
			message.setFrom(sender);
			message.setRecipients(MimeMessage.RecipientType.TO, email);
			message.setSubject("새하마노 방방곡곡 회원가입 아이디 발송");
			String body = "";
			body += "<h2>새하마노 방방곡곡</h2>";
			body += "<div style='margin:0 auto; border:1px solid #d9d9d9;'>";
			body += "<h1 style='text-align:center; font-weight:bold; margin: 10px'>회원가입 아이디 찾기</h1>";
			body += "<div style='margin:0 auto; background-color: #d9d9d9; width:80%; text-align:center; padding: 20px'>";
			body += "<p>" +name+ "님이 가입하신 아이디는 [ "+id+" ] 입니다.</p>";
			body += "<p>다음 아이디로 로그인하시면 됩니다.</p>";
			body += "</div>";
			body += "<h2 style='text-align:center; font-weight:bold'>"+name+"님의 아이디 : "+id+"</h2>";
			body += "</div>";
			message.setText(body, "utf-8", "html");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return message;
	}
	
	public void sendMail(String name, String email, String id) {
		MimeMessage message = createIdMail(name, email, id);
		javaMailSender.send(message);
	}
	
	public String getSavedId() {
		return id;
	}
}
