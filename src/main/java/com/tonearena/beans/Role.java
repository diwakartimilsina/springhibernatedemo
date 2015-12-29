package com.tonearena.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Role {
	
	@Id
    @Column(name="role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	public Long roleId;
	
	@Column(name="role_name")
	public String roleName;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setUserName(String roleName) {
		this.roleName = roleName;
	}
	
	

}
