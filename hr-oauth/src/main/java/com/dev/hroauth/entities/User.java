package com.dev.hroauth.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.dev.hroauth.entities.enums.RoleList;

import lombok.Data;

@Data
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String email;
	private String password;
	
	private Set<Integer> roles = new HashSet<>();
	
	public User() {
		addRoles(RoleList.USER);
	}
	
	public Set<RoleList> getRoleList(){
		return roles.stream().map(x -> RoleList.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addRoles(RoleList perfil) {
		roles.add(perfil.getCod());
	}

	public User(Long id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
}
