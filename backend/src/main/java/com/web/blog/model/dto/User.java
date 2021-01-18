package com.web.blog.model.dto;

public class User {
	private String uid;
	private String password;
	private String email;
	private String name;
	private String createDate;

	
	public User() {
		super();
	}
	
	public User(String uid, String email, String name, String createDate) {
		super();
		this.uid = uid;
		this.email = email;
		this.name = name;
		this.createDate = createDate;
	}

	public User(String uid, String password, String email, String name, String createDate) {
		super();
		this.uid = uid;
		this.password = password;
		this.email = email;
		this.name = name;
		this.createDate = createDate;
	}

	public String getUid() {
		return this.uid;
	}
	
	public void setUid(String uid) {
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

	
	
	@Override
	public String toString() {
		return "User [uid=" + uid + ", password=" + password + ", email=" + email + ", name=" + name + ", createDate="
				+ createDate + "]";
	}
	
	
}
