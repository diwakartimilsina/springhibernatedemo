package com.tonearena.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tonearena.model.User;
import com.tonearena.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
		@Autowired
		UserService userSvc;
	
	    @RequestMapping(value="/getuser", method=RequestMethod.GET)
	    public String getUserInfo(ModelMap model) {
	        return "loginPage";
	    }

	    
	    @RequestMapping(value="/add", method=RequestMethod.POST, consumes={"application/json"}, produces={"application/json"})
	    @ResponseBody
	    public ResponseEntity<User> addUser(@Valid @RequestBody User user){
	    	userSvc.addUser(user);
	    	return new ResponseEntity<User>(user, HttpStatus.OK);
	    }

	    
	    @RequestMapping(value="/logout", method=RequestMethod.GET)
	    public String doLogout(ModelMap model) {
	        return "welcomePage";
	    }
	    
}
