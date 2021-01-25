package com.web.blog.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.blog.model.dto.Posting;
import com.web.blog.model.service.FreeBoardService;

@RestController
@RequestMapping("/freeboard")
public class FreeBoardController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Autowired
	FreeBoardService fBoardServ;
	
	public Object getList(@RequestBody Map<String, Object> map) {
		logger.trace("getList");
		try {
			Map<String, Object> result = new HashMap();
			String page = (String) map.get("page");
			String tag = (String) map.get("tag");
			if(page == null) page = "1";
			if(map.get("name") != null) {
				String name = (String)map.get("name");
				if(tag == null)	fBoardServ.getPostingListByName(page, name);
				else fBoardServ.getPostingListByName(page, name, tag);
			}
			else if(map.get("title") != null){
				String title = (String)map.get("title");
				if(tag == null)	fBoardServ.getPostingListByTitle(page, title);
					else fBoardServ.getPostingListByTitle(page, title, tag);
			}
			else if(map.get("content") != null){
				String content = (String)map.get("content");
				if(tag == null)	fBoardServ.getPostingListByContent(page, content);
				else fBoardServ.getPostingListByContent(page, content, tag);
			}
			else {
				if(tag == null)	fBoardServ.getPostingListAll(page);
				else fBoardServ.getPostingListAll(page, tag);
			}
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch(Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Map<String, Object>>(new HashMap<String, Object>(){{
				put("errorMsg", e.getMessage());
				put("msg", "fail");
			}}, HttpStatus.NO_CONTENT);
		}
	}

}
