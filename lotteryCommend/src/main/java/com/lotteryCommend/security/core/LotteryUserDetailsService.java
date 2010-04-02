package com.lotteryCommend.security.core;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.lotteryCommend.exception.LotteryException;
import com.lotteryCommend.model.user.Authority;
import com.lotteryCommend.model.user.Role;
import com.lotteryCommend.model.user.User;
import com.lotteryCommend.service.system.UserManager;

/**
 * 
 * 实现SpringSecurity的UserDetailsService接口,实现获取用户Detail信息的回调函数.
 * @author LIYI
 *
 */
@Transactional(readOnly=true)
public class LotteryUserDetailsService implements UserDetailsService {
	private static final Logger log=LoggerFactory.getLogger(LotteryUserDetailsService.class);
	private UserManager userManager;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		try {
			User user=userManager.findUserByUsername(username);
			if(user==null){
				throw new UsernameNotFoundException("username == "+username+" not found.");
			}
			Set<GrantedAuthority>  grantedAuthorities=new HashSet<GrantedAuthority>();
			String name;
			String password;
			grantedAuthorities=getAuthorities(user);
		} catch (LotteryException e) {
			log.error(e.getMessage());
		}
		return null;
	}
	private Set<GrantedAuthority> getAuthorities(User user) {
		Set<GrantedAuthority> authorities=new Set<GrantedAuthority>();
		for (Role role:user.getRoles()) {
			for (Authority authority:role.getAuthorities()) {
				authorities.add(new GrantedAuthorityImpl(authority.getAuthorityName()));	
			}
			
		}
		return null;
	}

}
