package com.web.blog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	
	private static final String FAIL = "fail";
	private static final String SUCCESS = "success";
	
	@Autowired
	UserService userServ;
	
	@Autowired
	LoginServiceImpl loginServ;
	
	@PostMapping("/login")
	public Object login(@RequestBody User user, HttpServletResponse response) {
		System.out.println("[-- login --");
		System.out.println(user);
		try {
			User loginUser = userServ.login(user);
			String token = loginServ.generate(user);
			System.out.println(token);
			response.setHeader("authorizationToken", token);
			Map<String, Object> result = new HashMap<String, Object>(){{
				put("authorizationToken", token);
				put("email", loginUser.getEmail());
				put("name", loginUser.getName());
				put("createDate", loginUser.getCreateDate());
			}};
			System.out.println("-- login --]");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("-- login --] fail");
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/info")
	public Object getInfo(HttpServletRequest request) {
		System.out.println("[-- getInfo --");
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			String email = (String) loginServ.getData(request.getHeader("auth-token")).get("email");
			System.out.println(email);
			result.put("user", userServ.getInfo(email));
			System.out.println(result.get("user"));
			System.out.println("-- getInfo --]");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("-- getInfo --] fail");
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/join")
	public Object join(@RequestBody User user) {
		System.out.println("[-- join --");
		System.out.println(user);
		try {
			userServ.join(user);
			System.out.println("-- join --]");
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("-- join --] fail");
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("/modify")
	public Object modify(@RequestBody User user) {
		System.out.println("[-- modify --");
		System.out.println(user);
		try {
			userServ.modify(user);
			System.out.println("-- modify --]");
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("-- modify --] fail");
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping("/withdraw")
	public Object withdraw(HttpServletRequest request) {
		System.out.println("[-- withdraw --");
		try {
			Map result = new HashMap<String, Object>();
			String email = (String) loginServ.getData(request.getHeader("auth-token")).get("email");
			System.out.println(email);
			userServ.withdraw(email);
			System.out.println("-- withdraw --]");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("-- withdraw --] fail");
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	
}
