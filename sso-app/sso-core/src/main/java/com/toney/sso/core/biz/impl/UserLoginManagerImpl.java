package com.toney.sso.core.biz.impl;

import org.springframework.stereotype.Service;

import com.toney.sso.core.biz.UserLoginManager;
import com.toney.sso.core.bo.UserLoginedBO;

/**
 *************************************************************** 
 * <p>
 * @CLASS :UserLoginManagerImpl.java
 * @DESCRIPTION : User Login Manager
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Apr 1, 2013
 *       </p>
 **************************************************************** 
 */
@Service("userLoginManager")
public class UserLoginManagerImpl implements UserLoginManager {

	@Override
	public UserLoginedBO checkLoginedState(String tokenId, Long userId, String userName) {
		// TODO Auto-generated method stub
		return null;
	}

}
