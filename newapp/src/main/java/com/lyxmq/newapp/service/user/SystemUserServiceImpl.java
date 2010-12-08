package com.lyxmq.newapp.service.user;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service("systemUserService")
public class SystemUserServiceImpl implements SystemUserService {

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		return null;
	}

	// private GrantedAuthority[] getAuthorities(User userData, UserManager
	// umgr)
	// throws ServiceException {
	// List<String> roles = umgr.getRoles(userData);
	// GrantedAuthority[] authorities = new GrantedAuthorityImpl[roles.size()];
	// int i = 0;
	// for (String role : roles) {
	// authorities[i++] = new GrantedAuthorityImpl(role);
	// }
	// return authorities;
	// }
}
