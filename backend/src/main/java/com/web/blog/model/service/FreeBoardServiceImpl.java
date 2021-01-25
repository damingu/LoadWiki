package com.web.blog.model.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.web.blog.model.dto.Comment;
import com.web.blog.model.dto.Posting;
import com.web.blog.model.repo.CommentRepo;
import com.web.blog.model.repo.PostingRepo;

@Service
public class FreeBoardServiceImpl implements FreeBoardService{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Autowired
	CommentRepo commentRepo;
	
	@Autowired
	PostingRepo postingRepo;
	
	final static String[] TAG = new String[] {"tag"};
	final static int[] PAGESIZE = new int[]{10};
	
	public Object getPostingListAll(String page_s, String...tags) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int cnt = PAGESIZE[0];
			int page = Integer.parseInt(page_s) * cnt;
			if(tags.length == 0) {
				result.put("postings", postingRepo.selectListAll(page, cnt));
			} else {
				result.put("postings", postingRepo.selectListAllTag(page, cnt, tags[0]));
			}
			result.put("msg", "success");
		} catch(NumberFormatException e){
			logger.error("input data type error");
			result.put("msg", "fail");
		} catch(Exception e) {
			logger.error("i dont know");
			result.put("msg", "fail");
		}
		return result;
	}
	
	public Object getPostingListByName(String page_s, String name, String...tags) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int cnt = PAGESIZE[0];
			int page = Integer.parseInt(page_s) * cnt;
			if(tags.length == 0) {
				result.put("postings", postingRepo.selectListName(page, cnt, name));
			} else {
				result.put("postings", postingRepo.selectListNameTag(page, cnt, name, tags[0]));
			}
			result.put("msg", "success");
		} catch(NumberFormatException e){
			logger.error("input data type error");
			result.put("msg", "fail");
		} catch(Exception e) {
			logger.error("i dont know");
			result.put("msg", "fail");
		}
		return result;
	}
	
	public Object getPostingListByTitle(String page_s, String title, String...tags) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int cnt = PAGESIZE[0];
			int page = Integer.parseInt(page_s) * cnt;
			if(tags.length == 0) {
				result.put("postings", postingRepo.selectListTitle(page, cnt, title));
			} else {
				result.put("postings", postingRepo.selectListTitleTag(page, cnt, title, tags[0]));
			}
			result.put("msg", "success");
		} catch(NumberFormatException e){
			logger.error("input data type error");
			result.put("msg", "fail");
		} catch(Exception e) {
			logger.error("i dont know");
			result.put("msg", "fail");
		}
		return result;
	}
	
	public Object getPostingListByContent(String page_s, String content, String...tags) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int cnt = PAGESIZE[0];
			int page = Integer.parseInt(page_s) * cnt;
			if(tags.length == 0) {
				result.put("postings", postingRepo.selectListContent(page, cnt, content));
			} else {
				result.put("postings", postingRepo.selectListContentTag(page, cnt, content, tags[0]));
			}
			result.put("msg", "success");
		} catch(NumberFormatException e){
			logger.error("input data type error");
			result.put("msg", "fail");
		} catch(Exception e) {
			logger.error("i dont know");
			result.put("msg", "fail");
		}
		return result;
	}
	
	public Object getPosting(String pid) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result.put("posting", postingRepo.select(Integer.parseInt(pid)));
			result.put("comments", commentRepo.selectListPid(Integer.parseInt(pid)));
			result.put("msg", "success");
		} catch(NumberFormatException e){
			logger.error("input data type error");
			result.put("msg", "fail");
		} catch(Exception e) {
			logger.error("i dont know");
			result.put("msg", "fail");
		}
		return result;
	}
	
	public Object registPosting(Posting posting, String uid) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(postingRepo.select(posting.getPid()).getUid() != Integer.parseInt(uid)) throw new RuntimeException("wrong user");
			if(postingRepo.insert(posting) == 1) result.put("msg", "success");
			else result.put("msg", "fail");
		} catch(NumberFormatException e){
			logger.error("input data type error");
			result.put("msg", "fail");
		} catch(Exception e) {
			logger.error("i dont know");
			result.put("msg", "fail");
		}
		return result;
	}
	
	public Object editPosting(Posting posting, String uid) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(postingRepo.select(posting.getPid()).getUid() != Integer.parseInt(uid)) throw new RuntimeException("wrong user");
			if(postingRepo.update(posting) == 1) result.put("msg", "success");
			else result.put("msg", "fail");
		} catch(NumberFormatException e){
			logger.error("input data type error");
			result.put("msg", "fail");
		} catch(Exception e) {
			logger.error("i dont know");
			result.put("msg", "fail");
		}
		return result;
	}
	
	public Object deletePosting(String pid_s, String uid) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int pid = Integer.parseInt(pid_s);
			if(postingRepo.select(pid).getUid() != Integer.parseInt(uid)) throw new RuntimeException("wrong user");
			if(postingRepo.delete(pid) == 1) result.put("msg", "success");
			else result.put("msg", "fail");
//			commentRepo.deletePid(pid);
		} catch(NumberFormatException e){
			logger.error("input data type error");
			result.put("msg", "fail");
		} catch(Exception e) {
			logger.error("i dont know");
			result.put("msg", "fail");
		}
		return result;
	}
	
	public Object registComment(Comment comment, String uid) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(commentRepo.select(comment.getCid()).getUid() != Integer.parseInt(uid)) throw new RuntimeException("wrong user");
			if(commentRepo.insert(comment) == 1) result.put("msg", "success");
			else result.put("msg", "fail");
		} catch(NumberFormatException e){
			logger.error("input data type error");
			result.put("msg", "fail");
		} catch(Exception e) {
			logger.error("i dont know");
			result.put("msg", "fail");
		}
		return result;
	}
	
	public Object editComment(Comment comment, String uid) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(commentRepo.select(comment.getCid()).getUid() != Integer.parseInt(uid)) throw new RuntimeException("wrong user");
			if(commentRepo.update(comment) == 1) result.put("msg", "success");
			else result.put("msg", "fail");
		} catch(NumberFormatException e){
			logger.error("input data type error");
			result.put("msg", "fail");
		} catch(Exception e) {
			logger.error("i dont know");
			result.put("msg", "fail");
		}
		return result;
	}
	
	public Object deleteComment(String cid, String uid) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(commentRepo.select(Integer.parseInt(cid)).getUid() != Integer.parseInt(uid)) throw new RuntimeException("wrong user");
			if(commentRepo.delete(Integer.parseInt(cid)) == 1) result.put("msg", "success");
			else result.put("msg", "fail");
		} catch(NumberFormatException e){
			logger.error("input data type error");
			result.put("msg", "fail");
		} catch(Exception e) {
			logger.error("i dont know");
			result.put("msg", "fail");
		}
		return result;
	}
	
}
