package com.tonearena.security;

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

	public TokenHandler() {
		
	}

	public User parseUserFromToken(String token) {
		String username = Jwts.parser().setSigningKey(secret)
				.parseClaimsJws(token).getBody().getSubject();
		return authService.loadUserByUsername(username);
	}

	public String createTokenForUser(User user) {
		return Jwts.builder().setSubject(user.getUsername())
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
}