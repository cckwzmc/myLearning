package com.toney.istyle.core.service.impl;

import java.lang.reflect.InvocationTargetException;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

import com.toney.istyle.bo.UserBO;
import com.toney.istyle.bo.UserLoginedBO;
import com.toney.istyle.core.exception.RespositoryException;
import com.toney.istyle.dao.UserDao;
import com.toney.istyle.module.UserModule;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :UserRespositoryImpl.java
 * @DESCRIPTION : 用户repository服务
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 21, 2013
 *       </p>
 **************************************************************** 
 */
@Service("userRespository")
public class UserRespositoryImpl implements UserRespository {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(UserRespositoryImpl.class);

	@Autowired
	UserDao userDao;

	@Override
	@Cacheable(value = "userCache", key = "'userName_'+#userName")
	public UserBO getUserByUserName(String userName) throws RespositoryException {
		UserModule module = this.userDao.selectByUserName(userName);
		if (module != null) {
			UserBO bo = new UserBO();
			try {
				BeanUtils.copyProperties(bo, module);
			} catch (IllegalAccessException e) {
				LOGGER.error("根据用户查询用户信息失败 userName:{}", userName, e);
				throw new RespositoryException(e);
			} catch (InvocationTargetException e) {
				LOGGER.error("根据用户查询用户信息失败 userName:{}", userName, e);
				throw new RespositoryException(e);
			}
		}
		return null;
	}

	@Override
	@Cacheable(value = "userCache", key = "'userName_'+#userName")
	public void deleteCache(String userName) throws RespositoryException {

	}
}
