package com.tonearena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tonearena.beans.Song;
import com.tonearena.service.SongService;


	 
	@Controller
    @RequestMapping("/song")

	public class SongController {
	 
		@Autowired
		SongService songSvc;
		
		Song song;

	    @RequestMapping(value="/{id}", method=RequestMethod.GET)
	    public String getSong(@PathVariable int id, ModelMap model) {
	    	Song song = new Song();
	    	model.addAttribute("song",song);
	        return "listSong";
	    }
		
	    @RequestMapping(value="/add", method=RequestMethod.POST)
	    public String addSong(Song song, ModelMap model) {
	    	songSvc.addSong(song);
	    	model.addAttribute("model", song);
	        return "addSong";
	    }
	    
	    @RequestMapping(value="/delete", method=RequestMethod.POST)
	    public String deleteSong(Song song, ModelMap model) {
	    	songSvc.deleteSong(song);
	    	model.addAttribute("model", song);
	        return "deleteSong";
	    }

	    
	}
