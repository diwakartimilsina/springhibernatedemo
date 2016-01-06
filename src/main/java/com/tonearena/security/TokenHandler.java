package com.tonearena.security;

import org.springframework.security.core.userdetails.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public final class TokenHandler {

	private final String secret;
	private final AuthorizationDetailService authService;

	public TokenHandler(String secret, AuthorizationDetailService authService) {
		this.secret = secret;
		this.authService = authService;
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