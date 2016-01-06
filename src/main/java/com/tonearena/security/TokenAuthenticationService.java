package com.tonearena.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class TokenAuthenticationService {
 
     private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
 
     private final TokenHandler tokenHandler;
 
     public TokenAuthenticationService(String secret, AuthorizationDetailService authService) {
         tokenHandler = new TokenHandler(secret, authService);
     }
 
     public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
         final User user = authentication.getDetails();
         response.addHeader(AUTH_HEADER_NAME, tokenHandler.createTokenForUser(user));
     }
 
     public Authentication getAuthentication(HttpServletRequest request) {
         final String token = request.getHeader(AUTH_HEADER_NAME);
         if (token != null) {
             final User user = tokenHandler.parseUserFromToken(token);
             if (user != null) {
                 return new UserAuthentication(user);
             }
         }
         return null;
     }
 }