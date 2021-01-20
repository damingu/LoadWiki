 package com.web.blog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.blog.model.dto.User;
import com.web.blog.model.service.LoginServiceImpl;
import com.web.blog.model.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	private static final String FAIL = "fail";
	private static final String SUCCESS = "success";
	
	@Autowired
	UserService userServ;
	
	@Autowired
	LoginServiceImpl loginServ;
	
	@PostMapping("/login")
	public Object login(@RequestBody User user, HttpServletResponse response) {
		logger.trace("login");
		try {
			logger.info(user.toString());
			Map<String, Object> result = (Map<String, Object>) userServ.login(user);
			response.setHeader("authorizationToken", (String)result.get("token"));
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch(Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Map<String, Object>>(new HashMap<String, Object>(){{
				put("errorMsg", e.getMessage());
				put("msg", FAIL);
			}}, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/info")
	public Object getInfo(HttpServletRequest request) {
		logger.trace("getInfo");
		try {
			String email = (String) loginServ.getData(request.getHeader("auth-token")).get("email");
			logger.info(email);
			Map<String, Object> result = (Map<String, Object>) userServ.getInfo(email);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch(Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Map<String, Object>>(new HashMap<String, Object>(){{
				put("errorMsg", e.getMessage());
				put("msg", FAIL);
			}}, HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/join")
	public Object join(@RequestBody User user) {
		logger.trace("join");
		try {
			logger.info(user.toString());
			Map<String, Object> result = (Map<String, Object>) userServ.join(user);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch(Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Map<String, Object>>(new HashMap<String, Object>(){{
				put("errorMsg", e.getMessage());
				put("msg", FAIL);
			}}, HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("/modify")
	public Object modify(@RequestBody User user, HttpServletRequest request) {
		logger.trace("modify");
		try {
			String email = (String) loginServ.getData(request.getHeader("auth-token")).get("email");
			user.setEmail(email);
			logger.info(user.toString());
			Map<String, Object> result = (Map<String, Object>) userServ.modify(user);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch(Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Map<String, Object>>(new HashMap<String, Object>(){{
				put("errorMsg", e.getMessage());
				put("msg", FAIL);
			}}, HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping("/withdraw")
	public Object withdraw(HttpServletRequest request) {
		logger.trace("withdraw");
		try {
			String email = (String) loginServ.getData(request.getHeader("auth-token")).get("email");
			logger.info(email);
			Map<String, Object> result = (Map<String, Object>)userServ.withdraw(email);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch(Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Map<String, Object>>(new HashMap<String, Object>(){{
				put("errorMsg", e.getMessage());
				put("msg", FAIL);
			}}, HttpStatus.NO_CONTENT);
		}
	}
	
}
