package com.youkke.site_manage.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.youkke.site_manage.domain.Article;
import com.youkke.site_manage.service.ArticleService;

@Controller
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	protected String sessuserid = "0042dd84ff4d4246a0e3d06095392a86";
	
	
	@GetMapping("/index/article")
	public String article(Model model){
		List<Article> artcile = articleService.user(sessuserid);
	    model.addAttribute("list", artcile);
		return "article";
	}
	
	@GetMapping("/index/article_json")
	@ResponseBody
	public List<Article> article_json(){
		String type ="新闻";
		int number = 1;
		List<Article> artcile = articleService.users(sessuserid,type,number);
		return artcile;
	}
	
	
	@GetMapping("/index/article/input")
	public String input(){
		return "article_input";
	}
	
	
	@PostMapping("/index/article/inputs")
	@ResponseBody
	public void save(@Valid ArticleCreateForm articleCreateForm){
		JSONArray jsonArray = new JSONArray();
		
		for(String i : articleCreateForm.getTag()){
			jsonArray.add(i);
		//循环出tag保存值jsonArray
		}
		
		Article article =new Article(sessuserid,articleCreateForm.getTitle(),articleCreateForm.getContent(),jsonArray.toString());
		articleService.save(article);
		
	}
	
	@GetMapping("/index/article/{id}/details")
	public String details(@PathVariable String id,Model model){
		Article artcile =articleService.details(id);
	    model.addAttribute("list", artcile);
		return "article_details";
	}
	
	@PostMapping("/index/article/{id}/delete")
	@ResponseBody
	public Map<String, Object> delete(@PathVariable String id){
		Map<String, Object> map = new HashMap<String, Object>();
		articleService.delete(id);
		map.put("result", "success");
		return map;
	}
	
	@GetMapping("/index/article/{id}/edit")
	public String edit(@PathVariable String id, Model model){
		model.addAttribute("article", articleService.findById(id));
		return "article_update";
	}
	
	@PostMapping("/index/article/{id}")
	@ResponseBody
	public Map<String,Object> update(@PathVariable String id,@Valid ArticleCreateForm articleCreateForm){
		Map<String, Object> map = new HashMap<String, Object>();
		System.err.println(articleCreateForm.getTag());
		Article article = articleService.findById(id);
		articleService.update(article, articleCreateForm);
		return map;
	}
	
	


}
