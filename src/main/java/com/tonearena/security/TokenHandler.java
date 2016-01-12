package com.tonearena.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenHandler {

	private final String secret="234234";
	
	@Autowired
	public AuthorizationDetailService authService;
	
	Logger log = Logger.getLogger(TokenHandler.class);

	public TokenHandler() {
		
	}

	public User parseUserFromToken(String token) {
		User user = null;
		try{
			String username = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
			user=authService.loadUserByUsername(username);
		}
		catch(Exception e){
			log.error("Could not parse token provided in the header",e);
		}
		return user;
	}

	public String createTokenForUser(User user) {
		return Jwts.builder().setSubject(user.getUsername())
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
}