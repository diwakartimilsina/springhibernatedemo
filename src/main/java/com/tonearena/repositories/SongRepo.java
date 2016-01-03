package com.tonearena.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tonearena.model.Song;


@Repository
public interface SongRepo extends JpaRepository<Song,Long>{
	
	public Song findBySongName(String songName);

}
