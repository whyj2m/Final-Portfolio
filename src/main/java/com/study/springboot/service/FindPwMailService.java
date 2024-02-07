package com.study.springboot.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindPwMailService {
	private final JavaMailSender javaMailSender;
	private static final String sender = "localkorea20s@gmail.com";
	private String tempPw;
	
	public String createTempPw() {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        tempPw = "";

        int idx = 0;
        for (int i = 0; i < 8; i++) {
            idx = (int) (charSet.length * Math.random());
            tempPw += charSet[idx];
        }
        return tempPw;
	}
	
	public MimeMessage createTempPwMail(String id, String email, String tempPw) {
		MimeMessage message = javaMailSender.createMimeMessage();
		
		try {
			message.setFrom(sender);
			message.setRecipients(MimeMessage.RecipientType.TO, email);
			message.setSubject("새하마노 방방곡곡 임시 비밀번호 발송");
			String body = "";
			body += "<h2>새하마노 방방곡곡</h2>";
			body += "<div style='margin:0 auto; border:1px solid #d9d9d9;'>";
			body += "<h1 style='text-align:center; font-weight:bold; margin: 10px'>비밀번호 찾기</h1>";
			body += "<div style='margin:0 auto; background-color: #d9d9d9; width:80%; text-align:center; padding: 20px'>";
			body += "<p>" +id+ "님의 임시 비밀번호는 [ "+tempPw+" ] 입니다.</p>";
			body += "<p>해당 비밀번호로 로그인 후에 반드시 비밀번호를 변경해주시기 바랍니다.</p>";
			body += "</div>";
			body += "<h2 style='text-align:center; font-weight:bold'>"+id+"님의 임시 비밀번호 : "+tempPw+"</h2>";
			body += "</div>";
			message.setText(body, "utf-8", "html");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return message;
	}
	
	public String sendMail(String id, String email) {
		tempPw = createTempPw();
		MimeMessage message = createTempPwMail(id, email, tempPw);
		javaMailSender.send(message);
		return tempPw;
	}

	public String getSavedTempPw() {
		return tempPw;
	}
}
