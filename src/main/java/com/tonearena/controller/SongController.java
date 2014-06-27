package com.tonearena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tonearena.beans.Song;
import com.tonearena.service.SongService;


	 
	@Controller
	public class SongController {
	 
		@Autowired
		SongService songSvc;
		
		Song song;
		
	    @RequestMapping("/addSong")
	    public ModelAndView addSong() {
	    	song = new Song();
	    	song.setSongName("Great git in the sky");
	    	songSvc.addSong(song);
	        String message = "Song has been added";
	        return new ModelAndView("hello", "message", message);
	    }
	}
