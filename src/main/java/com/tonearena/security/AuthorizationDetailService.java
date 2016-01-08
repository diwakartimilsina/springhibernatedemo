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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.tonearena.model.Role;
import com.tonearena.service.UserService;

public class AuthorizationDetailService implements UserDetailsService {

	@Autowired
	UserService userService;

	@Override
	public User loadUserByUsername(final String userName)
			throws UsernameNotFoundException {

		com.tonearena.model.User user = userService.findByUserName(userName);
		List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());

		return buildUserForAuthentication(user, authorities);

	}

	private User buildUserForAuthentication(com.tonearena.model.User user,
			List<GrantedAuthority> authorities) {
		return new User(user.getUserName(), user.getPassword(), true, true,
				true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<Role> roles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		for (Role role : roles) {
			setAuths.add(new SimpleGrantedAuthority(role.getRoleName()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(
				setAuths);

		return Result;
	}

}
