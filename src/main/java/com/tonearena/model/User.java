package com.tonearena.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name="users")
public class User {
	
	@Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long userId;
	
	@Column(name="user_name")
	public String userName;
	
	@Column(name="password")
	public String password;
	
	@Column(name="email")
	public String email;
	
	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},fetch = FetchType.EAGER)
	@JoinTable(name="user_role",
				joinColumns={@JoinColumn(name="user_id")},
				inverseJoinColumns={@JoinColumn(name="role_id")})
	private Set<Role> roles;

	public User() {
		this.roles = new HashSet<Role>();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
