package com.web.blog.model.service;

import com.web.blog.model.dto.Posting;

public interface FreeBoardService {
	public Object getPostingListAll(String page, String...tags);
	public Object getPostingListByName(String page, String name, String...tags);
	public Object getPostingListByTitle(String page, String title, String...tags);
	public Object getPostingListByContent(String page, String content, String...tags);
	public Object getPosting(String pid);
	public Object regist(Posting posting);
	public Object edit(Posting posting);
	public Object delete(String pid);
}
