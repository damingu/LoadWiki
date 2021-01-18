package com.web.blog.model.service;

import java.sql.SQLException;

import com.web.blog.model.dto.User;

public interface UserService {
	
	public User getInfo(String email);
	public int join(User user);
	public int modify(User user);
	public int withdraw(String email);
	public User login(User user);
	
}
