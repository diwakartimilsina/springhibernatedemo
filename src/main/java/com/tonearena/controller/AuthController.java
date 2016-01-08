package com.tonearena.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tonearena.dto.UserNamePasswordDTO;
import com.tonearena.model.User;
import com.tonearena.security.AuthorizationDetailService;
import com.tonearena.security.TokenHandler;
import com.tonearena.service.UserService;

@Controller
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	UserService userSvc;
	
	@Autowired
	TokenHandler tokenHandler;
    
    @RequestMapping(value="/login", method=RequestMethod.POST, consumes={"application/json"}, produces={"application/json"})
    @ResponseBody
    public ResponseEntity<String> doLogin(@RequestBody UserNamePasswordDTO userNamePassword){
    	User user = new User();
    	user.setUserName(userNamePassword.getUserName());
    	user.setPassword(userNamePassword.getPassword());
    	
    	if(userSvc.authenticate(user)){
    		return new ResponseEntity<String>("{token:"+'"'+tokenHandler.createTokenForUser(tokenHandler.authService.loadUserByUsername(user.getUserName()))+'"'+"}",HttpStatus.OK);
    	}
    	return new ResponseEntity<String>("", HttpStatus.UNAUTHORIZED);
    }
}
