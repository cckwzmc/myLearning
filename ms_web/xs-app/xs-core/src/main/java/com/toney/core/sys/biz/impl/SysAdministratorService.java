package com.toney.core.sys.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.toney.core.sys.biz.SysUserManager;

@Service("administratorService")
public class SysAdministratorService implements UserDetailsService{

	@Autowired
	private SysUserManager sysUserManager;
	
	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		
		return null;
	}

}
