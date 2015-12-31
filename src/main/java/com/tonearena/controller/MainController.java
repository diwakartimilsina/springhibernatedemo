package com.tonearena.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class MainController {
	
	    @RequestMapping(value="/", method=RequestMethod.GET)
	    public String getWelcomePage(ModelMap model) {
	        return "welcomePage";
	    }
	    
	    @RequestMapping(value="/login", method=RequestMethod.GET)
	    public String getLoginPage(ModelMap model) {
	        return "loginPage";
	    }
	    
	    @RequestMapping(value="/login", method=RequestMethod.POST)
	    public String doLogin(ModelMap model) {
	        return "loginPage";
	    }

	    @RequestMapping(value="/logout", method=RequestMethod.GET)
	    public String doLogout(ModelMap model) {
	        return "welcomePage";
	    }
	    
}
