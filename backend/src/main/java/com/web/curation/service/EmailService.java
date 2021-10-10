package com.web.curation.service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.web.curation.utils.MailUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {
	private JavaMailSender javaMailSender;

	@Autowired
	private JavaMailSenderImpl mailSender;

	// 인증키 생성
	private String getKey() {
		return getAuthCode();
	}

	// 인증코드 난수 발생
	private String getAuthCode() {
		Random random = new Random();
		StringBuffer buffer = new StringBuffer();
		int num = 0;

		while (buffer.length() < 10) {
			num = random.nextInt(10);
			buffer.append(num);
		}

		return buffer.toString();
	}

	// 인증메일 보내기
	public String sendAuthMail(String email) {
		// 6자리 난수 인증번호 생성
		String user_authKey = getKey();

		// 인증메일 보내기
		try {
			MailUtils sendMail = new MailUtils(mailSender);
			sendMail.setSubject("CAMEO: 회원 가입 인증을 부탁드립니다");
			sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>").append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
					.append("<a href='http://localhost:8080/cameo/user/account/signUpConfirm?user_email=").append(email)
					.append("&user_authKey=").append(user_authKey).append("' target='_blenk'>이메일 인증 확인</a>").toString());
			sendMail.setFrom("cameo4mail@gmail.com", "관리자");
			sendMail.setTo(email);
			sendMail.send();

		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return user_authKey;
	}
}