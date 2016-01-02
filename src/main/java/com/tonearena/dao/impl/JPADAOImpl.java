package com.tonearena.dao.impl;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JPADAOImpl<E>{
	  
	protected Class<E> entityClass;
	
    @SuppressWarnings("unchecked")
	public Class<E> returnedClass() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
        return this.entityClass;
   }

    @PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void save(E object){
		em.persist(object);		
	}
	
	@Transactional
	public void update(E object){
		em.merge(object);		
	}
	
	@Transactional
	public void delete(E object){
		em.remove(object);		
	}
	
	@Transactional
	public E get(Long id){
		return em.find(this.entityClass, id);
	}	
}
