package com.tonearena.controller;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tonearena.model.Song;
import com.tonearena.service.SongService;
import com.tonearena.validators.SongFormValidator;


	 
	@Controller
    @RequestMapping("/song")

	public class SongController {
	 
		@Autowired
		SongService songSvc;
		
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
	    
	    
	    @RequestMapping(value="/add", method=RequestMethod.POST, consumes={"application/json"}, produces={"application/json"})
	    @ResponseBody
	    public ResponseEntity<Song> addSong( @Valid @RequestBody Song song){
	    	songSvc.addSong(song);
	    	return new ResponseEntity<Song>(song, HttpStatus.OK);
	    }
	    
	    @RequestMapping(value="/delete", method=RequestMethod.POST)
	    public String deleteSong(Song song, ModelMap model) {
	    	songSvc.deleteSong(song);
	    	model.addAttribute("model", song);
	        return "deleteSong";
	    }

	    
	}
