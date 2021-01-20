package com.web.blog.model.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.web.blog.model.repo.CommentRepo;
import com.web.blog.model.repo.PostingRepo;

public class FreeBoardServiceImpl {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Autowired
	CommentRepo commentRepo;
	
	@Autowired
	PostingRepo postingRepo;
	
	final static String[] TAG = new String[] {"tag"};
	final static int[] PAGESIZE = new int[]{10};
	
	public Object pleasshowlist(String page, String...tags) {
		int pageNo = 0;
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int pageSt = Integer.parseInt(page);
			int pageFin = pageSt + PAGESIZE[0];
			if(tags.length == 0) {
				result.put("postings", postingRepo.selectList(pageSt, pageFin));
			} else {
				result.put("postings", postingRepo.selectListTag(pageSt, pageFin, tags[0]));
			}
		} catch(NumberFormatException e){
			logger.error("input data type error");
		} catch(Exception e) {
			logger.error("i dont know");
		}
		return result;
	}
}
