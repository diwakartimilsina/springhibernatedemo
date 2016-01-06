package com.tonearena.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonearena.model.User;
import com.tonearena.repositories.UserRepo;

@Service
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
	
	public User findByUserName(String userName){
		return userRepo.findByUserName(userName);
	}
}
