package com.tonearena.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="songs")
public class Song {

	
	@Id
    @Column(name="song_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	Long songId;
	
	@Column(name="song_name")
	@NotEmpty
	@Max(50)
	String songName;

	
	public Song(){
		
	}
	
	public Long getSongId() {
		return songId;
	}
	
	public void setSongId(Long id) {
		this.songId = id;
	}
	
	public String getSongName() {
		return songName;
	}
	
	public void setSongName(String songName) {
		this.songName = songName;
	}
}
