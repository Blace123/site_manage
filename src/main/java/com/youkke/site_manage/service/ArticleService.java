package com.youkke.site_manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.youkke.site_manage.controller.ArticleCreateForm;
import com.youkke.site_manage.dao.ArticleDao;
import com.youkke.site_manage.domain.Article;

@Component
@Transactional
public class ArticleService {
	@Autowired
	private ArticleDao articledao;
	
	
	public void save(Article article){
		articledao.save(article);
		
	}
	
	public List<Article> user(String userid){
		return articledao.user(userid);
	}
	
	public List<Article> users(String userid,String type, int number){
		return articledao.users(userid,type,number);
	}
	public Article details(String id){
		return articledao.findById(id);

	}
	
	public void delete(String id){
		Article article = articledao.findById(id);
		articledao.delete(article);
		
	}
	
	public Article findById(String id){
		return articledao.findById(id);		
	}
	
	public void update(Article article,ArticleCreateForm articleCreateForm){
		JSONArray jsonArray = new JSONArray();
		for(String i : articleCreateForm.getTag()){
			jsonArray.add(i);
		}
		article.setTitle(articleCreateForm.getTitle());
		article.setContent(articleCreateForm.getContent());
		article.setTagjson(jsonArray.toString());
		articledao.update(article);
		
	}



	


}
