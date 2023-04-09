package com.dev.hruser.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.dev.hruser.entities.enums.RoleList;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_user")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@Column(unique = true)
	private String email;
	private String password;
	
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "tb_user_role", 
//			joinColumns = @JoinColumn(name = "user_id"), 
//			inverseJoinColumns = @JoinColumn(name = "role_id"))
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "roles")
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
