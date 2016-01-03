package com.tonearena.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.tonearena.model.User;
import com.tonearena.repositories.UserRepo;


public class UserService {
	
	@Autowired
	UserRepo userRepo;
	

	public void addUser(User user){
		userRepo.saveAndFlush(user);
	}

	public void updateUser(User user){
		userRepo.saveAndFlush(user);
	}

	public void deleteUser(User user){
		userRepo.delete(user);
	}
	
	public void authenticate(User user){
		
	}
}
