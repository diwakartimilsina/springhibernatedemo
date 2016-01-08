package com.tonearena.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class TokenAuthenticationService {
 
     private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
 
     @Autowired
     private TokenHandler tokenHandler;
     
     @Autowired
     AuthorizationDetailService authService;
 
     public TokenAuthenticationService() {

     }
 
     public void setAuthentication(HttpServletResponse response, UserAuthentication authentication) {
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