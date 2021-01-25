package com.web.blog.model.service;

import com.web.blog.model.dto.Comment;
import com.web.blog.model.dto.Posting;

public interface FreeBoardService {
	public Object getPostingListAll(String page, String...tags);
	public Object getPostingListByName(String page, String name, String...tags);
	public Object getPostingListByTitle(String page, String title, String...tags);
	public Object getPostingListByContent(String page, String content, String...tags);
	public Object getPosting(String pid);
	public Object registPosting(Posting posting, String uid);
	public Object editPosting(Posting posting, String uid);
	public Object deletePosting(String pid, String uid);
	public Object registComment(Comment comment, String uid);
	public Object editComment(Comment comment, String uid);
	public Object deleteComment(String cid, String uid);

}
