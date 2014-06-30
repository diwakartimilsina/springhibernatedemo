package com.tonearena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tonearena.beans.Song;
import com.tonearena.beans.MyURL;
import com.tonearena.service.URLService;


	 
	@Controller
    @RequestMapping("/url")

	public class UrlController {
	 
		@Autowired
		URLService urlSvc;
		
		MyURL url;

	    @RequestMapping(value="/fetch/{url}", method=RequestMethod.GET)
	    public String fetchURL(@PathVariable String url, ModelMap model) {
	    	MyURL myURL=urlSvc.fetchURLContent(url);
	    	model.addAttribute("model", myURL);
	        return "fetchURL";
	    }
		
	    @RequestMapping(value="/add", method=RequestMethod.POST)
	    public String addURL(@RequestParam String url, ModelMap model) {
	    	MyURL myURL=urlSvc.fetchURLContent(url);
	    	urlSvc.addURL(myURL);
	    	model.addAttribute("model", myURL);
	        return "addURL";
	    }
	    
	    @RequestMapping(value="/delete", method=RequestMethod.POST)
	    public String deleteURL(MyURL url, ModelMap model) {
	    	urlSvc.deleteURL(url);
	    	model.addAttribute("model", url);
	        return "deleteURL";
	    }

	    
	}
