package com.web.blog.model.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Autowired
	public JavaMailSender javaMailSender;
	
	@Override
	public Object email(String email) throws MessagingException {
		Random rand = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 6; i++)
			sb.append(rand.nextInt(9));

		byte[] tmp = sb.toString().getBytes();
		String oriText = new String(tmp);
		logger.info("original text : " + new String(tmp));
		for(int i = 0; i < tmp.length; i++) 
			tmp[i]^=(byte)1;
		logger.info("encrypt text : " + new String(tmp));

		try {
			String encryptText = new String(tmp);
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, false);
			helper.setFrom("team_code_C106");
			helper.setTo(email);
			helper.setSubject("certification request");
			helper.setText("certification number : " + oriText);
			javaMailSender.send(message);

			return new HashMap<String, Object>(){{
				put("code", encryptText);
				put("email", email);
			}};
		} catch (Exception e) {
			throw new RuntimeException("email error");
		}
	}

	@Override
	public Object authorization(Map<String, Object> map) {
		String email = (String) map.get("email");
		String code = (String) map.get("code");
		String inputCode = (String) map.get("inputCode");
		
		byte[] tmp = code.getBytes();
		for(int i = 0; i < tmp.length; i++) 
			tmp[i]^=(byte)1;
		String decryptText = new String(tmp);
		
		logger.info("email : " + email);
		logger.info("user input : " + inputCode);
		logger.info("encrypt text : " + code);
		logger.info("decrypt text : " + decryptText);
		if(decryptText.equals(inputCode)) {
			return new HashMap<String, Object>(){{
				put("msg", "success");
			}};	
		} else {
			return new HashMap<String, Object>(){{
				put("msg", "fail");
			}};	
		}
	}
	
}
