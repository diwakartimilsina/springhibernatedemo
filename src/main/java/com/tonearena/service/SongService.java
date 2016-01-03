package com.tonearena.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonearena.model.Song;
import com.tonearena.repositories.SongRepo;

@Service
public class SongService {
	
	@Autowired
	SongRepo songRepo;
	

	public void addSong(Song song){
		songRepo.saveAndFlush(song);
	}

	public void updateSong(Song song){
		songRepo.saveAndFlush(song);
	}

	public void deleteSong(Song song){
		songRepo.delete(song);
	}
	
	public Song find(Long id){
		return songRepo.findOne(id);
	}
}
