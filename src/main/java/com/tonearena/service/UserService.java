package com.tonearena.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.tonearena.dao.impl.UserDAOImpl;
import com.tonearena.model.User;

public class UserService {
	
	@Autowired
	UserDAOImpl userDAOImpl;
	

	public void addUser(User user){
		userDAOImpl.save(user);
	}

	public void updateUser(User user){
		userDAOImpl.update(user);
	}

	public void deleteUser(User user){
		userDAOImpl.delete(user);
	}
	
	public void authenticate(User user){
		
	}
}
