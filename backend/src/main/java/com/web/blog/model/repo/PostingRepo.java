package com.web.blog.model.repo;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.web.blog.model.dto.Posting;

@Repository
public interface PostingRepo {
	public Posting select(int pid) throws SQLException;
	public Posting[] selectListAll(int page, int cnt, String classifier) throws SQLException;
	public Posting[] selectListAllTag(int page, int cnt, String classifier, String tag) throws SQLException;
	public Posting[] selectListName(int page, int cnt, String classifier, String word) throws SQLException;
	public Posting[] selectListNameTag(int page, int cnt, String classifier, String word, String tag) throws SQLException;
	public Posting[] selectListTitle(int page, int cnt, String classifier, String word) throws SQLException;
	public Posting[] selectListTitleTag(int page, int cnt, String classifier, String word, String tag) throws SQLException;
	public Posting[] selectListContent(int page, int cnt, String classifier, String word) throws SQLException;
	public Posting[] selectListContentTag(int page, int cnt, String classifier, String word, String tag) throws SQLException;
	public int insert(Posting posting) throws SQLException;
	public int update(Posting posting) throws SQLException;
	public int delete(int pid) throws SQLException;
}
