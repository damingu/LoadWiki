package com.web.blog.model.dto;

public class Comment {
	private int cid;
	private int parentid;
	private int uid;
	private String type;
	private String content;
	private String createDate;
	private String modifyDate;
	private int likeCnt;
	
	public Comment() {
		super();
	}

	public Comment(int cid, int parentid, int uid, String type, String content, String createDate, String modifyDate,
			int likeCnt) {
		super();
		this.cid = cid;
		this.parentid = parentid;
		this.uid = uid;
		this.type = type;
		this.content = content;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.likeCnt = likeCnt;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public int getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}

	@Override
	public String toString() {
		return "Comment [cid=" + cid + ", parentid=" + parentid + ", uid=" + uid + ", type=" + type + ", content="
				+ content + ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", likeCnt=" + likeCnt + "]";
	}
	
	
	
}
