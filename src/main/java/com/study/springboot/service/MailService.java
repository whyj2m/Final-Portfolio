package com.study.springboot.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class MailService {
	private final JavaMailSender javaMailSender;
	private static final String sender = "localkorea20s@gmail.com";
	private int code;
	
	public int createCode() {
		code = (int)(Math.random() * (9000)) + 1000;
		return code;
	}
	
	public MimeMessage createMail(String email) {
		createCode();
		MimeMessage message = javaMailSender.createMimeMessage();
		
		try {
			message.setFrom(sender);
			message.setRecipients(MimeMessage.RecipientType.TO, email);
			message.setSubject("새하마노 방방곡곡 이메일 인증 요청");
			String body = "";
			body += "<h2>새하마노 방방곡곡</h2>";
			body += "<div style='margin:0 auto; border:1px solid #d9d9d9;'>";
			body += "<h1 style='text-align:center; font-weight:bold; margin: 10px'>인증번호 발송 메일</h1>";
			body += "<div style='margin:0 auto; background-color: #d9d9d9; width:80%; text-align:center; padding: 20px'>";
			body += "<p>본 이메일 인증은 새하마노 방방곡곡 회원가입 및 회원정보 변경을 위한 필수 사항입니다.</p>";
			body += "<p>아래 인증번호를 해당 입력란에 정확히 기입해주시길 바랍니다.</p>";
			body += "</div>";
			body += "<h2 style='text-align:center; font-weight:bold'>"+code+"</h2>";
			body += "</div>";
			message.setText(body, "utf-8", "html");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return message;
	}
	
	public int sendMail(String email) {
		MimeMessage message = createMail(email);
		javaMailSender.send(message);
		log.info(email);
		return code;
	}

	public int getSavedCode() {
		return code;
	}
}
