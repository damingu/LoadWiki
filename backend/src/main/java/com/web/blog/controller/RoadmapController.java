package com.web.blog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.blog.model.dto.Roadmap;
import com.web.blog.model.service.LoginServiceImpl;
import com.web.blog.model.service.RoadmapService;

@RestController
@RequestMapping("/roadmap")
public class RoadmapController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	private static final String FAIL = "fail";
	private static final String SUCCESS = "success";

	@Autowired
	RoadmapService roadmapservice;

	@Autowired
	LoginServiceImpl loginServ;

	// 로드맵 리스트 가져오기 uid, rmorder,
	// 로드맵 하나 가져오기 rmid
	// 로드맵 최초 생성
	// 로드맵 업데이트 생성
	// 로드맵 삭제

//	@GetMapping("/roadmapuid")
//	public Object getroadmapuid() {
//		logger.trace("getInfo");
//		String email = (String) loginServ.getData(request.getHeader("auth-token")).get("email");
//		return null;
//	}
//	공개 비공개 문제
	@GetMapping("/list/{uid}/{page}")
	public Object listRoadmap(@PathVariable String uid, @PathVariable String page, HttpServletRequest request) {
		logger.trace("listRoadmap");
		Map<String, Object> result = new HashMap<>();
		try {

			logger.info(uid);
			String nowuid = (String) loginServ.getData(request.getHeader("auth-token")).get("uid");
			result = (Map<String, Object>) roadmapservice.getRoadmapListByUid(page, nowuid, uid);
			result.put("msg", SUCCESS);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch (NumberFormatException e) {
			logger.error("input data type error");
			result.put("msg", FAIL);
			result.put("errorMsg", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.put("errorMsg", e.getMessage());
			result.put("msg", FAIL);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.NO_CONTENT);
		}
	}
	@GetMapping("/log/{uid}/{page}/{rmorder}")
	public Object listLog(@PathVariable String uid, @PathVariable String page,@PathVariable String rmorder, HttpServletRequest request) {
		logger.trace("listLog");
		Map<String, Object> result = new HashMap<>();
		try {

			logger.info(uid);
			String nowuid = (String) loginServ.getData(request.getHeader("auth-token")).get("uid");
			result = (Map<String, Object>) roadmapservice.getRoadmapListByRmorder(page, nowuid, uid, rmorder);
			result.put("msg", SUCCESS);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch (NumberFormatException e) {
			logger.error("input data type error");
			result.put("msg", FAIL);
			result.put("errorMsg", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.put("errorMsg", e.getMessage());
			result.put("msg", FAIL);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/get/{rmid}/")
	public Object getRoadmap(@PathVariable String rmid , HttpServletRequest request) {
		logger.trace("getRoadmap");
		Map<String, Object> result = new HashMap<>();
		try {

			logger.info(rmid);
			String nowuid = (String) loginServ.getData(request.getHeader("auth-token")).get("uid");
			result = (Map<String, Object>) roadmapservice.getRoadmap(nowuid, rmid);
			result.put("msg", SUCCESS);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch (NumberFormatException e) {
			logger.error("input data type error");
			result.put("msg", FAIL);
			result.put("errorMsg", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.put("errorMsg", e.getMessage());
			result.put("msg", FAIL);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.NO_CONTENT);
		}
	}
			
	@PostMapping("/create")
	public Object createRoadmap(@RequestBody Roadmap roadmap, HttpServletRequest request) {
		logger.trace("createRoadmap");
		Map<String, Object> result = new HashMap<>();

		try {
			logger.info(roadmap.toString());
			String nowuid = (String) loginServ.getData(request.getHeader("auth-token")).get("uid");
			result = (Map<String, Object>) roadmapservice.create(nowuid,roadmap);
			result.put("msg", SUCCESS);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch (NumberFormatException e) {
			logger.error("input data type error");
			result.put("msg", FAIL);
			result.put("errorMsg", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.put("errorMsg", e.getMessage());
			result.put("msg", FAIL);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/update")
	public Object updateRoadmap(@RequestBody Roadmap roadmap, HttpServletRequest request) {
		logger.trace("update");
		Map<String, Object> result = new HashMap<>();
		
		try {
			logger.info(roadmap.toString());
			String nowuid = (String) loginServ.getData(request.getHeader("auth-token")).get("uid");
			result = (Map<String, Object>) roadmapservice.modify(nowuid,roadmap);
			
			result.put("msg", SUCCESS);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch (NumberFormatException e) {
			logger.error("input data type error");
			result.put("msg", FAIL);
			result.put("errorMsg", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.IM_USED);
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.put("errorMsg", e.getMessage());
			result.put("msg", FAIL);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/delete/{uid}/{rmorder}")
	public Object deleteRoadmap(@PathVariable String uid,@PathVariable String rmorder, HttpServletRequest request) {
		logger.trace("delete");
		Map<String, Object> result = new HashMap<>();
		
		try {
			logger.info(uid + " " + rmorder);
			String nowuid = (String) loginServ.getData(request.getHeader("auth-token")).get("uid");
			result = (Map<String, Object>) roadmapservice.deleteRoadmap(nowuid,uid,rmorder);
			result.put("msg", SUCCESS);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch (NumberFormatException e) {
			logger.error("input data type error");
			result.put("msg", FAIL);
			result.put("errorMsg", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.IM_USED);
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.put("errorMsg", e.getMessage());
			result.put("msg", FAIL);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.NOT_FOUND);
		}
	}

}