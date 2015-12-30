package com.tonearena.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tonearena.beans.Song;
import com.tonearena.service.SongService;
import com.tonearena.validators.SongFormValidator;


	 
	@Controller
    @RequestMapping("/song")

	public class SongController {
	 
		@Autowired
		SongService songSvc;
		
		Song song;
		
		@Autowired
		SongFormValidator songFormValidator;
		
		@InitBinder
		protected void initBinder(WebDataBinder binder) {
			binder.setValidator(songFormValidator);
		}

	    @RequestMapping(value="/{id}", method=RequestMethod.GET)
	    public String getSong(@PathVariable int id, ModelMap model) {
	    	Song song = new Song();
	    	model.addAttribute("song",song);
	        return "listSong";
	    }
		
	    @RequestMapping(value="/add", method=RequestMethod.GET)
	    public String addSong(ModelMap model) {
	    	model.addAttribute("song", new Song());
	        return "addNewSong";
	    }
	    
	    @RequestMapping(value="/add", method=RequestMethod.POST, consumes={"application/json", "application/xml"})
	    public String addSong(@RequestBody Song song, ModelMap model) {
	    	songSvc.addSong(song);
	    	model.addAttribute("model", song);
	        return "addSongSuccess";
	    }
	    
	    @RequestMapping(value="/add", method=RequestMethod.POST)
	    public String addSongFromForm(@Validated @ModelAttribute("song") Song song, BindingResult result, Model model) {
	    	if(result.hasErrors()){
	    		return "addNewSong";
	    	}
	    	songSvc.addSong(song);
	    	model.addAttribute("model", song);
	        return "addSongSuccess";
	    }
	    
	    @RequestMapping(value="/delete", method=RequestMethod.POST)
	    public String deleteSong(Song song, ModelMap model) {
	    	songSvc.deleteSong(song);
	    	model.addAttribute("model", song);
	        return "deleteSong";
	    }

	    
	}
