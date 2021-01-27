package com.web.blog.model.service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.web.blog.model.dto.User;

public interface UserService {
	
	public Object getInfo(String email);
	public Object join(User user);
	public Object modify(User user);
	public Object withdraw(String email);
	public Object login(User user);
	public void upload(MultipartFile[] files, Model model, HttpSession session) throws Exception, IOException;
}
