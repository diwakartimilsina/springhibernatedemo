package com.tonearena.dao;

import com.tonearena.beans.Song;


public interface SongDAO extends BaseDAO<Song> {
	
	public void save(Song song);
	public void update(Song song);
	public void delete(Song song);

}
