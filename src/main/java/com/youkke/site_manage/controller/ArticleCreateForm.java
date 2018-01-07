package com.youkke.site_manage.controller;

import java.util.List;

public class ArticleCreateForm {
	private String content;
	private String title;
	private List<String> tag;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getTag() {
		return tag;
	}
	public void setTag(List<String> tag) {
		this.tag = tag;
	}
	
	


}
