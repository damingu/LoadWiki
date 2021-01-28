package com.web.blog.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.blog.model.dto.User;
import com.web.blog.model.service.LoginServiceImpl;
import com.web.blog.model.service.UserService;

import io.jsonwebtoken.lang.Assert;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
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
		System.out.println("로그인" + user);
		try {
			logger.info(user.toString());
			Map<String, Object> result = (Map<String, Object>) userServ.login(user);
			response.setHeader("authorizationToken", (String) result.get("token"));
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Map<String, Object>>(new HashMap<String, Object>() {
				{
					put("errorMsg", e.getMessage());
					put("msg", FAIL);
				}
			}, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/info")
	public Object getInfo(HttpServletRequest request) {
		logger.trace("getInfo");
		try {
			String email = (String) loginServ.getData(request.getHeader("auth-token")).get("email");
			logger.info(email);
			Map<String, Object> result = (Map<String, Object>) userServ.getInfo(email);
			result.put("msg", SUCCESS);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Map<String, Object>>(new HashMap<String, Object>() {
				{
					put("errorMsg", e.getMessage());
					put("msg", FAIL);
				}
			}, HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/join")
	public Object join(@RequestBody User user) {
		logger.trace("join");
		try {
			logger.info(user.toString());
			Map<String, Object> result = (Map<String, Object>) userServ.join(user);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Map<String, Object>>(new HashMap<String, Object>() {
				{
					put("errorMsg", e.getMessage());
					put("msg", FAIL);
				}
			}, HttpStatus.NO_CONTENT);
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
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Map<String, Object>>(new HashMap<String, Object>() {
				{
					put("errorMsg", e.getMessage());
					put("msg", FAIL);
				}
			}, HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("/withdraw")
	public Object withdraw(HttpServletRequest request) {
		logger.trace("withdraw");
		System.out.println(request.getHeader("auth-token"));
		try {
			String email = (String) loginServ.getData(request.getHeader("auth-token")).get("email");
			logger.info(email);
			Map<String, Object> result = (Map<String, Object>) userServ.withdraw(email);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Map<String, Object>>(new HashMap<String, Object>() {
				{
					put("errorMsg", e.getMessage());
					put("msg", FAIL);
				}
			}, HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/pic")
	public Object getImg(@RequestPart(value = "file", required = true) MultipartFile file, HttpServletRequest request)
			throws Exception {
		System.out.println("in");
		System.out.println(file);
		System.out.println(file.toString());

		int uid = (int) loginServ.getData(request.getHeader("auth-token")).get("uid");
		System.out.println(uid);
		// System.out.println(dto.toString());

		// parent directory를 찾는다.
//		Path directory = Paths.get(".\\src\\main\\java\\com\\web\\blog\\controller" + uid + "\\").toAbsolutePath()
//				.normalize();
		Path directory = Paths.get(".\\src\\main\\java\\com\\web\\blog\\controller\\").toAbsolutePath()
				.normalize();

		// directory 해당 경로까지 디렉토리를 모두 만든다.
		Files.createDirectories(directory);

		// 파일명을 바르게 수정한다.
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		// 파일명에 '..' 문자가 들어 있다면 오류를 발생하고 아니라면 진행(해킹및 오류방지)
		Assert.state(!fileName.contains(".."), "Name of file cannot contain '..'");
		// 파일을 저장할 경로를 Path 객체로 받는다.
		Path targetPath = directory.resolve(fileName).normalize();

		// 파일이 이미 존재하는지 확인하여 존재한다면 오류를 발생하고 없다면 저장한다.
		Assert.state(!Files.exists(targetPath), fileName + " File alerdy exists.");
		file.transferTo(targetPath);

		return "good";
	}
	
	@GetMapping("/get-text")
	public @ResponseBody String getText() {
	    return "Hello world";
	}
	
	@GetMapping(value = "/image")
	public @ResponseBody byte[] getImage() throws IOException {
		System.out.println("이미지 실행");
		
	    InputStream in = getClass()
//		  	      .getResourceAsStream("../../../../../../main/resources/statics/upload/1/1.jpg");
	    		.getResourceAsStream("1.jpg");
	    if(in == null) {
	    	System.out.println("여기들어와?");
	    	in = getClass()
	    		      .getResourceAsStream("default.png");
	    }
	    return IOUtils.toByteArray(in);
	}

//	@GetMapping(value = "image")
//	public Object userSearch() throws IOException {
//		Path file = Paths.get(".\\src\\main\\resources\\statics\\upload\\" + 1 + "\\").resolve("1.jpg");
//		Resource resource = new UrlResource(file.toUri());
//		if (resource.exists() || resource.isReadable()) {
//			System.out.println("파일 리소스 나오긴 함 @@@@@@@@@@@@@@@@@@");
//		}
//
//		InputStream imageStream = new FileInputStream(
//				".\\src\\main\\resources\\statics\\upload\\" + 1 + "\\" + "1.jpg");
//		System.out.println("File null? : " + imageStream);
//		byte[] imageByteArray = IOUtils.toByteArray(imageStream);
//		imageStream.close();
//
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put("image", imageByteArray);
//		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
//	}

	@GetMapping("/name/{uid}")
	public Object getName(@PathVariable String uid) {
		logger.trace("getName");
		try {
			Map<String, Object> result = (Map<String, Object>) userServ.getName(uid);
			result.put("msg", SUCCESS);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Map<String, Object>>(new HashMap<String, Object>() {
				{
					put("errorMsg", e.getMessage());
					put("msg", FAIL);
				}
			}, HttpStatus.NO_CONTENT);
		}

	}

}
