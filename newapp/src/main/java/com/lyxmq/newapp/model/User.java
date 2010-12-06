package com.lyxmq.newapp.model;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * User Data Access Object (GenericDao) interface.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class User implements Serializable,UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5789494832975955822L;
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Collection<GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
