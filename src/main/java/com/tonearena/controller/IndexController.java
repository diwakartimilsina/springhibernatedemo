package com.tonearena.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


	 
	@Controller
	public class IndexController {
	 
	    @RequestMapping("/")
	    public ModelAndView IndexPage() {
	 
	        String message = "This is the index page";
	        return new ModelAndView("hello", "message", message);
	    }
	    
	    @RequestMapping("/show")
	    public ModelAndView showPage(){
	    	String message = "This is the show page";
	    	return new ModelAndView("hello","message",message);
	    }
	}