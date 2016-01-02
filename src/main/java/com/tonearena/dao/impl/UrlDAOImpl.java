package com.tonearena.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tonearena.model.MyURL;

@Repository
public class UrlDAOImpl extends JPADAOImpl<MyURL> {

	@Transactional
	public void save(MyURL url){
		super.save(url);
	}
	
	@Transactional
	public void update(MyURL url){
		super.update(url);
	}
	
	@Transactional
	public MyURL populate(Long id){
		return super.get(id);
	}
	
	@Transactional
	public void delete(MyURL url){
		super.delete(url);
	}
}
