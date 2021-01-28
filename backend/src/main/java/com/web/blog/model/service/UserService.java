package com.web.blog.model.service;

import com.web.blog.model.dto.User;

public interface UserService {
	
	public Object getInfo(String email);
	public Object join(User user);
	public Object modify(User user);
	public Object withdraw(String email);
	public Object login(User user);
	public Object getName(String uid) throws Exception;
	
}
