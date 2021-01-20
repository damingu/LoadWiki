package com.web.blog.model.repo;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.web.blog.model.dto.User;

@Repository
public interface UserRepo {
	public User select(String email) throws SQLException;
	public int insert(User user) throws SQLException;
	public int update(User user) throws SQLException;
	public int delete(String email) throws SQLException;
}
