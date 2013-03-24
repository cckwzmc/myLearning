package com.toney.istyle.core.user.impl;

import java.util.List;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service("userQueryService")
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

	/* (non-Javadoc)
	 * @see com.toney.istyle.core.user.UserQueryService#getUserByRegType(java.lang.Short)
	 */
	@Override
	public List<UserBO> getUserByRegType(Short regType) throws ServiceException {
		try {
			return this.userRespository.getUserByRegType(regType);
		} catch (RespositoryException e) {
			LOGGER.error("根据用户注册类型查询用户信息 ， regType:{}", regType,e);
			throw new ServiceException(e);
		}
	}

	@Override
	public UserBO getUserById(Long id) throws ServiceException {
		try {
			return this.userRespository.getUserById(id);
		} catch (RespositoryException e) {
			LOGGER.error("根据用户名查询用户信息 ， id:{}", id,e);
			throw new ServiceException(e);
		}
	}
	
}
