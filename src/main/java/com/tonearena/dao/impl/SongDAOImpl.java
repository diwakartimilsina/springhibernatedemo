package com.tonearena.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tonearena.model.Song;

@Repository
public class SongDAOImpl extends JPADAOImpl<Song> {
	
	@Transactional
	public void save(Song song){
		super.save(song);
	}
	
	@Transactional
	public void update(Song song){
		super.update(song);
	}
	
	@Transactional
	public Song populate(Long id){
		return super.get(id);
	}
	
	@Transactional
	public void delete(Song song){
		super.delete(song);
	}
}
