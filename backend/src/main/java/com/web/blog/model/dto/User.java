package com.web.blog.model.dto;

public class User {
	private int uid;
	private String password;
	private String email;
	private String name;
	private String createDate;
	private String image;
	private Keyword keyword;
	//다른 dto를 여기에 넣어도 문제가 없을까요??
	
	public User() {
		super();
	}

	public User(int uid, String password, String email, String name, String createDate, String image,
			Keyword keyword) {
		super();
		this.uid = uid;
		this.password = password;
		this.email = email;
		this.name = name;
		this.createDate = createDate;
		this.image = image;
		this.keyword = keyword;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Keyword getKeyword() {
		return keyword;
	}

	public void setKeyword(Keyword keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", password=" + password + ", email=" + email + ", name=" + name + ", createDate="
				+ createDate + ", image=" + image + ", keyword=" + keyword + "]";
	}
	
}
