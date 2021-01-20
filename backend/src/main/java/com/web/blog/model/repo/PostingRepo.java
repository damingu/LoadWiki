package com.web.blog.model.repo;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.web.blog.model.dto.Posting;

@Repository
public interface PostingRepo {
	public Posting select(String pid) throws SQLException;
	public Posting selectList(int pageSt, int pageFin) throws SQLException;
	public Posting selectListTag(int pageSt, int pageFin, String tag) throws SQLException;
	public int insert(Posting posting) throws SQLException;
	public int update(Posting posting) throws SQLException;
	public int delete(String email) throws SQLException;
}
