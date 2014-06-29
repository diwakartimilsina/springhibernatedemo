package com.tonearena.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="\"songs\"")
@XmlRootElement(name = "song")
public class Song {

	
	@Id
    @Column(name="song_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	int songId;
	
	@Column(name="song_name")
	String songName;

	
	public Song(){
		
	}
	
	public int getSongId() {
		return songId;
	}
	
	public void setSongId(int id) {
		this.songId = id;
	}
	public String getSongName() {
		return songName;
	}
	
	public void setSongName(String songName) {
		this.songName = songName;
	}
}
