package com.youkke.site_manage.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Component;


import com.youkke.site_manage.domain.Article;

@Component
@Transactional
public class ArticleDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Session getSession(){
		return entityManager.unwrap(Session.class);
	}
	
	public void save(Article article){
		getSession().save(article);
	}
	
	public void delete(Article article){
		getSession().delete(article);
	}
	
	public void update(Article article){
		getSession().update(article);
	}
	
	public Article findById(String id){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Article> criteria = builder.createQuery(Article.class);
		Root<Article> root = criteria.from(Article.class);
		criteria.where(builder.equal(root.get("id"), id));
		Article article =  entityManager.createQuery(criteria).getSingleResult();
		return article;
	}
	
	public List<Article> findAll(String userid ){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Article> criteria = builder.createQuery(Article.class);
		Root<Article> root = criteria.from(Article.class);
		criteria.where(builder.equal(root.get("userid"), userid));
		List<Article> article = entityManager.createQuery(criteria).getResultList();
		return article;
	}
	
	public List<Article> user(String userid){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Article> criteria = builder.createQuery(Article.class);
		Root<Article> root = criteria.from(Article.class);
		criteria.where(builder.equal(root.get("userid"), userid));
		List<Article> article =  entityManager.createQuery(criteria).getResultList();
		System.err.println(article);
		return article;
	}
	
	public List<Article> users(String userid,String type,int number){
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Article> criteria = builder.createQuery(Article.class);
		Root<Article> root = criteria.from(Article.class);
		Predicate conditionForUser = builder.equal(root.get("userid"), userid);
		Predicate conditionForTag = builder.like(root.get("tagjson"), "%"+type+"%");
		Predicate condition = builder.and(conditionForUser,conditionForTag);
		criteria.where(condition);
		//criteria.where(builder.equal(root.get("userid"), userid),builder.like(root.get("tagjson"), "%"+type+"%"));
		criteria.orderBy(builder.desc(root.get("date")));
		List<Article> article =  entityManager.createQuery(criteria).setMaxResults(5).getResultList();
		return article;
	}
	
	
		

}
