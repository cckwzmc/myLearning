package com.toney.istyle.core.user.impl;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.toney.istyle.bo.UserBO;
import com.toney.istyle.core.exception.RespositoryException;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.core.user.UserQueryService;
import com.toney.istyle.dao.UserDao;

/**
 *************************************************************** 
 * <p>
 * @CLASS :UserQueryServiceImpl.java
 * @DESCRIPTION : 用户查询服务类
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 21, 2013
 *       </p>
 **************************************************************** 
 */
public class UserQueryServiceImpl implements UserQueryService{
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(UserQueryServiceImpl.class);
	@Autowired
	UserDao userDao;
	@Autowired
	UserRespository userRespository;
	
	/* (non-Javadoc)
	 * @see com.toney.istyle.core.service.UserQueryService#getUserByUserName(java.lang.String)
	 */
	@Override
	public UserBO getUserByUserName(String userName) throws ServiceException{
		try {
			return this.userRespository.getUserByUserName(userName);
		} catch (RespositoryException e) {
			LOGGER.error("根据用户名查询用户信息 ， userName:{}", userName,e);
			throw new ServiceException(e);
		}
	}
	

	
}
