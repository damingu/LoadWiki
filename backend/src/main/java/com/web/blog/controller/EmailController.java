package com.web.blog.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.blog.model.service.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@Autowired
	public JavaMailSender javaMailSender;
	
	@Autowired
	EmailService emailServ;
	
	final static Long WAITTIME = 3L * 1000 * 60;

	@GetMapping("/{email}")
	public Object email(@PathVariable String email) throws MessagingException {
		logger.trace("email");
		try {
			logger.info(email);
			Map<String, Object> result = (Map<String, Object>) emailServ.email(email);	
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch(Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Map<String, Object>>(new HashMap<String, Object>(){{
				put("errorMsg", e.getMessage());
				put("msg", "fail");
			}}, HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/")
	public Object authorization(@RequestBody Map<String, Object> map) {
		logger.trace("authorization");
		try {
			Map<String, Object> result = (Map<String, Object>) emailServ.authorization(map);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK) ;
		} catch(Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Map<String, Object>>(new HashMap<String, Object>(){{
				put("errorMsg", e.getMessage());
				put("msg", "fail");
			}}, HttpStatus.NO_CONTENT);
		}
	}

}