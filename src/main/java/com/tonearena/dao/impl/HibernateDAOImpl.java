package com.tonearena.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HibernateDAOImpl<E> extends HibernateDaoSupport{

	
	public void save(E object){
		getHibernateTemplate().save(object);		
	}
	
	public void update(E object){
		getHibernateTemplate().update(object);		
	}
	
	public void delete(E object){
		getHibernateTemplate().delete(object);		
	}
}
