package com.web.blog.model.repo;

import java.sql.SQLException;

import com.web.blog.model.dto.User;

public interface UserRepo {
	public User select(String email) throws SQLException;
	public int insert(User user) throws SQLException;
	public int update(User user) throws SQLException;
	public int delete(String email) throws SQLException;
}
