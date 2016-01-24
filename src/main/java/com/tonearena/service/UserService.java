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
	
	public boolean authenticate(User user){
		if(userRepo.findByUserNameAndPassword(user.getUserName(), user.getPassword())!=null){
			return true;
		}
		return false;
	}
	
	public User findByUserName(String userName){
		return userRepo.findByUserName(userName);
	}
}
