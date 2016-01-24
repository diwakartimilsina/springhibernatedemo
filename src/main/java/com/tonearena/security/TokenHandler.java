package com.tonearena.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenHandler {

	private final String secret="234234";
	private final long tokenValidForInMilliSeconds = 7200000;
	
	@Autowired
	public AuthorizationDetailService authService;
	
	Logger log = Logger.getLogger(TokenHandler.class);

	public TokenHandler() {
		
	}

	public UserDetails parseUserFromToken(String token) {
		User user = null;
		try{
			String userName = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
			List<String> roles = (List<String>) Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("roles");
			return authService.loadUserFromToken(userName, roles);
		}
		catch(Exception e){
			log.error("Could not parse token provided in the header",e);
		}
		return user;
	}

	public String createTokenForUser(UserDetails user) {
		
		// Get list of string of GrantedAuthority
		Iterator<? extends GrantedAuthority> it = user.getAuthorities().iterator();
		List<String> roles = new ArrayList<String>();
		while(it.hasNext()){
			roles.add(it.next().getAuthority());
		}
		
		return Jwts.builder().setSubject(user.getUsername()).claim("roles",roles)
				.setId(UUID.randomUUID().toString())
				.setExpiration(new Date(System.currentTimeMillis()+tokenValidForInMilliSeconds))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
}