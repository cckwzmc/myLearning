package com.lyxmq.xs.hibernate.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.UserDetails;

@Entity
@Table(name="user")
public class User extends BaseObject implements Serializable,UserDetails{

	private static final long serialVersionUID = 8249391781975748871L;
	private Long user_id;
	private String user_name;
	private String password;
	/*
	 * 是否激活;0:未激活,1:已激活 
	 */
	private boolean is_active;
	private Date create_date;
	/*
	 * 用户性质，0：普通用户；1：管理用户（特殊用户）
	 */
	private Integer user_flag;
	private Long group_id;
	private Date lastLogin_date;
	private String lastLogin_ip;
	private Integer gender;
	private String email;
    private boolean enabled;
    private boolean accountExpired;
    private boolean accountLocked;
    
    public User(){
    	
    }
    public User(final String userName){
    	this.user_name=userName;
    }
	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#getAuthorities()
	 */
	@Override
	public GrantedAuthority[] getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#isAccountNonExpired()
	 */
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#isAccountNonLocked()
	 */
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
