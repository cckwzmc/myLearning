package com.toney.istyle.core.biz.impl;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Service;

import com.toney.istyle.bo.UserLoginedBO;
import com.toney.istyle.core.biz.UserLoginManager;
import com.toney.istyle.core.exception.ManagerException;

/**
 *************************************************************** 
 * <p>
 * @CLASS :UserLoginManagerImpl.java
 * @DESCRIPTION : 用户登录管理
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 21, 2013
 *       </p>
 **************************************************************** 
 */
@Service("userLoginManager")
public class UserLoginManagerImpl implements UserLoginManager {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(UserLoginManagerImpl.class);

	@Override
	public UserLoginedBO checkLoginedState(String tokenId, Long userId, String userName) throws ManagerException {
		return null;
	}

}
