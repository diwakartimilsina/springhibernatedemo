package com.tonearena.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tonearena.beans.Song;

@Repository
public class SongDAOImpl extends HibernateDAOImpl<Song> {
	
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
		return super.populate(id);
	}
	
	@Transactional
	public void delete(Song song){
		super.delete(song);
	}
}
