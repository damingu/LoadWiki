package com.web.blog.model.repo;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.web.blog.model.dto.User;

@Repository
public interface UserRepo {
	public User select(String email) throws SQLException;
	public String[] selectkeyword(int uid) throws SQLException;
	public int insert(User user) throws SQLException;
	public int insertkeyword(Map data) throws SQLException;
	public int update(User user) throws SQLException;
	public int delete(String email) throws SQLException;
	public String getName(int uid) throws SQLException;
}
