package com.tonearena.security;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.tonearena.model.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenHandler {

	private final String secret="234234";
	private final long tokenValidForInMilliSeconds = 3600000;
	
	@Autowired
	public AuthorizationDetailService authService;
	
	Logger log = Logger.getLogger(TokenHandler.class);

	public TokenHandler() {
		
	}

	public User parseUserFromToken(String token) {
		User user = null;
		try{
			String userName = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
			List<Role> roles = (List<Role>) Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("roles");
			return authService.loadUserFromToken(userName, roles);
		}
		catch(Exception e){
			log.error("Could not parse token provided in the header",e);
		}
		return user;
	}

	public String createTokenForUser(User user) {
		return Jwts.builder().setSubject(user.getUsername()).claim("roles", user.getAuthorities())
				.setId(UUID.randomUUID().toString())
				.setExpiration(new Date(System.currentTimeMillis()+tokenValidForInMilliSeconds))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
}