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
import com.web.blog.model.repo.UserRepo;

@Service
public class FreeBoardServiceImpl implements FreeBoardService{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Autowired
	CommentRepo commentRepo;
	
	@Autowired
	PostingRepo postingRepo;
	
	@Autowired
	UserRepo userRepo;
	
	final static String[] TAG = new String[] {"tag"};
	final static int[] PAGESIZE = new int[]{10};
	
	public Object getPostingListAll(String page_s, String classifier, String...tags) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int cnt = PAGESIZE[0];
			int page = 1 + (Integer.parseInt(page_s) - 1) * cnt;
			if(tags.length == 0) {
				result.put("postings", postingRepo.selectListAll(page, cnt, classifier));
			} else {
				result.put("postings", postingRepo.selectListAllTag(page, cnt, classifier, tags[0]));
			}
			Posting[] Postings = (Posting[]) result.get("postings");
			String[] names = new String[Postings.length];
			for(int i = 0; i < Postings.length; i++) 
				names[i] = userRepo.getName(Postings[i].getUid());
			result.put("names", names);
			result.put("msg", "success");
		} catch(NumberFormatException e){
			logger.error("input data type error");
			result.put("msg", "fail");
		} catch(Exception e) {
			e.printStackTrace();
			logger.error("i dont know");
			result.put("msg", "fail");
		}
		return result;
	}
	
	public Object getPostingListByName(String page_s, String classifier, String word, String...tags) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int cnt = PAGESIZE[0];
			int page = 1 + (Integer.parseInt(page_s) - 1) * cnt;
			if(tags.length == 0) {
				result.put("postings", postingRepo.selectListName(page, cnt, classifier, word));
			} else {
				result.put("postings", postingRepo.selectListNameTag(page, cnt, classifier, word, tags[0]));
			}
			Posting[] Postings = (Posting[]) result.get("postings");
			String[] names = new String[Postings.length];
			for(int i = 0; i < Postings.length; i++) 
				names[i] = userRepo.getName(Postings[i].getUid());
			result.put("names", names);
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
	
	public Object getPostingListByTitle(String page_s, String classifier, String word, String...tags) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int cnt = PAGESIZE[0];
			int page = 1 + (Integer.parseInt(page_s) - 1) * cnt;
			if(tags.length == 0) {
				result.put("postings", postingRepo.selectListTitle(page, cnt, classifier, word));
			} else {
				result.put("postings", postingRepo.selectListTitleTag(page, cnt, classifier, word, tags[0]));
			}
			Posting[] Postings = (Posting[]) result.get("postings");
			String[] names = new String[Postings.length];
			for(int i = 0; i < Postings.length; i++) 
				names[i] = userRepo.getName(Postings[i].getUid());
			result.put("names", names);
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
	
	public Object getPostingListByContent(String page_s, String classifier, String word, String...tags) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int cnt = PAGESIZE[0];
			int page = 1 + (Integer.parseInt(page_s) - 1) * cnt;
			if(tags.length == 0) {
				result.put("postings", postingRepo.selectListContent(page, cnt, classifier, word));
			} else {
				result.put("postings", postingRepo.selectListContentTag(page, cnt, classifier, word, tags[0]));
			}
			Posting[] Postings = (Posting[]) result.get("postings");
			String[] names = new String[Postings.length];
			for(int i = 0; i < Postings.length; i++) 
				names[i] = userRepo.getName(Postings[i].getUid());
			result.put("names", names);
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
			Posting posting = postingRepo.select(Integer.parseInt(pid));
			Comment[] comments = commentRepo.selectListPid(Integer.parseInt(pid));
			if(posting == null) throw new RuntimeException("cant fine posting");
			if(comments != null)result.put("comments", comments);
			result.put("posting", posting);
			result.put("name", userRepo.getName(posting.getUid()));
			result.put("msg", "success");
		} catch(NumberFormatException e){
			logger.error("input data type error");
			result.put("msg", "fail");
		} catch(Exception e) {
			e.printStackTrace();
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
