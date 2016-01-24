package com.tonearena.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.tonearena.model.Role;
import com.tonearena.service.UserService;

public class AuthorizationDetailService implements UserDetailsService {

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(final String userName)
			throws UsernameNotFoundException {

		com.tonearena.model.User user = userService.findByUserName(userName);
		List<GrantedAuthority> authorities = buildUserAuthorityFromRole(user.getRoles());

		return buildUserForAuthentication(user, authorities);

	}
	
	public UserDetails loadUserFromToken(final String userName, List<String> roles){
		List<GrantedAuthority> authorities = buildUserAuthorityFromString(roles);
		com.tonearena.model.User user = new com.tonearena.model.User();
		user.setUserName(userName);
		user.setPassword(userName);
		return buildUserForAuthentication(user, authorities);
	}

	private UserDetails buildUserForAuthentication(com.tonearena.model.User user,
			List<GrantedAuthority> authorities) {
		return new User(user.getUserName(), user.getPassword(), authorities);
	}

	private List<GrantedAuthority> buildUserAuthorityFromRole(List<Role> roles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		for (Role role : roles) {
			setAuths.add(new SimpleGrantedAuthority(role.getRoleName()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}
	
	private List<GrantedAuthority> buildUserAuthorityFromString(List<String> roles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		for (String role : roles) {
			setAuths.add(new SimpleGrantedAuthority(role));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}

}
