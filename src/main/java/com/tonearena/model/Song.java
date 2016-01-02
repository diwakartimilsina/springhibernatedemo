package com.tonearena.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="songs")
@XmlRootElement(name = "song")
public class Song {

	
	@Id
    @Column(name="song_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	Long songId;
	
	@Column(name="song_name")
	String songName;

	
	public Song(){
		
	}
	
	@XmlElement
	public Long getSongId() {
		return songId;
	}
	
	public void setSongId(Long id) {
		this.songId = id;
	}
	
	@XmlElement
	public String getSongName() {
		return songName;
	}
	
	public void setSongName(String songName) {
		this.songName = songName;
	}
}
