package com.web.blog.model.dto;

import java.util.List;

public class Keyword {
	List<String> words;

	public Keyword() {
		super();
	}

	public Keyword(List<String> words) {
		super();
		this.words = words;
	}

	public List<String> getwords() {
		return words;
	}

	public void setWords(List<String> words) {
		this.words = words;
	}

	@Override
	public String toString() {
		return "Keyword [words=" + words + "]";
	}
	
}
