package com.tonearena.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tonearena.model.User;

@Repository
public class UserDAOImpl extends JPADAOImpl<User> {
	
	@Transactional
	public void save(User user){
		super.save(user);
	}
	
	@Transactional
	public void update(User user){
		super.update(user);
	}
	
	@Transactional
	public User get(User user){
		return super.get(user.getUserId());
	}
	
	@Transactional
	public void delete(User user){
		super.delete(user);
	}
}
