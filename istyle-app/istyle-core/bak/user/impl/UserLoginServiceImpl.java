package com.toney.istyle.core.user.impl;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.istyle.bo.UserLoginedBO;
import com.toney.istyle.core.exception.RespositoryException;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.core.user.UserLoginService;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :UserLoginServiceImpl.java
 * @DESCRIPTION : EhCache实现Session持久化
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 21, 2013
 *       </p>
 **************************************************************** 
 */
@Service("userLoginService")
public class UserLoginServiceImpl implements UserLoginService {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(UserLoginServiceImpl.class);
	@Autowired
	private UserLoginRespository userLoginRespository;

	/*
	 *  (non-Javadoc)
	 * @see com.toney.istyle.core.service.UserLoginService#isUserLogined(java.lang.String)
	 */
	@Override
	public boolean isUserLogined(String userName) throws ServiceException {
		try {
			UserLoginedBO userBo = this.userLoginRespository.userLoginedInfo(userName);
			if (userBo != null) {
				this.userLoginRespository.refreshLogined(userName);
				return true;
			}
		} catch (RespositoryException e) {
			LOGGER.error("根据用户名查询用户是否已登录 ， userName:{}", userName, e);
			throw new ServiceException(e);
		}
		return false;
	}
}
