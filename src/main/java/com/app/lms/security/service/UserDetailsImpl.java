package com.app.lms.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.lms.entity.Employee;
import com.app.lms.entity.Role;

public class UserDetailsImpl implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String password;
	private String username;
	private String email;
	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Long id, String password, String username, String email,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.password = password;
		this.username = username;
		this.email = email;
		this.authorities = authorities;
	}
  
	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	public static UserDetails build(Employee employee) {
		Role role = employee.getRoles();
		GrantedAuthority grantedAuth;
		List<GrantedAuthority> finalAuthorities = new ArrayList<>();
		grantedAuth = new SimpleGrantedAuthority(role.getRole().name());
		finalAuthorities.add(grantedAuth);
		return new UserDetailsImpl(employee.getEmployeeId(), employee.getPassword(), employee.getEmail(),
				employee.getEmail(), finalAuthorities);
	}

}
