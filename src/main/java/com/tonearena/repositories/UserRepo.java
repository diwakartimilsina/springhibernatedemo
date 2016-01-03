package com.tonearena.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tonearena.model.Song;
import com.tonearena.model.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{
	
	public Song findByUserName(String userName);
}
