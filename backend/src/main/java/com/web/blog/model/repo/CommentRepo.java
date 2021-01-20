package com.web.blog.model.repo;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.web.blog.model.dto.Comment;

@Repository
public interface CommentRepo {
	public Comment select(String email) throws SQLException;
	public int insert(Comment comment) throws SQLException;
	public int update(Comment comment) throws SQLException;
	public int delete(String email) throws SQLException;
}
