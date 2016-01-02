package com.tonearena.dao;

import com.tonearena.model.User;


public interface UserDAO extends BaseDAO<User> {
	
	public void save(User user);
	public void update(User user);
	public void delete(User user);
	public void getUser(User user);

}
