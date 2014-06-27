package com.tonearena.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tonearena.beans.Song;
import com.tonearena.service.SongService;


	 
	@Controller
	public class SongController {
	 
	    @RequestMapping("/addSong")
	    public ModelAndView addSong() {
	
	    	Song song = new Song();
	    	SongService songSvc = new SongService();
	    	songSvc.addSong(song);
	        String message = "Song has been added";
	        return new ModelAndView("hello", "message", message);
	    }
	}
