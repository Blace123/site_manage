package com.youkke.site_manage.domain;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.alibaba.fastjson.JSON;

@Entity
public class Article {
	@Id
	private String id;
	private String userid;
	private String title;
	@Column(length=16777216)
	private String content;
	private String tagjson;
	@Transient
	private List<String> tag;
	private Timestamp date;
	public Article(){
		
	}
	public Article(String userid,String title,String content,String tag){
		this.date = new Timestamp(System.currentTimeMillis());
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
		this.userid = userid;
		this.title = title;
		this.content = content;
		this.tagjson = tag;
		
	}

	public String getDate() {
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(date);
		return str;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public String getTagjson() {
		return tagjson;
	}
	public void setTagjson(String tagjson) {
		this.tagjson = tagjson;
	}
	public List<String> getTag() {
		try {
			this.tag = JSON.parseArray(tagjson, String.class);
		}catch(Exception e){
			
		}
		return tag;
	}
	public void setTag(List<String> tag) {
		this.tag = tag;
	}


	

	

}
