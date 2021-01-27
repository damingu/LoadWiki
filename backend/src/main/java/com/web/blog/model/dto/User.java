package com.web.blog.model.dto;

import java.util.Arrays;

public class User {
	private int uid;
	private String password;
	private String email;
	private String name;
	private String createDate;
	private String image;
	private String bgImage;
	private String address;
	private String keyword[];
	
	public User() {
		super();
	}

	public User(int uid, String password, String email, String name, String createDate, String image, String bgImage,
			String address, String[] keyword) {
		super();
		this.uid = uid;
		this.password = password;
		this.email = email;
		this.name = name;
		this.createDate = createDate;
		this.image = image;
		this.bgImage = bgImage;
		this.address = address;
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

	public String getBgImage() {
		return bgImage;
	}

	public void setBgImage(String bgImage) {
		this.bgImage = bgImage;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String[] getKeyword() {
		return keyword;
	}

	public void setKeyword(String[] keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", password=" + password + ", email=" + email + ", name=" + name + ", createDate="
				+ createDate + ", image=" + image + ", bgImage=" + bgImage + ", address=" + address + ", keyword="
				+ Arrays.toString(keyword) + "]";
	}
	
	
}
