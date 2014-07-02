package com.tonearena.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tonearena.beans.Song;
import com.tonearena.dao.impl.SongDAOImpl;

@Service
public class SongService {
	
	@Autowired
	SongDAOImpl songDAOImpl;
	

	public void addSong(Song song){
		songDAOImpl.save(song);
	}

	public void updateSong(Song song){
		songDAOImpl.update(song);
	}

	public void deleteSong(Song song){
		songDAOImpl.delete(song);
	}
}
