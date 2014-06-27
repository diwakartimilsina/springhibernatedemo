package com.tonearena.service;

import com.tonearena.beans.Song;
import com.tonearena.dao.impl.SongDAOImpl;

public class SongService {

	SongDAOImpl songDAO;
	
	public SongService(){
		this.songDAO = new SongDAOImpl();
	}
	
	public void addSong(Song song){
		this.songDAO.save(song);
	}
	public void updateSong(Song song){
		this.songDAO.update(song);
	}
	public void deleteSong(Song song){
		this.songDAO.delete(song);
	}

	
	
}
