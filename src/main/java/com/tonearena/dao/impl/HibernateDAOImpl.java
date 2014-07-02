package com.tonearena.dao.impl;

import java.lang.reflect.ParameterizedType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class HibernateDAOImpl<E>{
	    
    public Class<?> returnedClass() {
        ParameterizedType parameterizedType = (ParameterizedType)getClass()
                                                    .getGenericSuperclass();
        return (Class<?>) parameterizedType.getActualTypeArguments()[0];
   }

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
	    return sessionFactory.getCurrentSession();
	}
	
	@Transactional
	public void save(E object){
		sessionFactory.getCurrentSession().save(object);		
	}
	
	@Transactional
	public void update(E object){
		sessionFactory.getCurrentSession().update(object);		
	}
	
	@Transactional
	public void delete(E object){
		sessionFactory.getCurrentSession().delete(object);		
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public E populate(Long id){
		return (E) sessionFactory.getCurrentSession().load(returnedClass(),id);
	}	
}
