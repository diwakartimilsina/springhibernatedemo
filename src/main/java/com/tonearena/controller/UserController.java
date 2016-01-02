package com.tonearena.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
	
	    @RequestMapping(value="/getuser", method=RequestMethod.GET)
	    public String getUserInfo(ModelMap model) {
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
