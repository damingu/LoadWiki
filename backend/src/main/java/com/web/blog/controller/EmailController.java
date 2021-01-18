package com.web.blog.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

	@Autowired
	public JavaMailSender javaMailSender;
	
	final static Long WAITTIME = 3L * 1000 * 60;

	@GetMapping("/{email}")
	public Object email(@PathVariable String email) throws MessagingException {
		System.out.println("[-- email --");
		Random rand = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 6; i++)
			sb.append(rand.nextInt(9));

		byte[] tmp = sb.toString().getBytes();
		String oriText = new String(tmp);
		System.out.println("original text : " + new String(tmp));
		for(int i = 0; i < tmp.length; i++) 
			tmp[i]^=(byte)1;
		System.out.println("encrypt text : " + new String(tmp));
		
		String encryptText = new String(tmp);
		System.out.println(email + " : " + oriText);

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, false);
		helper.setFrom("team_code_C106");
		helper.setTo(email);
		helper.setSubject("certification request");
		helper.setText("certification number : " + oriText);
		javaMailSender.send(message);

		try {
			System.out.println("-- email --]");
			return new ResponseEntity<Map<String, Object>>(new HashMap<String, Object>(){{
				put("code", encryptText);
				put("email", email);
			}}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("email error");
			System.out.println("-- email --] fail");
			return new ResponseEntity<String>("error", HttpStatus.NO_CONTENT);
		}
	}

	
	@PostMapping("/")
	public Object authorization(@RequestBody Map<String, String> map) {
		System.out.println(map);
		System.out.println("[-- authorization --");
		String email = map.get("email");
		String code = map.get("code");
		String inputCode = map.get("inputCode");
		System.out.println("email : " + email);
		System.out.println("encrypt text : " + code);
		System.out.println("user input : " + inputCode);
		
		byte[] tmp = code.getBytes();
		for(int i = 0; i < tmp.length; i++) 
			tmp[i]^=(byte)1;
		
		String decryptText = new String(tmp);
		System.out.println("decrypt text : " + decryptText);
		if(decryptText.equals(inputCode)) {
			System.out.println("-- authorization --]");
			return new ResponseEntity<String>("success", HttpStatus.OK);	
		} else {
			System.out.println("-- authorization --] fail");
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
		}
	}

}