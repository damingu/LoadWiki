package com.web.blog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.blog.model.dto.Comment;
import com.web.blog.model.dto.Posting;
import com.web.blog.model.service.FreeBoardService;
import com.web.blog.model.service.LoginService;

@RestController
@RequestMapping("/freeboard")
public class FreeBoardController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	private static final String FAIL = "fail";
	private static final String SUCCESS = "success";
	
	@Autowired
	FreeBoardService fBoardServ;
	
	@Autowired
	LoginService loginServ;
	
	@GetMapping(value = {
			"/list/{classifier}/{selector}/{word}/{page}",
			"/list/{classifier}/{selector}/{word}/{page}/{tag}",
		})
	public Object getList(@PathVariable String classifier, 
			@PathVariable String selector, 
			@PathVariable String word, 
			@PathVariable String page,
			@PathVariable(required = false) String tag) {
		logger.trace("getList");
		try {
			Map<String, Object> result;
			if(word == " ") word = "";
			if(selector.equals("none")) {
				if(tag == null) result = (Map<String, Object>) fBoardServ.getPostingListAll(page, classifier);
				else result = (Map<String, Object>) fBoardServ.getPostingListAll(page, classifier, tag);
			}
			else if(selector.equals("name")) {
				if(tag == null) result = (Map<String, Object>) fBoardServ.getPostingListByName(page, classifier, word);
				else result = (Map<String, Object>) fBoardServ.getPostingListByName(page, classifier, word, tag);
			}
			else if(selector.equals("title")){
				if(tag == null) result = (Map<String, Object>) fBoardServ.getPostingListByTitle(page, classifier, word);
					else result = (Map<String, Object>) fBoardServ.getPostingListByTitle(page, classifier, word, tag);
			}
			else if(selector.equals("content")){
				if(tag == null) result = (Map<String, Object>) fBoardServ.getPostingListByContent(page, classifier, word);
				else result = (Map<String, Object>) fBoardServ.getPostingListByContent(page, classifier, word, tag);
			} else {
				throw new RuntimeException("wrong select tag");
			}
			result.put("msg", SUCCESS);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch(Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Map<String, Object>>(new HashMap<String, Object>(){{
				put("errorMsg", e.getMessage());
				put("msg", FAIL);
			}}, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/posting/{pid}")
	public Object getPosting(@PathVariable String pid) {
		try {
			Map<String, Object> result;
			result = (Map<String, Object>) fBoardServ.getPosting(pid);
			result.put("msg", SUCCESS);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch(Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Map<String, Object>>(new HashMap<String, Object>(){{
				put("errorMsg", e.getMessage());
				put("msg", FAIL);
			}}, HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/posting")
	public Object registPosting(@RequestBody Map<String, Object> map, HttpServletRequest request) {
		try {
			Map<String, Object> result;
			String uid = (String) loginServ.getData(request.getHeader("auth-token")).get("uid");
			result = (Map<String, Object>) fBoardServ.registPosting((Posting)map.get("posting"), uid);
			result.put("msg", SUCCESS);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch(Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Map<String, Object>>(new HashMap<String, Object>(){{
				put("errorMsg", e.getMessage());
				put("msg", FAIL);
			}}, HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("/posting")
	public Object editPosting(@RequestBody Map<String, Object> map, HttpServletRequest request) {
		try {
			Map<String, Object> result;
			String uid = (String) loginServ.getData(request.getHeader("auth-token")).get("uid");
			result = (Map<String, Object>) fBoardServ.editPosting((Posting)map.get("posting"), uid);
			result.put("msg", SUCCESS);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch(Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Map<String, Object>>(new HashMap<String, Object>(){{
				put("errorMsg", e.getMessage());
				put("msg", FAIL);
			}}, HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping("/posting/{pid}")
	public Object deletePosting(@PathVariable String pid, HttpServletRequest request) {
		try {
			Map<String, Object> result;
			String uid = (String) loginServ.getData(request.getHeader("auth-token")).get("uid");
			result = (Map<String, Object>) fBoardServ.deletePosting(pid, uid);
			result.put("msg", SUCCESS);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch(Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Map<String, Object>>(new HashMap<String, Object>(){{
				put("errorMsg", e.getMessage());
				put("msg", FAIL);
			}}, HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/comment")
	public Object registComment(@RequestBody Map<String, Object> map, HttpServletRequest request) {
		try {
			Map<String, Object> result;
			String uid = (String) loginServ.getData(request.getHeader("auth-token")).get("uid");
			result = (Map<String, Object>) fBoardServ.registComment((Comment)map.get("posting"), uid);
			
			result.put("msg", SUCCESS);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch(Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Map<String, Object>>(new HashMap<String, Object>(){{
				put("errorMsg", e.getMessage());
				put("msg", FAIL);
			}}, HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("/comment")
	public Object editComment(@RequestBody Map<String, Object> map, HttpServletRequest request) {
		try {
			Map<String, Object> result;
			String uid = (String) loginServ.getData(request.getHeader("auth-token")).get("uid");
			result = (Map<String, Object>) fBoardServ.editComment((Comment)map.get("posting"), uid);
			result.put("msg", SUCCESS);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch(Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Map<String, Object>>(new HashMap<String, Object>(){{
				put("errorMsg", e.getMessage());
				put("msg", FAIL);
			}}, HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping("/comment/{cid}")
	public Object deleteComment(@PathVariable String cid, HttpServletRequest request) {
		try {
			Map<String, Object> result;
			String uid = (String) loginServ.getData(request.getHeader("auth-token")).get("uid");
			result = (Map<String, Object>) fBoardServ.deleteComment(cid, uid);
			result.put("msg", SUCCESS);
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
